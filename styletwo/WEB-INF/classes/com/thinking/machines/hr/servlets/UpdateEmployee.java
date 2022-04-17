package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.util.*;
import java.text.*;

public class UpdateEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
HttpSession session=request.getSession();
String uName=(String)session.getAttribute("userName");
if(uName==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}
EmployeeBean employeeBean=(EmployeeBean)request.getAttribute("employeeBean");
EmployeeDTO employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeBean.getEmployeeId());
employeeDTO.setName(employeeBean.getName());
employeeDTO.setDesignationCode(employeeBean.getDesignationCode());
employeeDTO.setDesignation(employeeBean.getDesignation());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
Date d=simpleDateFormat.parse(employeeBean.getDateOfBirth());
employeeDTO.setDateOfBirth(new Date(d.getYear(),d.getMonth(),d.getDate()));
employeeDTO.setGender(employeeBean.getGender());
employeeDTO.setIsIndian(employeeBean.getIsIndian());
employeeDTO.setBasicSalary(new BigDecimal(employeeBean.getBasicSalary()));
employeeDTO.setPanNumber(employeeBean.getPanNumber());
employeeDTO.setAadharCardNumber(employeeBean.getAadharCardNumber());
EmployeeDAO employeeDAO=new EmployeeDAO();
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
boolean designationCodeExists=false;
EmployeeDTO employee;
panNumberExists=false;
employee=employeeDAO.getByPanNumber(employeeDTO.getPanNumber());
if(employee!=null && employee.getEmployeeId().equals(employeeDTO.getEmployeeId())==false) panNumberExists=true;
aadharCardNumberExists=false;
employee=employeeDAO.getByAadharCardNumber(employeeDTO.getAadharCardNumber());
if(employee!=null && employee.getEmployeeId().equals(employeeDTO.getEmployeeId())==false) aadharCardNumberExists=true;
designationCodeExists=employeeDAO.designationCodeExists(employeeDTO.getDesignationCode());

if(panNumberExists || aadharCardNumberExists || designationCodeExists==false)
{
EmployeeErrorBean employeeErrorBean=new EmployeeErrorBean();
if(panNumberExists) employeeErrorBean.setPanNumberError("PAN Number already exists");
if(aadharCardNumberExists) employeeErrorBean.setAadharCardNumberError("Aadhar Card Number already exists");
if(designationCodeExists==false) employeeErrorBean.setDesignationCodeError("invalid Designation Code");
request.setAttribute("employeeErrorBean",employeeErrorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeEditForm.jsp");
requestDispatcher.forward(request,response);
return;
}
employeeDAO.update(employeeDTO);
MessageBean messageBean=new MessageBean();
messageBean.setHeading("Employee (Update Module)");
messageBean.setMessage("employee Updated ");
messageBean.setGenerateButtons(true);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("Employees.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
// do nothing 
}
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
}