package com.thinking.machines.hr.tags;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*; 

public class DesignationsTagHandler extends BodyTagSupport
{
private List<DesignationBean> designations;
private int index;
public DesignationsTagHandler()
{
reset();
}
public void reset()
{
this.index=0;
if(designations!=null)
{
this.designations.clear();
this.designations=null;
}
}
public int doStartTag()
{
List<DesignationDTO> dlDesignations=null;
try
{
dlDesignations=(new DesignationDAO()).getAll();
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage()); // remove after testing
}
if(dlDesignations.size()==0) return super.SKIP_BODY;
designations=new ArrayList<>();
DesignationBean designationBean=null;
for(DesignationDTO designation:dlDesignations)
{
designationBean=new DesignationBean();
designationBean.setCode(designation.getCode());
designationBean.setTitle(designation.getTitle());
designations.add(designationBean);
}
index=0;
designationBean=designations.get(index);
pageContext.setAttribute("designationBean",designationBean,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_INCLUDE;
}
public int doAfterBody()
{
index++;
if(designations.size()==index) return super.SKIP_BODY;
DesignationBean designationBean;
designationBean=designations.get(index);
pageContext.setAttribute("designationBean",designationBean,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_AGAIN;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}