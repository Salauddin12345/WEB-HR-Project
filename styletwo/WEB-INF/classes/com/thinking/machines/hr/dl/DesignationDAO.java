package com.thinking.machines.hr.dl;
import java.sql.*;
import java.util.*;
public class DesignationDAO
{
public void add(DesignationDTO designationDTO) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where title=?");
preparedStatement.setString(1,designationDTO.getTitle());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation : "+designationDTO.getTitle()+" Already Exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into designation (title) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,designationDTO.getTitle());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int code=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
designationDTO.setCode(code);
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public void update(DesignationDTO designationDTO) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
int code=designationDTO.getCode();
String title=designationDTO.getTitle();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid designation Code : "+code);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from designation where title=? and code!=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("title :"+title+" already exists ");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update designation set title=? where code=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}


public List<DesignationDTO> getAll() throws DAOException
{
try
{
List<DesignationDTO> designations=new LinkedList<>();
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from designation order by title");
DesignationDTO designationDTO;
int code;
String title;
while(resultSet.next())
{
code=resultSet.getInt("code");
title=resultSet.getString("title").trim();
designationDTO=new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designations.add(designationDTO);
}
resultSet.close();
statement.close();
connection.close();
return designations;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public DesignationDTO getByCode(int code) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid  Designation code : "+code);
}
DesignationDTO designationDTO=new DesignationDTO();
designationDTO.setCode(resultSet.getInt("code"));
designationDTO.setTitle(resultSet.getString("title").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return designationDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public void deleteByCode(int code) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid code :"+code);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select employee_id from employee where designation_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("can not delete designation because it has been alloted to an Employee");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from designation where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}


}

public boolean designationCodeExists(int code) throws DAOException
{
try
{
boolean codeExists=false;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
codeExists=true;
resultSet.close();
preparedStatement.close();
connection.close();
return codeExists;
}
resultSet.close();
preparedStatement.close();
connection.close();
return codeExists;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}


}

}