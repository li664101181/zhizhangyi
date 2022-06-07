package com.user.controller;


import com.user.entity.TUser;
import com.user.service.TUserService;
import com.user.service.impl.TUserServiceImpl;
import com.user.utils.RespBean;
import com.user.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李昶
 * @since 2022-06-05
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {
    @Autowired
    TUserService tUserService;

    /***
     * 新增User数据
     * @param username password
     * @return
     */
    @PostMapping(value = "/add")
    public RespBean add(String username,String password) {
        TUser user=tUserService.addUser(username,password);

        if (null==user){
            return  new  RespBean(500,"该用户已存在",user);
        }else {
            RespBean respBean = RespBean.build();
            respBean.setObj(user);
            respBean.setStatus(200);
            return respBean;}
        //调用UserService实现添加User

    }

    /***
     * 更改User数据
     * @param
     * @return
     */
    @PostMapping(value = "/change")
    public RespBean change(String username,String password,String newpassword) {
        Boolean tag=tUserService.change(username,password,newpassword);
        RespBean respBean = RespBean.build();
        if (tag){
            respBean.setStatus(200);
        }else {
            respBean.setStatus(500);
        }
        return respBean;
    }
    @PostMapping(value = "/getUsersByTime")
    public RespBean findUserBytime(@RequestBody List<String> time) throws ParseException {
        String starttime=time.get(0);
        String endtime=time.get(1);
        List<TUser>  userList= tUserService.findUserBytime(starttime,endtime);
        RespBean respBean = RespBean.build();
        respBean.setStatus(200);
        respBean.setObj(userList);
        return respBean;
        //
    }


}
