package com.thinking.machines.hr.servlets;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
HttpSession session=request.getSession();
session.invalidate();
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
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