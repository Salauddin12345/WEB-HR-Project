package com.thinking.machines.hr.dl;
import java.sql.*;

public class AdministratorDAO 
{
public AdministratorDTO getByUserName(String userName) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from administrator where uname=?");
preparedStatement.setString(1,userName);
ResultSet resultSet=preparedStatement.executeQuery();
AdministratorDTO administratorDTO=new AdministratorDTO();
resultSet.next();
administratorDTO.setUserName(resultSet.getString("uname").trim());
administratorDTO.setPassword(resultSet.getString("pwd").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return administratorDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
}