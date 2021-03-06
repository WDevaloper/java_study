package com.github.java.study.jvm;

import java.lang.ref.WeakReference;


//-Xms20M  初始堆大小为20M
// -Xmx20M  堆最大分配内存20M
public class WeekReferenceTest {
    public static void main(String[] args) {
        //先打印内存
        MemoryUtil.printMemory();
        // 创建强引用  11M
        ReferenceObject object = new ReferenceObject(11);

        WeakReference<ReferenceObject> softReference = new WeakReference<>(object);
        // 因为object是强引用 为不影响 测试软引用 把object = null
        object = null;//断开强引用


        System.out.println("softReference = " + softReference.get());
        System.out.println("object = " + object);

        //gc完成之后再打印内存
        MemoryUtil.printMemory();
        // 来一次 gc
        System.gc();
        MemoryUtil.printMemory();

        System.out.println("softReference = " + softReference.get());
        System.out.println("object = " + object);

    }
}
