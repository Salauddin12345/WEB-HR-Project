package com.thinking.machines.hr.dl;

public class AdministratorDTO implements java.io.Serializable,Comparable<AdministratorDTO>
{
private String userName;
private String password;
public AdministratorDTO()
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
public int hashCode()
{
return userName.hashCode();
}
public boolean equals(Object other)
{
if(!(other instanceof AdministratorDTO)) return false;
AdministratorDTO administratorDTO=(AdministratorDTO)other;
return this.password.equals(administratorDTO.password);
}
public int compareTo(AdministratorDTO administratorDTO)
{
return this.password.compareTo(administratorDTO.password);
}
}