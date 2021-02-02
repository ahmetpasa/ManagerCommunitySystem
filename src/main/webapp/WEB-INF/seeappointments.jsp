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
  
</head>
<body>

<%@ include file="template.jsp" %>

	<div class="content-wrapper">
	<div class="container-fluid">
	<div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><i class="fa fa-calendar-check-o"></i> Appointments Created By You</div>
            <div class="card-body">
              <div class="table-responsive">
              <table id="default-datatable" class="table table-bordered">
			  <thead>
  			  <tr>
  				<th>ID</th>
    			<th>Date</th>
    			<th>Time</th> 
    			<th>Description</th>
    			<th>Type</th>
  			</tr> 
  			</thead> 
  			<tbody>
    		<c:forEach var="info" items="${apps}">
    		<tr>
    			<td><c:out value="${info.appointmentID}"></c:out></td>
    			<td><c:out value="${info.dateof_app}"></c:out></td>
    			<td><c:out value="${info.time}"></c:out></td>
    			<td><c:out value="${info.description_app}"></c:out></td>
    			<td><c:out value="${info.appType}"></c:out></td>
    			<sec:authorize access="hasRole('ADMIN')">
    			<td><a class="btn btn-primary custom-btn" href="/appdetails/${info.appointmentID}">Details</a></td>
    			<td><a class="btn btn-primary custom-btn" href="/updateapp/${info.appointmentID}">Update</a></td>
    			<td><a class="btn btn-primary custom-btn" href="/deleteapp/${info.appointmentID}" onclick="return confirm('Are you sure you want to delete?');">Delete</a></td>
    			</sec:authorize>
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
        
<!-- Bootstrap core JavaScript-->
  <script src="/js/jquery.min.js"></script>
  <script src="/js/popper.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
	
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
      
      } );

    </script>
  
</body>
</html>