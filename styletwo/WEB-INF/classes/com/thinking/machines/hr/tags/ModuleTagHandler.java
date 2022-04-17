package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class ModuleTagHandler extends TagSupport
{
private String name;
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public ModuleTagHandler()
{
reset();
}
public void reset()
{
this.name="";
}
public int doStartTag()
{
pageContext.setAttribute("HOME",new Integer(0),PageContext.REQUEST_SCOPE);
pageContext.setAttribute("DESIGNATION",new Integer(1),PageContext.REQUEST_SCOPE);
pageContext.setAttribute("EMPLOYEE",new Integer(2),PageContext.REQUEST_SCOPE);
if(this.name.equalsIgnoreCase("DESIGNATION"))
{
pageContext.setAttribute("module",new Integer(1),PageContext.REQUEST_SCOPE);
}
else if(this.name.equalsIgnoreCase("EMPLOYEE"))
{
pageContext.setAttribute("module",new Integer(2),PageContext.REQUEST_SCOPE);
}
else if(this.name.equalsIgnoreCase("HOME"))
{
pageContext.setAttribute("module",new Integer(0),PageContext.REQUEST_SCOPE);
}
else
{
pageContext.setAttribute("module",new Integer(-1),PageContext.REQUEST_SCOPE);
}
return SKIP_BODY;
}
public int doEndTag()
{
reset();
return EVAL_PAGE;
}
}