package by.epam.twitterservice.controller;

import by.epam.twitterservice.util.ParseResult;
import by.epam.twitterservice.util.TweetParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

/**
 *
 * @author Dzmitry Dydyshka
 *
 */

@Component
@Path("/api")
public class HomeTimelineController {

    private static final String API_USER_TIMELINE = "https://api.twitter.com/1.1/statuses/user_timeline.json";

    @GET
    @Path("/news")
    @Produces(MediaType.APPLICATION_JSON)
    public ParseResult obtainNews(@QueryParam("access_token") String accessToken,
                                             @QueryParam("screen_name") String name,
                                             @QueryParam("since_id") String sinceId) throws Exception {

        Client client = Client.create();
        WebResource webResource = client.resource(API_USER_TIMELINE).queryParam("screen_name", name);
        if (sinceId != null){
            webResource = webResource.queryParam("since_id", sinceId);
        }
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Bearer " + accessToken)
                .get(ClientResponse.class);

        return TweetParser.parseTweetsToNews(response.getEntity(String.class));

    }

}
