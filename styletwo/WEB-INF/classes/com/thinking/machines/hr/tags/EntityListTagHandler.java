package com.thinking.machines.hr.tags;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*; 
import java.lang.reflect.*;

public class EntityListTagHandler extends BodyTagSupport
{
private String populatorClass;
private String populatorMethod;
private String name;
private int index;
private List list;
public EntityListTagHandler()
{
reset();
}
public void reset()
{
this.populatorClass="";
this.populatorMethod="";
this.name="";
this.index=0;
this.list=null;
}
public void setPopulatorClass(java.lang.String populatorClass)
{
this.populatorClass=populatorClass;
}
public java.lang.String getPopulatorClass()
{
return this.populatorClass;
}
public void setPopulatorMethod(java.lang.String populatorMethod)
{
this.populatorMethod=populatorMethod;
}
public java.lang.String getPopulatorMethod()
{
return this.populatorMethod;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public int doStartTag()
{
try
{
if(name==null || name.trim().length()==0) return super.SKIP_BODY;
Class c=Class.forName(populatorClass);
Object someClassObject=c.newInstance();
Class []parameters=new Class[0];
Method someMethod=c.getMethod(populatorMethod,parameters);
list=(List)someMethod.invoke(someClassObject);
if(list.size()==0)return SKIP_BODY;
index=0;
pageContext.setAttribute("serialNumber",index+1,PageContext.REQUEST_SCOPE);
pageContext.setAttribute(name,list.get(index),PageContext.REQUEST_SCOPE);
return EVAL_BODY_INCLUDE;
}catch(Exception e)
{
System.out.println(e);
return SKIP_BODY;
}
}
public int doAfterBody()
{
try
{
index++;
if(list.size()==index) return super.SKIP_BODY;
pageContext.setAttribute(name,list.get(index),PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_AGAIN;
}catch(Exception e)
{
System.out.println(e);
return SKIP_BODY;
}
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}