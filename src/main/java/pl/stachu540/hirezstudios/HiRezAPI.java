package pl.stachu540.hirezstudios;

import com.sun.istack.internal.NotNull;
import pl.stachu540.hirezstudios.games.Paladins;
import pl.stachu540.hirezstudios.games.Smite;

import java.util.Arrays;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class HiRezAPI {
    /**
     * Developer ID (DevId)
     * @see HiRezAPI#HiRezAPI(String, String)
     */
    private final String devId;
    /**
     * Authorization Key (AuthKey)
     * @see HiRezAPI#HiRezAPI(String, String)
     */
    private final String authKey;

    /**
     * Smite API
     * @see Smite
     */
    public Smite smite;
    /**
     * Paladins API
     * @see Paladins
     */
    public Paladins paladins;

    /**
     * <p>Initialize Hi-Rez API. All stuff will delivered by Hi-Rez employer via E-Mail.<br />
     * Please fill <a href="https://fs12.formsite.com/HiRez/form48/secure_index.html">this form first</a> to using script.<br />
     * After acceptation your request, you can proceed to action.</p>
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     */
    public HiRezAPI(@NotNull String devId, @NotNull String authKey) {
        this.devId = devId;
        this.authKey = authKey;
        setPlatform("pc");
    }

    /**
     * <p>Before using variables {@link #smite} and {@link #paladins} define platform first. (Default platform is "PC")<br />
     * You can use it with variables mentioned above.</p>
     * <p>Example:<br /><br />
     * <code>
     *   HiRezAPI hirez = new HiRezAPI("1004","23DF3C7E9BD14D84BF892AD206B6755C");<br />
     *   hirez.setPlatform("xbox").smite.getPlayer("stachu Official");
     * </code>
     * </p>
     * @param platform Platform name <b>PC</b>/<b>XBOX</b>/<b>PS4</b>
     */
    public HiRezAPI setPlatform(String platform) {
        smite = new Smite(devId, authKey, Arrays.stream(Smite.Endpoint.values())
                .filter(endpoint -> (endpoint.name().toLowerCase().equals(platform.toLowerCase()))).findFirst().get());
        paladins = new Paladins(devId, authKey, Arrays.stream(Paladins.Endpoint.values())
                .filter(endpoint -> endpoint.name().toLowerCase().equals(platform.toLowerCase())).findFirst().get());
        return this;
    }
}
