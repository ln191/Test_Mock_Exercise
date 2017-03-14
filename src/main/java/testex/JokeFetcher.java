
package testex;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.ExtractableResponse;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class used to fetch jokes from a number of external joke API's
 */

public class JokeFetcher {

  private IFetcherFactory factory;

  public JokeFetcher(IFetcherFactory factory){
    this.factory = factory;
  }
  
  /**
   * Verifies whether a provided value is a valid string (contained in availableTypes)
   * @param jokeTokens. Example (with valid values only): "eduprog,chucknorris,chucknorris,moma,tambal"
   * @return true if the param was a valid value, otherwise false
   *
   *
   */
  //Default java method scope is package-private. All classes in the same package can access the method/field/class.
  //Package-private is stricter than protected and public scopes, but more permissive than private scope.
  boolean isStringValid(String jokeTokens){
    String[] tokens = jokeTokens.split(",");
      for(String token: tokens){
      if(!factory.getAvailableTypes().contains(token)){
        return false;
      }
    }
    return true;
  }
  
  /**
   * Fetch jokes from external API's as given in the input string - jokesToFetch
   * @param jokesToFetch A comma separated string with values (contained in availableTypes) indicating the jokes
   * to fetch. Example: "eduprog,chucknorris,chucknorris,moma,tambal" will return five jokes (two chucknorris)
   * @param timeZone. Must be a valid timeZone string as returned by: TimeZone.getAvailableIDs()
   * @param formatter. Need a DateFormatter to access getFormattedDate() method
   * @return A Jokes instance with the requested jokes + time zone adjusted string representing fetch time
   * (the jokes list can contain null values, if a server did not respond correctly)
   * @throws JokeException. Thrown if either of the two input arguments contains illegal values
   */
  public Jokes getJokes(String jokesToFetch,String timeZone, IDateFormatter dateFormatter) throws JokeException{
    isStringValid(jokesToFetch);
    List<IJokeFetcher> result = factory.getJokeFetchers(jokesToFetch);
    Jokes jokes = new Jokes();
    for (IJokeFetcher fetcher : result)
    {       jokes.addJoke(fetcher.getJoke());     }
    String tzString = dateFormatter.getFormattedDate(timeZone, new Date());
    jokes.setTimeZoneString(tzString);
    return jokes;
  }
  
  /**
   * DO NOT TEST this function. It's included only to get a quick way of executing the code
   * @param args 
   */
//  public static void main(String[] args) throws JokeException {
//    JokeFetcher jf = new JokeFetcher();
//    DateFormatter formatter = new DateFormatter();
//    Jokes jokes = jf.getJokes("eduprog,chucknorris,chucknorris,moma,tambal",
//            "Europe/Copenhagen",formatter);
//    jokes.getJokes().forEach((joke) -> {
//      System.out.println(joke);
//    });
//    System.out.println("Is String Valid: "+jf.isStringValid("edu_prog,xxx"));
//  }
}
