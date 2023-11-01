package com.chunjae.test03.ctrl;

import com.chunjae.test03.EmailSocket;
import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.biz.UserService;
import com.chunjae.test03.exception.NoSuchDataException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session; // 세션 생성



/*   @GetMapping("userList.do")
    @ResponseBody
    public List<Euser> getUserList(Model model) throws Exception {
        List<Euser> getUserList = userService.getUserList();
        //model.addAttribute("getUserList", getUserList);

        return getUserList;
    }


    @GetMapping("user.do")
    @ResponseBody
    public Euser getuser(@RequestParam("name") String name) throws Exception {
        Euser user = userService.getUser(name);
        //model.addAttribute("user", user);

        return user;
    }*/

    //마이페이지
    @GetMapping("userIndex.do")
    public String userIndex(Model model) throws Exception {
        return "user/userIndex";
    }

    //관리자 페이지
    @GetMapping("adminIndex.do")
    public String adminIndex(Model model) throws Exception {

        return "user/adminIndex";
    }


    @GetMapping("userList.do")
    public String getUserList(Model model) throws Exception {
        List<Euser> userList = userService.getUserList();

        // Exception 처리
        if (userList.isEmpty()) {
            throw new NoSuchDataException("No Such List");
        }

        model.addAttribute("userList", userList);
        return "user/list";
    }


    @GetMapping("user.do")
    public String getuser(@RequestParam("name") String name, Model model) throws Exception {
        String sname = (String) session.getAttribute("sname");
        Euser user = userService.getUser(name);
        // Exception 처리
        if (user == null) {
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("user", user);
        return "user/get";
    }


    //로그인(로그인방식 선택) 폼
    @RequestMapping("login.do")
    public String userLoginForm(Model model) throws Exception {
        return "user/login";
    }


    //이메일 로그인
    @RequestMapping("emailLogin.do")
    public String emailLoginForm(Model model) throws Exception {
        /*String sname = (String) session.getAttribute("sname");
        if(sname!=null){
            return "redirect:/";
        }*/
        return "user/emailLogin";
    }

    // 로그인 시도
    @PostMapping("emailSignIn.do")
    public String emailSignIn(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        String email = request.getParameter("email");
        String pw = request.getParameter("passwd");

        boolean check = userService.loginCheck(email, pw);
        if (check) { // 로그인 성공
            Euser user = new Euser();
            user = userService.getByEmail(email);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 성공');</script>");
            out.flush();
            session.setAttribute("sname", user.getName());
            session.setAttribute("slevel", user.getLev());
            return "index";
        } else { // 로그인 실패
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 실패');</script>");
            out.flush();
            return "user/emailLogin";
        }
    }


    //아이디 로그인
    @RequestMapping("idLogin.do")
    public String idLoginForm(Model model) throws Exception {
        /*String sname = (String) session.getAttribute("sname");
        if(sname!=null){
            return "redirect:/";
        }*/
        return "user/idLogin";
    }

    @PostMapping("idSignIn.do")
    public String idSignIn(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        String name = request.getParameter("name");
        String pw = request.getParameter("passwd");

        boolean check = userService.loginCheck2(name, pw);
        if (check) { // 로그인 성공
            Euser user = new Euser();
            user = userService.getByName(name);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 성공');</script>");
            out.flush();
            session.setAttribute("sname", user.getName());
            session.setAttribute("slevel", user.getLev());
            return "index";
        } else { // 로그인 실패
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 실패');</script>");
            out.flush();
            return "user/emailLogin";
        }
    }


    //탈퇴
/*    @PutMapping("withdraw.do")
    @ResponseBody
    public int userwithdraw(@RequestParam("name") String name, Model model) {
        int cnt = userService.getWithdraw(name);
        return cnt;
    }*/



    //계정 활성화

    //휴면 처리

    //아이디 찾기

    //비밀번호 찾기
    @PostMapping("/findPw.do")
    public String findByPw(Euser euser, Model model) {
        Euser user = userService.findByPw(euser.getEmail(), euser.getTel(), euser.getName());
        return "user/findId";
    }

    public void sendMail(Euser euser) {
        EmailSocket dm = new EmailSocket();
        dm.sendMail(euser);
    }

    //회원가입
    @RequestMapping("join.do")
    public String joinForm(Model model) throws Exception {

        return "user/join";
    }

    //회원 가입 - 회원 가입 처리
    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public String memberWrite(HttpServletResponse response, Euser user, Model model) throws Exception {

        userService.userInsert(user);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원가입이 완료되었습니다.');</script>");
        out.flush();
        return "redirect:/";

    }


    /*회원 정보 수정 폼 이동*/
    @GetMapping("userUpdate.do")
    public String userUpdate(@RequestParam("name") String name, HttpServletRequest request, Model model) throws Exception {
        Euser user = userService.getUser(name);


        model.addAttribute("user", user);
        System.out.println(user.toString());
        return "user/userUpdate";
    }

    //회원정보수정
    // 회원 정보 수정
/*    @PostMapping("userUpdate.do")
    public String memberEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        Euser user = new Euser();

        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");

        user.setName(name);
        user.setPasswd(passwd);
        user.setUsername(username);
        user.setEmail(email);
        user.setTel(tel);
        user.setAddress(address);

        userService.userUpdate(user);
        model.addAttribute("user", user);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원님의 정보가 수정되었습니다.');</script>");
        out.flush();

        return "user/get";
    }*/

    @PutMapping("updateUserPro.do")
    @ResponseBody
    public Euser updateUserPro(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        Euser user = new Euser();

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");

        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setTel(tel);
        user.setAddress(address);


        userService.userUpdate(user);
//        int cnt = userService.userUpdate(user);
//
//        // Exception 처리
//        if(cnt == 0){
//            throw new NoSuchDataException("No Delete Process Data");
//        }
        model.addAttribute("user", user);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원님의 정보가 수정되었습니다.');</script>");
        out.flush();

        return user;
    }


    /*//회원등급 변경
    @PutMapping("/upgradeLevel.do")
    @ResponseBody
    public int upgradeLevel(@RequestParam("name") String name, @RequestParam("lev") String lev, Model model) {

        int cnt = userService.updateLevel(name, lev);
        model.addAttribute("msg","회원등급을 수정하였습니다");
        return cnt;
    }*/

    //회원탈퇴 폼 이동
    @RequestMapping("userDelete.do")
    public String userDeleteForm(Model model) throws Exception {

        return "user/userDelete";
    }

/*    //계정 삭제
    @DeleteMapping("/removeUser.do")
    @ResponseBody
    public int removeUser(@RequestParam("name") String name, Model model) {
        int cnt = userService.removeUser(name);
        return cnt;
    }*/

    //계정 완전히 삭제
    @DeleteMapping("/removeUser.do/{name}")
    @ResponseBody
    public int removeUser(@PathVariable("name") String name, Model model) {
        int cnt = userService.removeUser(name);
        // Exception 처리
        if (cnt == 0) {
            throw new NoSuchDataException("No Delete Process Data");
        }
        return cnt;
    }


    //계정 활성화
    //@PutMapping("/activate.do")
}
