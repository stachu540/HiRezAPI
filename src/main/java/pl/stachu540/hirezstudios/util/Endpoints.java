package pl.stachu540.hirezstudios.util;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */

public enum Endpoints {
    Smite_PC("http://api.smitegame.com/smiteapi.svc"),
    Smite_XB1("http://api.xbox.smitegame.com/smiteapi.svc"),
    Smite_PS4("http://api.ps4.smitegame.com/smiteapi.svc"),

    Paladins_PC("http://api.paladins.com/paladinsapi.svc"),
    Paladins_XB1("http://api.xbox.paladins.com/paladinsapi.svc"),
    Paladins_PS4("http://api.ps4.paladins.com/paladinsapi.svc");

    private String url;

    Endpoints(String url) {
        this.url = url;
    }

    public String url() { return url; }
}