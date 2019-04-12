package hirez.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

public class HiRezUtils {
    public static Date parse(String timestamp) {
        timestamp = timestamp.replace("\\/", "/");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        try {
            return sdf.parse(timestamp);
        } catch (ParseException ignore) {
            return null;
        }
    }
}
