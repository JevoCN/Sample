package org.jevo.annotation.sample;

import java.util.EnumSet;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 10:08:16
 * To change this template use File | Settings | File Templates.
 */

public class CarWash {
    public enum Cycle {
        UNDERBODY {void action() {
            System.err.println("Spraying the underbody");
        }},
        WHEELWASH {void action() {
            System.err.println("Washing the wheels");
        }},
        PREWASH {void action() {
            System.err.println("Loosening the dirt");
        }},
        BASIC {void action() {
            System.err.println("The basic wash");
        }},
        HOTWAX {void action() {
            System.err.println("Applying hot wax");
        }},
        RINSE {void action() {
            System.err.println("Rinsing");
        }},
        BLOWDRY {void action() {
            System.err.println("Blowing dry");
        }};

        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for (Cycle c : cycles)
            c.action();
    }

    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        System.err.println(wash);
        wash.washCar();
// Order of addition is unimportant:
        wash.add(Cycle.BLOWDRY);
        wash.add(Cycle.BLOWDRY); // Duplicates ignored
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOTWAX);
        System.err.println(wash);
        wash.washCar();
    }
}

