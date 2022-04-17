package com.thinking.machines.hr.beans;

public class AdministratorBean implements java.io.Serializable
{
private String userName;
private String password;
public AdministratorBean()
{
this.userName="";
this.password="";
}
public void setUserName(java.lang.String userName)
{
this.userName=userName;
}
public java.lang.String getUserName()
{
return this.userName;
}
public void setPassword(java.lang.String password)
{
this.password=password;
}
public java.lang.String getPassword()
{
return this.password;
}
}