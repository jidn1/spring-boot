package com.notes.study.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/8/4 17:06
 * @Description:
 */
public class MyClassLoaderTest {


    private static class MyClassLoader extends ClassLoader {

        String classPath;

        public MyClassLoader() {
        }

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        public byte[] loadByte(String name) throws Exception{
            name = name.replaceAll("\\.", "/");
            System.out.println(classPath + "/" + name +".class");
            FileInputStream f = new FileInputStream(classPath + "/" + name +".class");
            int available = f.available();
            byte[] data = new byte[available];
            f.read(data);
            f.close();
            return data;

        }


        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }

        }


        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
//                    try {
//                        if (parent != null) {
//                            c = parent.loadClass(name, false);
//                        } else {
//                            c = findBootstrapClassOrNull(name);
//                        }
//                    } catch (ClassNotFoundException e) {
//                        // ClassNotFoundException thrown if class not found
//                        // from the non-null parent class loader
//                    }

                        long t1 = System.nanoTime();


                        if(!name.startsWith("com.notes.study.jvm")){
                            c = this.getParent().loadClass(name);
                        } else {
                            c = findClass(name);
                        }

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }




    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("D:/ifeng");
        Class<?> loadClass = myClassLoader.loadClass("com.notes.study.jvm.User");
        Object o = loadClass.newInstance();
        Method method = loadClass.getDeclaredMethod("sout", null);
        method.invoke(o,null);

        System.out.println(loadClass.getClassLoader().getClass().getName());
    }


}
