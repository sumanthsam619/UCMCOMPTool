<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Student Reports</title>
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
          <h1><a href="facultyhome.html">UCM<span class="logo_colour">COMP TOOL</span></a></h1><h1 style="float:right">View Reports</h1>
          <h2>UCM comp tool helps</h2>
        </div>
      </div>
      <h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
	  <div id="menubar">
        <ul id="menu">
          <li ><a target="_self" href="facultyhome.jsp">Home</a></li>
          <li ><a target="_self" href="deseval.jsp">Design Evaluation</a></li>
          <li class="selected"><a target="_self" href="viewrep.jsp">View Reports</a></li>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      Below are the details of the students, marks obtained.
      </div>
      </div>
      <div id="content">
      <TABLE>
		<% 
		 try
        {
			double cntStuds=0,cntMarks=0;
			
			 Map<String, Object> attr = (Map<String, Object>) ActionContext
 					.getContext().get("session");
			 Connection conn = new DBUtil().getConnection();
			Statement stmt = conn.createStatement();
			String query = "select fname,lname,marks from student where university='"+attr.get("universityOfLoggedUser").toString()+"'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				cntStuds+=1;
		 %>
    <TR><TD><%=rs.getString("fname")%><%=rs.getString("lname")%></TD>
    <TD><%=rs.getString("marks")%></TD>
    </TR>
    
     <%
      if(rs.getString("marks")==null)
    	 cntMarks+=0;
     else
     cntMarks=cntMarks+Double.parseDouble(rs.getString("marks"));}%>
     </TABLE>
     Average marks of all students:
     <%=cntMarks/cntStuds%>
     <% 
     query = "update university set average=? where universityname='"+attr.get("universityOfLoggedUser").toString()+"'";
		PreparedStatement prepared = conn.prepareStatement(query);
		prepared.setString(1,String.valueOf(cntMarks/cntStuds));
		int count = prepared.executeUpdate();
		prepared.close();
		conn.close();
			}
      catch (SQLException e) {
			System.out.println("Exception in viewrep.jsp"
					+ e.getMessage());
			e.printStackTrace();
		}   %> 

      </div>
</body>
</html>