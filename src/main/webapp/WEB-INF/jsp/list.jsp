<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" type="text/css" media="all" />
</head>
<body>

<div class="box">
    <% DateFormat sdf = SimpleDateFormat.getTimeInstance(); %>
<h2>Showing latest relevant products at <%= sdf.format(new java.util.Date()) %></h2>

<table>
    <c:forEach var="t" items="${r}">
        <tr>
            <td>(${t.id})</td>
            <td><a href="/app/detail/${t.id}"><c:out value="${t.name}"/></a></td>
            <td>EUR ${t.price}</td>
        </tr>
    </c:forEach>
</table>
    </div>

</body>
</html>
