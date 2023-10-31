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
    <title>로그인</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="${path}/user/emailSignIn.do">
        <h2 class="form-signin-heading">로그인</h2>
        <p>
            <label for="email" class="sr-only">EMAIL</label>
            <input type="text" id="email" name="email" class="form-control" placeholder="email" required autofocus>
        </p>
        <p>
            <label for="passwd" class="sr-only">Password</label>
            <input type="password" id="passwd" name="passwd" class="form-control" placeholder="Password" required>
        </p>
       <%-- <input name="_csrf" type="hidden" value="UUMGNQSFRs0i3-z_w4gNb2g_LUwk0lSDMjPuFN6iXe2_pgXpYScwBjfgf_0P7NXP9qU5CQxbAHRC5WSuU1HaLLiXbY6NlWGK" />--%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
    </form>
</div>
</body></html>