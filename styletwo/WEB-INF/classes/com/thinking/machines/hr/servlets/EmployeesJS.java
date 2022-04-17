package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.bl.*;
import com.thinking.machines.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class EmployeesJS extends HttpServlet
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
PrintWriter pw=response.getWriter();
response.setContentType("text/javascript");
ServletContext servletContext=getServletContext();
File file=new File(servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"js"+File.separator+"Employees.js");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
pw.println(randomAccessFile.readLine());
}
randomAccessFile.close();
List<EmployeeBean> employeesList=(new EmployeeBL()).getAll();
int i=0;
pw.println("var employee;");
for(EmployeeBean employeeBean:employeesList)
{
pw.println("employee=new Employee();");
pw.println("employee.employeeId=\""+employeeBean.getEmployeeId()+"\";");
pw.println("employee.name=\""+employeeBean.getName()+"\";");
pw.println("employee.designationCode="+employeeBean.getDesignationCode()+";");
pw.println("employee.designation=\""+employeeBean.getDesignation()+"\";");
pw.println("employee.dateOfBirth=\""+employeeBean.getDateOfBirth()+"\";");
pw.println("employee.gender=\""+employeeBean.getGender()+"\";");
pw.println("employee.isIndian="+employeeBean.getIsIndian()+";");
pw.println("employee.basicSalary="+employeeBean.getBasicSalary()+";");
pw.println("employee.panNumber=\""+employeeBean.getPanNumber()+"\";");
pw.println("employee.aadharCardNumber=\""+employeeBean.getAadharCardNumber()+"\";");
pw.println("employees["+i+"]=employee;");
i++;
}
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
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}