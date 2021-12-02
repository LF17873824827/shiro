<%--
  Created by IntelliJ IDEA.
  User: LF
  Date: 2021/11/29
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <h1>首页</h1>
    <div>
    <s:hasRole name="普通用户">
        <a href="#">普通用户</a>
    </s:hasRole>
    </div>
    <div>
        <s:hasRole name="高级用户">
            <a href="<%=request.getContextPath()%>/user/gj">高级用户</a>
        </s:hasRole>
    </div>
    <div>
        <s:hasRole name="管理员">
            <a href="#">管理员</a>
        </s:hasRole>
    </div>

    <div>
        <s:hasPermission name="书本查询">
            <h3>书本查询</h3>
        </s:hasPermission>
    </div>
</body>
</html>
