package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditDesignation extends HttpServlet
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
int code=Integer.parseInt(request.getParameter("code"));
DesignationDAO designationDAO=new DesignationDAO();
DesignationDTO designation;
designation=designationDAO.getByCode(code);
DesignationBean designationBean=new DesignationBean();
designationBean.setCode(designation.getCode());
designationBean.setTitle(designation.getTitle());
request.setAttribute("designationBean",designationBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/DesignationEditForm.jsp");
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
try
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);
}catch(Exception e)
{
// do nothing
}
}

}
}