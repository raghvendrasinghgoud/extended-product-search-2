<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@page import="com.nagarro.entities.*"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.nagarro.categories.*"%>
<!DOCTYPE html>

<%
	HttpSession ss=request.getSession();
	User user=(User)ss.getAttribute("user");
	if(user==null){
		response.sendRedirect("/");
	}
	

	String error="";
	if(request.getAttribute("error")!=null){
		error=(String)request.getAttribute("error");
		request.setAttribute("error", null);
	}
	
	
	
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management-Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
        .error{
            color: red;
            visibility: none;
        }
    </style>
    
</head>
<body>
    
    <jsp:include page="header.jsp" />
   
    <section class="container mt-5">
        <form action="tshirtpath"  onsubmit='return formv()' method="get" id="tshirtform">
    <p><b>Please enter tshirt requirement to add to stock</b></p>
    
   
    <p class="error ms-3 text-start fs-3"><%= error %></p>
    <table>
        <tr>
            <td>
                <label for="color">Color</label>
            </td>
            <td>
                <input type="text" name="color" id="col">
            </td>
            <td>
                <p class="error" id="colorerror"></p>
            </td>
        </tr>
        <tr>
            <td>
                <label for="size">Size</label>
            </td>
            <td>
                <select name="size" id="si">
                	<option disabled="disabled" selected="selected">--Select Size--</option>
                <%
                	Size[] sizes=Size.values();
                		for(Size s: sizes){
                %>
                	<option value="<%= s.toString() %>" > <%= s.toString() %> </option>
                	
                <% 
                		}
                %>
                
                </select>
            </td>
            <td>
                <p class="error" id="sizeerror"></p>
            </td>
        </tr>
        <tr>
            <td>
                <label for="gender">Gender</label>
            </td>
            <td>
                <input type="radio" name="gender" value="<%= Gender.M %>">Male
                <input type="radio" name="gender" value="<%= Gender.F %>">Female
                <input type="radio" name="gender" value="<%= Gender.U %>">Uni
            </td>
            <td>
                <p class="error" id="gendererror"></p>
            </td>
        </tr>
        <tr>
            <td>
                <label for="op">Output Preference</label>
            </td>
            <td>
               <select name="op" id="op">
               		<option disabled="disabled" selected="selected">--Select Preference--</option>
                <%
                	OutputPreferenceType[] ops=OutputPreferenceType.values();
                		for(OutputPreferenceType op: ops){
                %>
                	<option value="<%= op.toString() %>"><%= op.toString() %></option>
                	
                <% 
                		}
                %>
                
                </select>
            </td>
        </tr>
    </table>
			    <input type="submit" value="Submit">
</form>

<table class="table table-striped mt-5">
   <thead>
   <tr>
    <th>ID</th>
    <th>Colour</th>
    <th>Gender Recommendation</th>
    <th>Size</th>
    <th>Price</th>
    <th>Rating</th>
    <th>Availability</th>
    </tr>
   </thead>
   <%
	
	
	 ArrayList<Tshirt> tshirtList=(ArrayList<Tshirt>)request.getAttribute("tshirtsList");
	
   	if(tshirtList!=null){
   		if(tshirtList.size()==0){
   	  	 %>
   	  	<h3 class="error">No Result Found</h3>
   	  	<% 
   	   		}else{
   	for(Tshirt t: tshirtList){
   %>
   <tr>
    <td><%= t.getId() %></td>
    <td><%= t.getColor() %></td>
    <td><%= t.getGender() %></td>
    <td><%= t.getSize() %></td>
    <td><%= t.getPrice() %></td>
    <td><%= t.getRating() %></td>
    <td><%= t.getAvailability() %></td>
     </tr>
   <%
   }
   	   		}
   	}
  	%>
  </table>
</section>


    <!-- <script src="resources/JS/script.js"></script> -->
</body>
</html>