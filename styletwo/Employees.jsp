<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/Employees.js'></script>
<link rel='stylesheet' type='text/css' href='/styletwo/css/employees.css'>
<h3>Employees</h3>
<div class='employeeGrid'>
<table border='1' id='employeeGridTable'>
<thead>
<tr>
<th colspan='6' class='employeeGridHeader'><a href='/styletwo/EmployeeAddForm.jsp'>Add Employee</a></th>
</tr>
<tr>
<th class='employeeGridSnoColumn'>S.NO</th>
<th class='employeeGridIdColumn'>Id</th>
<th class='employeeGridNameColumn'>Name</th>
<th class='employeeGridDesignationColumn'>Designation</th>
<th class='employeeGridEditColumn'>Edit</th>
<th class='employeeGridDeleteColumn'>Delete</th>
</tr>
</thead>
<tbody>
<tr>
<td style='text-align:right' placeHolderId='serialNumber'></td>
<td placeHolderId='employeeId'></td>
<td placeHolderId='name'></td>
<td placeHolderId='designation'></td>
<td style='text-align:center' placeHolderId='editOption'></td>
<td style='text-align:center' placeHolderId='deleteOption'></td>
</tr>
</tbody>
</table>
</div>
<div style='margin:5px;padding:5px;height:19vh;border:1px solid black'>
<label style='background:gray;color:white;padding-left:5px;padding-right:5px'>Details</label>
<table border='0' width='100%'>
<tr>
<td>EmployeeId:<span id='detailPanel_employeeId' style='margin-right:30px'></span></td> <td>Name:<span id='detailPanel_name' style='margin-right:30px'></span></td> <td>Designation:<span id='detailPanel_designation' style='margin-right:30px'></span></td> 
</tr>
<tr>
<td>DateOfBirth:<span id='detailPanel_dateOfBirth' style='margin-right:30px'></span></td> <td> Gender:<span id='detailPanel_gender' style='margin-right:30px'></span></td> <td>Is Indian:<span id='detailPanel_isIndian' style='margin-right:30px'></span></td> 
</tr>
<tr>
<td>basic salary:<span id='detailPanel_basicSalary' style='margin-right:30px'></span></td> <td>pan number:<span id='detailPanel_panNumber' style='margin-right:30px'></span></td> <td>aadhar Card Number:<span id='detailPanel_aadharCardNumber' style='margin-right:30px'></span></td>
</tr>
</table>
</div>
<jsp:include page='/MasterPageBottomSection.jsp' />