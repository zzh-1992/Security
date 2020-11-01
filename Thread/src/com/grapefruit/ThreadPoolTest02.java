package com.grapefruit;

import java.util.concurrent.*;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/11/1 11:37:50
 */
public class ThreadPoolTest02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * corePoolSize 核心线程的数量
         * maximumPoolSize 线程数量的最大值
         * keepAliveTime 当前线程池的线程数量超过核心线程数并且处于空闲时，线程池将回收部分线程。该参数用于设置超过coolPoolSize数量的线程在多长时间后被回收
         * TimeUnit 设定keepAliveTime的时间单位
         * workQueue 用于存放已经提交到线程池但未被执行的任务
         * ThreadFactory 创建线程的工厂
         * RejectedExecutionHandler 当任务数量超过阻塞队列边界时，这个时候的线程池会拒绝新增任务。该参数用于设置拒绝策略。
         *
         */
        // create pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8,30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        // 执行异步任务,获取返回值
        //Future<String> future = executor.submit(() -> "Execute the callable task and this is the result");
       //System.out.println(future.get());

        // executor
        /*executor.execute(()->{
            System.out.println("print task");
        });*/

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executor.execute(() ->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task finish done" + Thread.currentThread());
            });
        }
        System.out.println("ActiveCount" + executor.getActiveCount());
        System.out.println("LargestPoolSize" + executor.getLargestPoolSize());
        System.out.println("CorePoolSize:" + executor.getCorePoolSize());
        System.out.println("TaskCount:" + executor.getTaskCount());
        System.out.println("ActiveCount: " + executor.getActiveCount());
        System.out.println(executor.getQueue().isEmpty()?"空队列":"队列中有元素");
        System.out.println("Queue().size:" + executor.getQueue().size());
        System.out.println();
        executor.shutdown();
        long end = System.currentTimeMillis();
        long time  = end - begin;
        System.out.println("花费的时间:"+ time/1000 + "秒");
    }

}
