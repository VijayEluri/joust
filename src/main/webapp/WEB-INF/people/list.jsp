Hello World!
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
  <c:forEach var="person" items="${people}">
    <li>${person.name}</li>
  </c:forEach>
</ul>
<ul>
  <c:forEach var="place" items="${places}">
    <li>${place.latitude},${place.longitude}</li>
  </c:forEach>
</ul>
<a href="/something.do">do something</a>