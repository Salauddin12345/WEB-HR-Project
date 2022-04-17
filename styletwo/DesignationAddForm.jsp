<%@taglib uri='WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<tm:Module name='DESIGNATION' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/DesignationAddForm.js'></script>
<h3>Designation (Add Module)</h3>
<form method='post' action='/styletwo/AddDesignation.jsp'  onsubmit='return validateForm(this)'>
<tm:FormId />
Designation
<input type='text' id='title' name='title' maxlength='35' size='36' value='${designationBean.title}'>
<span id='titleErrorSection' class='error'><jsp:getProperty name='errorBean' property='error' /></span><br><br>
<button type='submit'>Add</button>
<button type='button' onclick='cancelAddition()'>Cancel</button>
</form>
<form id='cancelAdditionForm' action='/styletwo/Designations.jsp'> 
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />