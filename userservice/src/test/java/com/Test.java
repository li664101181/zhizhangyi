package com;

import com.mysql.cj.util.Base64Decoder;
import com.user.utils.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * created By lichang on
 */
public class Test {
    public static void main(String[] args) throws ParseException {
//        boolean checkpw = BCrypt.checkpw("123", "$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm");
//        System.out.println(checkpw);
        String starttime="2022-06-04 12:22:09";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = simpleDateFormat.parse(starttime);
//        yyyy-MM-dd
        System.out.println(simpleDateFormat.format(start));
    }

}
