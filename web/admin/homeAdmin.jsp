<%-- 
    Document   : body
    Created on : Mar 11, 2022, 12:44:50 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Sach"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
    <meta name="description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <title>Book Store - Admin</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
    <%
        ArrayList<Sach> lstSach = (ArrayList<Sach>) request.getAttribute("lstSach");
    %>
    <body class="app sidebar-mini rtl">
<!--        Header-->
        <%@ include file="header.jsp" %>
<!--        End Header-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<!--    Sidebar-->
        <%@ include file="sidebar.jsp" %>
<!--End Sidebar-->
<main class="app-content">
        <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Book Management</h1>
            <p>Manage information about books in the system</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item">Bookstore</li>
            <li class="breadcrumb-item active"><a href="homeAdmin">Book Management</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div style="width: 100%; text-align: right; margin-bottom: 5px">
                <a class="btn btn-primary" href="addbook">Add new books</a>
            </div>
            <div class="tile">
                <div class="tile-body">
                    <table class="table table-hover table-bordered" id="sampleTable">
                        <thead>
                        <tr>
                            <th>Image</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Date created</th>
                            <th>Author:</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Sach s : lstSach) {
                            %>
                                <tr>
                                    <td><img src="images/<%=s.getAnhSach()%>" width="80px" height="80px"/></td>
                                    <td><%=s.getMaSach()%></td>
                                    <td><%=s.getTenSach()%></td>
                                    <td><%=s.getSoLuong()%></td>
                                    <td><%=s.getMoTa()%></td>
                                    <td><%=s.getGiaSach()%></td>
                                    <td><%=s.getNgayTao()%></td>
                                    <td><%=s.getTacGia()%></td>
                                    <td><a class="btn btn-xs btn-info" href="update?ma=<%=s.getMaSach()%>"><i class="ace-icon fa fa-pencil bigger-120"></i></a>
                                        &nbsp;
                                        <a class="btn btn-xs btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa thông tin này ?');" href="delete?ma=<%=s.getMaSach()%>"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>
                                    </td>
                                </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </main>
<!--    footer-->
    <%@ include file="footer.jsp" %>
<!--End footer-->
    </body>
</html>
