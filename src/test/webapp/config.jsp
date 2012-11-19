<%@ page import="org.joust.ConfigServlet" %>
<html>
<head>
<style>
label {
  font-size: 26px;
  font-weight: bold;
}
input {
  font-size: 26px;
  color: blue;
}
textarea {
  font-size: 18px;
  color: blue;
}
.button {
  text-align: center;
}
button {
  font-size: 32px;
}
.readonly {
  border: 0;
}
</style>
</head>
<body>
 <form action="/config" method="post">
  <label for="jsp">JSP filepath</label><br />
  <input type="text" name="jsp" size="80" value="${jsp}" /><br />
 	<label for="tags">Tags directory</label><br />
  <input type="text" name="tags" size="80" value="${tags}" /><br />
  <label for="head">URL of page with styling</label><br />
  <input type="text" name="head" size="80" value="${head}" /><br />
  <br />
  <button type="submit">Save</button>
  <hr />
  <label for"yaml">Data for JSP in YAML</label>
    (File stored as ${yamlFilepath})
  <br />
  <textarea name="yaml" rows="20" cols="120"></textarea>
 </form>
</body>
</html>