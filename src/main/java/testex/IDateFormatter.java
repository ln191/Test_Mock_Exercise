package testex;

import java.util.Date;

/**
 * Created by lucas on 10-03-2017.
 */
public interface IDateFormatter {
    String getFormattedDate(String timeZone, Date time) throws JokeException;
}
