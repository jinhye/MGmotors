<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Search</title>
</head>
<body>
	<%@ page import="java.util.ArrayList, mgCar.CarEntity" %>

	<h2>Car View</h2>

	<hr size="5" color="red">
	<center>
		<h2>MG CAR STOCKS</h2>
		
		<jsp:useBean id="cardb" class="mgCar.CarDBCP" scope="page" />
		
		<%
			ArrayList<CarEntity> list = cardb.getCarList();
			int counter = list.size();
			
			if(counter > 0){
		%>
		<table width="100%" border="2" cellpadding="1">
			<tr>
				<th align="center"><b>VIN</b></th>
				<th align="center"><b>BRAND</b></th>
				<th align="center"><b>MODEL</b></th>
				<th align="center"><b>MILEAGE</b></th>
			</tr>
			<%
				for(CarEntity ce : list){
			%>
			<tr>
				<td align="center"><%=ce.getVin() %></td>
				<td align="center"><%=ce.getBrand() %></td>
				<td align="center"><%=ce.getModel() %></td>
				<td align="center"><%=ce.getMileage() %></td>
			</tr>
			<%
				}
			%>
		
		</table>
		<%
			}
		%>
	</center>
		
	<hr size="5" color="green">
	<p>
	<hr>
	The number of cars are
	<font size="3" color="green"> <%=counter %></font>
	<hr size="5" color="green">
</body>
</html>