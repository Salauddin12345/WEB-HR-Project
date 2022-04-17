package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateDesignation extends HttpServlet
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
DesignationBean designationBean;
designationBean=(DesignationBean)request.getAttribute("designationBean");
DesignationDAO designationDAO=new DesignationDAO();
DesignationDTO designation=new DesignationDTO();
designation.setCode(designationBean.getCode());
designation.setTitle(designationBean.getTitle());
try
{
designationDAO.update(designation);
MessageBean messageBean;
messageBean=new MessageBean();
messageBean.setHeading("Designation (Update Module)");
messageBean.setMessage("Designation Updated");
messageBean.setGenerateButtons(true);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Designations.jsp");
messageBean.setGenerateTwoButtons(false);
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
requestDispatcher=request.getRequestDispatcher("/DesignationEditForm.jsp");
requestDispatcher.forward(request,response);
}

}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
try
{
requestDispatcher=request.getRequestDispatcher("/ErrorPage.jsp");
requestDispatcher.forward(request,response);
}catch(Exception e)
{
//do nothing
}
}

}

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}