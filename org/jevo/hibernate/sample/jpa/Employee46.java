package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:30:55
 * To change this template use File | Settings | File Templates.
 */
//@Lob
//Ĭ������£�JPA �������ṩ����������г־����ݾ����Ա�ʾΪ���͵����ݿ��������͡�

//���ʹ�� @Lob ��ע�� @Basic ӳ�䣬��ָ���־����Ի��ֶ�Ӧ��Ϊ���Ͷ���־ñ��浽���ݿ�֧�ֵĴ��Ͷ������͡�

//Lob �����Ƕ��������ͻ��ַ����͡��������ṩ����ӳ־��ֶλ����Ե������ƶϳ� Lob ���͡�

//���ڻ����ַ������ַ������ͣ�Ĭ��ֵΪ Clob����������������£�Ĭ��ֵΪ Blob��

//������ʹ�� @Column ���� columnDefinition ��һ���Ľ� Lob ���͡�

//����עû������ ���йظ�����ϸ��Ϣ������� API��

//ʾ�� 1-46 ��ʾ�����ʹ�ô���עָ���־��ֶ� pic Ӧ��Ϊ Blob ���г־ñ��档

//ʾ�� 1-46 @Lob

@Entity
public class Employee46 implements Serializable {
    @Lob
//    @Basic(fetch = FetchType.LAZY)
    @Basic(optional=false)
    @Column(name = "EMP_PIC", columnDefinition = "BLOB NOT NULL")
    protected byte[] pic;

}

