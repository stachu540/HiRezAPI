package com.github.stachu540.hirezapi.api;

import com.github.stachu540.hirezapi.api.serverstatus.ServerStatusIncident;
import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.models.json.ServiceStatus;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusServer {
  private final List<ServerStatusIncident> incidents = new ArrayList<ServerStatusIncident>();
  @Setter(AccessLevel.NONE)
  private SyndFeed feed =
      new SyndFeedInput()
          .build(new XmlReader(new URL("http://status.hirezstudios.com/history.atom")));
  private String game;
  private String platform;
  private ServiceStatus.Status status;

  public StatusServer() throws Exception {
    reload();
  }

  public StatusServer(BasePlatform platform, boolean allPlatforms) throws Exception {
    setGamePlatform(platform, allPlatforms);
    reload();
  }

  public StatusServer(BasePlatform platform) throws Exception {
    setGamePlatform(platform);
    reload();
  }

  public void setGamePlatform(BasePlatform platform) {
    setGamePlatform(platform, false);
  }

  public void setGamePlatform(BasePlatform platform, boolean allPlatforms) {
    this.game = platform.getGame();
    this.platform = (allPlatforms) ? null : platform.getPlatform();
  }

  private void reload() {
    if (!incidents.isEmpty()) {
      incidents.clear();
    }
    for (SyndEntry entry : feed.getEntries()) {
      ServerStatusIncident incident =
          new ServerStatusIncident(entry.getTitle(), entry.getLink(), entry.getContents().get(0).getValue(), entry.getPublishedDate());
      if (incident.contains(game)) {
        if (platform != null && incident.contains(platform)) {
          incidents.add(incident);
        } else {
          incidents.add(incident);
        }
      }
    }
  }

  public ServerStatusIncident getIncident(int i) {
    reload();
    return incidents.get(i);
  }

  @Override
  public String toString() {
    return incidents.toString();
  }
}
