<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UCM COMP TOOL</title>
  <link rel="stylesheet" type="text/css" href="style.css" />

<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.Map"%>
  
</head>
<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <h1><a href="adminhomepage.html">UCM<span class="logo_colour">COMP TOOL</span></a></h1><h1 style="float:right">Add Department</h1>
          <h2>UCM comp tool helps</h2>
        </div>
      </div>
      <h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
	  <div id="menubar">
        <ul id="menu">
          <li><a target="_self" href="adminhomepage.jsp">Home</a></li>
          <li><a target="_self" href="addcourse.jsp">Add Course</a></li>
          <li><a target="_self" href="adduser.jsp">Add User</a></li>
          <li class="selected"><a target="_self" href="adddepartment.jsp">Add Department</a></li>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      
      </div>
      </div>
      <div id="content">
        <h1>Department</h1>
		<form action="addDeptAction" method="post">			  
			  <div class="form_settings">
                 <p> <label>Department Name<input type="text" name="dname"  placeholder = "Department Name"/></label></p>
				  <p><label>Department Head<input type="text" name="dhead"  placeholder = "Dept Head"/></label></p>
				  <p><label>Email<input type="email" name="demail"  placeholder = "email"/></label></p>
			 <p> <button type="submit">Add</button></p>	
			  </div>			  			  
			  </form>
        </div>
</body>
</html>