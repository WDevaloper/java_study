package com.github.java.study.jvm;

import java.lang.ref.SoftReference;


//-Xms20M  初始堆大小为20M
// -Xmx20M  堆最大分配内存20M
public class SoftReferenceTest {
    public static void main(String[] args) {
        //先打印内存
        MemoryUtil.printMemory();
        // 创建强引用  11M
        ReferenceObject object =
                new ReferenceObject(11);
        // 因为object是强引用 为不影响 测试软引用 把object = null


        // 使用软引用 引用 object对象
        SoftReference<ReferenceObject> softReference =
                new SoftReference<>(object);

        object = null;//断开强引用


        System.out.println("softReference = " + softReference.get());
        System.out.println("object = " + object);

        //gc完成之后再打印内存
        MemoryUtil.printMemory();
        // 来一次 gc
        System.gc();
        MemoryUtil.printMemory();

        try {
            //堆只有9M JVM 宁愿抛出OutOfMemoryError 运行时错误让程序异常终止，也不会回收强引用所指向的对象实例
            ReferenceObject object8M = new ReferenceObject(8);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.out.println("softReference = " + softReference.get());
            System.out.println("object = " + object);
            MemoryUtil.printMemory();
        }
    }
}
