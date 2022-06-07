package com.user.controller;//package com.user.controller;

import com.user.utils.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请先登录！");
    }
}