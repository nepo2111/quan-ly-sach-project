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
        ArrayList<ChuDe> lstChuDe = (ArrayList<ChuDe>)request.getAttribute("lstChuDe");
        ArrayList<Sach> lstSach = (ArrayList<Sach>)request.getAttribute("lstSach");
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

                
                <div id="templatemo_content_right">
                    <form name="f" action="" method="post">
                        <h1>Số lượng: &nbsp;<input type="number" id="iSoLuong" name="txtSoLuong" value="1" class="buy_now_button"/></h1>
                    <% int i = 0;%>
                    <%
                        for (Sach s : lstSach) {
                    %>
                            <div class="templatemo_product_box">
                                <h1><%=s.getTenSach()%>  <span>(<%=s.getTacGia()%>)</span></h1>
                                <img src="images/<%=s.getAnhSach()%>" alt="image" width="80px" height="100px"/>
                                <div class="product_info">
                                    <p><%=s.getMoTa()%><br/>(SL:<%=s.getSoLuong() <= 0? 0:s.getSoLuong()%>)</p>
                                    <h3><fmt:formatNumber value="<%=s.getGiaSach()%>" type="number"/></h3>
                                    <%
                                        if (s.getSoLuong() <= 0) {
                                    %>
                                    <div class="buy_now_button"><input type="submit" value="Sold out" class="btn btn-primary" disabled/></div>    
                                    <%}else {%>
                                    <div class="buy_now_button"><input onclick="buy('<%=s.getMaSach()%>','<%=s.getSoLuong()%>')" type="submit" value="Add To Cart" class="btn btn-primary"/></div>              
                                    <%}%>
                                    <div class="detail_button"><a href="view?maSach=<%=s.getMaSach()%>">Details</a></div>
                                </div>
                                <div class="cleaner">&nbsp;</div>
                            </div>

                            <%
                                if (i % 2 == 0) {
                            %>
                            <div class="cleaner_with_width">&nbsp;</div>
                            <%} else {%>

                            <div class="cleaner_with_height">&nbsp;</div>
                            <%}%>
                            <%i++;%>
                            <%}%>


               

                    <a href="subpage.html"><img src="user/images/templatemo_ads.jpg" alt="ads" /></a>
                    </form>
                </div> <!-- end of content right -->

                <div class="cleaner_with_height">&nbsp;</div>
            </div> <!-- end of content -->

            <%@ include file="footer.jsp" %> 
        </div> <!-- end of container -->
        <div align=center>This template  downloaded form <a href="#">fpt.edu.vn</a></div></body>
</html>
        
        <script type="text/javascript">
            function buy(masach, soluong) { 
                var q = document.getElementById("iSoLuong").value;
                if (q > soluong) {
                    alert("Chỉ có thể mua tối đa " + soluong + " quyển sách");
                  
                }else {
                   document.f.action="cart?ma="+masach;
                    document.f.submit(); 
                }               
            }
        </script>