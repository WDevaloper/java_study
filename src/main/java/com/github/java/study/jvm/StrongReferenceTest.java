package com.github.java.study.jvm;


import static com.github.java.study.jvm.Util.printMemory;

//-Xms20M  初始堆大小为20M
// -Xmx20M  堆最大分配内存20M
public class StrongReferenceTest {
    private static final int _1MB = 1024 * 1024;
    byte[] a1 = new byte[11 * _1MB];

    public static void main(String[] args) {
        //先打印内存
        printMemory();
        // 创建强引用  11M
        StrongReferenceTest strongReference = new StrongReferenceTest();
        //gc完成之后再打印内存
        printMemory();
        // 来一次 gc
        System.gc();

        try {
            //堆只有9M JVM 宁愿抛出OutOfMemoryError 运行时错误让程序异常终止，也不会回收强引用所指向的对象实例
            byte[] ref1 = new byte[8 * _1MB];
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }
}
