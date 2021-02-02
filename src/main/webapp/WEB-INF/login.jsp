<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
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
  <link href="/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
  <!-- simplebar CSS-->
  <link href="/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
  <!-- Bootstrap core CSS-->
  <link href="/css/bootstrap.min.css" rel="stylesheet"/>
  <!-- animate CSS-->
  <link href="/css/animate.css" rel="stylesheet" type="text/css"/>
  <!-- Icons CSS-->
  <link href="/css/icons.css" rel="stylesheet" type="text/css"/>
  <!-- Sidebar CSS-->
  <link href="/css/sidebar-menu.css" rel="stylesheet"/>
  <!-- Custom Style-->
  <link href="/css/app-style.css" rel="stylesheet"/>
  
  <script src="/js/jquery.min.js"></script>
  <script src="/js/popper.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  
  <script>
  	  $(document).ready(function() {
  		  
  		var showOrHideFirst = $("input[name='joiningState']:checked").val();
  		if(showOrHideFirst == "true"){
  			$("#descriptionForReject").slideUp();
			$("#basic-textarea").removeAttr('required');
  		}
  		
  		var showOrHideFirstFamily = $("input[name='joiningStateFamily']:checked").val();
  		if(showOrHideFirstFamily == "true"){
  			$("#descriptionForRejectFamily").slideUp();
  			$("#selection").slideDown();
			$("#basic-textareaf").removeAttr('required');
  		}
 	   
	  $("#setanswer").click(function(){
  	   $("#radio-buutons").slideToggle();
     }); 
	  
	  $("#setanswerFamily").click(function(){
		 $("#familyform").slideToggle(); 
	  });
	  
	  $("input[name='joiningState']:radio").change(function(){
		var showOrHide = ($(this).val() == "true") ? true : false;
		if(showOrHide){
			$("#descriptionForReject").slideUp();			
			$("#basic-textarea").removeAttr('required');
		}
		else{
			$("#descriptionForReject").slideDown();			
			$("#basic-textarea").attr('required', "true");			
		}
	 });
	  
	  $("input[name='joiningStateFamily']:radio").change(function(){
			var showOrHide = ($(this).val() == "true") ? true : false;
			if(showOrHide){
				$("#descriptionForRejectFamily").slideUp();
				$("#selection").slideDown();
				$("#basic-textareaf").removeAttr('required');
			}
			else{
				$("#descriptionForRejectFamily").slideDown();
				$("#selection").slideUp();
				$("#basic-textareaf").attr('required', "true");			
			}
		 });
	  
	  
	});
  
  </script>
  
</head>
<body>

