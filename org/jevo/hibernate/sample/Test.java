package org.jevo.hibernate.sample;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.BatchSize;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
    * 开启查询缓存,关闭二级缓存, 开启一个session,分别调用query.list
    */
    //如果不用查询缓存的话,那两个都发出查询语句,这也是默认的情况.
    /*
      try {
        session = HibernateUtils.getSession();
        t = session.beginTransaction();
        Query query = session.createQuery("select s.name from Student s");
        //启用查询缓存
        query.setCacheable(true);
        List<String> names = query.list();
        for (Iterator<String> it = names.iterator(); it.hasNext();) {
          String name = it.next();
          System.out.println(name);
        }
        System.out.println("================================");
        query = session.createQuery("select s.name from Student s");
        //启用查询缓存
        query.setCacheable(true);
        //没有发出查询语句,因为这里使用的查询缓存
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
 * 开启查询缓存,关闭二级缓存, 开启两个session,分别调用query.list
 *//*
        //如果不用查询缓存的话,那两个都发出查询语句,这也是默认的情况.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s.name from Student s");
          //启用查询缓存
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
          //启用查询缓存
          //query.setCacheable(true);
          //不会发出查询语句,因为查询缓存和session无关.
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
 * 开启查询缓存,关闭二级缓存, 开启两个session,分别调用query.iterate
 *//*
        //如果不用查询缓存的话,那两个都发出查询语句,这也是默认的情况.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s.name from Student s");
          //启用查询缓存
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
          //启用查询缓存
          query.setCacheable(true);
          //会发出查询语句,因为query.iterate不使用查询缓存
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
 * 关闭查询缓存,关闭二级缓存, 开启两个session,分别调用query.list查询实体对象
 *//*
        //如果不用查询缓存的话,那两个都发出查询语句,这也是默认的情况.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s from Student s");
          //启用查询缓存
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
          //启用查询缓存
          //query.setCacheable(true);
          //会发出查询语句,因为list默认每次都会发出sql语句
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
     * 开启查询缓存,关闭二级缓存, 开启两个session,分别调用query.list查询实体对象
     *//*
        //如果不用查询缓存的话,那两个都发出查询语句,这也是默认的情况.
        try {
          session = HibernateUtils.getSession();
          t = session.beginTransaction();
          Query query = session.createQuery("select s from Student s");
          //启用查询缓存
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
          //启用查询缓存
          query.setCacheable(true);
          //会发出根据id查询实体的n条查询语句,因为这种情况下,查询过程是这样的：
          // 在第一次执行list时,会把查询对象的id缓存到查询缓存里
          // 第二次执行list时, 会遍历查询缓存里的id到缓存里去找实体对象,由于这里没找到实体对象,
          //所以就发出n条查询语句到数据库中查询.
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
         * 开启查询缓存,开启二级缓存, 开启两个session,分别调用query.list查询实体对象
         */
        //如果不用查询缓存的话,那两个都发出查询语句,这也是默认的情况.
        try {
            session = HibernateUtils.getSession();
            t = session.beginTransaction();
            Query query = session.createQuery("select s from Student s");
            //启用查询缓存
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
            //启用查询缓存
            query.setCacheable(true);
            //不会发出查询语句,因为这种情况下,查询过程是这样的：
            // 在第一次执行list时,会把查询对象的id缓存到查询缓存里
            // 第二次执行list时, 会遍历查询缓存里的id到缓存里去找实体对象,由于这里开启了二级缓存,可以找到目标实体对象,
            //所以就不会再发出n条查询语句.
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