package testex.jokefetching;

import org.hamcrest.core.Every;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.each;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.*;

/**
 * Created by Lucas on 11-03-2017.
 */
public class FetcherFactoryTest {
    private FetcherFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new FetcherFactory();
    }

    @Test
    public void getAvailableTypes() throws Exception {
        List<String> res = factory.getAvailableTypes();
        //test that list has the right values
        assertThat(res, hasItems("eduprog","chucknorris","moma","tambal"));
        //test if list sizes i correct
        assertThat(res, hasSize(4));
    }

    @Test
    public void getJokeFetchers() throws Exception {
        //First time i ran the test, i got list on 0, because the input string is case sensitive,
        //so it didn´t match the switch case values.
        List<IJokeFetcher> res = factory.getJokeFetchers("eduprog,chucknorris,moma,tambal");
        //test if list sizes i correct
        assertThat(res, hasSize(4));
        //test that all the objects is of type IJokeFetcher
        assertThat(res, Every.everyItem(isA(IJokeFetcher.class)));
        //test that each object on the list are of the right type
        assertThat(res.get(0),instanceOf(EduJoke.class));
        assertThat(res.get(1),instanceOf(ChuckNorris.class));
        assertThat(res.get(2),instanceOf(Moma.class));
        assertThat(res.get(3),instanceOf(Tambal.class));
    }

}