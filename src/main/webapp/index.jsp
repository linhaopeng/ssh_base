<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="common/common.jsp" %>
 </head>
  
  <body>
  <script type="text/javascript">
  $(function(){
	  $('#dd').dialog({
		    title: 'My Dialog',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: 'get_content.php',
		    modal: true
		});
  })
  </script>
   <div id="dd">Dialog Content.</div>
  </body>
</html>
