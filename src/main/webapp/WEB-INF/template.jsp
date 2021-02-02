<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="wrapper">
<div id="sidebar-wrapper" data-simplebar="" data-simplebar-auto-hide="true" class="border-right border-secondary-light shadow-none color-sidebar bg-custom">
<div class="brand-logo">
      <a href="/abc">
      <img src="../images/logo-icon.png" class="logo-icon" alt="logo icon">
       <h5 class="logo-text">Manager System</h5>
     </a>
</div>

<ul class="sidebar-menu do-nicescrol">
      <li class="sidebar-header">MAIN NAVIGATION</li>
      <li>
        <a href="/seeapps" class="waves-effect">
          <i class="fa fa-calendar-check-o"></i> <span>Appointment Menu</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="sidebar-submenu">
          <li><a href="/seeapps"><i class="fa fa-circle-o"></i>See All Appointments</a></li>
          <sec:authorize access="hasRole('ADMIN')">
          <li><a href="/appointment"><i class="fa fa-circle-o"></i>Add New Appointment</a></li>
          </sec:authorize>
        </ul>
      </li>
      <li>
        <a href="index.html" class="waves-effect">
          <i class="fa fa-birthday-cake"></i> <span>Family Member Menu</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="sidebar-submenu">
          <li><a href="/seemember"><i class="fa fa-circle-o"></i>See All Family Members</a></li>
          <li><a href="/addmember"><i class="fa fa-circle-o"></i>Add New Family Member</a></li>
        </ul>
      </li>
      <sec:authorize access="hasRole('ADMIN')">
      <li>
        <a href="index.html" class="waves-effect">
          <i class="fa fa-birthday-cake"></i> <span>Users Menu</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="sidebar-submenu">
          <li><a href="/abc"><i class="fa fa-circle-o"></i>Add User</a></li>
        </ul>
      </li>	
      </sec:authorize>
      </ul>
    </div>
      
<header class="topbar-nav">
 <nav class="navbar navbar-expand fixed-top bg-custom border-bottom border-secondary-light shadow-none color-header">
  <ul class="navbar-nav mr-auto align-items-center">
    <li class="nav-item">
      <a class="nav-link toggle-menu" href="javascript:void();">
       <i class="icon-menu menu-icon"></i>
     </a>
    </li>
    </ul>
   <ul class="navbar-nav align-items-center right-nav-link">
   <li class="nav-item">
      <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" data-toggle="dropdown" href="#">
        <span class="user-profile"><img src="../images/logo-icon.png" class="img-circle" alt="user avatar"></span>
      </a>
      <ul class="dropdown-menu dropdown-menu-right">
       <li class="dropdown-item user-details">
        <a href="/details">
           <div class="media">
             <div class="avatar"><img class="align-self-start mr-3" src="../images/user-icon.png" alt="user avatar"></div>
            <div class="media-body">
            <h6 class="mt-2 user-title">${name}</h6>
            <p class="user-subtitle">${email}</p>
            </div>
           </div>
          </a>
        </li>
        <li class="dropdown-item">
        <a href="/logout">
        <i class="icon-power mr-2"></i> Logout  
        </a>      
        </li>
      </ul>
    </li>
  </ul>    
    </nav>
    </header>