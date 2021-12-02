<%--
  Created by IntelliJ IDEA.
  User: LF
  Date: 2021/11/26
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>用户注册</h1>
    <form action="<%=request.getContextPath()%>/user/reg" method="post">
            <h2>${message}</h2>
         用户名:<input name="username"><br/>
        密码:<input type="password" name="password"><br/>
        <input type="submit" value="注册">
    </form>
</body>
</html>
