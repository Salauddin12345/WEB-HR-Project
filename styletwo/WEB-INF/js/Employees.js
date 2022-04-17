function Employee()
{
this.employeeId="";
this.name="";
this.designationCode=0;
this.designation="";
this.dateOfBirth="";
this.gender="";
this.isIndian=true;
this.basicSalary=0;
this.panNumber="";
this.aadharCardNumber="";
}
var selectedRow=null;
var employees=[];

function selectEmployee(row,employeeId)
{
if(selectedRow==row) return;
if(selectedRow!=null)
{
selectedRow.style.background='white';
selectedRow.style.color='black';
}
row.style.background='#7C7B7B'
row.style.color='white';
selectedRow=row;

var i;
for(i=0;i<employees.length;i++)
{
if(employees[i].employeeId==employeeId) break;
}
var emp=employees[i];
document.getElementById('detailPanel_employeeId').innerHTML=emp.employeeId;
document.getElementById('detailPanel_name').innerHTML=emp.name;
document.getElementById('detailPanel_designation').innerHTML=emp.designation;
document.getElementById('detailPanel_dateOfBirth').innerHTML=emp.dateOfBirth;
document.getElementById('detailPanel_gender').innerHTML=emp.gender;
if(emp.isIndian)
{
document.getElementById('detailPanel_isIndian').innerHTML="Yes";
}
else
{
document.getElementById('detailPanel_isIndian').innerHTML="No";
}
document.getElementById('detailPanel_basicSalary').innerHTML=emp.basicSalary;
document.getElementById('detailPanel_panNumber').innerHTML=emp.panNumber;
document.getElementById('detailPanel_aadharCardNumber').innerHTML=emp.aadharCardNumber;
}
function createDynamicRowClickHandler(rowAddress,employeeId)
{
return function()
{
selectEmployee(rowAddress,employeeId);
};
}

function populateEmployeeGridTable()
{
var employeeGridTable=document.getElementById('employeeGridTable');
var employeeGridTableBody=employeeGridTable.getElementsByTagName('tbody')[0];
var employeeGridTableBodyRowTemplate=employeeGridTableBody.getElementsByTagName('tr')[0];
employeeGridTableBodyRowTemplate.remove();

var i;
var dynamicRow;
var dynamicRowCells;
var cellTemplate;
var placeHolderFor;
for(i=0;i<employees.length;i++)
{
dynamicRow=employeeGridTableBodyRowTemplate.cloneNode(true);
employeeGridTableBody.appendChild(dynamicRow);
dynamicRowCells=dynamicRow.getElementsByTagName('td');
for(var t=0;t<dynamicRowCells.length;t++)
{
cellTemplate=dynamicRowCells[t];
placeHolderFor=cellTemplate.getAttribute('placeHolderId');
if(placeHolderFor==null) continue;
if(placeHolderFor=="serialNumber") cellTemplate.innerHTML=i+1;
if(placeHolderFor=="employeeId") cellTemplate.innerHTML=employees[i].employeeId;
if(placeHolderFor=="name") cellTemplate.innerHTML=employees[i].name;
if(placeHolderFor=="designationCode") cellTemplate.innerHTML=employees[i].designationCode;
if(placeHolderFor=="designation") cellTemplate.innerHTML=employees[i].designation;
if(placeHolderFor=="dateOfBirth") cellTemplate.innerHTML=employees[i].dateOfBirth;
if(placeHolderFor=="gender") cellTemplate.innerHTML=employees[i].gender;
if(placeHolderFor=="isIndian") cellTemplate.innerHTML=employees[i].isIndian;
if(placeHolderFor=="basicSalary") cellTemplate.innerHTML=employees[i].basicSalary;
if(placeHolderFor=="panNumber") cellTemplate.innerHTML=employees[i].panNumber;
if(placeHolderFor=="aadharCardNumber") cellTemplate.innerHTML=employees[i].aadharCardNumber;
if(placeHolderFor=="editOption") cellTemplate.innerHTML="<a href='/styletwo/editEmployee?employeeId="+employees[i].employeeId+"'>Edit</a>";
if(placeHolderFor=="deleteOption") cellTemplate.innerHTML="<a href='/styletwo/confirmDeleteEmployee?employeeId="+employees[i].employeeId+"'>Delete</a>";
}
dynamicRow.onclick=createDynamicRowClickHandler(dynamicRow,employees[i].employeeId);
}

}
window.addEventListener('load',populateEmployeeGridTable);
