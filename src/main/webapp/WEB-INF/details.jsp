<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <div class="card-header"><i class="fa fa-table"></i> User Details</div>
            <div class="card-body">
              <div class="table-responsive">
              <table class="table">
			  <thead>
  <tr>
  	<th>Name</th>
    <th>Value</th>
  </tr>
  </thead>
    <tr>
    <td><strong>ID</strong></td>
    <td><c:out value="${user.manager_ID}"></c:out></td>
    </tr>
    <tr>
    <td><strong>Firstname</strong></td>
    <td><c:out value="${user.name}"></c:out></td>
    </tr>
    <tr>
    <td><strong>Lastname</strong></td>
    <td><c:out value="${user.surname}"></c:out></td>
    </tr>
    <tr>
    <td><strong>Email</strong></td>
    <td><c:out value="${user.email}"></c:out></td>
    </tr>
    <tr>
    <td><strong>Address</strong></td>
    <td><c:out value="${user.address}"></c:out></td>
    </tr>
    <tr>
    <td><strong>Telephone</strong></td>
    <td><c:out value="${user.telephone}"></c:out></td>
    </tr>
    <tr>
    <td><strong>Birthday</strong></td>
    <td><c:out value="${user.birthday}"></c:out></td>
    </tr> 
    		</table>
            </div>
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