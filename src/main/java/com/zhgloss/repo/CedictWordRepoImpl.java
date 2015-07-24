package com.zhgloss.repo;

import static com.zhgloss.domain.ZhGlossConstants.UMLAUT;
import static com.zhgloss.domain.ZhGlossConstants.UTF8;
import static org.jooq.lambda.tuple.Tuple.tuple;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.impl.DSL;
import org.jooq.lambda.Unchecked;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhgloss.cedict.parser.CedictTwoFileCsvUnloader;
import com.zhgloss.cedict.parser.CedictTwoFileCsvUnloaderConfig;
import com.zhgloss.repo.jooq.tables.CedictLoad;

public class CedictWordRepoImpl implements CedictWordRepo {

	private static final String CEDICT_RAW_FILE = "cedict_ts.u8";
	private static final String CEDICT_WORD_FILE = "cedict_word.csv";
	private static final String CEDICT_DEF_FILE = "cedict_def.csv";
	private static final Supplier<String> PK_GENERATOR = () -> UUID.randomUUID().toString();
	private static final Logger LOGGER = LoggerFactory.getLogger(CedictWordRepoImpl.class);
	
	private DataSource dataSource;
	private String dataDirectory;
	private DSLContext context;
	
	public CedictWordRepoImpl(DataSource dataSource, String dataDirectory, DSLContext context) {
		this.dataSource = dataSource;
		this.dataDirectory = dataDirectory;
		this.context = context;
	}
	
	@Override
	public void load() {

		Path cedictRawPath = Paths.get(dataDirectory, CEDICT_RAW_FILE);
		Path cedictWordPath = Paths.get(dataDirectory, CEDICT_WORD_FILE);
		Path cedictDefPath = Paths.get(dataDirectory, CEDICT_DEF_FILE);
		
		LOGGER.info("Downloading and parsing CEDICT data -- data directory: {}; raw file: {}, word file: {}; def file: {}", 
				dataDirectory, CEDICT_RAW_FILE, CEDICT_WORD_FILE, CEDICT_DEF_FILE);
		
		try {
			CedictTwoFileCsvUnloader.get()
				.writeFiles(new CedictTwoFileCsvUnloaderConfig()
					.cedictPath(cedictRawPath)
					.wordsPath(cedictWordPath)
					.definitionsPath(cedictDefPath)
					.wordPkGenerator(PK_GENERATOR)
					.defPkGenerator(PK_GENERATOR)
					.umlautRepresentation(UMLAUT));
			
			try (
				BaseConnection baseConnection = dataSource.getConnection().unwrap(BaseConnection.class);
				Reader cedictWordReader = Files.newBufferedReader(cedictWordPath, UTF8);
				Reader cedictDefReader = Files.newBufferedReader(cedictDefPath, UTF8);)
			{
				LOGGER.info("Truncating CEDICT-related tables");
				baseConnection.createStatement().execute("truncate table cedict_word cascade");
				CopyManager copyManager = new CopyManager(baseConnection);
				LOGGER.info("Loading CEDICT-related data");
				Stream.of(tuple(cedictWordReader, "cedict_word"),
						tuple(cedictDefReader, "cedict_word_def"))
					.map(t -> t.concat(String.format("copy %s from stdin csv", t.v2())))
					.peek(t -> LOGGER.info("Loading data: {}", t.v3()))
					.forEach(Unchecked.consumer(t -> copyManager.copyIn(t.v3(), t.v1())));
			} finally {
		        Stream.of(cedictRawPath, cedictWordPath, cedictDefPath)
		        	.map(Path::toFile)
		        	.forEach(f -> {
		        		boolean deleted = f.delete();
		        		LOGGER.info("Deleting file: {}, successful: {}", f, deleted);
		        	});
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<LocalDateTime> getLastLoad() {
		Record1<LocalDateTime> record = context
			.select(DSL.max(CedictLoad.CEDICT_LOAD.LOAD_FINISH))
			.from(CedictLoad.CEDICT_LOAD)
			.fetchAny();
		return record == null ? Optional.empty() : Optional.of(record.value1());
	}

}
