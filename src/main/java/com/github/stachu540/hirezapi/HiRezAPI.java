package com.github.stachu540.hirezapi;

import com.github.stachu540.hirezapi.api.HiRez;
import com.github.stachu540.hirezapi.api.Paladins;
import com.github.stachu540.hirezapi.api.Smite;
import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.exception.BasePlatformException;
import com.github.stachu540.hirezapi.util.storage.SessionStorage;
import lombok.Getter;


/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
@Getter
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

    private SessionStorage sessionStorage;

    /**
     * <p>Initialize Hi-Rez API. All stuff will delivered by Hi-Rez employer via E-Mail.</p>
     * <p>Please fill <a href="https://fs12.formsite.com/HiRez/form48/secure_index.html">this form first</a> to using script.</p>
     * <p>After acceptation your request, you can proceed to action.</p>
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     */
    public HiRezAPI(String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
    }

    @SuppressWarnings("unchecked")
    public <T extends HiRez, P extends BasePlatform> T getFor(P platform) {
        if (platform.getGame().toLowerCase().equals("smite"))
            return (T) new Smite(this, (com.github.stachu540.hirezapi.enums.url.Smite) platform);
        else if (platform.getGame().toLowerCase().equals("paladins"))
            return (T) new Paladins(this, (com.github.stachu540.hirezapi.enums.url.Paladins) platform);
        // TODO: Hand of the Gods API (Soon)
//        else if (platform.getGame().toLowerCase().equals("hotg"))
//            return (T)
        else throw new BasePlatformException(platform);
    }
}
