<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
  <meta name="description" content=""/>
  <meta name="author" content=""/>
  <title>Welcome</title>
  <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
  <!-- Vector CSS -->
  <link href="../plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
  <!-- simplebar CSS-->
  <link href="../plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
  <!-- Bootstrap core CSS-->
  <link href="../css/bootstrap.min.css" rel="stylesheet"/>
  <!-- animate CSS-->
  <link href="../css/animate.css" rel="stylesheet" type="text/css"/>
  <!-- Icons CSS-->
  <link href="../css/icons.css" rel="stylesheet" type="text/css"/>
  <!-- Sidebar CSS-->
  <link href="../css/sidebar-menu.css" rel="stylesheet"/>
  <!-- Custom Style-->
  <link href="../css/app-style.css" rel="stylesheet"/>
  
</head>
<body>

<%@ include file = "template.jsp" %>


<div class="content-wrapper">
       <div class="container-fluid">
       <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
        <form:form class="member-form" action="/addmember" method="post" modelAttribute="memberForm">
        <h3>Add New Family Member</h3>
        	<form:hidden path="memberID"/>
        		<div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Name:</label>
                		<div class="col-sm-10">
                    		<form:input path="name" class="form-control"/>
                    		<form:errors path="name" class="control-label"></form:errors>
                    	</div>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Surname:</label>
                		<div class="col-sm-10">
                    		<form:input path="surname" class="form-control"/>
                    		<form:errors path="surname" class="control-label"></form:errors>
                    	</div>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Email:</label>
                		<div class="col-sm-10">
                    		<form:input path="email" class="form-control"/>
                    	</div>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Telephone:</label>
                		<div class="col-sm-10">
                    		<form:input path="telephone" class="form-control"/>
                    		<form:errors path="telephone" class="control-label"></form:errors>
                    	</div>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Address:</label>
                		<div class="col-sm-10">
                    		<form:input path="address" class="form-control"/>
                    		<form:errors path="address" class="control-label"></form:errors>
                    	</div>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Birthday:</label>
                		<div class="col-sm-10">
                    		<form:input type="date" path="birthday" class="form-control"/>
                    		<form:errors path="birthday" class="control-label"></form:errors>
                    	</div>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                		<label class="col-sm-2 control-label">Degree:</label>
                		<div class="col-sm-10">
                    		<form:input path="degree" class="form-control"/>
                    		<form:errors path="degree" class="control-label"></form:errors>
                    	</div>
                </div>
            <button class="btn btn-primary custom-btn" type="submit">Add</button>
        </form:form>
        </div>
          </div>
        </div>
      </div>
      </div>
    </div>
    </div>
    <!-- Bootstrap core JavaScript-->
  <script src="../js/jquery.min.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
	
  <!-- simplebar js -->
  <script src="../plugins/simplebar/js/simplebar.js"></script>
  <!-- waves effect js -->
  <script src="../js/waves.js"></script>
  <!-- sidebar-menu js -->
  <script src="../js/sidebar-menu.js"></script>
  <!-- Custom scripts -->
  <script src="../js/app-script.js"></script>
  
  <!-- Vector map JavaScript -->
  <script src="../plugins/vectormap/jquery-jvectormap-2.0.2.min.js"></script>
  <script src="../plugins/vectormap/jquery-jvectormap-world-mill-en.js"></script>
  <!-- Chart js -->
  <script src="../plugins/Chart.js/Chart.min.js"></script>
  <script src="../plugins/Chart.js/Chart.extension.js"></script>
  <!-- Index js -->
  <script src="../js/index6.js"></script>
</body>
</html>