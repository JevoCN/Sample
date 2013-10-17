package org.jevo.enusample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-6
 * Time: 22:58:09
 * To change this template use File | Settings | File Templates.
 */

public enum OzWitch {
// Instances must be defined first, before methods:
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " + "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");
    
    private String description;

    // Constructor must be package or private access:
    private OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }


    public static void main(String[] args) {
        for (OzWitch witch : OzWitch.values())
            System.err.println(witch + ": " + witch.getDescription() + ", name = " + witch.toString());
    }
}

