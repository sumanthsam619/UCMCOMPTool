<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.Map"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="login.css" />
<title>Test Results</title>
</head>
<body>
<div id="main">
		<div id="header">
		  <div id="logo">
			<div id="logo_text">
			  <h1><a href="main.html">UCM<span class="logo_colour">COMP TOOL</span></a></h1>
			</div>
		  </div>
		</div>
		<h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
	  </div>  
	 <div id="content">	
	  	  
		  <h2>You have completed your exam and results are as follows:</h2>
		  
		  <%
		  Map<String, Object> attr = (Map<String, Object>) ActionContext
				.getContext().get("session");
		%>
		<div id="content">
		<h3>No of questions attempted: </h3><%=attr.get("actualNoOfQuestions") %> 
		<h3>No of questions answered correctly: </h3><%=attr.get("answeredCorrectly") %></div>
		<a href="studenthome.html">Home page</a>
	  </div> 	
</body>
</html>