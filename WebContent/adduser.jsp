<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DBAccessor.DBUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add user page</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="main">
		<div id="header">
			<div id="logo">
				<div id="logo_text">
					<h1>
						<a href="adminhomepage.html">UCM<span class="logo_colour">COMP
								TOOL</span></a>
					</h1>
					<h1 style="float: right">Add User</h1>
				</div>
			</div>
			<h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
			<div id="menubar">
				<ul id="menu">
					<li><a target="_self" href="adminhomepage.jsp">Home</a></li>
					<li><a target="_self" href="addcourse.jsp">Add Course</a></li>
					<li class="selected"><a target="_self" href="adduser.jsp">Add
							User</a></li>
					<li><a target="_self" href="adddepartment.jsp">Add
							Department</a></li>
				</ul>
			</div>
		</div>
		<div id="content_header"></div>
		<div id="site_content"></div>
	</div>
	<div id="content">
		<h1>Registration</h1>
		<form action="addUserAction" method="post">
			<div class="form_settings">
				<p>
					<label>First Name<input type="text" name="fname"
						placeholder="First Name" /></label>
				</p>
				<p>
					<label>Last Name<input type="text" name="lname"
						placeholder="Last name" /></label>
				</p>
				<p>
					<label>Email<input type="email" name="email"
						placeholder="example@ucmo.edu" /></label>
				</p>

				<p>
					<label>Role <select name="role" id="role">
							<option value="faculty">Faculty</option>
							<option value="student">Student</option>
					</select>
					</label>
				</p>

				<p>
					<label>Department <select name="department" id="department">
					<%
                //Pulls the ids and decriptions from the codes table and stores them in the first drop down
                try
                {
                	Map<String, Object> attr = (Map<String, Object>) ActionContext
        					.getContext().get("session");
                	Connection conn = new DBUtil().getConnection();
        			Statement stmt = conn.createStatement();
        			String query = "select dname from department where university ='"+attr.get("universityOfLoggedUser").toString()+"'";
        			ResultSet rs = stmt.executeQuery(query);
        			while (rs.next()) {
        				 %>
                         <option value="<%=rs.getString("dname")%>"><%=rs.getString("dname")%></option>  
                     <%
        			}
        		} catch (SQLException e) {
        			System.out.println("Exception while adding user."
        					+ e.getMessage());
        			e.printStackTrace();
        		}  
            %>
					</select>
					</label>
				</p>
				<p>
					<button type="submit">Register</button>
				</p>
			</div>
		</form>
	</div>
</body>
</html>