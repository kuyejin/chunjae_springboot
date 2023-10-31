package com.chunjae.test03.ctrl;

import com.chunjae.test03.EmailSocket;
import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.biz.UserService;
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


    @GetMapping("userList.do")
    public String getUserList(Model model) throws Exception {
        List<Euser> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "user/list";
    }


    @GetMapping("user.do")
    public String getuser(@RequestParam("name") String name, Model model) throws Exception {
        String sname = (String) session.getAttribute("sname");
        Euser user = userService.getUser(name);
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
    @PostMapping("userUpdate.do")
    public String memberEdit(HttpServletRequest request, Euser user, HttpServletResponse response, Model model) throws Exception {


        String id = request.getParameter("name");
        String pw = request.getParameter("passwd");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String addr1 = request.getParameter("addr1");
        String addr2 = request.getParameter("addr2");
        String postcode = request.getParameter("postcode");
        String birth = request.getParameter("birth");
        int job = Integer.parseInt(request.getParameter("job"));

        Member member = new Member();
        member.setId(id);
        member.setPw(pw);
        member.setName(name);
        member.setEmail(email);
        member.setTel(tel);
        member.setAddr1(addr1);
        member.setAddr2(addr2);
        member.setPostcode(postcode);
        member.setBirth(birth);


        if (job==2) {

            MultipartHttpServletRequest files = (MultipartHttpServletRequest) request;
            MultipartFile img = files.getFile("img");

            String devFolder = uploadPath;    //개발자용 컴퓨터에 업로드 디렉토리 지정
            String uploadFolder = request.getRealPath("/resources/upload");
            File folder = new File(uploadFolder);
            File devfol = new File(devFolder);

            if (!folder.exists()) folder.mkdirs();
            if (!devfol.exists()) devfol.mkdirs();

            //파라미터 분리
            Enumeration<String> enum1 = files.getParameterNames();
            Map map = new HashMap();
            while (enum1.hasMoreElements()) {
                String name2 = enum1.nextElement();
                String value = files.getParameter(name2);
                map.put(name2, value);
            }


            Instructor inst2 = instructorService.getInstructorById(id);
            inst2.setCate(request.getParameter("cate"));
            inst2.setIntro(request.getParameter("intro"));
            inst2.setName(request.getParameter("name"));
            inst2.setEmail(request.getParameter("email"));
            inst2.setTel(request.getParameter("tel"));
            inst2.setId(request.getParameter("id"));
            System.out.println("inst2 : " + inst2.toString());
// 개발 서버 파일 저장 경로
//        String uploadDir = "D:/team_pro4/team14/src/main/webapp/resources/upload/"; // 회사
//            String uploadDir = "/Users/juncheol/Desktop/team_pro4/team14/src/main/webapp/resources/upload/"; // 백준철
            // String uploadDir = "E:/git/spring_study/pro04/src/main/webapp/resources/upload/"; // 집
            // 실제 서버 파일 저장 경로
            String uploadSev = request.getRealPath("/resources/upload/");

            if (!img.isEmpty()) {
                String randomUUID = UUID.randomUUID().toString(); // 파일 이름 중복 방지를 위한 랜덤 설정
                String OriginalFilename = img.getOriginalFilename();
                String Extension = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
                String RandomFileName = randomUUID + Extension;
                inst2.setImg(RandomFileName);

                try {
                    img.transferTo(new File(uploadSev + RandomFileName));
//                    img.transferTo(new File(uploadDir + RandomFileName));

                } catch (IOException e) {
                    e.printStackTrace(); // 오류 처리
                }
            }

            instService.updateInstructor(inst2);
        }


        memberService.memberUpdate(member);
        model.addAttribute("member", member);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원님의 정보가 수정되었습니다.');</script>");
        out.flush();

        List<Member> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        model.addAttribute("title", "회원 목록");
        String sid = (String) session.getAttribute("sid");
        if (sid.equals("admin")) {
            return "/admin/memberList";
        } else {
            return "/member/myPage/memberUpdate";
        }
    }









    //회원등급 변경


}
