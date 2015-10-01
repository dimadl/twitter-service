package by.epam.twitterservice.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Dzmitry_Dydyshka.
 */
public final class TweetParser {

    //JSON keys in the twitter response
    private static final String JSON_KEY_TWEET_ID = "id";
    private static final String JSON_KEY_TWEET_TEXT = "text";
    private static final String JSON_KEY_TWEET_CREATED_AT = "created_at";
    private static final String JSON_KEY_TWEET_ENTITIES = "entities";
    private static final String JSON_KEY_ENTITY_HASHTAGS = "hashtags";
    private static final String JSON_KEY_HASHTAG_TEXT = "text";
    private static final String JSON_KEY_TWEET_USER = "user";
    private static final String JSON_KEY_USER_NAME = "name";


    private static long lastTweetId;

    /**
     *
     * The method allows to obtain the list of news based on json array of tweets
     *
     * @param data - json of tweets
     * @return - the
     * @throws ParseException
     */

    public static ParseResult parseTweetsToNews(String data) throws ParseException{

        List<NewsJSONBody> listNews = new LinkedList<NewsJSONBody>();

        JsonArray tweets = Json.parse(data).asArray();

        for (JsonValue tweet : tweets) {

            JsonObject tweetObj = tweet.asObject();

            NewsJSONBody news = parseTweetToNews(tweetObj);

            listNews.add(news);

        }

        ParseResult parseResult = new ParseResult(listNews, lastTweetId);

        return parseResult;

    }


    /**
     *
     * The method allows to obtain news object based on the tweet from json
     *
     * @param tweetObj - json tweet object
     * @return - the news for jackson mapping
     * @throws ParseException
     */

    private static NewsJSONBody parseTweetToNews(JsonObject tweetObj) throws ParseException {

        NewsJSONBody news = new NewsJSONBody();

        long id = tweetObj.get(JSON_KEY_TWEET_ID).asLong();

        if (id > lastTweetId){
            lastTweetId = id;
        }

        //news text
        String text = tweetObj.get(JSON_KEY_TWEET_TEXT).asString();

        news.setTitle(text);
        news.setShortText(text);
        news.setFullText(text);

        //news creation date

        String createdAt = tweetObj.get(JSON_KEY_TWEET_CREATED_AT).asString();
        news.setModificationDate(createdAt);
        news.setCreationDate(createdAt);

        //news tags
        JsonObject entities = tweetObj.get(JSON_KEY_TWEET_ENTITIES).asObject();
        JsonArray hashtags = entities.get(JSON_KEY_ENTITY_HASHTAGS).asArray();
        if (!hashtags.isEmpty()){

            List<String> tags = new LinkedList<String>();

            for (JsonValue hashtag : hashtags) {
                tags.add(hashtag.asObject().get(JSON_KEY_HASHTAG_TEXT).asString());
            }

            news.setTags(tags);
        }else{
            news.setTags(Collections.EMPTY_LIST);
        }

        //news author
        JsonObject user = tweetObj.get(JSON_KEY_TWEET_USER).asObject();
        news.setAuthor(user.get(JSON_KEY_USER_NAME).asString());

        return news;
    }

}
