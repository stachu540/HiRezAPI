package pl.stachu540.hirezstudios;

import com.sun.istack.internal.NotNull;
import pl.stachu540.hirezstudios.games.Paladins;
import pl.stachu540.hirezstudios.games.Smite;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class HiRezAPI {
    private final String devId;
    private final String authKey;

    public Smite smite;
    public Paladins paladins;

    public HiRezAPI(@NotNull String devId, @NotNull String authKey) {
        this.devId = devId;
        this.authKey = authKey;
    }

    public String getDevId() {
        return devId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
