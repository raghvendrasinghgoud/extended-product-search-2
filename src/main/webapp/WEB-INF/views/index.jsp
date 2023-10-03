<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<%
		String error="";
		HttpSession ss=request.getSession();
				if(ss.getAttribute("msg")!=null){
					error=ss.getAttribute("msg").toString();
				}
	%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product Management-Login</title>
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
    
    <section>
        <div class="container col-6 mt-5 pt-5">
    		<p class="error text-end"><%= error %></p>
            <div class="text-bg-secondary p-3 fs-4">Login</div>
            <form class="bg-light" action="userloginpath" method="post">
                <div class="mb-3 pt-3 ps-5 pe-5">
                  <label for="exampleInputEmail1" class="form-label">Username</label>
                  <input type="text" class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp">
                </div>
                <div class="mb-3 pb-3 ps-5 pe-5">
                  <label for="exampleInputPassword1" class="form-label">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                </div>
                <div class="text-bg-secondary p-3 fs-4">
                    <input type="submit" value="Login" />
                </div>
              </form>
        </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
  </body>
</html>