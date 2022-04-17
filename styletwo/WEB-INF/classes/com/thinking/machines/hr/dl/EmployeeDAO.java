package com.thinking.machines.hr.dl;

import java.sql.*;
import java.util.*;
import java.math.*;

public class EmployeeDAO 
{
public List<EmployeeDTO> getAll() throws DAOException
{
try
{
List<EmployeeDTO> list=new LinkedList<>();
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select employee.employee_id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code");
String employeeId=null;
String name=null;
int designationCode=0;
String designation=null;
java.sql.Date sqlDateOfBirth=null;
String gender=null;
boolean isIndian=false;
BigDecimal basicSalary=null;
String panNumber=null;
String aadharCardNumber=null;
EmployeeDTO employeeDTO;
while(resultSet.next())
{
employeeId="A"+resultSet.getInt("employee_id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
designation=resultSet.getString("title").trim();
sqlDateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(designation);
employeeDTO.setDateOfBirth(sqlDateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPanNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
list.add(employeeDTO);
}
resultSet.close();
statement.close();
connection.close();
return list;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public void add(EmployeeDTO employeeDTO) throws DAOException
{
try
{
int designationCode=employeeDTO.getDesignationCode();
String panNumber=employeeDTO.getPanNumber();
String aadharCardNumber=employeeDTO.getAadharCardNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,designationCode);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid designation code");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select employee_id from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number: "+panNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select employee_id from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number: "+aadharCardNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into employee (name,designation_code,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);	
preparedStatement.setString(1,employeeDTO.getName());
preparedStatement.setInt(2,employeeDTO.getDesignationCode());
java.util.Date utilDateOfBirth=employeeDTO.getDateOfBirth();
java.sql.Date sqlDateOfBirth=new java.sql.Date(utilDateOfBirth.getYear(),utilDateOfBirth.getMonth(),utilDateOfBirth.getDate());
preparedStatement.setDate(3,sqlDateOfBirth);
preparedStatement.setString(4,employeeDTO.getGender());
preparedStatement.setBoolean(5,employeeDTO.getIsIndian());
preparedStatement.setBigDecimal(6,employeeDTO.getBasicSalary());
preparedStatement.setString(7,employeeDTO.getPanNumber());
preparedStatement.setString(8,employeeDTO.getAadharCardNumber());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int employeeId=resultSet.getInt(1);
employeeDTO.setEmployeeId("A"+employeeId);
preparedStatement.close();
connection.close();
}catch(SQLException sqlException) // here,if we take pointer of Exception then problem.. (double wraping of DAOException)
{
throw new DAOException(sqlException.getMessage());
}
}

public void update(EmployeeDTO employeeDTO) throws DAOException
{
try
{
String employeeId=employeeDTO.getEmployeeId();
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception e)
{
throw new DAOException("invalid employeeId"+employeeId);
}

Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where employee_id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();

if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid employee id : "+employeeId);
}
resultSet.close();
preparedStatement.close();
int designationCode=employeeDTO.getDesignationCode();
String panNumber=employeeDTO.getPanNumber();
String aadharCardNumber=employeeDTO.getAadharCardNumber();
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,designationCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid designation code");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("select gender from employee where pan_number=? and employee_id<>?");
preparedStatement.setString(1,panNumber);
preparedStatement.setInt(2,actualEmployeeId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number: "+panNumber+" already exists");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("select gender from employee where aadhar_card_number=? and employee_id<>?");
preparedStatement.setString(1,aadharCardNumber);
preparedStatement.setInt(2,actualEmployeeId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number: "+aadharCardNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,gender=?,is_indian=?,basic_salary=?,pan_number=?,aadhar_card_number=? where employee_id=?");	
preparedStatement.setString(1,employeeDTO.getName());
preparedStatement.setInt(2,employeeDTO.getDesignationCode());
java.util.Date utilDateOfBirth=employeeDTO.getDateOfBirth();
java.sql.Date sqlDateOfBirth=new java.sql.Date(utilDateOfBirth.getYear(),utilDateOfBirth.getMonth(),utilDateOfBirth.getDate());
preparedStatement.setDate(3,sqlDateOfBirth);
preparedStatement.setString(4,employeeDTO.getGender());
preparedStatement.setBoolean(5,employeeDTO.getIsIndian());
preparedStatement.setBigDecimal(6,employeeDTO.getBasicSalary());
preparedStatement.setString(7,employeeDTO.getPanNumber());
preparedStatement.setString(8,employeeDTO.getAadharCardNumber());
preparedStatement.setInt(9,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException) // here,if we take pointer of Exception then problem.. (double wraping of DAOException)
{
throw new DAOException(sqlException.getMessage());
}
}


public boolean panNumberExists(String panNumber) throws DAOException
{
try
{
boolean isPanNumberExists=false;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee_id from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
isPanNumberExists=true;
resultSet.close();
preparedStatement.close();
connection.close();
return isPanNumberExists;
}
resultSet.close();
preparedStatement.close();
connection.close();
return isPanNumberExists;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
try
{
boolean isAadharCardNumberExists=false;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee_id from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
isAadharCardNumberExists=true;
resultSet.close();
preparedStatement.close();
connection.close();
return isAadharCardNumberExists;
}
resultSet.close();
preparedStatement.close();
connection.close();
return isAadharCardNumberExists;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public void deleteByEmployeeId(String employeeId) throws DAOException
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(NumberFormatException nfe)
{
throw new DAOException("invalid employee id : "+employeeId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select gender from employee where employee_id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid employee Id: "+employeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from employee where employee_id=?");
preparedStatement.setInt(1,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public EmployeeDTO getByEmployeeId(String employeeId) throws DAOException
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(NumberFormatException nfe)
{
throw new DAOException("invalid employe id: "+employeeId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select employee_id,name,designation_code,title,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number from employee inner join designation on designation_code=code and employee_id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false) return null;
String stringEmployeeId=null;
String name=null;
int designationCode=0;
String designation=null;
java.sql.Date sqlDateOfBirth=null;
String gender=null;
boolean isIndian=false;
BigDecimal basicSalary=null;
String panNumber=null;
String aadharCardNumber=null;
EmployeeDTO employeeDTO;
stringEmployeeId="A"+resultSet.getInt("employee_id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
designation=resultSet.getString("title").trim();
sqlDateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(stringEmployeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(designation);
employeeDTO.setDateOfBirth(sqlDateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPanNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public boolean employeeIdExists(String employeeId) throws DAOException
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception e)
{
return false;
}
try
{
boolean isEmployeeIdExists=false;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where employee_id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
isEmployeeIdExists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return isEmployeeIdExists;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public EmployeeDTO getByPanNumber(String panNumber1) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select employee_id,name,designation_code,title,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number from employee inner join designation on designation_code=code and pan_number=?");
preparedStatement.setString(1,panNumber1);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false) return null;
String stringEmployeeId=null;
String name=null;
int designationCode=0;
String designation=null;
java.sql.Date sqlDateOfBirth=null;
String gender=null;
boolean isIndian=false;
BigDecimal basicSalary=null;
String panNumber=null;
String aadharCardNumber=null;
EmployeeDTO employeeDTO;
stringEmployeeId="A"+resultSet.getInt("employee_id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
designation=resultSet.getString("title").trim();
sqlDateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(stringEmployeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(designation);
employeeDTO.setDateOfBirth(sqlDateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPanNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public boolean designationCodeExists(int designationCode) throws DAOException 
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,designationCode);
ResultSet resultSet=preparedStatement.executeQuery();
return resultSet.next();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public EmployeeDTO getByAadharCardNumber(String aadharCardNumber1) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select employee_id,name,designation_code,title,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number from employee inner join designation on designation_code=code and aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber1);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false) return null;
String stringEmployeeId=null;
String name=null;
int designationCode=0;
String designation=null;
java.sql.Date sqlDateOfBirth=null;
String gender=null;
boolean isIndian=false;
BigDecimal basicSalary=null;
String panNumber=null;
String aadharCardNumber=null;
EmployeeDTO employeeDTO;
stringEmployeeId="A"+resultSet.getInt("employee_id");
name=resultSet.getString("name").trim();
designationCode=resultSet.getInt("designation_code");
designation=resultSet.getString("title").trim();
sqlDateOfBirth=resultSet.getDate("date_of_birth");
gender=resultSet.getString("gender");
isIndian=resultSet.getBoolean("is_indian");
basicSalary=resultSet.getBigDecimal("basic_salary");
panNumber=resultSet.getString("pan_number").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(stringEmployeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDesignation(designation);
employeeDTO.setDateOfBirth(sqlDateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPanNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

}