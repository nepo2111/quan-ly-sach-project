<%@page import="model.ChuDe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Sach"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>View Book</title>
        <meta name="keywords" content="Book Store" />
        <meta name="description" content="Book Store" />
        <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
    </head>
    <%
        ArrayList<ChuDe> lstChuDe = (ArrayList<ChuDe>)request.getAttribute("lstChuDe");
        Sach s = (Sach)request.getAttribute("sach");
    %>
<body>
<!--  Templates from www.stanford.com.vn -->
<div id="templatemo_container">
	 <%@ include file="menu.jsp" %>

            <%@ include file="header.jsp" %>
    
    <div id="templatemo_content">
    	
        <div id="templatemo_content_left">
        	<div class="templatemo_content_left_section">
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
        </div> <!-- end of content left -->
        
        <div id="templatemo_content_right">
        
            <h1><%=s.getTenSach()%> <span>(<%=s.getTacGia()%>)</span></h1>
            <div class="image_panel"><img src="images/<%=s.getAnhSach()%>" alt="CSS Template" width="100" height="150" /></div>
          <h2><%=s.getTenSach()%></h2>
            <ul>
	            <li>Tác giả <a href="#"><%=s.getTacGia()%></a></li>
                <li><%=s.getNgayTao()%></li>
            	<li>Chủ đề: <%=s.getChuDe().getTenChuDe()%></li>
                <li>Giá: <fmt:formatNumber value="<%=s.getGiaSach()%>" type="number"/> | SL: <%=s.getSoLuong()%></li>
            </ul>           
            <p><%=s.getMoTa()%></p>

		
            
             <div class="cleaner_with_height">&nbsp;</div>
  
            <a href="subpage.html"><img src="user/images/templatemo_ads.jpg" alt="ads" /></a>
            
        </div> <!-- end of content right -->
    
    	<div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->
    
    <%@ include file="footer.jsp" %> 
</div> <!-- end of container -->
<div align=center>This template  downloaded form <a href='http://www.stanford.com.vn'>Stanford.com.vn</a></div></body>
</html>