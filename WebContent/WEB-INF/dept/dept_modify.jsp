<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建部门</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
	function modifyDept() {
	    if($("input[name='deptName']").val().trim() == ""){
			alert("部门名称不能为空");
			return;
	    }
	    if($("input[name='deptAddress']").val().trim() == ""){
			alert("部门地址不能为空");
			return;
	    }
	    $("#deptForm").submit();
	}
</script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
	    <img src="../images/logo.png" alt="logo" class="left"/>
	    <a href="../login/logout.do">[退出]</a>            
	</div>
	<!--Logo区域结束-->
	<!--导航区域开始-->
	<div id="navi">
	    <ul id="menu">
	        <li><a href="../login/toIndex.do" class="index_off"></a></li>
	        <li><a href="../role/findAllRole.do" class="role_off"></a></li>
	        <li><a href="../emp/findAllEmp.do" class="admin_off"></a></li>
	        <li><a href="../dept/findAllDept.do" class="fee_on"></a></li>
	        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
	        <li><a href="../attend/findAllAttend.do" class="service_off"></a></li>
	        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
	    </ul>
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main">            
	    <form action="modifyDept.do" method="post" class="main_form" id="deptForm">
	    	<!-- 保存部门id -->
	    	<input type="hidden" name="deptId" value="${dept.deptId }"/>
            <div class="text_info clearfix"><span>部门名称：</span></div>
            <div class="input_info">
                <input type="text" name="deptName" value="${dept.deptName }" maxlength="5"/>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>部门地址：</span></div>
            <div class="input_info">
                <input type="text" name="deptAddress" value="${dept.deptAddress }" maxlength="18"/>
                <span class="required">*</span>
            </div>
           
            <div class="button_info clearfix">
                <input type="button" value="保存" class="btn_save" onclick="modifyDept()"/>
                <input type="button" value="取消" class="btn_save" onclick="javascript:history.back()" />
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