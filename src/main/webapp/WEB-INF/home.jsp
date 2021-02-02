<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
  <meta name="description" content=""/>
  <meta name="author" content=""/>
  <title>Login Deneme</title>
  <!-- Bootstrap core CSS-->
  <link href="../css/bootstrap.min.css" rel="stylesheet"/>
  <!-- animate CSS-->
  <link href="../css/animate.css" rel="stylesheet" type="text/css"/>
  <!-- Icons CSS-->
  <link href="../css/icons.css" rel="stylesheet" type="text/css"/>
  <!-- Custom Style-->
  <link href="../css/app-style.css" rel="stylesheet"/>
  <script>  	
  	function login_submit(){
  		var formData = new FormData(document.getElementById("loginForm"));
  		
  		$.ajax({
  			type: "POST",
  			contentType: false,
  			url: "/loginControl",
  			data: formData,
  			dataType: 'json',
  			processData: false,
  			cache: false,
  			timeout: 600000,
  			success: function(response){
  				console.log(response);
  				if(response.success){
  	  				window.location.href = "/abc";	
  				}else{
  					window.location.href = "/abc";	
  				}
  			},
  			error: function(error){
  				console.log(error);
  			}
  		});
  	}
  </script>
  
</head>

<body>

<div id="wrapper">
    <div class="height-100v d-flex align-items-center justify-content-center">
	<div class="card border-primary border-top-sm border-bottom-sm card-authentication1 mx-auto my-5 animated bounceInDown">
		<div class="card-body">
		 <div class="card-content p-2">
    		<form method="POST" action="/loginControl" class="form-signin" id="loginForm" modelAttribute="user">
        		<h2 class="form-heading">Log In</h2>        
        		<c:if test="${not empty errorMsg}">
					<div class="error">${errorMsg}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>          		
        		<div class="form-group">
        		<div class="position-relative has-icon-right">
        		<label for="username" class="sr-only">Username</label>
            		<input name="username" type="text" class="form-control form-control-rounded" placeholder="Username"
                   	autofocus="true"/>
            	</div>
            	</div>
            	<div class="form-group">
            	<div class="position-relative has-icon-right">
            	<label for="password" class="sr-only">Password</label>
            		<input name="password" type="password" class="form-control form-control-rounded" placeholder="Password"/>
				</div>
				</div>
            	<button onclick="login_submit()" class="btn btn-primary btn-round btn-block waves-effect waves-light" type="button">Log In</button>
       		</form>
     	</div>
     </div>    
</div>
</div>
</div>
<!-- /container -->
 <script src="../js/jquery.min.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  
</body>
</html>