package ly.priv.mobile.utils;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Util for Twitter
 *
 * @author Ivan Metla e-mail: metlaivan@gmail.com
 */
public final class TwitterUtil {

    private RequestToken requestToken = null;
    private TwitterFactory twitterFactory = null;
    private Twitter twitter;
    private Values mValues;

    public TwitterUtil() {
        mValues = Values.getInstance();
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder
                .setOAuthConsumerKey(mValues.getTwitterConsumerKey());
        configurationBuilder
                .setOAuthConsumerSecret(mValues.getTwitterConsumerSecret());
        Configuration configuration = configurationBuilder.build();
        twitterFactory = new TwitterFactory(configuration);
        twitter = twitterFactory.getInstance();
    }

    public TwitterFactory getTwitterFactory() {
        return twitterFactory;
    }

    public void setTwitterFactory(AccessToken accessToken) {
        twitter = twitterFactory.getInstance(accessToken);
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public RequestToken getRequestToken() {
        if (requestToken == null) {
            try {
                requestToken = twitterFactory.getInstance()
                        .getOAuthRequestToken(
                                ConstantValues.TWITTER_CALLBACK_URL);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        return requestToken;
    }

    static TwitterUtil instance = new TwitterUtil();

    public static TwitterUtil getInstance() {
        return instance;
    }

    public void reset() {
        instance = new TwitterUtil();
    }
}