<%@ include file = "template.jsp" %>


      
<div class="content-wrapper">
	<div class="container-fluid">
	
		<div class="row mt-3">
		<sec:authorize access="hasRole('ADMIN')">
		 <div class="col-12 col-lg-4 col-xl-4">
          <div class="card border-info border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-info mb-0">${countapps}</h4>
                <span>Total Appointments</span>
              </div>
              <div class="align-self-center w-circle-icon rounded-circle gradient-scooter">
                <i class="icon-calendar"></i></div>
            </div>
            </div>
          </div>
        </div>
        
        <div class="col-12 col-lg-4 col-xl-4">
          <div class="card border-danger border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-danger mb-0">${countfamily}</h4>
                <span>Your Family Members</span>
              </div>
              <div class="align-self-center w-circle-icon rounded-circle gradient-bloody">
                <i class="icon-home"></i></div>
            </div>
            </div>
          </div>
         </div>
         
        
        <div class="col-12 col-lg-4 col-xl-4">
          <div class="card border-danger border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-danger mb-0">${countallfamily}</h4>
                <span>Total Family Members</span>
              </div>
              <div class="align-self-center w-circle-icon rounded-circle gradient-bloody">
                <i class="icon-home"></i></div>
            </div>
            </div>
          </div>
         </div>   
         
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
        <div class="col-12 col-lg-6 col-xl-6">
          <div class="card border-info border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-info mb-0">${countapps}</h4>
                <span>Total Appointments</span>
              </div>
              <div class="align-self-center w-circle-icon rounded-circle gradient-scooter">
                <i class="icon-calendar"></i></div>
            </div>
            </div>
          </div>
        </div>
        
        <div class="col-12 col-lg-6 col-xl-6">
          <div class="card border-danger border-left-sm">
            <div class="card-body">
              <div class="media align-items-center">
              <div class="media-body text-left">
                <h4 class="text-danger mb-0">${countfamily}</h4>
                <span>Total Family Members</span>
              </div>
              <div class="align-self-center w-circle-icon rounded-circle gradient-bloody">
                <i class="icon-home"></i></div>
            </div>
            </div>
          </div>
         </div>        
        </sec:authorize>
        </div> 
        
        <div class="row">
        <div class="col-lg-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Closest Appointment</h5>
			  <div class="table-responsive">
               <table class="table">
                  <tbody>
                    <tr>
                      <th scope="row">Date</th>
                      <td>${closestapp.dateof_app}</td>
                    </tr>
                    <tr>
                      <th scope="row">Time</th>
                      <td>${closestapp.time}</td>
                    </tr>
                    <tr>
                      <th scope="row">Description</th>
                      <td>${closestapp.description_app}</td>
                    </tr>
                    <tr>
                      <th scope="row">Type</th>
                      <td>${closestapp.appType}</td>
                    </tr>
                    </tbody>
                </table>
            <sec:authorize access="hasRole('ADMIN')">
            <a href="/appdetails/${closestapp.appointmentID}" class="btn btn-primary waves-effect waves-light m-1"><i class="fa fa-star mr-1"></i> Details</a>
            <a href="/createqr/${closestapp.appointmentID}" class="btn btn-primary waves-effect waves-light m-1"><i class="fa fa-star mr-1"></i> Create QR Code</a>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
            <c:if test="${closestapp.appType == 'Individual'}">
            <button id="setanswer" class="btn btn-primary custom-btn"> Update Answer</button>
            </c:if>
            <c:if test="${closestapp.appType == 'Family'}">
            <button id="setanswerFamily" class="btn btn-primary custom-btn"> Update Answer</button>
            </c:if>
            <c:if test="${closestapp.appType == 'Individual'}">
            <form:form method="post" id="radio-buutons" action="/updateAnswer" modelAttribute="appStateForm">
            <form:hidden path="appStateID"/>
            <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="joiningState" value="true"></form:radiobutton>
			  <label class="form-check-label" for="inlineRadio1">Accept</label>
			</div>
			<div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="joiningState" value="false"></form:radiobutton>
			  <label class="form-check-label" for="inlineRadio2">Reject</label>
			</div>
			<button id="submitAnswer" class="btn btn-primary custom-btn" type="submit"> Submıt</button>
			<div id="descriptionForReject">
			<form:textarea rows="4" class="form-control" name="descofapp" path="description" id="basic-textarea" placeholder="Please enter your description for rejecting" required="required"></form:textarea>
			</div>
			</form:form>
			</c:if> 
			<c:if test="${closestapp.appType == 'Family'}">
			  <form:form method="post" id="familyform" action="/updateAnswerFamily" modelAttribute="familyAnswer">
			  <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="joiningStateFamily" value="true"></form:radiobutton>
			  <label class="form-check-label" for="inlineRadio1">Accept</label>
			  </div>
			  <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="joiningStateFamily" value="false"></form:radiobutton>
			  <label class="form-check-label" for="inlineRadio2">Reject</label>
			  </div>
			   <button id="submitAnswer" class="btn btn-primary custom-btn" type="submit"> Submıt</button>
			  <div id="selection">
			  <form:select  id="choosemember" itemLabel="fullName" itemValue="memberID" path="comingMembers" multiple="true" items="${comingMembers}"/>
			  </div>			 
			  <div id="descriptionForRejectFamily">
			  <form:textarea rows="4" class="form-control" name="descofapp" path="descriptionFamily" id="basic-textareaf" placeholder="Please enter your description for rejecting" required="required"></form:textarea>
			  </div>
			  </form:form>
            </c:if>       
			</sec:authorize>
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
  <script src="/plugins/jquery-validation/js/jquery.validate.min.js"></script>

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
      
     $("#personal-info").validate(); 
     
     } );

    </script>
  
</body>
</html>