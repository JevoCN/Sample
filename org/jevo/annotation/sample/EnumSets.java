package org.jevo.annotation.sample;

import java.util.EnumSet;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 9:09:56
 * To change this template use File | Settings | File Templates.
 */

public class EnumSets {
    enum AlarmPoints {STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3,OFFICE4, BATHROOM, UTILITY, KITCHEN};

    public static void main(String[] args) {
        EnumSet<AlarmPoints> points1 = EnumSet.allOf(AlarmPoints.class); //����Ԫ�ص�ö��set
        System.err.println(points1);
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class); //��Set
        points.add(AlarmPoints.BATHROOM);
        System.err.println(points);
        points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));  //����ָ��Ԫ�ص�ö��
        System.err.println(points);
        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.err.println(points);
        points.removeAll(EnumSet.range(AlarmPoints.OFFICE1, AlarmPoints.OFFICE4));//�������˷�Χ�ڵ�����Ԫ�ص�ö��set
        System.err.println(points);
        points = EnumSet.complementOf(points); //����ָ�� set �в� ��������Ԫ��
        System.err.println(points);
    }
}
