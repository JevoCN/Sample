package org.jevo.hotswap.sample.hot;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-18
 * Time: 22:51:51
 * To change this template use File | Settings | File Templates.
 */

public class ClassTransform implements ClassFileTransformer {
    private Instrumentation inst;

    protected ClassTransform(Instrumentation inst) {
        this.inst = inst;
    }

    /**
     * �˷�����redefineClassesʱ���߳��μ���ʱ�ᱻ���ã�Ҳ����˵��class���ٴμ���ʱ�ᱻ���ã���������ͨ���˷������Զ�̬�޸�class�ֽ��룬ʵ�����ƴ���֮��Ĺ��ܣ����巽����ʹ��ASM����javasist��������ֽ������Ϥ�Ļ�����ֱ���޸��ֽ��롣
     */
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        HotAgent.clsnames.add(className);
        return null;
    }
}

