<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.nagarro.entities.User" %>
<%
	String username="";
	String logout="";
	String log="";
	HttpSession ss=request.getSession();
	User user=(User)ss.getAttribute("user");
	if(user!=null){
		username="Hi "+user.getUsername();
		logout="logoutpath";
		log="logout";
	}
%>
<header>
      <nav class="navbar navbar-expand-lg bg-body-tertiary row">
          <div   class="text-end fs-3 col-8" >
           <b class="me-5">Tshirt Management Tool</b>
          </div>
           <div class=" text-end col row ">
                <p class="col-9 mt-2 "><%= username %></p>           
                <a class="nav-link me-3 col" href="<%= logout %>"><button class="btn btn-light"><%= log %></button></a>
           </div>
        </nav>
  </header>