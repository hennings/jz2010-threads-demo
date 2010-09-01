<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

This is the list!

<table>
    <c:forEach var="t" items="${r}">
        <tr>
            <td>(${t.id})</td>
            <td><c:out value="${t.name}"/></td>
            <td>EUR ${t.price}</td>
        </tr>
    </c:forEach>
</table>