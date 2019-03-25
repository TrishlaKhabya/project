   <%@page import="model.Users"%>
<%@page import="model.Result"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
      <%@page import="model.Candidate" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="result_details" method="get" modelAttribute="user">
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>L0</th><th>L1</th><th>HR</th><th></th></tr>  
<%

//HttpSession session=request.getSession();
ArrayList<Candidate> li=(ArrayList<Candidate>)request.getAttribute("list");
ArrayList<Result> rec=new ArrayList();

for(int i=0;i<li.size();i++)
{
	int id=li.get(i).getId();
	String name=li.get(i).getFirstname()+""+li.get(i).getLastname();
	System.out.println("result:"+li.get(i).getId());
System.out.println(li.get(i).getFirstname());
%>
<tr><td><%= li.get(i).getId() %></td>
<td><%= li.get(i).getFirstname() %>&nbsp<%=li.get(i).getLastname() %></td>

<td><input type="checkbox" name="l0"></td>
<td><input type="checkbox" name="l1"></td>
<td><input type="checkbox" name="hr"></td>

<%	
	String l0=request.getParameter("l0");
String l1=request.getParameter("l1");
String hr=request.getParameter("hr");
Result res=new Result();
	res.setId(id);
	res.setName(name);
	res.setL0(l0);
	res.setL1(l1);
	res.setHr(hr);
	
	rec.add(res);
}
Users user=new Users();
user.setResult(rec);
System.out.println("Record is:"+user);
System.out.println("Record is:"+user.getResult().get(0).getL0());
%>
<td></td>
</tr>
</table>
</br>&nbsp
<a href="/HelloWeb/result_details/<%= rec%>">Update</a>
</form>
</body>
</html>