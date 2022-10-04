package com.example.basecase.util;

/**
 * @author ：zb
 * @date ：Created in 2021/10/24 19:48
 * @description：${description}
 * @modified By：
 * @version:
 */
public class SnowFlakeGenerateIdWorker {


    private static long sequence;

    //初始时间戳
    private static long twepoch = 1288834974657L;

    //序列号id长度
    private static long sequenceBits = 4L;
    //序列号最大值
    private static long sequenceMask = -1L ^ (-1L << sequenceBits);

    //时间戳需要左移位数 4位
    private static long timestampLeftShift = sequenceBits;

    //上次时间戳，初始值为负数
    private static long lastTimestamp = -1L;


    //下一个ID生成算法
    public static synchronized long nextId() {
        long timestamp = timeGen();

        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //将上次时间戳值刷新
        lastTimestamp = timestamp;

        /**
         * 返回结果：
         * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
         * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
         * (workerId << workerIdShift) 表示将工作id左移相应位数
         * | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
         * 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
         */
        System.out.println("(timestamp - twepoch) :" + (timestamp - twepoch));
        System.out.println("front :" + ((timestamp - twepoch) << timestampLeftShift));
        System.out.println("sequence :" + sequence);
        return ((timestamp - twepoch) << timestampLeftShift) | sequence;
    }

    //获取时间戳，并与上次时间戳比较
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取系统时间戳
    private static long timeGen(){
        return System.currentTimeMillis();
    }

}
