package com.github.stachu540.hirezapi;


import com.github.stachu540.hirezapi.enums.url.Smite;
import com.github.stachu540.hirezapi.models.json.ServiceStatus;

public class TestIt {
    public static void main(String[] args) throws Exception {
        HiRezAPI hirez = new HiRezAPI(System.getenv("DEV_ID"), System.getenv("AUTH_KEY"));
        ServiceStatus status = hirez.getFor(Smite.PC).getServerStatus();
        System.out.println(status);
        System.out.println(status.getLastIncident());
    }
}
