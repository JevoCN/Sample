package org.jevo.hotswap.sample.hot;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Timer;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-18
 * Time: 22:51:09
 * To change this template use File | Settings | File Templates.
 */

/**
 Tomcat中的class文件是通过org.apache.catalina.loader. WebappClassLoader装载的,同样我们可以做个测试，测试过程与jsp测试类似，测试步骤就不说了，只说一下结果：
  在热部署的情况下，对于被该classloader 加载的class文件，它的classloader始终是同一个WebappClassLoader，除非容器重启了，相信做完这个实验你就不会再认为tomcat是使用一个新的classloader来加载修改过的class了，而且对于有状态的实例，之前该实例拥有的属性和状态都将保存，并在下次执行时拥有了新的class的逻辑，这就是热部署的神秘之处（其实每个实例只是保存了该实例的状态属性，我们通过序列化对象就能看到对象中包含的状态，最终的逻辑还是存在于class文件中）。
 下面的class重定义是通过：java.lang.instrument实现的，具体可参考相关文档。
  下面我们看一下如何通过代理修改内存中的class字节码：
 */
public class HotAgent {
    protected static Set<String> clsnames = new TreeSet<String>();

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        ClassFileTransformer transformer = new ClassTransform(inst);
        inst.addTransformer(transformer);
        System.out.println("是否支持类的重定义：" + inst.isRedefineClassesSupported());
        Timer timer = new Timer();
        timer.schedule(new ReloadTask(inst), 2000, 2000);
    }
}

