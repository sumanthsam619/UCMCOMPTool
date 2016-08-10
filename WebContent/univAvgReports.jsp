<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Average marks in universities</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DBAccessor.DBUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>

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
          <li ><a target="_self" href="superadminhome.html">Home</a></li>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      
      </div>
      </div>
      <div id="content">
		<TABLE>
		<% 
		 try
        {
			 Map<String, Object> attr = (Map<String, Object>) ActionContext
 					.getContext().get("session");
			 Connection conn = new DBUtil().getConnection();
			Statement stmt = conn.createStatement();
			String query = "select university, avg(cast (marks as float)) as average from student group by university";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
		 %>
    <TR><TD><%=rs.getString("university")%></TD>
    <TD><%=rs.getString("average")%></TD>
    </TR><%}%>
    </TABLE>
    <% 
     } 
		catch (SQLException e) {
			System.out.println("Exception in viewrep.jsp"
					+ e.getMessage());
			e.printStackTrace();
		}%>
      </div>
</body>
</html>