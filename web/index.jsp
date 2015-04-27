<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

  String x = "0";
  String y = "0";
  String r = "0";

  Boolean is_in_area = false;
  Boolean actuality = true;

  if(session.getAttribute("actuality") != null) {

    x = (String) session.getAttribute("x_val");
    y = (String) session.getAttribute("y_val");
    r = (String) session.getAttribute("r_val");

    is_in_area = (Boolean) session.getAttribute("is_in_area");
    actuality = (Boolean) session.getAttribute("actuality");

  }

%>

<html>
<head>
  <title></title>
  <style>
    <%@ include file="path_to_file_css"%>
  </style>
</head>
<body>


<% if (!actuality) { %>
<%@ include file="path_to_file"%>
<% } else {%>
<b><%@ include file="path_to_file"%></b>
<% session.setAttribute("actuality", false); } %>
</body>
</html>
