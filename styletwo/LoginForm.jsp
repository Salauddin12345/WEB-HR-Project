<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<jsp:useBean id='administratorBean' scope='request' class='com.thinking.machines.hr.beans.AdministratorBean' />
<!DOCTYPE HTML>
<html lang='en'>
<head>
<meta charset='utf-8'>
<title>HR Application</title>
<link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'>
<script src='/styletwo/js/LoginForm.js'> </script>
</head>
<body>
<!-- main container -->
<div class='main-container'>
<!-- header -->
<div class='header'>
<img src='/styletwo/images/logo.png' class='logo'>
<div class='brand-name'> Thinking Machines </div>
</div>
<!-- header ends here -->
<!-- content section -->
<div class='content'>
<div class='label-title'>Administrator</div>
<!-- administrator -->

<div class='administrator'>
<div style='margin-left:130px;margin-top:20px'><span class='error'>${errorBean.error}</span></div>
<form method='post' action='/styletwo/Login.jsp' onsubmit='return validateLoginForm(this)'>
<label style='margin-left:30px'><b>User Name</b></label>
<input type='text' id='userName' name='userName' value='${administratorBean.userName}' style='margin-top:10px;margin-left:20px'><span id='userNameErrorSection' class='error'> </span><br>
<label style='margin-left:30px'><b>Password</b></label>
<input type='password' id='password' name='password' value='${administratorBean.password}' style='margin-left:31px;margin-top:5px'><span id='passwordErrorSection' class='error'> </span><br>
<button type='submit' style='margin-top:12px;margin-left:180px'>Login</button>
</form>

</div>
<!-- administrator ends here -->
</div>
<!-- content ends here -->
</div>
<!-- main container ends here -->
</body>
</html>