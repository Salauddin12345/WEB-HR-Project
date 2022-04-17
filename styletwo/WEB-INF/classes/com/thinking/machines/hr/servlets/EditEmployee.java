package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
import java.text.*;

public class EditEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
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
String employeeId=request.getParameter("employeeId");
EmployeeBean employeeBean;
EmployeeDTO employeeDTO;
EmployeeDAO employeeDAO=new EmployeeDAO();
employeeDTO=employeeDAO.getByEmployeeId(employeeId);
employeeBean=new EmployeeBean();
employeeBean.setEmployeeId(employeeDTO.getEmployeeId());
employeeBean.setName(employeeDTO.getName());
employeeBean.setDesignationCode(employeeDTO.getDesignationCode());
employeeBean.setDesignation(employeeDTO.getDesignation());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
employeeBean.setDateOfBirth(simpleDateFormat.format(employeeDTO.getDateOfBirth()));
employeeBean.setGender(String.valueOf(employeeDTO.getGender()));
employeeBean.setIsIndian(employeeDTO.getIsIndian());
employeeBean.setBasicSalary(employeeDTO.getBasicSalary().toPlainString());
employeeBean.setPanNumber(employeeDTO.getPanNumber());
employeeBean.setAadharCardNumber(employeeDTO.getAadharCardNumber());
request.setAttribute("employeeBean",employeeBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeEditForm.jsp");
requestDispatcher.forward(request,response);
return;
}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Employees.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception ex)
{
// do nothing
}
return;
}
catch(Exception e)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exce)
{
// do nothing
}
return;
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}