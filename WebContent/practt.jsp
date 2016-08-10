<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student practice test</title>
  <link rel="stylesheet" type="text/css" href="style.css" />
    <%@page import="com.opensymphony.xwork2.ActionContext" %>
	<%@page import="java.util.Map" %>
	<%@ page import="java.sql.Connection" %>
	<%@ page import="java.sql.Statement" %>
	<%@ page import="java.sql.ResultSet" %>
	<%@ page import="java.sql.SQLException" %>
	<%@ page import="DBAccessor.DBUtil" %>
</head>
<body>
 <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <h1><a href="studenthome.html">UCM<span class="logo_colour">COMP TOOL</span></a></h1><h1 style="float:right">Practice Tests</h1>
        </div>
      </div>
      <h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
	  <div id="menubar">
        <ul id="menu">
          <li ><a target="_self" href="studenthome.html">Home</a></li>
          <li class="selected"><a target="_self" href="practt.jsp">Practice Tests</a></li>
          <li><a target="_self" href="testt.jsp">Tests</a></li>
        
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      
      </div>
      </div>
      <div id="content">
        <h1>Practice Tests</h1>
		<form action="takePracticeTestAction" method="post">			  
			  <div class="form_settings">
				
				<p><label>Select Course
				<select name="courseSelected" id="courseSelected">
				<%
                //Pulls the ids and decriptions from the codes table and stores them in the first drop down
                try
                {
                	Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
                	Connection conn = new DBUtil().getConnection();
        			Statement stmt = conn.createStatement();
        			String query = "select coursename from questions where university='"+attr.get("universityOfLoggedUser").toString()+"'";
        			ResultSet rs = stmt.executeQuery(query);
        			while (rs.next()) {
        				 %>
                         <option value="<%=rs.getString("coursename")%>"><%=rs.getString("coursename")%></option>  
                     <%
        			}
        			attr.put("isPracticeTest","true");
        		} catch (SQLException e) {
        			System.out.println("Exception while adding course."
        					+ e.getMessage());
        			e.printStackTrace();
        		}  
            %>
				</select></label></p>
			 	  <p> <button type="submit">Submit</button></p>	
			  </div>			  			  
		</form>
      </div>
</body>
</html>