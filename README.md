[To run]
=======
 - mvn test joust:run
 
Now you can test any JSP behind the /joust/ path and by specifying a data YAML file.    
For an example, browse to:
 <pre>
 http://localhost:8101/<b>joust</b>/people/list.jsp<b>?yaml=people.yaml</b>
 </pre>
 
[To use]
 - Merge this src/test folder with the src/test folder in your project.
 - Merge this pom.xml with the pom.xml in your project. 
 - You do not need src/main - that's just for the example.