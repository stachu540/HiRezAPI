package hirezapi.endpoints.statuspage;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import hirezapi.Configuration;
import hirezapi.json.status.Incident;
import hirezapi.json.status.IncidentInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Feeds {
    private static final String URL = "http://status.hirezstudios.com/history.atom";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Configuration configuration;
    private final boolean allPlatforms;
    private List<Incident> incidents;

    public Feeds(Configuration configuration, boolean allPlatforms) {
        this.configuration = configuration;
        this.allPlatforms = allPlatforms;
        fetchIncidents();
    }

    public void fetchIncidents() {
        try {
            final SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(URL)));
            final List<Incident> incidents = new CopyOnWriteArrayList<>();
            incidents.addAll(feed.getEntries().stream().filter(e -> {
                if (e.getContents().get(0).getValue().matches("([hH]i[Rr]ez(/s)?[Gg]ame[s]?)") &&
                        e.getTitle().matches("([hH]i[Rr]ez(/s)?[Gg]ame[s]?)")) {
                    return true;
                } else if (allPlatforms) {
                    return StringUtils.containsIgnoreCase(e.getContents().get(0).getValue(), configuration.getPlatform().getGame()) &&
                            StringUtils.containsIgnoreCase(e.getTitle(), configuration.getPlatform().getGame());
                } else {
                    return StringUtils.containsIgnoreCase(e.getContents().get(0).getValue(), configuration.getPlatform().getGame()) &&
                            StringUtils.containsIgnoreCase(e.getTitle(), configuration.getPlatform().getGame()) &&
                            StringUtils.containsIgnoreCase(e.getContents().get(0).getValue(), configuration.getPlatform().getPlatform()) &&
                            StringUtils.containsIgnoreCase(e.getTitle(), configuration.getPlatform().getPlatform());
                }
            }).map(e -> new Incident(e.getTitle(), e.getLink(), buildInfo(e.getContents().get(0).getValue(), e.getPublishedDate())))
                    .collect(Collectors.toList()));
            this.incidents = Collections.unmodifiableList(incidents);
        } catch (FeedException e) {
            logger.error("Cannot parse Feed", e);
        } catch (MalformedURLException e) {
            logger.error("Malformed URL", e);
        } catch (IOException e) {
            logger.error("I/O Error", e);
        }
    }

    private List<IncidentInfo> buildInfo(String value, Date publishedDate) {
        Document document = Jsoup.parse(value);
        return Collections.unmodifiableList(document.select("p").stream().map(paragraph -> {
            Instant timestamp = dateFormat(paragraph.getElementsByTag("small").first().text(), publishedDate);
            IncidentInfo.Status status = IncidentInfo.Status.get(paragraph.getElementsByTag("strong").first().text());
            String description = paragraph.text().replace(new StringBuilder(paragraph.getElementsByTag("small").first().text())
                    .append(" ").append(status).append(" - "), "");
            return new IncidentInfo(timestamp, status, description);
        }).collect(Collectors.toList()));
    }

    private Instant dateFormat(String timestamp, Date publishedDate) {
        DateFormat df = new SimpleDateFormat("yyyyMMMdd,kk:mmz", Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(publishedDate);
        try {
            date = df.parse(String.valueOf(calendar.get(Calendar.YEAR) + timestamp.replaceAll("\\s+", "")));
        } catch (ParseException e) {
            date = new Date();
        }
        return date.toInstant();
    }

    public Incident getIncident(int index) {
        if (notInitialize()) fetchIncidents();
        return incidents.get(index);
    }

    public Incident getLastIncident() {
        if (notInitialize()) fetchIncidents();
        return getIncident(0);
    }

    public List<Incident> getIncidents() {
        if (notInitialize()) fetchIncidents();
        return incidents;
    }

    private boolean notInitialize() {
        return incidents == null;
    }
}
