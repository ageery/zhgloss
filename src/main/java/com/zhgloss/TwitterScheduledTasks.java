package com.zhgloss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhgloss.domain.SortInfo;
import com.zhgloss.domain.WordDetailSearchCriteria;
import com.zhgloss.domain.WordDetailSort;
import com.zhgloss.domain.external.WordParts;
import com.zhgloss.service.TwitterSevice;
import com.zhgloss.service.WordService;

@ConditionalOnProperty(name = "twitter_enabled")
@Component
public class TwitterScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterScheduledTasks.class);
	
	private static final String TWEET_MESSAGE_TEMPLATE = "New words: %s %s #cedict";
	private static final String TWEET_URL_TEMPLATE = "http://www.zhgloss.com/zhgloss/activity/day/%s";
	private static final DateTimeFormatter ACTIVITY_PAGE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	@Inject
	private WordService wordService;

	@Inject
	private TwitterSevice twitterService;
	
	public TwitterScheduledTasks() {
		super();
	}
	
	/*
	 * Tweet newly loaded words every day at 02:07:13 AM EST.
	 */
	@Scheduled(cron = "13 7 2 * * *", zone = "EST")
	public void tweetNewWords() {
		String wordList = wordService.find(new WordDetailSearchCriteria().withCreatedDate(LocalDate.now()), "H", 
						Stream.of(new SortInfo<>(WordDetailSort.CREATED, false), 
								new SortInfo<>(WordDetailSort.TRANSCRIPTION, true)), 
						0, 
						100)
				.map(WordParts::getSimplified)
				.collect(Collectors.joining("\u3001"));
		if (!StringUtils.isEmpty(wordList)) {
			if (wordList.length() > 100) {
				wordList = wordList.substring(0, 100) + "...";
			}
			String msg = String.format(TWEET_MESSAGE_TEMPLATE, wordList, 
					String.format(TWEET_URL_TEMPLATE, LocalDate.now().format(ACTIVITY_PAGE_DATE_FORMATTER)));
			LOGGER.debug("Tweet message: {}", msg);
			twitterService.tweet(msg);
		} else {
			LOGGER.info("No new words to tweet for {}", LocalDate.now().format(ACTIVITY_PAGE_DATE_FORMATTER));
		}
	}
	
}
