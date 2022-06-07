package com.user.service.impl;

import com.user.entity.TUser;
import com.user.mapper.TUserMapper;
import com.user.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李昶
 * @since 2022-06-05
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
  @Resource
TUserMapper tUserMapper;

    @Override
    public TUser selectByUsername(String username) {
        if (username == null){  //如果用户名不存在，就抛出下面的信息
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //用户存在的话就返回hr对象
        TUser tUser = tUserMapper.loadUserByUsername(username);
        return tUser;
    }

    @Override
    public TUser addUser(String username, String password) {
        //如果用户名已存在，就不许创建
        if (selectByUsername(username)!=null) {
            return  null;};
        TUser tUser = new TUser();
        tUser.setUsername(username);
        //使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        tUser.setPassword( passwordEncoder.encode(password));
        LocalDateTime ldt = new Date().toInstant()
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        tUser.setCreateTime(ldt);
        tUser.setUpdateTime(ldt);
        tUser.setType(1);
        tUserMapper.insert(tUser);
        return tUser;
    }

    @Override
    public Boolean change(String username, String password,String newpass) {
        TUser tUser = tUserMapper.loadUserByUsername(username);
        //如果用户密码正确，则可以更改密码
        boolean tag = BCrypt.checkpw(password, tUser.getPassword());
        if (tag){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            tUser.setPassword( passwordEncoder.encode(newpass));
        }
        return tag;
    }

    @Override
    public List<TUser> findUserBytime(String starttime,String endtime) throws ParseException {
        //时间转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = simpleDateFormat.parse(starttime);
        Date end = simpleDateFormat.parse(endtime);
//        LocalDateTime ldt = new Date().toInstant()
//                .atZone( ZoneId.systemDefault() )
//                .toLocalDateTime();
        List<TUser> userList = tUserMapper.countuserbytime(start, end);
        return userList;
    }


}
