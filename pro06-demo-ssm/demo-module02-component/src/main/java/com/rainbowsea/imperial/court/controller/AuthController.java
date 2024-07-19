package com.rainbowsea.imperial.court.controller;


import com.rainbowsea.imperial.court.entity.User;
import com.rainbowsea.imperial.court.service.api.UserService;
import com.rainbowsea.imperial.court.utils.ImperialCourtConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller //
public class AuthController {


    @Autowired
    private UserService userService;

    @RequestMapping("/auth/login")
    public String doLogin(
            @RequestParam("loginAccount") String loginAccount,
            @RequestParam("loginPassword") String loginPassword,
            HttpSession session,
            Model model
    ) {

        // 1、尝试查询登录信息
        User user = userService.getUserByLogin(loginAccount, loginPassword);

        // 2、判断登录是否成功
        if (user == null) {
            // 登录失败，密码错误
            model.addAttribute("message", ImperialCourtConst.LOGIN_FAILED_MESSAGE);

            // 返回首页重新登录
            // 3、如果登录失败则回到登录页面显示提示消息
            return "index";

        } else {

            // 4、如果登录成功则将登录信息存入 Session 域
            session.setAttribute("loginInfo", user);

            return "target";
        }

    }
}
