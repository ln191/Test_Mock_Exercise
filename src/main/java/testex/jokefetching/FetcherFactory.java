package testex.jokefetching;

import testex.Jokes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lucas on 11-03-2017.
 */
public class FetcherFactory implements  IFetcherFactory {

    private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");
    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {

        String[] tokens = jokesToFetch.split(",");

        List<IJokeFetcher> res = new ArrayList<>();

        for (String token : tokens) {

            switch (token) {

                case "eduprog":
                    res.add(new EduJoke());
                    break;

                case "chucknorris":
                    res.add(new ChuckNorris());
                    break;

                case "moma":
                    res.add(new Moma());
                    break;

                case "tambal":
                    res.add(new Tambal());
                    break;

            }

        }
        return res;
    }
}
