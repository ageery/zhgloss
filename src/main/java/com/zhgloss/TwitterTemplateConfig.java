package com.zhgloss;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@ConditionalOnProperty(name = "twitter_enabled")
@Configuration
public class TwitterTemplateConfig {

	@Autowired
	private Environment env;

	@Bean
	public Twitter getTwitter() {
		String consumerKey = env.getProperty("twitter_consumerKey");
		String consumerSecret = env.getProperty("twitter_consumerSecret");
		String accessToken = env.getProperty("twitter_accessToken");
		String accessTokenSecret = env.getProperty("twitter_accessTokenSecret");
		Objects.requireNonNull(consumerKey);
		Objects.requireNonNull(consumerSecret);
		Objects.requireNonNull(accessToken);
		Objects.requireNonNull(accessTokenSecret);
		TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, accessToken,
				accessTokenSecret);
		return twitterTemplate;
	}
	
}
