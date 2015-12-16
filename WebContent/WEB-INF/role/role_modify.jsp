<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色权限修改</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
	function submitForm(){
	    if($("input[name='roleName']").val().trim() == ""){
			alert("角色名称不能为空");
			return;
	    }
		var moduleIds = $(":checkbox[name='moduleIds']:checked");
		if(moduleIds.length == 0){
			alert("至少选择一个权限！");
			return;
		}else{
			$.post(
				"checkRoleName.do",
				{"roleName" : $("input[name='roleName']").val(),
				 "roleId" : $("input[name='roleId']").val()},
				function(data){
					if(data == 4){
						alert("此角色名称已存在");
						return;
					}else{
						$("#roleForm").submit();
					}
				}
			);
		}
	}
</script>
</head>
<body>
	<div id="header">
	    <img src="../images/logo.png" alt="logo" class="left"/>
	    <a href="../login/logout.do">[退出]</a>            
	</div>
	<div id="navi">
<%-- 	    <jsp:include page="/WEB-INF/main/menu.jsp"/> --%>
		<ul id="menu">
	        <li><a href="../login/toIndex.do" class="index_off"></a></li>
	        <li><a href="../role/findAllRole.do" class="role_on"></a></li>
	        <li><a href="../emp/findAllEmp.do" class="admin_off"></a></li>
	        <li><a href="../dept/findAllDept.do" class="fee_off"></a></li>
	        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
	        <li><a href="../attend/findAllAttend.do" class="service_off"></a></li>
	        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
        </ul>
	</div>
	<div id="main">           
	    <form action="modifyRole.do" method="post" class="main_form" id="roleForm">
	        <input type="hidden" name="roleId" value="${role.roleId}"/>
	        <div class="text_info clearfix"><span>角色名称：</span></div>
	        <div class="input_info">
	            <input type="text" class="width200" name="roleName" value="${role.roleName}" />
	            <span class="required">*</span>
	        </div>                    
	        <div class="text_info clearfix"><span>设置权限：</span></div>
	        <div class="input_info_high">
	            <div class="input_info_scroll">
	                <ul>
	                	<c:forEach items="${modules}" var="m">
	                    	<li>
	                    		<input type="checkbox" name="moduleIds" value="${m.moduleId}" 
	                    			<c:forEach items="${role.modules}" var="rm">
	                    				<c:if test="${rm.moduleId==m.moduleId}">checked</c:if>
	                    			</c:forEach>
	                    		 />${m.moduleName}
	                    	</li>
	                    </c:forEach>
	                </ul>
	            </div>
	            <span class="required">*</span>
	            <div class="validate_msg_tiny">至少选择一个权限</div>
	        </div>
	        <div class="button_info clearfix">
	            <input type="button" value="保存" class="btn_save" onclick="submitForm();"/>
	            <input type="button" value="取消" class="btn_save" onclick="javascript:history.back()"/>
	        </div>
	    </form> 
	</div>
	<div id="footer">
	    <span>[LiangDuanDuan的毕业设计]</span>
	    <br/>
	    <span>版权所有(C)梁端端 </span>
	</div>
</body>
</html>