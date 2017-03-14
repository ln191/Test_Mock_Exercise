package testex;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lucas on 10-03-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    //By running IntelliJet only code coverage Tool
    //It showed 100% coverage of the DataFormatter, JokeFetcher and FetcherFactory classes
    //Which was the point of this assignment
    private JokeFetcher jokeFetcher;
    @Mock
    IDateFormatter dateFormatterMock;
    @Mock
    IFetcherFactory factoryMock;
    @Mock
    EduJoke eduJokeMock;
    @Mock
    ChuckNorris chuckNorrisMock;
    @Mock
    Moma momaMock;
    @Mock
    Tambal tambalMock;

    @Before
    public void setUp() throws Exception {
        List<IJokeFetcher> fetchers = Arrays.asList(eduJokeMock,chuckNorrisMock,momaMock,tambalMock);
        when(factoryMock.getJokeFetchers("eduprog,chucknorris,moma,tambal"))
                .thenReturn(fetchers);
        List<String> types = Arrays.asList("eduprog","chucknorris","moma","tambal");
        when(factoryMock.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(factoryMock);
    }


    @Test
    public void isStringValid() throws Exception {

        String[] trueTestData = {"eduprog","chucknorris","moma","tambal"};
        String[] falseTestData = {"aaa","nlceneledn","215","ab654a"};

        //Can access this methods because it is package-private.
        //assertThat
        for (String testString : trueTestData){
            assertThat(jokeFetcher.isStringValid(testString), is(true));
        }
        for (String testString : falseTestData){
            assertThat(jokeFetcher.isStringValid(testString), is(false));
        }

    }

    @Test
    public void getJokes() throws Exception {

        Joke eduJoke = new Joke("haha it was just a edu test","From me");
        Joke chuJoke = new Joke("haha it was just a chu test","From bob");
        Joke momJoke = new Joke("haha it was just a mom test","From me");
        Joke tamJoke = new Joke("haha it was just a tam test","From ip");
        given(dateFormatterMock.getFormattedDate(eq("Europe/Copenhagen"),
                anyObject())).willReturn("17 feb. 2017 10:56 AM");
        given(eduJokeMock.getJoke()).willReturn(eduJoke);
        given(chuckNorrisMock.getJoke()).willReturn(chuJoke);
        given(momaMock.getJoke()).willReturn(momJoke);
        given(tambalMock.getJoke()).willReturn(tamJoke);



       assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal",
               "Europe/Copenhagen",dateFormatterMock).getTimeZoneString(), is("17 feb. 2017 10:56 AM"));
        //Check that getFormattedDate only run 1 one
        verify(dateFormatterMock,times(1)).getFormattedDate(anyString(),any());

        verify(factoryMock,times(1)).getJokeFetchers(anyString());

        //test jokes list size
       assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal",
               "Europe/Copenhagen",dateFormatterMock).jokes,hasSize(4));
        //test jokes list items
        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal",
                "Europe/Copenhagen",dateFormatterMock).jokes.get(0),
                is(eduJoke));
        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal",
                "Europe/Copenhagen",dateFormatterMock).jokes.get(1),
                is(chuJoke));

        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal",
                "Europe/Copenhagen",dateFormatterMock).jokes.get(2),
                is(momJoke));
        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal",
                "Europe/Copenhagen",dateFormatterMock).jokes.get(3),
                is(tamJoke));

}

}