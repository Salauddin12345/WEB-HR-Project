package com.thinking.machines.hr.beans;
public class EmployeeErrorBean implements java.io.Serializable
{
private String designationCodeError;
private String panNumberError;
private String aadharCardNumberError;
public EmployeeErrorBean()
{
this.designationCodeError="";
this.panNumberError="";
this.aadharCardNumberError="";
}
public void setDesignationCodeError(java.lang.String designationCodeError)
{
this.designationCodeError=designationCodeError;
}
public java.lang.String getDesignationCodeError()
{
return this.designationCodeError;
}
public void setPanNumberError(java.lang.String panNumberError)
{
this.panNumberError=panNumberError;
}
public java.lang.String getPanNumberError()
{
return this.panNumberError;
}
public void setAadharCardNumberError(java.lang.String aadharCardNumberError)
{
this.aadharCardNumberError=aadharCardNumberError;
}
public java.lang.String getAadharCardNumberError()
{
return this.aadharCardNumberError;
}
}