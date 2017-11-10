package com.github.stachu540.hirezapi.api.serverstatus;

import com.github.stachu540.hirezapi.enums.ServerStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import lombok.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Getting status Incidents from status page
 * @author <a href="mailto:damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 2.0
 */
@Data
public class ServerStatusIncident {
  private final String title;
  private final String url;
  private final List<Incident> incidents = new ArrayList<Incident>();

  public ServerStatusIncident(String title, String url, String description) {
    this.title = title;
    this.url = url;
    formatDescriptionIncident(description);
  }

  private void formatDescriptionIncident(String description) {
    Document document = Jsoup.parse(description);
    document.select("p").forEach(paragraph -> incidents.add(new Incident(paragraph)));
  }

  public Incident getIncident(int i) {
    return incidents.get(i);
  }

  public boolean contains(String s) {
    boolean contained = false;
    if (title.contains(s)) {
      contained = true;
    }
    for (Incident incident : incidents) {
      if (incident.getDescription().contains(s)) {
        contained = true;
      }
    }
    return contained;
  }

  @Data
  public static class Incident {
    private final ServerStatus status;
    private final long timestamp;
    private final String description;

    private Incident(Element message) {
      String timestamp = message.getElementsByTag("small").first().text();
      String description = message.text();
      this.timestamp = parseTimestamp(timestamp);
      this.status = ServerStatus.get(message.getElementsByTag("strong").first().text());
      this.description =
          description.replace(
              new StringBuilder(timestamp).append(" ").append(status).append(" - "), "");
    }

    private long parseTimestamp(String timestamp) {
      DateFormat df = new SimpleDateFormat("yyyyMMMdd,kk:mmz", Locale.US);
      df.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date date;
      try {
        date = df.parse(String.valueOf(Calendar.getInstance(Locale.US).get(Calendar.YEAR)) + timestamp.replaceAll("\\s+", ""));
      } catch (ParseException e) {
        date = new Date();
      }
      return date.getTime();
    }
  }
}
