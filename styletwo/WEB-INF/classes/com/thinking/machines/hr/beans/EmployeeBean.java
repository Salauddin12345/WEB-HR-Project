package com.thinking.machines.hr.beans;
import java.math.*;
public class EmployeeBean implements java.io.Serializable,Comparable<EmployeeBean>
{
private String employeeId;
private String name;
private int designationCode;
private String designation;
private String dateOfBirth;
private String gender;
private boolean isIndian;
private String basicSalary;
private String panNumber;
private String aadharCardNumber;
public EmployeeBean()
{
this.employeeId="";
this.name="";
this.designationCode=0;
this.designation="";
this.dateOfBirth="1070-01-01";
this.gender="";
this.isIndian=false;
this.basicSalary=null;
this.panNumber="";
this.aadharCardNumber="";
}

public void setEmployeeId(java.lang.String employeeId)
{
this.employeeId=employeeId;
}
public java.lang.String getEmployeeId()
{
return this.employeeId;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}
public void setDesignation(java.lang.String designation)
{
this.designation=designation;
}
public java.lang.String getDesignation()
{
return this.designation;
}
public void setDateOfBirth(java.lang.String dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.lang.String getDateOfBirth()
{
return this.dateOfBirth;
}
public void setGender(java.lang.String gender)
{
this.gender=gender;
}
public java.lang.String getGender()
{
return this.gender;
}
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setBasicSalary(String basicSalary)
{
this.basicSalary=basicSalary;
}
public String getBasicSalary()
{
return this.basicSalary;
}
public void setPanNumber(java.lang.String panNumber)
{
this.panNumber=panNumber;
}
public java.lang.String getPanNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public boolean isMale()
{
return this.gender.equals("M");
}
public boolean isFemale()
{
return this.gender.equals("F");
}



public int hashCode()
{
return this.employeeId.hashCode();
}

public boolean equals(Object other)
{
if(!(other instanceof EmployeeBean)) return false;
EmployeeBean employeeBean=(EmployeeBean)other;
return this.employeeId.equalsIgnoreCase(employeeBean.employeeId);
}

public int compareTo(EmployeeBean employeeBean)
{
return this.employeeId.compareToIgnoreCase(employeeBean.employeeId);
}

}