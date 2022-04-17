function validateForm(frm)
{
var valid=true;
var firstInvalidComponent=null;

var name=frm.name.value.trim();
var nameErrorSection=document.getElementById('nameErrorSection');
nameErrorSection.innerHTML='';
if(name.length==0)
{
nameErrorSection.innerHTML='Name Requried';
valid=false;
firstInvalidComponent=frm.name;
}

var designationCode=frm.designationCode.value;
var designationCodeErrorSection=document.getElementById('designationCodeErrorSection');
designationCodeErrorSection.innerHTML='';
if(designationCode==-1)
{
designationCodeErrorSection.innerHTML='Designation Required';
valid=false;
if(firstInvalidComponent==null) firstInvalidComponent=frm.designationCode;
}

var dateOfBirth=frm.dateOfBirth.value;
var dateOfBirthErrorSection=document.getElementById('dateOfBirthErrorSection');
dateOfBirthErrorSection.innerHTML='';
if(dateOfBirth.length==0)
{
dateOfBirthErrorSection.innerHTML='Date Of Birth Required';
valid=false;
if(firstInvalidComponent==null) firstInvalidComponent=frm.dateOfBirth;
}

var genderErrorSection=document.getElementById('genderErrorSection');
genderErrorSection.innerHTML='';
if(frm.gender[0].checked==false && frm.gender[1].checked==false)
{
genderErrorSection.innerHTML='Gender Required';
valid=false;
}

var basicSalary=frm.basicSalary.value.trim();
var basicSalaryErrorSection=document.getElementById('basicSalaryErrorSection');
basicSalaryErrorSection.innerHTML='';
if(basicSalary.length==0)
{
basicSalaryErrorSection.innerHTML='basic Salary Required';
valid=false;
if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;
}
else
{
var v='0123456789.';
var e;
var isBasicSalaryValid=true;
for(e=0;e<basicSalary.length;e++)
{
if(v.indexOf(basicSalary.charAt(e))==-1)
{
basicSalaryErrorSection.innerHTML='Invalid Basic Salary';
valid=false;
if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;
isBasicSalaryValid=false;
break;
}
}
if(isBasicSalaryValid==true)
{
var dot=basicSalary.indexOf('.');
if(dot!=-1)
{
if((basicSalary.length-(dot+1))>2)
{
basicSalaryErrorSection.innerHTML='Invalid Basic Salary';
valid=false;
if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;
}
else
{
var d=basicSalary.indexOf('.',dot+1);
if(d!=-1)
{
basicSalaryErrorSection.innerHTML='Invalid Basic Salary';
valid=false;
if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;
}
}
}
}
}

var panNumber=frm.panNumber.value.trim();
var panNumberErrorSection=document.getElementById('panNumberErrorSection');
panNumberErrorSection.innerHTML='';
if(panNumber.length==0)
{
panNumberErrorSection.innerHTML='PAN Number Requried';
valid=false;
if(firstInvalidComponent==null)firstInvalidComponent=frm.panNumber;
}

var aadharCardNumber=frm.aadharCardNumber.value.trim();
var aadharCardNumberErrorSection=document.getElementById('aadharCardNumberErrorSection');
aadharCardNumberErrorSection.innerHTML='';
if(aadharCardNumber.length==0)
{
aadharCardNumberErrorSection.innerHTML='Aadhar Card Number Required';
valid=false;
if(firstInvalidComponent==null)firstInvalidComponent=frm.aadharCardNumber;
}



if(!valid && firstInvalidComponent!=null) firstInvalidComponent.focus();
return valid;
}
function cancelDeletion()
{
document.getElementById('cancelDeletionForm').submit();
}
