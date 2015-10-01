package by.epam.twitterservice.util;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Dzmitry_Dydyshka.
 */
public class ParseResult {

    private List<NewsJSONBody> news;

    @XmlElement(name="last_id")
    private long lastTweetId;

    public ParseResult() {
    }

    public ParseResult(List<NewsJSONBody> news, long lastTweetId) {
        this.news = news;
        this.lastTweetId = lastTweetId;
    }

    public List<NewsJSONBody> getNews() {
        return news;
    }

    public void setNews(List<NewsJSONBody> news) {
        this.news = news;
    }

    public long getLastTweetId() {
        return lastTweetId;
    }

    public void setLastTweetId(long lastTweetId) {
        this.lastTweetId = lastTweetId;
    }
}
