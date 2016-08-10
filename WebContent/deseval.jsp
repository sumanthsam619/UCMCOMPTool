<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Design evaluation page</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<%@ taglib prefix="s" uri="/struts-tags" %>  
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
					<h1>
						<a href="facultyhome.html">UCM<span class="logo_colour">COMP
								TOOL</span></a>
					</h1>
					<h1 style="float: right">Design Evaluation</h1>
					<h2>UCM Comp tool helps</h2>
				</div>
			</div>
			<h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
			<div id="menubar">
				<ul id="menu">
					<li><a target="_self" href="facultyhome.jsp">Home</a></li>
					<li class="selected"><a target="_self" href="deseval.jsp">Design
							Evaluation</a></li>
					<li><a target="_self" href="viewrep.jsp">View reports</a></li>

				</ul>
			</div>
		</div>
		<div id="content_header"></div>
		<div id="site_content"></div>
	</div>
	<div id="content">
		<h1>Design Evaluation</h1>
		<form action="addEvalAction" method="post">
			<div class="form_settings">
				<h2>
					<label>Select Course 
					<select id="courseSelected" name="courseSelected">
			<%
			
			try
                {
					Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
					Connection conn = new DBUtil().getConnection();
        			Statement stmt = conn.createStatement();
        			String query = "select coursename from faculty where email='"+attr.get("uname")+"' and university='"+attr.get("universityOfLoggedUser").toString()+"'";
        			ResultSet rs = stmt.executeQuery(query);
        			while (rs.next()) {
        	%>
                         <option value="<%=rs.getString("coursename")%>"><%=rs.getString("coursename")%></option>  
            <%
        			}
        		} catch (SQLException e) {
        			System.out.println("Exception while adding course."
        					+ e.getMessage());
        			e.printStackTrace();
        		}  
        		
        	%>
					</select><br>
					</label> Question
					<textarea rows="4" cols="50" placeholder="Question" id="question" name="question"></textarea><br>
					Option A<br><input type="text" placeholder="Option A" id="optiona" name="optiona"><br>
					Option B<br><input type="text" placeholder="Option B" id="optionb" name="optionb"><br>
					Option C<br><input type="text" placeholder="Option C" id="optionc" name="optionc"><br>
					Option D<br><input type="text" placeholder="Option D" id="optiond" name="optiond"><br>
					Correct Option<br><input type="text" name="correctOption" id="correctOption" placeholder="Correct Option"><br>
				</h2>
				<h2>
					<button type="submit">Add Question</button>
				</h2>
			</div>
		</form>
	</div>
</body>
</html>