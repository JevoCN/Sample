package org.jevo.hibernate.sample;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.BatchSize;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-23
 * Time: 22:11:01
 * To change this template use File | Settings | File Templates.
 */

public class Test {
//    @Fetch(value= FetchMode.SUBSELECT)
//    @BatchSize(size=4)
//    public void set(){
//
//    }

    /*   public static void main(String[] args) {
   Session session = null;
   Transaction t = null;

//    /**
    * ������ѯ����,�رն�������, ����һ��session,�ֱ����query.list
    */
    //������ò�ѯ����Ļ�,��������������ѯ���,��Ҳ��Ĭ�ϵ����.
    /*
      try {
        session = HibernateUtils.getSession();
        t = session.beginTransaction();
        Query query = session.createQuery("select s.name from Student s");
        //���ò�ѯ����
        query.setCacheable(true);
        List<String> names = query.list();
        for (Iterator<String> it = names.iterator(); it.hasNext();) {
          String name = it.next();
          System.out.println(name);
        }
        System.out.println("================================");
        query = session.createQuery("select s.name from Student s");
        //���ò�ѯ����
        query.setCacheable(true);
        //û�з�����ѯ���,��Ϊ����ʹ�õĲ�ѯ����
        names = query.list();
        for (Iterator<String> it = names.iterator(); it.hasNext();) {
          String name = it.next();
          System.out.println(name);
        }
        t.commit();
      } catch (Exception e) {
        e.printStackTrace();
        t.rollback();
      } finally {
        HibernateUtils.closeSession(session);
      }
    }*/

/*  @SuppressWarnings("unchecked")
public static void main(String[] args) {
Session session = null;
Transaction t = null;

*//**
 * ������ѯ����,�رն�������, ��������session,�ֱ����query.list
 *//*
        //������ò�ѯ����Ļ�,��������������ѯ���,��Ҳ��Ĭ�ϵ����.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s.name from Student s");
          //���ò�ѯ����
          //query.setCacheable(true);
          List<String> names = query.list();
          for (Iterator<String> it = names.iterator(); it.hasNext();) {
            String name = it.next();
            System.out.println(name);
          }

          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }

        System.out.println("================================");

        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s.name from Student s");
          //���ò�ѯ����
          //query.setCacheable(true);
          //���ᷢ����ѯ���,��Ϊ��ѯ�����session�޹�.
          List<String> names = query.list();
          for (Iterator<String> it = names.iterator(); it.hasNext();) {
            String name = it.next();
            System.out.println(name);
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }
      }*/

/*  @SuppressWarnings("unchecked")
public static void main(String[] args) {
Session session = null;
Transaction t = null;

*//**
 * ������ѯ����,�رն�������, ��������session,�ֱ����query.iterate
 *//*
        //������ò�ѯ����Ļ�,��������������ѯ���,��Ҳ��Ĭ�ϵ����.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s.name from Student s");
          //���ò�ѯ����
          query.setCacheable(true);
          for (Iterator<String> it = query.iterate(); it.hasNext();) {
            String name = it.next();
            System.out.println(name);
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }

        System.out.println("================================");

        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s.name from Student s");
          //���ò�ѯ����
          query.setCacheable(true);
          //�ᷢ����ѯ���,��Ϊquery.iterate��ʹ�ò�ѯ����
          for (Iterator<String> it = query.iterate(); it.hasNext();) {
            String name = it.next();
            System.out.println(name);
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }
      }*/

/*    @SuppressWarnings("unchecked")
public static void main(String[] args) {
Session session = null;
Transaction t = null;

*//**
 * �رղ�ѯ����,�رն�������, ��������session,�ֱ����query.list��ѯʵ�����
 *//*
        //������ò�ѯ����Ļ�,��������������ѯ���,��Ҳ��Ĭ�ϵ����.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s from Student s");
          //���ò�ѯ����
          //query.setCacheable(true);
          List<Student> students = query.list();
          for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student s = it.next();
            System.out.println(s.getName());
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }

        System.out.println("================================");

        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s from Student s");
          //���ò�ѯ����
          //query.setCacheable(true);
          //�ᷢ����ѯ���,��ΪlistĬ��ÿ�ζ��ᷢ��sql���
          List<Student> students = query.list();
          for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student s = it.next();
            System.out.println(s.getName());
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }
      }*/

/*  @SuppressWarnings("unchecked")
public static void main(String[] args) {
Session session = null;
Transaction t = null;

*/

    /**
     * ������ѯ����,�رն�������, ��������session,�ֱ����query.list��ѯʵ�����
     *//*
        //������ò�ѯ����Ļ�,��������������ѯ���,��Ҳ��Ĭ�ϵ����.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s from Student s");
          //���ò�ѯ����
          query.setCacheable(true);
          List<Student> students = query.list();
          for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student s = it.next();
            System.out.println(s.getName());
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }

        System.out.println("================================");

        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s from Student s");
          //���ò�ѯ����
          query.setCacheable(true);
          //�ᷢ������id��ѯʵ���n����ѯ���,��Ϊ���������,��ѯ�����������ģ�
          // �ڵ�һ��ִ��listʱ,��Ѳ�ѯ�����id���浽��ѯ������
          // �ڶ���ִ��listʱ, �������ѯ�������id��������ȥ��ʵ�����,��������û�ҵ�ʵ�����,
          //���Ծͷ���n����ѯ��䵽���ݿ��в�ѯ.
          List<Student> students = query.list();
          for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student s = it.next();
            System.out.println(s.getName());
          }
          t.commit();
        } catch (Exception e) {
          e.printStackTrace();
          t.rollback();
        } finally {
          HibernateUtils.closeSession(session);
        }
      }*/


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Session session = null;
        Transaction t = null;

        /**
         * ������ѯ����,������������, ��������session,�ֱ����query.list��ѯʵ�����
         */
        //������ò�ѯ����Ļ�,��������������ѯ���,��Ҳ��Ĭ�ϵ����.
        try {
            session = HibernateUtils.getSession();
            t = session.beginTransaction();
            Query query = session.createQuery("select s from Student s");
            //���ò�ѯ����
            query.setCacheable(true);
            List<Student> students = query.list();
            for (Iterator<Student> it = students.iterator(); it.hasNext();) {
                Student s = it.next();
                System.out.println(s.getName());
            }
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }

        System.out.println("================================");

        try {
            session = HibernateUtils.getSession();
            t = session.beginTransaction();
            Query query = session.createQuery("select s from Student s");
            //���ò�ѯ����
            query.setCacheable(true);
            //���ᷢ����ѯ���,��Ϊ���������,��ѯ�����������ģ�
            // �ڵ�һ��ִ��listʱ,��Ѳ�ѯ�����id���浽��ѯ������
            // �ڶ���ִ��listʱ, �������ѯ�������id��������ȥ��ʵ�����,�������￪���˶�������,�����ҵ�Ŀ��ʵ�����,
            //���ԾͲ����ٷ���n����ѯ���.
            List<Student> students = query.list();
            for (Iterator<Student> it = students.iterator(); it.hasNext();) {
                Student s = it.next();
                System.out.println(s.getName());
            }
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }

    }
}