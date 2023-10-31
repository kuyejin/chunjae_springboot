package com.chunjae.test03.ctrl;

import com.chunjae.test03.entity.UserInfo;
import com.chunjae.test03.biz.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session; // 세션 생성



    @GetMapping("userList.do")
    public String userList(Model model) throws Exception{
        List<UserInfo> userList = userService.userList();
        model.addAttribute("userList", userList);

        return "/user/userList";
    }




    @GetMapping("getUser.do")
    public String getUser(HttpServletRequest request, Model model) throws Exception{
        String id = request.getParameter("id");
        UserInfo user = userService.getUser(id);
        model.addAttribute("user", user);

        return"/user/getUser" ;
    }









}
