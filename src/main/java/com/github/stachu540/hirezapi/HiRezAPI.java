package com.github.stachu540.hirezapi;

import com.github.stachu540.hirezapi.api.HiRez;
import com.github.stachu540.hirezapi.api.Paladins;
import com.github.stachu540.hirezapi.api.Smite;
import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.enums.url.EPaladins;
import com.github.stachu540.hirezapi.enums.url.ESmite;
import com.github.stachu540.hirezapi.exception.BasePlatformException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * API wrapper for Hi-Rez Studio games
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.0
 */
@Getter
public class HiRezAPI<M extends Map<P, String>, P extends BasePlatform> {

  /**
   * Developer ID (DevId)
   *
   * @see HiRezAPI#HiRezAPI(String, String)
   */
  private final String devId;

  /**
   * Authorization Key (AuthKey)
   *
   * @see HiRezAPI#HiRezAPI(String, String)
   */
  private final String authKey;

  @Setter
  @SuppressWarnings("unchecked")
  private M sessionCache = (M) new HashMap<P, String>();

  /**
   * Initialize Hi-Rez API. All stuff will delivered by Hi-Rez employer via E-Mail.
   *
   * <p>Please fill <a href="https://fs12.formsite.com/HiRez/form48/secure_index.html">this form
   * first</a> to using script.
   *
   * <p>After acceptation your request, you can proceed to action.
   *
   * @param devId Developer ID (DevId)
   * @param authKey Authorization Key (AuthKey)
   */
  public HiRezAPI(String devId, String authKey) {
    this.devId = devId;
    this.authKey = authKey;
  }

  /**
   * Get endpoint from {@link BasePlatform}
   * @param platform Base Platform
   * @param <T> {@link Smite} / {@link Paladins} if endpoint is specified
   * @param <P> {@link ESmite} / {@link EPaladins}
   * @return Endpoint'ed platform
   */
  @SuppressWarnings("unchecked")
  public <T extends HiRez, P extends BasePlatform> T getFor(P platform) {
    if (platform.getGame().equalsIgnoreCase("smite")) {
      return (T) new Smite(this, (ESmite) platform);
    } else if (platform.getGame().equalsIgnoreCase("paladins")) {
      return (T) new Paladins(this, (EPaladins) platform);
    } else {
      throw new BasePlatformException(platform);
    }
  }

  @Setter
  @Accessors(chain = true)
  public static class Builder {

    private String devId;
    private String authKey;

    private Builder() {}

    public static Builder init() {
      return new Builder();
    }

    public HiRezAPI build() {
      Assert.notNull(devId, "Required \"Developer ID\"");
      Assert.notNull(authKey, "Required \"Authorization Key\"");

      return new HiRezAPI(devId, authKey);
    }
  }
}
