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
<%@ taglib prefix="s" uri="/struts-tags" %>  


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript">
</script>
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
					<h1 style="float: right">Add Course</h1>
				</div>
			</div>
			<!--<s:url id="url" action="logoutAction"></s:url>
			<s:a href="%{url}">Logout</s:a>
-->
			<h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
			<div id="menubar">
				<ul id="menu">
					<li><a target="_self" href="adminhomepage.html">Home</a></li>
					<li class="selected"><a target="_self" href="addcourse.jsp">Add
							Course</a></li>
					<li><a target="_self" href="adduser.jsp">Add User</a></li>
					<li><a target="_self" href="adddepartment.jsp">Add
							Department</a></li>
				</ul>
			</div>
		</div>
		<div id="content_header"></div>
		<div id="site_content"></div>
	</div>
	<div id="content">
		<h1>Courses</h1>
		<form action="addCourseAction" method="post">
			<div class="form_settings">

				<p>
					<label>Course Name<input type="text" name="cname"
						placeholder="Course Name" /></label>
				</p>
				<p>
					<label>Course Number<input type="text" name="cnum"
						placeholder="Course Number" /></label>
				</p>
				<p>
					<label>Select Instructor <select name="instr">  
           			<%
                //Pulls the ids and decriptions from the codes table and stores them in the first drop down
                try
                {
                	Map<String, Object> attr = (Map<String, Object>) ActionContext
        					.getContext().get("session");
                	Connection conn = new DBUtil().getConnection();
        			Statement stmt = conn.createStatement();
        			String query = "select fname,lname from faculty where university='"+attr.get("universityOfLoggedUser").toString()+"'";
        			ResultSet rs = stmt.executeQuery(query);
        			while (rs.next()) {
        				 %>
                         <option value="<%=rs.getString("fname")%>"><%=rs.getString("fname")%> <%=rs.getString("lname")%></option>  
                     <%
        			}
        		} catch (SQLException e) {
        			System.out.println("Exception while adding course."
        					+ e.getMessage());
        			e.printStackTrace();
        		}  
            %>
                        </select>  
					</label>
				</p>
				<p>
					<label>Course Outcomes<input type="text" name="coutc"
						placeholder="Course Outcomes" /></label>
				</p>
				<p>
					<button type="submit">Add</button>
				</p>
			</div>
		</form>
	</div>
</body>
</html>