package com.zhgloss.web.wicket.content.about;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.wicketstuff.annotation.mount.MountPath;

import com.zhgloss.web.wicket.component.BootstrapExternalLink;
import com.zhgloss.web.wicket.component.TitledPage;
import com.zhgloss.web.wicket.content.about.ForkMeLink.Location;

@MountPath("/about")
public class AboutPage extends TitledPage {

    public AboutPage(PageParameters parameters) {
        super(parameters);

        queue(new ForkMeLink("fork",
                Model.of("https://github.com/ageery/zixinxi"),
                Location.LOWER_RIGHT));

        queue(new BootstrapExternalLink("unihanLicense",
                Model.of("http://www.unicode.org/copyright.html"),
                new ResourceModel("label.license")));

        queue(new LinkedThumbnail("wikipediaLicense",
                Model.of("http://creativecommons.org/licenses/by-sa/3.0/"),
                new PackageResourceReference(getClass(), "res/by-sa.png")));

        queue(new LinkedThumbnail("cedictLicense",
                Model.of("http://creativecommons.org/licenses/by-sa/3.0/"),
                new PackageResourceReference(getClass(), "res/by-sa.png")));

        queue(new LinkedThumbnail("javaThumbnail",
                Model.of("http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html"),
                new PackageResourceReference(getClass(), "res/java-logo.png")));

        queue(new LinkedThumbnail("wicketThumbnail",
                Model.of("http://wicket.apache.org/"),
                new PackageResourceReference(getClass(), "res/wicket-logo.png")));

        queue(new LinkedThumbnail("springBootThumbnail",
                Model.of("http://projects.spring.io/spring-boot/"),
                new PackageResourceReference(getClass(), "res/springboot-logo.png")));

        queue(new LinkedThumbnail("postgresqlThumbnail",
                Model.of("http://www.postgresql.org/"),
                new PackageResourceReference(getClass(), "res/postgresql-logo.png")));

        queue(new LinkedThumbnail("bootstrapThumbnail",
                Model.of("http://getbootstrap.com/"),
                new PackageResourceReference(getClass(), "res/bootstrap-logo.png")));

        queue(new LinkedThumbnail("jooqThumbnail",
                Model.of("http://jooq.org/"),
                new PackageResourceReference(getClass(), "res/jooq-logo.png")));

    }

}
