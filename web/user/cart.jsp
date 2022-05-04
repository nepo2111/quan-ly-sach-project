<%@page import="model.NguoiDung"%>
<%@page import="model.Item"%>
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
    </head>
    <%
        ArrayList<ChuDe> lstChuDe = (ArrayList<ChuDe>) request.getAttribute("lstChuDe");
        ArrayList<Item> lstItems = (ArrayList<Item>) request.getAttribute("lstItems");
    %>
    <body>
        <div id="templatemo_container">
            <% NguoiDung username = (NguoiDung)request.getSession().getAttribute("user"); %>
<div id="templatemo_menu">
    <ul>
        <li><a href="home" >Home</a></li>
        <li><a href="cartlist" class="current">Cart</a></li>
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

            <%@ include file="header.jsp" %>
            <!-- end of header -->

            <div id="templatemo_content">

                <div id="templatemo_content_left">
                    <div class="templatemo_content_left_section">
                        <h1>Danh mục chủ đề</h1>

                        <ul>
            <%
                for(ChuDe c : lstChuDe) {
            %>
            <li><a href="search?maChuDe=<%=c.getMaChuDe()%>"><%=c.getTenChuDe()%></a></li>
            
            <%}%>
            <li><a href="home">Tất cả</a></li>
        </ul>
                    </div>
                    <div class="templatemo_content_left_section">
                        <h1>Sách bán chạy</h1>
        <ul>
            <li><a href="view?maSach=LTOR">Lập trình Oracle</a></li>
            <li><a href="view?maSach=LTJV">Lập trình Java</a></li>
        </ul>
                    </div>

                    <div class="templatemo_content_left_section">
                        <a href="http://validator.w3.org/check?uri=referer"><img style="border:0;width:88px;height:31px" src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Transitional" width="88" height="31" vspace="8" border="0" /></a>
                        <a href="http://jigsaw.w3.org/css-validator/check/referer"><img style="border:0;width:88px;height:31px"  src="http://jigsaw.w3.org/css-validator/images/vcss-blue" alt="Valid CSS!" vspace="8" border="0" /></a>
                    </div>
                </div>
                <div id="templatemo_content_right" style="padding-left: 10px">

                    <%
                        if (lstItems.size() > 0) {
                            for (Item i : lstItems) {

                    %>

                    <div style="display: flex">
                        <img src="images/<%=i.getSach().getAnhSach()%>" alt="image" width="80px" height="100px"/>
                        <ul>
                            <li>Tên sách: <a href="#"><%=i.getSach().getTenSach()%></a></li>
                            <li>Giá sách: <fmt:formatNumber value="<%=i.getGiaSach()%>" type="number"/></li>
                            <li>Số lượng: <%=i.getSoLuong()%></li>
                            <li>Đơn giá: <%=i.getSoLuong() * i.getGiaSach()%></li>
                            <li></li>
                            <form action="deleteitem?ma=<%=i.getSach().getMaSach()%>" method="post">
                                <input type="submit" value="Xóa"/>
                            </form>
                        </ul>
                    </div>


                    <%}%>
                    <hr/>
                    <div class="detail_button"><a href="order">Thanh toán</a></div>
                    <%} else {%>

                    <h1 style="padding-left: 200px">There are no products in the cart</h1>
                    <%}%>
                    <div class="cleaner_with_height">&nbsp;</div>

                    <a href="subpage.html"><img src="user/images/templatemo_ads.jpg" alt="ads" /></a>
                    <div class="cleaner_with_height">&nbsp;</div>
                </div> <!-- end of content right --><!-- end of content right -->


            </div> <!-- end of content -->

            <%@ include file="footer.jsp" %> 
        </div> <!-- end of container -->
        <div align=center>This template  downloaded form <a href="#">fpt.edu.vn</a></div></body>
</html>