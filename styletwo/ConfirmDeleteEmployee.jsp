<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:useBean id='employeeErrorBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeErrorBean' />
<tm:Module name='EMPLOYEE' /> 
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/ConfirmDeleteEmployee.js'></script>
<h3>Employee (Edit Module)</h3>
<form method='POST' action='/styletwo/DeleteEmployee.jsp'  onsubmit='return validateForm(this)'>
<input type='hidden' id='employeeId' name='employeeId' value='${employeeBean.employeeId}'>
Name :
<b>${employeeBean.name}</b><br>
Designation :
<b>${employeeBean.designation}</b><br>
Date Of Birth :
<b>${employeeBean.dateOfBirth}</b><br>
Gender :
<b>${employeeBean.gender}</b><br>
Is Indian :
<b>${employeeBean.isIndian}</b><br>
Basic Salary :
<b>${employeeBean.basicSalary}</b><br>
PAN Number :
<b>${employeeBean.panNumber}</b><br>
Aadhar Card Number :
<b>${employeeBean.aadharCardNumber}</b><br>
Are you sure you want to delete this ?<br>
<button type='submit'>Yes</button>&nbsp;&nbsp
<button type='button' onclick='cancelDeletion()'>No</button>
</form>
<form id='cancelDeletionForm' action='/styletwo/Employees.jsp'> 
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />
