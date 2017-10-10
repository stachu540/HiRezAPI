package com.github.stachu540.hirezapi.enums.queues;

import lombok.Getter;

@Getter
public enum Paladins implements Queue {
    LIVE_Casual                     (424, "Siege"),
    LIVE_Onslaught                  (452, "Onslaught"),
    LIVE_Payload                    (437, "Payload"),
    LIVE_Test_Maps                  (445, "Test Maps"),
    LIVE_Practice_Siege             (425, "Siege Training"),
    LIVE_Onslaught_Practice         (453, "Onslaught Training"),
    LIVE_Payload_Practice           (427, "Payload Training"),
    LIVE_Competitive                (428, "Competitive"),
    Custom_K$StoneKeep              (423, "Stone Keep (Custom)"),
    Custom_F$Dock                   (431, "Fish Market (Custom)"),
    Custom_T$Isle                   (433, "Frog Isle (Custom)"),
    Custom_F$TimberMill             (430, "Timber Mill (Custom)"),
    Custom_T$Beach                  (440, "Serpent Beach (Custom)"),
    Custom_T$Temple                 (438, "Jaguar Falls (Custom)"),
    Custom_B$Brightmarsh            (458, "Brightmarsh (Custom)"),
    Custom_Q$SplitstoneQuarry       (459, "Splitstone Quarry (Custom)"),
    Custom_I$Igloo                  (432, "Frozen Guard (Custom)"),
    Custom_I$Mine                   (439, "Ice Mines (Custom)"),
    Custom_I$Junction_Onslaught     (454, "Junction - Onslaught (Custom)"),
    Custom_T$Court_Onslaught        (455, "Court - Onslaught (Custom)"),
    ZDelete_Custom_TP$Hidden_Temple (441, "Shooting Range");

    private final int id;
    private final String name;

    Paladins(int id, String name) {
        this.name = name;
        this.id = id;
    }
}
