package org.jevo.hotswap.sample.instru;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:22:11
 * To change this template use File | Settings | File Templates.
 */

class Transformer implements ClassFileTransformer {

    public static final String classNumberReturns2 = "TransClass.class.2";

    public static byte[] getBytesFromFile(String fileName) {
        try {
            // precondition
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length]; 

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset <bytes.length  && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            is.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!" + e.getClass().getName());
            return null;
        }
    }

    public byte[] transform(ClassLoader l, String className, Class<?> c, ProtectionDomain pd, byte[] b)
            throws IllegalClassFormatException {
        if (!className.equals("TransClass")) {
            return null;
        }
        return getBytesFromFile(classNumberReturns2);

    }
 }



