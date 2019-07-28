<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <%@ include file="/login.html" %>


<%Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for (Cookie c : cookies) {
            if (c.getName().equals("name") && !c.getValue().equals("")) {
                response.sendRedirect("inChat");}}}%>