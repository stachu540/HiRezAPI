package com.hirezstudios.games.smite;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public enum Queue {
    Conquest_5vs5(423),
    Novice_Queue(424),
    Conquest(426),
    Practice(427),
    Conquest_Challenge(429),
    Conquest_Ranked(430),
    Domination(433),
    MOTD(434),
    Arena(435),
    Arena_Challenge(438),
    Domination_Challenge(439),
    Duel(440),
    Joust_Challenge(441),
    Assault(445),
    Assault_Challenge(446),
    Joust(448),
    Joust_Ranked(450),
    Conquest_Ranked2(451),
    Arena_Ranked(452),
    Clash(466),
    Clash_Challenge(467);

    private int id;

    private static Map<Integer, Queue> map = new HashMap<Integer, Queue>();

    static {
        for(Queue queue : Queue.values()) {
            map.put(queue.id, queue);
        }
    }

    Queue(final int id) {
        this.id = id;
    }

    public static Queue valueOf(int id) {
        return map.get(id);
    }
}