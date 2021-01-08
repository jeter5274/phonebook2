<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>

<%@ page import="com.javaex.vo.PersonVo" %>
<%
	//리턴값이 Object이므로, List로 강제형변환을 시켜줌
	List<PersonVo> personList = (List<PersonVo>)(request.getAttribute("pList"));
	//System.out.println("===List.jsp===");
	//System.out.println(personList);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>전화번호 리스트</h1>
		
		<p>입력한 정보 내역입니다.</p>
		
		<% for(int i=0; i<personList.size();i++){ %>
			<table border="1">
				<tr>
					<td>이름(name)</td>
					<td>
						<%= personList.get(i).getName() %>
						(<%= personList.get(i).getPersonId() %>)
					</td>
				</tr>
				<tr>
					<td>핸드폰(hp)</td>
					<td><%= personList.get(i).getHp() %></td>
				</tr>
				<tr>
					<td>회사(company)</td>
					<td><%= personList.get(i).getCompany() %></td>
				</tr>
				<tr>
					<td><a href="/phonebook2/pbc?action=uform&id=<%=personList.get(i).getPersonId()%>">[수정]</a></td>
					<td><a href="/phonebook2/pbc?action=delete&id=<%=personList.get(i).getPersonId()%>">[삭제]</a></td>
				</tr>
			</table>
			<br>
		<% } %>
		<br>
		<a href="/phonebook2/pbc?action=wform">추가번호 등록</a>
	</body>
</html>