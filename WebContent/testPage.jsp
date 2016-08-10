<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="DBAccessor.DBUtil"%>
<%@ page import="model.Questions"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exam page</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript">
function getSlctd(radio_name){
	var oRadio = document.forms[0].elements[radio_name];
	   for(var i = 0; i < oRadio.length; i++)
	   {
	      if(oRadio[i].checked)
	      {
	    	  document.getElementById("optionSelected_ID").value = oRadio[i].value;
	    	  document.forms["testSubmittedAction"].submit();
	      }
	   }
}
</script>
</head>
<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<div id="logo_text">
					<h1>
						<a href="facultyhome.jsp">UCM<span class="logo_colour">COMP
								TOOL</span></a>
					</h1>
					<h1 style="float: right">Test</h1>
				</div>
			</div>
			<h3 style="float: right"><a href="login.html" style="color: white">Logout</a></h3>
			<div id="menubar">
				<ul id="menu">
					<li><a target="_self" href="studenthome.html">Home</a></li>
				</ul>
			</div>
		</div>
		<div id="content_header"></div>
		<div id="site_content"></div>
	</div>
	<div id="content">
		<form action="testSubmittedAction" method="post">
			<input type="hidden" value="" name="optionSelected_ID" id="optionSelected_ID"/>
			<div class="form_settings">
			<%
				Map<String,Object> attr = (Map<String,Object>) ActionContext.getContext().get("session");
				ArrayList<Questions> questionsList = (ArrayList)attr.get("questionsList");
				ArrayList<Questions> tempList=(ArrayList<Questions>)questionsList.clone();
				Questions qstn=questionsList.get(0);
			%> 
				<%=qstn.getQuestion()%><br>
				<INPUT TYPE="radio" NAME="qstnRadio" id="qstnRadio1" VALUE="A"><%=qstn.getOptiona() %><BR>
				<INPUT TYPE="radio" NAME="qstnRadio" id="qstnRadio2" VALUE="B"><%=qstn.getOptionb() %><BR> 
				<INPUT TYPE="radio" NAME="qstnRadio" id="qstnRadio3" VALUE="C"><%=qstn.getOptionc() %><BR>
				<INPUT TYPE="radio" NAME="qstnRadio" id="qstnRadio4" VALUE="D"><%=qstn.getOptiond() %><BR>
				<%
			tempList.remove(qstn);
			attr.put("questionsList",tempList);
			attr.put("correctAnswer",qstn.getAnswer());
			%>
				<h2>
					<button type="submit" onclick="getSlctd('qstnRadio')">Submit</button>
				</h2>
			</div>
		</form>
	</div>
</body>
</html>