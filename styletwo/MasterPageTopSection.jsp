<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Guard>
<jsp:forward page='/LoginForm.jsp' />
</tm:Guard>
<!DOCTYPE HTML>
<html lang='en'>
<head>
<title> HR Application </title>
<link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'>
</head>
<body>
<!-- Main container starts here -->
<div class='main-container'>
<!-- header starts here -->
<div class='header'>
<img src='/styletwo/images/logo.png' class='logo'>
<div class='brand-name'>Thinking Machines</div>
<div class='logoutDivision'>
<img src='/styletwo/images/userIcon.png' class='user'>
&nbsp;<label class='userName'>${userName}</label>&nbsp;&nbsp;<a href='/styletwo/logout'>Logout</a>
</div>
</div>
<!-- header ends here -->
<!-- content section starts here -->
<div class='content'>
<!-- left panel starts here -->
<div class='content-left-panel'>

<tm:If condition='${module==DESIGNATION}'>
<b> Designations </b><br>
</tm:If>
<tm:If condition='${module!=DESIGNATION}'>
<a href='/styletwo/Designations.jsp'> Designations </a><br>
</tm:If>

<tm:If condition='${module==EMPLOYEE}'>
<b> Employees </b><br><br>
</tm:If>
<tm:If condition='${module!=EMPLOYEE}'>
<a href='/styletwo/Employees.jsp'> Employees </a><br><br>
</tm:If>

<tm:If condition='${module!=HOME}'>
<a href='/styletwo/index.jsp'>Home</a><br>
</tm:If>

</div>
<!-- left panel ends here -->
<!-- right panel starts here -->
<div class='content-right-panel'>