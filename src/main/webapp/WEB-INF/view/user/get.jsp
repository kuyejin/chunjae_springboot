<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%--부트스트랩--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>회원 상세보기</title>


</head>
<body>
<div class="container-fluid container text-center">

    <div>상세 보기</div>

    <table class="table">

        <tbody>

        <tr>
            <th>고유번호</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>아이디</th>
            <td>${user.name}</td>
        </tr>
        <tr>
            <th>이름</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>이메일</th>
            <td>${user.email}</td>
        <tr>
            <th>주소</th>
            <td>${user.address}</td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td>${user.tel}</td>
        </tr>
        <tr>
            <th>가입일</th>
            <td>${user.regdate}</td>
        </tr>
        <tr>
            <th>권한</th>
            <td>${user.lev}</td>
        </tr>
        <tr>
            <th>상태</th>
            <td>${user.act}</td>
        </tr>

        </tbody>
    </table>

    <button type="button" class="button is-info"
            onclick="location.href='${path}/user/userUpdate.do?name=${user.name}' ">
        회원정보수정
    </button>

</div>

</body>
</html>