function validateLoginForm(frm)
{
var valid=true;
var firstInvalidComponent=null;
var userName=frm.userName.value.trim();
var userNameErrorSection=document.getElementById("userNameErrorSection");
userNameErrorSection.innerHTML='';
if(userName.length==0)
{
valid=false;
userNameErrorSection.innerHTML='Required';
firstInvalidComponent=frm.userName;
}
var password=frm.password.value.trim();
var passwordErrorSection=document.getElementById("passwordErrorSection");
passwordErrorSection.innerHTML='';
if(password.length==0)
{
valid=false;
passwordErrorSection.innerHTML='Required';
if(firstInvalidComponent==null)firstInvalidComponent=frm.password;
}
if(firstInvalidComponent)firstInvalidComponent.focus();
return valid;
}