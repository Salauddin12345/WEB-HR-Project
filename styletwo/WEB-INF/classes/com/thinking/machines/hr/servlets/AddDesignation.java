package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddDesignation extends HttpServlet
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

DesignationBean designationBean;
designationBean=(DesignationBean)request.getAttribute("designationBean");
DesignationDTO designation=new DesignationDTO();
designation.setTitle(designationBean.getTitle());
DesignationDAO designationDAO=new DesignationDAO();
try
{
designationDAO.add(designation);
MessageBean messageBean=new MessageBean();
messageBean.setHeading("Designation (Add Module)");
messageBean.setMessage("Designation Added,add more ?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("DesignationAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Designations.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);
}catch(DAOException daoException)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/DesignationAddForm.jsp");
requestDispatcher.forward(request,response);
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
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
}