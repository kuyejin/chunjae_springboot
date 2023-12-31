<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>메인페이지</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
</head>
<body>
<div class="container">

    <h2 class="form-signin-heading">메인페이지</h2>

    <br><br>

    <nav>


        <a href="${path}/user/emailLogin.do">이메일 로그인</a><br>
        <a href="${path}/user/idLogin.do">아이디 로그인</a><br>
        <a href="${path}/user/join.do">회원가입</a><br>
        <a href="${path}/user/logout.do">로그아웃</a><br>
        <a href="${path}/user/userList.do">회원목록보기</a><br>
        <a href="${path}/user/userIndex.do">마이페이지</a><br>
        <a href="${path}/user/adminIndex.do">관리자페이지</a><br>
    <%-- <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>--%>

    </nav>


</div>
</body>
</html>