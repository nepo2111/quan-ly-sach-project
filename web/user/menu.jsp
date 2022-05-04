<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/5/2022
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="model.NguoiDung"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Book Store Template, Stanford - Day lap trinh" />
    <meta name="description" content="Book Store Template, Stanford - Day lap trinh" />

    <link href="<c:url value="/css/templatemo_style.css"/>" rel="stylesheet" type="text/css" />
</head>
<% NguoiDung username = (NguoiDung)request.getSession().getAttribute("user"); %>
<div id="templatemo_menu">
    <ul>
        <li><a href="home" class="current">Home</a></li>
        <li><a href="cartlist">Cart</a></li>
        <li><a href="#">Hotline</a></li>
        <%
            if (username != null) {
        %>
            <li><a href="logout?name=user">Logout</a></li>  
            <%} else {%>
        <li><a href="login">Login</a></li> 
        <%}%>

    </ul>
</div>
