package com.thinking.machines.hr.bl;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
import java.text.*;

public class EmployeeBL
{
public List<EmployeeBean> getAll()
{
List<EmployeeBean> list=new LinkedList<>();
try
{
List<EmployeeDTO> employees=(new EmployeeDAO()).getAll();
EmployeeBean employeeBean;
for(EmployeeDTO employee:employees)
{
employeeBean=new EmployeeBean();
employeeBean.setEmployeeId(employee.getEmployeeId());
employeeBean.setName(employee.getName());
employeeBean.setDesignationCode(employee.getDesignationCode());
employeeBean.setDesignation(employee.getDesignation());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
employeeBean.setDateOfBirth(simpleDateFormat.format(employee.getDateOfBirth()));
employeeBean.setGender(employee.getGender());
employeeBean.setIsIndian(employee.getIsIndian());
employeeBean.setBasicSalary(employee.getBasicSalary().toPlainString());
employeeBean.setPanNumber(employee.getPanNumber());
employeeBean.setAadharCardNumber(employee.getAadharCardNumber());
list.add(employeeBean);
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
return list;
}

}