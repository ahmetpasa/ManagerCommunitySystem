<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8"/>
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
  
  <script src="/js/jquery.min.js"></script>
  <script src="/js/popper.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  
  <script>
  		$(document).ready(function() {
  	   
 		$("#accepteduserdetails").click(function(){
    	   $("#accepteduserstable").slideToggle();
       });
       
       $("#rejecteduserdetails").click(function(){
    	   $("#rejecteduserstable").slideToggle();
       });
       
       $("#notanswereduserdetails").click(function(){
    	   $("#notanswereduserstable").slideToggle();
       });
       
       $("#cameuserdetails").click(function(){
    	   $("#userscametable").slideToggle();
       });
       
  	});
  </script>
  
</head>
<body>

<%@ include file="template.jsp" %>

	<div class="content-wrapper">
	<div class="container-fluid">
	<div class="row mt-4">
		<div class="col-12 col-lg-3 col-xl-3">
          <div class="card border-info border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-info mb-0">${numberofaccepteduser}</h4>
                <span>Accepted Users</span>
              </div>
              <div id="accepteduserdetails" class="align-self-center w-circle-icon rounded-circle gradient-scooter">
                <i class="icon-calendar"></i></div>
            </div>
            </div>
          </div>
        </div>
        
        <div class="col-12 col-lg-3 col-xl-3">
          <div class="card border-info border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-info mb-0">${numberofrejecteduser}</h4>
                <span>Rejected Users</span>
              </div>
              <div id="rejecteduserdetails" class="align-self-center w-circle-icon rounded-circle gradient-scooter">
                <i class="icon-calendar"></i></div>
            </div>
            </div>
          </div>
        </div>
        
        <div class="col-12 col-lg-3 col-xl-3">
          <div class="card border-info border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-info mb-0">${numberofnotruser}</h4>
                <span>Not Answered Users</span>
              </div>
              <div id="notanswereduserdetails" class="align-self-center w-circle-icon rounded-circle gradient-scooter">
                <i class="icon-calendar"></i></div>
            </div>
            </div>
          </div>
        </div>
        
        <div class="col-12 col-lg-3 col-xl-3">
          <div class="card border-info border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-info mb-0">${numberofcame}</h4>
                <span>Users Came To Appointment</span>
              </div>
              <div id="cameuserdetails" class="align-self-center w-circle-icon rounded-circle gradient-scooter">
                <i class="icon-calendar"></i></div>
            </div>
            </div>
          </div>
        </div>
	
	
	<div class="row">
        <div class="col-lg-12">
          <div id="accepteduserstable" class="card">
            <div class="card-header"><i class="fa fa-calendar-check-o"></i> Accepted Users</div>
            <div class="card-body">
              <div class="table-responsive">
              <table id="default-datatable" class="table table-bordered">
			  <thead>
  			  <tr>
    			<th>Name</th>
    			<th>Surname</th> 
    			<th>Email</th>
    			<th>Telephone</th>
    			<th>Address</th>
  			</tr> 
  			</thead> 
  			<tbody>
    		<c:forEach var="info" items="${accepteduser}">
    		<tr>
    			<td><c:out value="${info.name}"></c:out></td>
    			<td><c:out value="${info.surname}"></c:out></td>
    			<td><c:out value="${info.email}"></c:out></td>
    			<td><c:out value="${info.telephone}"></c:out></td>
    			<td><c:out value="${info.address}"></c:out></td>
    		</tr>
    		</c:forEach>
    		</tbody>
    		</table>
            </div>
            </div>
          </div>
        </div>
        </div>
        
        
    <div class="row">
        <div class="col-lg-12">
          <div id="rejecteduserstable" class="card">
            <div class="card-header"><i class="fa fa-calendar-check-o"></i> Rejected Users</div>
            <div class="card-body">
              <div class="table-responsive">
              <table id="default-datatable" class="table table-bordered">
			  <thead>
  			  <tr>
    			<th>Name</th>
    			<th>Surname</th>
    			<th>Email</th>
    			<th>Telephone</th>
    			<th>Address</th> 
    			<th>Description</th>
  			</tr> 
  			</thead> 
  			<tbody>
    		<c:forEach var="info" items="${rejecteduser}">
    		<tr>
    			<td><c:out value="${info[0]}"></c:out></td>
    			<td><c:out value="${info[1]}"></c:out></td>
    			<td><c:out value="${info[2]}"></c:out></td>
    			<td><c:out value="${info[3]}"></c:out></td>
    			<td><c:out value="${info[4]}"></c:out></td>
    			<td><c:out value="${info[5]}"></c:out></td>
    		</tr>
    		</c:forEach>
    		</tbody>
    		</table>
            </div>
            </div>
          </div>
        </div>
        </div>
        
        
   <div class="row">
        <div class="col-lg-12">
          <div id="notanswereduserstable" class="card">
            <div class="card-header"><i class="fa fa-calendar-check-o"></i> Not Answered Users</div>
            <div class="card-body">
              <div class="table-responsive">
              <table id="default-datatable" class="table table-bordered">
			  <thead>
  			  <tr>
    			<th>Name</th>
    			<th>Surname</th> 
    			<th>Email</th>
    			<th>Telephone</th>
    			<th>Address</th>
  			</tr> 
  			</thead> 
  			<tbody>
    		<c:forEach var="info" items="${notruser}">
    		<tr>
    			<td><c:out value="${info.name}"></c:out></td>
    			<td><c:out value="${info.surname}"></c:out></td>
    			<td><c:out value="${info.email}"></c:out></td>
    			<td><c:out value="${info.telephone}"></c:out></td>
    			<td><c:out value="${info.address}"></c:out></td>
    		</tr>
    		</c:forEach>
    		</tbody>
    		</table>
            </div>
            </div>
          </div>
        </div>
        </div>
        
   <div class="row">
        <div class="col-lg-12">
          <div id="userscametable" class="card">
            <div class="card-header"><i class="fa fa-calendar-check-o"></i> Users Came To Appointment</div>
            <div class="card-body">
              <div class="table-responsive">
              <table id="default-datatable" class="table table-bordered">
			  <thead>
  			  <tr>
    			<th>Name</th>
    			<th>Surname</th> 
    			<th>Email</th>
    			<th>Telephone</th>
    			<th>Address</th>
  			</tr> 
  			</thead> 
  			<tbody>
    		<c:forEach var="info" items="${came}">
    		<tr>
    			<td><c:out value="${info.name}"></c:out></td>
    			<td><c:out value="${info.surname}"></c:out></td>
    			<td><c:out value="${info.email}"></c:out></td>
    			<td><c:out value="${info.telephone}"></c:out></td>
    			<td><c:out value="${info.address}"></c:out></td>
    		</tr>
    		</c:forEach>
    		</tbody>
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

	
  <!-- simplebar js -->
  <script src="/plugins/simplebar/js/simplebar.js"></script>
  <!-- waves effect js -->
  <script src="/js/waves.js"></script>
  <!-- sidebar-menu js -->
  <script src="/js/sidebar-menu.js"></script>
  <!-- Custom scripts -->
  <script src="/js/app-script.js"></script>
  
  <!-- Vector map JavaScript -->
  <script src="/plugins/vectormap/jquery-jvectormap-2.0.2.min.js"></script>
  <script src="/plugins/vectormap/jquery-jvectormap-world-mill-en.js"></script>
  <!-- Chart js -->
  <script src="/plugins/Chart.js/Chart.min.js"></script>
  <script src="/plugins/Chart.js/Chart.extension.js"></script>
  <!-- Index js -->
  <script src="/js/index6.js"></script>
  
  <!--Data Tables js-->
  <script src="/plugins/bootstrap-datatable/js/jquery.dataTables.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/dataTables.bootstrap4.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/dataTables.buttons.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/buttons.bootstrap4.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/jszip.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/pdfmake.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/vfs_fonts.js"></script>
  <script src="/plugins/bootstrap-datatable/js/buttons.html5.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/buttons.print.min.js"></script>
  <script src="/plugins/bootstrap-datatable/js/buttons.colVis.min.js"></script>

    <script>
     $(document).ready(function() {
      //Default data table
       $('#default-datatable').DataTable();


       var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'copy', 'excel', 'pdf', 'print', 'colvis' ]
      } );
 
     table.buttons().container()
        .appendTo( '#example_wrapper .col-md-6:eq(0)' );
     
       
      
      });

    </script>
  
</body>
</html>