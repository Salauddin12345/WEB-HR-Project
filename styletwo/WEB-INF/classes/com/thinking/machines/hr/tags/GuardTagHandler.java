package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class GuardTagHandler extends TagSupport
{
public GuardTagHandler()
{
reset();
}
private void reset()
{
// do nothing
}
public int doStartTag()
{
String g=(String)pageContext.getAttribute("userName",PageContext.SESSION_SCOPE);
if(g==null) return EVAL_BODY_INCLUDE;
else return SKIP_BODY;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}