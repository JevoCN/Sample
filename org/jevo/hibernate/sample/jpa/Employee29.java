package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:36:36
 * To change this template use File | Settings | File Templates.
 */

//@ExcludeDefaultListeners
//Ĭ�ϼ��������� orm.xml �ļ���ָ����һ�����������¼����������࣬����Ӧ���ڳ����Ե�Ԫ������� @PersistenceUnit���е�����ʵ�塣�ڵ����κ�����ʵ�������������� @EntityListeners��֮ǰ��JPA �������ṩ�������Ȱ��� orm.xml �ļ��ж����˳�����Ĭ�ϼ�����������У���

//���Ĭ�ϼ���������Ϊ�����ã���ʹ�� @ExcludeDefaultListeners ��ע���ǣ�����ֹ����Ը��� @Entity �� @MappedSuperclass ִ�е�Ĭ�ϼ�������

//����עû������ ���йظ�����ϸ��Ϣ������� API��

//ʾ�� 1-29 ��ʾ�����ʹ�ô���עָ����Ӧ�� Employee ʵ��ִ��Ĭ�ϼ�������

//ʾ�� 1-29 @ExcludeDefaultListeners

@Entity
@ExcludeDefaultListeners
public class Employee29 implements Serializable {
  
}

