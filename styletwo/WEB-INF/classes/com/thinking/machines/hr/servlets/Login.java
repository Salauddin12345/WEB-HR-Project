package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
AdministratorBean administratorBean=(AdministratorBean)request.getAttribute("administratorBean");
if(administratorBean==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}
String userName=administratorBean.getUserName();
String password=administratorBean.getPassword();
AdministratorDTO administratorDTO=null;
try
{
administratorDTO=(new AdministratorDAO()).getByUserName(userName);
}catch(DAOException daoException)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError("invalid User Name");
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}
if(password.equals(administratorDTO.getPassword())==false) 
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError("Invalid Password");
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}
HttpSession session=request.getSession();
session.setAttribute("userName",userName);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.jsp");
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
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}