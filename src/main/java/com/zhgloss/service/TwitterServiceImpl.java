package com.zhgloss.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "twitter_enabled")
@Service
public class TwitterServiceImpl implements TwitterSevice {
	
   private static final Logger LOGGER = LoggerFactory.getLogger(TwitterServiceImpl.class);
 
   @Inject
   private Twitter twitter;
   
   public TwitterServiceImpl() {
	   super();
   }
   
   @Override
   public void tweet(String tweetText) {
      try {
         twitter.timelineOperations().updateStatus(tweetText);
      } catch (RuntimeException ex) {
         LOGGER.error("Unable to tweet '" + tweetText + "'", ex);
      }
   }
}
