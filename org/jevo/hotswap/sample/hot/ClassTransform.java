package org.jevo.hotswap.sample.hot;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
     * 此方法在redefineClasses时或者初次加载时会被调用，也就是说在class被再次加载时会被调用，并且我们通过此方法可以动态修改class字节码，实现类似代理之类的功能，具体方法可使用ASM或者javasist，如果对字节码很熟悉的话可以直接修改字节码。
     */
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        HotAgent.clsnames.add(className);
        return null;
    }
}

