package com.chunjae.test03.biz;

import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.per.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 전체 회원 리스트 보기
    public List<Euser> getUserList() throws Exception {
        return userMapper.getUserList();
    }

    // 원하는 회원 정보 보기
    public Euser getUser(String name) throws Exception {
        return userMapper.getUser(name);
    }

    //WITHDRAW(탈퇴)
    public void getWithdraw(String name){
        userMapper.getWithdraw(name);
    }
    //JOIN(활동 중)
    public void getActivate(String name){
        userMapper.getActivate(name);
    }
    //DORMANT(휴면 중)
    public void getDormant(String name){
        userMapper.getDormant(name);
    }


    //이메일 로그인
    public Euser getByEmail(String email){
        return userMapper.getByEmail(email);

    }

    //id 로그인
    public Euser getByName(String name){
        return userMapper.getByName(name);
    }



    // Ajax로 로그인 처리 (컨트롤러)
    public Euser login(String email) throws Exception {
        return userMapper.login(email);
    }

    public boolean loginCheck(String email, String pw) throws Exception{
        boolean comp = false;
        //Euser user = userMapper.loginCheck(email);
        Euser user = userMapper.getByEmail(email);
        try{
            //비밀번호 체크
            //boolean check = pwEncoder.matches(pw, member.getPw()); // 입력된 비밀번호와 db의 암호화된 비밀번호 비교
            if(pw.equals(user.getPasswd())) {
                comp = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return comp;
    }

    public boolean loginCheck2(String name, String pw) throws Exception{
        boolean comp = false;
        //Euser user = userMapper.loginCheck(email);
        Euser user = userMapper.getByName(name);
        try{
            //비밀번호 체크
            //boolean check = pwEncoder.matches(pw, member.getPw()); // 입력된 비밀번호와 db의 암호화된 비밀번호 비교
            if(pw.equals(user.getPasswd())) {
                comp = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return comp;
    }

    //아이디 찾기
    public Euser findById(String email, String tel){
        return userMapper.findById(email,tel);
    }

    //비밀번호 찾기
    public Euser findByPw(String email, String tel, String name){
        return userMapper.findByPw(email,tel,name);
    }


    public void userInsert(Euser user) {
        userMapper.userInsert(user);
    }
    public void userUpdate(Euser user) {
        userMapper.userUpdate(user);
    }




}
