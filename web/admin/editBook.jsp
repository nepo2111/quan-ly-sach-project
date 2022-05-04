<%-- 
    Document   : addBook
    Created on : Mar 11, 2022, 2:08:12 PM
    Author     : Admin
--%>

<%@page import="model.ChuDe"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm mới thông tin sách</title>
        <style type="text/css">

            body {
                background-color: #babccb;
            }

            .error {
                color: red;
            }

            .errorBlock {
                color: black;
                border: 1px solid #fc9191;
                background-color: #c45454;
            }

            .row {
                margin-bottom: .6em;
            }
        </style>
    </head>
    <%
        ArrayList<ChuDe> lstChuDe = (ArrayList<ChuDe>) request.getAttribute("lstChuDe");
        String maChuDe = request.getAttribute("maChuDe").toString();
    %>

    <body class="app sidebar-mini rtl">
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
                    <li class="breadcrumb-item active"><a href="#">Book Management</a></li>
                </ul>
            </div>
            <form action="update" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <div class="tile">
                            <div class="tile-body">
                                <fieldset>
                                    <legend>Book information</legend>
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-md-2">Id: </label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text" name="txtMaSach" value="${maSach}" disabled="disabled"/>
                                                <input class="form-control" type="hidden" name="hMaSach" value="${maSach}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-md-2">Name: </label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text" name="txtTenSach" value="${tenSach}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-md-2">Quantity: </label>
                                            <div class="col-md-4">
                                                <input class="form-control" type="text" name="txtSoLuong" value="${soLuong}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-md-2">Description: </label>
                                            <div class="col-md-10">

                                                <textarea class="form-control" name="txtMoTa" rows="5">${moTa}</textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-md-2">Image: </label>
                                            <div class="col-md-4">
                                                <input type="hidden" name="hAnh" value="${anhSach}"/>
                                                <input type="file" name="fUpload"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-md-2">Price: </label>
                                            <div class="col-md-4">
                                                <input class="form-control" type="text" name="txtGiaSach" value="${giaSach}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-sm-2">Author: </label>
                                            <div class="col-md-4">
                                                <input class="form-control" type="text" name="txtTacGia" value="${tacGia}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-md-2">Topic: </label>
                                            <div class="col-md-4">
                                                <select class="form-control" name="cboChuDe">
                                                    <option value="">---Select---</option>
                                                    <%
                                                        for (ChuDe c : lstChuDe) {
                                                    %>
                                                    <option value="<%=c.getMaChuDe()%>" <%=maChuDe.equals(c.getMaChuDe())? "selected":""%>><%=c.getTenChuDe()%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-2"></div>
                                            <div class="col-md-4">
                                                <input type="submit" value="Sửa sách" class="btn btn-primary">
                                                <a href="homeAdmin" class="btn btn-danger">Quay lại</a>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-2"></div>
                                            <div class="col-md-10">
                                                <span class="text-warning">${mess}</span><br>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
    </body>
</html>
