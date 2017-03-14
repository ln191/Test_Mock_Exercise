package testex.jokefetching;

import java.util.List;

/**
 * Created by Lucas on 11-03-2017.
 */
public interface IFetcherFactory {
    List<String> getAvailableTypes();
    List<IJokeFetcher> getJokeFetchers(String jokesToFetch);
}
