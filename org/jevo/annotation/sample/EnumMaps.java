package org.jevo.annotation.sample;

import java.util.Map;
import java.util.EnumMap;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 9:26:15
 * To change this template use File | Settings | File Templates.
 */

interface Command {
    void action();
}

enum AlarmPoints {STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,OFFICE4, BATHROOM, UTILITY, KITCHEN};

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(AlarmPoints.KITCHEN, new Command() {
            public void action() {
                System.err.println("Kitchen fire!");
            }
        });
        em.put(AlarmPoints.BATHROOM, new Command() {
            public void action() {
                System.err.println("Bathroom alert!");
            }
        });
        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            System.err.println(e.getKey() + ": ");
            e.getValue().action();
        }
        try { // If there's no value for a particular key:
            em.get(AlarmPoints.UTILITY).action();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

