package by.epam.twitterservice.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

/**
 * @author Dzmitry_Dydyshka.
 */
public class TweetParserTest {

    @Test
    public void testParseTweetsToNews() throws FileNotFoundException, ParseException {

        URL url = TweetParserTest.class.getClassLoader().getResource("test.json");
        File file = new File(url.getPath());

        String data = new Scanner(file).useDelimiter("\\Z").next();
        List<NewsJSONBody> expectedList = new LinkedList<NewsJSONBody>();
        NewsJSONBody news = new NewsJSONBody();
        news.setAuthor("Twitter API");
        news.setTitle("Introducing the Twitter Certified Products Program: https://t.co/MjJ8xAnT");
        news.setFullText("Introducing the Twitter Certified Products Program: https://t.co/MjJ8xAnT");
        news.setShortText("Introducing the Twitter Certified Products Program: https://t.co/MjJ8xAnT");
        news.setModificationDate("Wed Aug 29 17:12:58 +0000 2012");
        news.setCreationDate("Wed Aug 29 17:12:58 +0000 2012");
        news.setTags(Arrays.asList("FITU"));

        expectedList.add(news);

        ParseResult parseResultExpected = new ParseResult();
        parseResultExpected.setNews(expectedList);
        parseResultExpected.setLastTweetId(240859602684612608L);

        ParseResult parseResultActual = TweetParser.parseTweetsToNews(data);

        List<NewsJSONBody> actualList = parseResultActual.getNews();
        NewsJSONBody actualNews = actualList.get(0);

        //asserts
        Assert.assertNotNull(actualList);
        Assert.assertEquals(1, actualList.size());
        Assert.assertEquals(news.getModificationDate(), actualNews.getModificationDate());
        Assert.assertEquals(news.getAuthor(), actualNews.getAuthor());
        Assert.assertEquals(news.getCreationDate(), actualNews.getCreationDate());
        Assert.assertEquals(news.getFullText(), actualNews.getFullText());
        Assert.assertEquals(news.getTitle(), actualNews.getTitle());
        Assert.assertEquals(news.getShortText(), actualNews.getShortText());
        Assert.assertEquals(1, actualNews.getTags().size());
        Assert.assertEquals(news.getTags().get(0), actualNews.getTags().get(0));

        Assert.assertEquals(parseResultExpected.getLastTweetId(), parseResultActual.getLastTweetId());

    }

}
