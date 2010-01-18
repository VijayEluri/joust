<%@ page import="org.joust.model.PokerModel"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="model" scope="request" class="java.util.LinkedHashMap"/>
<head>
    <link href="/content/Site.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1>${helloMsg}</h1>
    <div id="main">
	    <table>
	        <tr>
	            <th>Game</th>
	            <th colspan="2" align="center">High Score</th>
	        </tr>
	  
	    <% for (Map game : (List<Map>)model.get("Games")) { %>
	        <tr>
	            <td><a href="<%= game.get("Link") %>"><%= game.get("Name") %></a></td>
	            <td><%= ((PokerModel)game.get("Obj")).getCards() %></td>
	            <td><%= game.get("HighScore") %></td>
	        </tr>
	    <% } %>
	    </table>
	 </div>
</body>