<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student actual test</title>
  <link rel="stylesheet" type="text/css" href="style.css" />
  
    <%@page import="com.opensymphony.xwork2.ActionContext" %>
	<%@page import="java.util.Map" %>
	
</head>
<body>
 <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <h1><a href="studenthome.html">UCM<span class="logo_colour">COMP TOOL</span></a></h1><h1 style="float:right">Tests</h1>
        </div>
      </div>
      <h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
	  <div id="menubar">
        <ul id="menu">
          <li ><a target="_self" href="studenthome.html">Home</a></li>
          <li ><a target="_self" href="practt.jsp">Practice Tests</a></li>
          <li class="selected"><a target="_self" href="testt.jsp">Tests</a></li>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      
      </div>
      </div>
      <div id="content">
        <h1>Tests</h1>
		<form action="finalTestTakeAction" method="post">			  
			  <div class="form_settings">
				You are about to take final test. If sure, please click continue button
			 	  <p> <button type="submit">Continue</button></p>	
			  </div>
			  <%
               	Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
			  	attr.put("isPracticeTest","false");
               %>
		</form>
      </div>
</body>
</html>