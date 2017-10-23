package com.github.stachu540.hirezapi.api;

import com.github.stachu540.hirezapi.HiRezAPI;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.exception.UnknownPlayerException;
import com.github.stachu540.hirezapi.models.Ping;
import com.github.stachu540.hirezapi.models.TestSession;
import com.github.stachu540.hirezapi.models.json.*;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class HiRez<T extends BasePlatform> {
  private final Authentication authentication;

  @SuppressWarnings("unchecked")
  HiRez(HiRezAPI main, T platform) {
    authentication = new Authentication(main, platform, this);
  }

  <X> X get(Class<X> classModel, String... args) {
    Endpoint endpoint;
    if (classModel.isArray())
      endpoint = classModel.getComponentType().getAnnotation(Endpoint.class);
    else endpoint = classModel.getAnnotation(Endpoint.class);
    return get(endpoint.value(), classModel, args);
  }

  <M> M get(String endpoint, Class<M> classModel, String... args) {
    return authentication.getRestClient().request(endpoint, classModel, args);
  }

  public Ping ping() {
    return new Ping(get("ping", String.class));
  }

  public TestSession testSession() {
    return new TestSession(get("testsession", String.class));
  }

  public PlayerData getPlayerDataByUsername(String username) throws UnknownPlayerException {
    PlayerData[] players = get(PlayerData[].class, username);
    if (players.length > 0) {
      for (PlayerData player : players) {
        if (player.getName().toLowerCase().contains(username.toLowerCase())) {
          player.setAchievments(getPlayerAchievments(player.getId()));
          player.setStatus(getPlayerStatus(username));
        }
      }
    } else throw new UnknownPlayerException(username);
    return null;
  }

  public PlayerData getPlayerData(long userId) throws UnknownPlayerException {
    Achievments achievments = getPlayerAchievments(userId);
    PlayerData[] players = get(PlayerData[].class, achievments.getUsername());
    if (players.length > 0) {
      for (PlayerData player : players) {
        if (player.getName().toLowerCase().contains(achievments.getUsername().toLowerCase())) {
          player.setAchievments(getPlayerAchievments(player.getId()));
          player.setStatus(getPlayerStatus(achievments.getUsername()));
        }
      }
    } else throw new UnknownPlayerException(achievments.getUsername());
    return null;
  }

  public Player getPlayer(String username) throws UnknownPlayerException {
    Player[] players = get(Player[].class, username);
    if (players.length > 0) {
      for (Player player : players) {
        if (player.getName().toLowerCase().contains(username.toLowerCase())) return player;
      }
    } else throw new UnknownPlayerException(username);
    return null;
  }

  public Player getPlayer(long userId) throws UnknownPlayerException {
    return getPlayer(String.valueOf(userId));
  }

  public PlayerStatus getPlayerStatus(String username) {
    PlayerStatus[] players = get(PlayerStatus[].class, username);
    System.out.println(Arrays.asList(players).toString());
    if (players.length > 0) {
      return players[0];
    }
    return null;
  }

  public CreateSession createSession() {
    return get(CreateSession.class);
  }

  public Achievments getPlayerAchievments(String username) throws UnknownPlayerException {
    Player player = getPlayer(username);
    return get(Achievments.class, String.valueOf(player.getId()));
  }

  public Achievments getPlayerAchievments(long playerId) {
    return get(Achievments.class, String.valueOf(playerId));
  }

  public ServiceStatus getServerStatus() throws Exception {
    ServiceStatus status = get(ServiceStatus[].class)[0];
    status.setServerStatus(new StatusServer(authentication.getPlatform()));
    return status;
  }
}
