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


    <title>회원 목록</title>
</head>
<body>

<div class="container-fluid container text-center">

    <div>회원 목록</div>

    <table class="table">
        <thead>
        <tr>
            <th>고유번호</th>
            <th>아이디</th>
            <th>이름</th>
            <th>이메일</th>
            <th>주소</th>
            <th>전화번호</th>
            <th>가입일</th>
            <th>권한</th>
            <th>상태</th>
            <th>상세보기</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="userList" items="${userList }" varStatus="status">
            <tr>
                <td>${userList.id}</td>
                <td>${userList.name}</td>
                <td>${userList.username}</td>
                <td>${userList.email}</td>
                <td>${userList.address}</td>
                <td>${userList.tel}</td>
                <td>${userList.regdate}</td>
                <td>${userList.lev}</td>
                <td>${userList.act}</td>
                <td>
                    <button type="button" class="button is-info"
                            onclick="location.href='${path}/user/user.do?name=${userList.name}' ">
                        상세
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>


</body>
</html>