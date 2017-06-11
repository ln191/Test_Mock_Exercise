package testex;


import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

/**
 * Created by lucas on 10-03-2017.
 */
public class DateFormatterTest {

    DateFormatter dateFormatter;
    @Before
    public void setUp() throws Exception {
        dateFormatter = new DateFormatter();
    }


    @Test
    public void getFormattedDate() throws Exception {
        Date testDate = new Date(30);

        String res = dateFormatter.getFormattedDate("Europe/Copenhagen",testDate);
       assertThat(res, is("01 jan. 1970 01:00 AM" ));
    }
    @Test(expected = JokeException.class)
    public void getFormattedDateException() throws Exception {
        Date testDate = new Date(30);

        String res = dateFormatter.getFormattedDate("Europe/bob",testDate);
        assertThat(res, is("01 jan. 1970 01:00 AM" ));
    }

}