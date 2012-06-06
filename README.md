[To run]
=======
 - mvn test jetty:run
 
Now you can test any JSP behind the /joust/ path and by specifying a data YAML file.    
For an example, browse to:
 <pre>
 http://localhost:8101/<b>joust</b>/people/list.jsp<b>?yaml=people.yaml</b>
 </pre>