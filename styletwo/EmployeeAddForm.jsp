<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:useBean id='employeeErrorBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeErrorBean' />
<tm:Module name='EMPLOYEE' /> 
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/EmployeeAddForm.js'></script>
<h3>Employee (Add Module)</h3>
<form method='post' action='/styletwo/AddEmployee.jsp'  onsubmit='return validateForm(this)'>
<tm:FormId />
<table>
<tr>
<td>Name</td>
<td><input type='text' id='name' name='name' maxlength='50' size='51' value='${employeeBean.name}'>
<span id='nameErrorSection' style='color:red'></span></td>
</tr>
<tr>
<td>Designation</td>
<td><select id='designationCode' name='designationCode'>
<option value='-1'>&lt select Designation &gt</option>
<tm:EntityList populatorClass='com.thinking.machines.hr.bl.DesignationBL' populatorMethod='getAll' name='dBean'>
<tm:If condition='${employeeBean.designationCode!=dBean.code}'>
<option value='${dBean.code}'>${dBean.title}</option>
</tm:If>
<tm:If condition='${employeeBean.designationCode==dBean.code}'>
<option selected value='${dBean.code}'>${dBean.title}</option>
</tm:If>
</tm:EntityList>
</select>
<span id='designationCodeErrorSection' style='color:red'>${employeeErrorBean.designationCodeError}</span>
</td>
</tr>
<tr>
<td>Date Of Birth</td>
<td><input type='date' id='dateOfBirth' name='dateOfBirth' value='${employeeBean.dateOfBirth}'>
<span id='dateOfBirthErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Gender</td>
<td>
<tm:If condition='${employeeBean.isMale()==true}'>
<input checked type='radio' id='male' name='gender' value='M'>
</tm:If>
<tm:If condition='${employeeBean.isMale()==false}'>
<input type='radio' id='male' name='gender' value='M'>
</tm:If>
Male
&nbsp;&nbsp;&nbsp;&nbsp;
<tm:If condition='${employeeBean.isFemale()==true}'>
<input checked type='radio' id='female' name='gender' value='F'>
</tm:If>
<tm:If condition='${employeeBean.isFemale()==false}'>
<input type='radio' id='female' name='gender' value='F'>
</tm:If>
Female
<span id='genderErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Indian ?</td>
<tm:If condition='${employeeBean.isIndian}' >
<td><input checked type='checkbox' id='isIndian' name='isIndian' value='true'></td>
</tm:If>
<tm:If condition='${employeeBean.isIndian==false}' >
<td><input type='checkbox' id='isIndian' name='isIndian' value='true'></td>
</tm:If>
</tr>
<tr>
<td>Basic Salary</td>
<td><input type='text'id='basicSalary' name='basicSalary' style='text-align:right' maxlength='12' size='13' value='${employeeBean.basicSalary}'> 
<span id='basicSalaryErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>PAN Number</td>
<td><input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='${employeeBean.panNumber}'>
<span id='panNumberErrorSection' style='color:red'>${employeeErrorBean.panNumberError}</span>
</td>
</tr>
<tr>
<td>Aadhar Card Number</td>
<td><input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='${employeeBean.aadharCardNumber}'>
<span id='aadharCardNumberErrorSection' style='color:red'>${employeeErrorBean.aadharCardNumberError}</span>
</td>
</tr>
<tr>
<td colspan='2'>
<button type='submit'>Add</button>
<button type='button' onclick='cancelAddition()'>Cancel</button>
</td>
</tr>
</table>
</form>
<form id='cancelAdditionForm' action='/styletwo/Employees.jsp'> 
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />
