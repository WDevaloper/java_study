package com.github.java.study.jvm;


//VM agrs:
// -Xms20M  初始堆大小为20M
// -Xmx20M  堆最大分配内存20M
// -Xmn10M  新生代内存大小
// -XX:+PrintGCDetails
// -XX:SurvivorRatio=8 新生代比例
public class OldGenTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] a1, a2;


        a1 = new byte[2 * _1MB];

        a2 = new byte[1 * _1MB];
    }
}
