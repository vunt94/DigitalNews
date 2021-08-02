<%-- 
    Document   : SearchResultPage
    Created on : May 12, 2021, 9:40:05 AM
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">                
                    <c:forEach items="${list}" var="x">
                        <div class="tittle">
                            <a href="DetailController?id=${x.id}">      
                                ${x.title}
                            </a>
                        </div>
                        <div class="image_search">
                            <img src="img/${x.image}" alt=""/>
                        </div>
                        <div class="text_search">
                            ${x.shortDes}
                        </div>
                        <br>
                    </c:forEach>
                    <div class="paging">
                        <c:if test="${maxPage < 1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage > 1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==index?"active":""}" href="SearchController?index=${i}&searchContent=${content}">${i}</a>
                            </c:forEach>
                        </c:if>
                    </div>
                </div> 
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
