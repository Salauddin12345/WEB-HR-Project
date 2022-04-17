<%@taglib uri='WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<tm:Module name='DESIGNATION' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/ConfirmDeleteDesignation.js'></script>
<h3>Designation (Delete Module)</h3>
Are you sure you want to delete it ?
<form method='get' action='/styletwo/DeleteDesignation.jsp'  onsubmit='return validateForm(this)'>
<input type='hidden' id='code' name='code' value='${designationBean.code}'>
Designation : <b>${designationBean.title}</b><br><br>
<button type='submit'>Delete</button>
<button type='button' onclick='cancelDeletion()'>Cancel</button>
</form>
<form id='cancelDeletionForm' action='/styletwo/Designations.jsp'> 
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />