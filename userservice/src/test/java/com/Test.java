package com;

import com.mysql.cj.util.Base64Decoder;
import com.user.utils.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created By lichang on
 */
public class Test {
//    public static void main(String[] args) throws ParseException {
////        boolean checkpw = BCrypt.checkpw("123", "$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm");
////        System.out.println(checkpw);
//        String starttime="2022-06-04 12:22:09";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date start = simpleDateFormat.parse(starttime);
////        yyyy-MM-dd
//        System.out.println(simpleDateFormat.format(start));
//    }

    public static void main(String[] args) throws Exception {
        ///设置线程池最大执行20个线程并发执行任务
        int threadSize = 20;
        //AtomicInteger通过CAS操作能保证统计数量的原子性
        AtomicInteger successCount = new AtomicInteger(0);
        CountDownLatch downLatch = new CountDownLatch(20);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);
        for (int i = 0; i < threadSize; i++) {
            fixedThreadPool.submit(() -> {
                RestTemplate restTemplate = new RestTemplate();
                String str = restTemplate.getForObject("http://localhost:8010/ratelimit", String.class);
                if ("success".equals(str)) {
                    successCount.incrementAndGet();
                }
                System.out.println(str);
                downLatch.countDown();
            });
        }
        //等待所有线程都执行完任务
        downLatch.await();
        fixedThreadPool.shutdown();
        System.out.println("总共有" + successCount.get() + "个线程获得到了令牌!");
    }


}
