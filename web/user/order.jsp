<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="model.NguoiDung"%>
<%@page import="model.Item"%>
<%@page import="model.Cart"%>
<%@page import="model.Sach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ChuDe"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Book Store</title>
        <meta name="keywords" content="Book Store" />
        <meta name="description" content="Book Store" />
        <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
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
                                                                                Cart cart = (Cart) request.getAttribute("cart");
                                                                                NguoiDung user = (NguoiDung) request.getAttribute("user");
                                                                                Date date = (Date) request.getAttribute("date");
                                                                                DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                                                                            %>
                                                                            <body>
                                                                                <div id="templatemo_container">
                                                                                    <%@ include file="menu.jsp" %>

                                                                                    <%@ include file="header.jsp" %>
                                                                                    <!-- end of header -->

                                                                                    <div id="templatemo_content">

                                                                                        <div id="templatemo_content_left">
                                                                                            <div class="templatemo_content_left_section">
                                                                                                <h1>Danh mục chủ đề</h1>

                                                                                                <ul>
                                                                                                    <%
                                                                                                        for (ChuDe c : lstChuDe) {
                                                                                                    %>
                                                                                                    <li><a href="search?maChuDe=<%=c.getMaChuDe()%>"><%=c.getTenChuDe()%></a></li>

                                                                                                    <%}%>
                                                                                                    <li><a href="home">Tất cả</a></li>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <div class="templatemo_content_left_section">
                                                                                                <h1>Sách bán chạy</h1>
                                                                                                <ul>
                                                                                                    <li><a href="#">Vestibulum ullamcorper</a></li>
                                                                                                    <li><a href="#">Maece nas metus</a></li>
                                                                                                    <li><a href="#">In sed risus ac feli</a></li>
                                                                                                    <li><a href="#">Praesent mattis varius</a></li>
                                                                                                    <li><a href="#">Maece nas metus</a></li>
                                                                                                    <li><a href="#">In sed risus ac feli</a></li>
                                                                                                </ul>
                                                                                            </div>

                                                                                            <div class="templatemo_content_left_section">
                                                                                                <a href="http://validator.w3.org/check?uri=referer"><img style="border:0;width:88px;height:31px" src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Transitional" width="88" height="31" vspace="8" border="0" /></a>
                                                                                                <a href="http://jigsaw.w3.org/css-validator/check/referer"><img style="border:0;width:88px;height:31px"  src="http://jigsaw.w3.org/css-validator/images/vcss-blue" alt="Valid CSS!" vspace="8" border="0" /></a>
                                                                                            </div>
                                                                                        </div>


                                                                                        <div id="templatemo_content_right">
                                                                                            <form action="order" method="post">
                                                                                                <fieldset>
                                                                                                    <legend>Thanh toán</legend>
                                                                                                    <hr/>
                                                                                                    <div class="container-fluid">
                                                                                                        <div class="row">
                                                                                                            <label class="col-md-2">Họ tên: </label>
                                                                                                            <div class="col-md-10">
                                                                                                                <input class="form-control" type="text" name="txtHoTen" value="<%=user.getHoTen()%>"/>
                                                                                                                <input type="hidden" name="hUserName" value="<%=user.getUserName()%>"/>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="row">
                                                                                                            <label class="col-md-2">Điện thoại: </label>
                                                                                                            <div class="col-md-10">
                                                                                                                <input class="form-control" type="text" name="txtDienThoai" value="<%=user.getDienThoai()%>"/>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="row">
                                                                                                            <label class="col-md-2">Địa chỉ: </label>
                                                                                                            <div class="col-md-4">
                                                                                                                <input class="form-control" type="text" name="txtDiaChi" value="<%=user.getDiaChi()%>"/>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="row">
                                                                                                            <label class="col-md-2">Email: </label>
                                                                                                            <div class="col-md-4">
                                                                                                                <input class="form-control" type="text" name="txtEmail" value="<%=user.getEmail()%>"/>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <hr/>
                                                                                                        <div class="row">
                                                                                                            <label class="col-sm-2">Sách: </label>
                                                                                                            <div class="col-md-5">
                                                                                                                <%
                                                                                                                    for (Item i : cart.getItems()) {
                                                                                                                %>                                       
                                                                                                                <img src="images/<%=i.getSach().getAnhSach()%>" width="70px" height="90px"/>&nbsp;(x<%=i.getSoLuong()%>)&nbsp;
                                                                                                                <%}%>
                                                                                                            </div>  
                                                                                                        </div>
                                                                                                        <div class="row">
                                                                                                            <label class="col-md-2">Ngày tạo: </label>
                                                                                                            <div class="col-md-10">
                                                                                                                <input class="form-control" type="text" name="txtNgayTao" value="<%=dfm.format(date)%>"/>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="row">
                                                                                                            <label class="col-md-2">Đơn giá: </label>
                                                                                                            <div class="col-md-10">

                                                                                                                <div class="input-group">
                                                                                                                    <div class="input-group-prepend"><span class="input-group-text">$</span></div>
                                                                                                                    <input class="form-control" id="exampleInputAmount" type="number" placeholder="Amount" value="<fmt:formatNumber value="${cart.getTotalMoney()}" type="number"/>"/>
                                                                                                                    <div class="input-group-append"><span class="input-group-text">.00</span>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>





                                                                                                        <hr/>
                                                                                                        <div class="row">
                                                                                                            <div class="col-md-2"></div>
                                                                                                            <div class="col-md-5">
                                                                                                                <a href="cartlist" class="btn btn-danger">Quay lại</a>
                                                                                                                <input type="submit" value="Thanh toán" class="btn btn-primary"/>
                                                                                                            </div>
                                                                                                            <div class="row">
                                                                                                            </div>
                                                                                                            <div class="row">
                                                                                                                <div class="col-md-2"></div>
                                                                                                                <div class="col-md-10">
                                                                                                                    <span class="text-warning">${mess}</span><br/>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                </fieldset>
                                                                                            </form>
                                                                                        </div> <!-- end of content right -->

                                                                                        <div class="cleaner_with_height">&nbsp;</div>
                                                                                    </div> <!-- end of content -->

                                                                                    <%@ include file="footer.jsp" %> 
                                                                                </div> <!-- end of container -->
                                                                                <div align=center>This template  downloaded form <a href="#">fpt.edu.vn</a></div></body>
                                                                            </html>
