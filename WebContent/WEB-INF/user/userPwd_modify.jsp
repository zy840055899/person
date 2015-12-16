<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script type="text/javascript">
	function modifyPwd(){
	    if($("input[name='password']").val().trim() == ""){
			alert("请输入原密码");
			return;
	    }
	    if($("input[name='newPassword']").val().trim() == ""){
			alert("请输入新密码");
			return;
	    }
	    if($("input[name='newPassword2']").val().trim() == ""){
			alert("请输入确认密码");
			return;
	    }
		if($("input[name='newPassword']").val() != $("input[name='newPassword2']").val()){
			alert("新密码和确认密码不一致");
			return;
		}
		if(
			checkLoginPwd($("input[name='password']").val()) && 
			checkLoginPwd($("input[name='newPassword']").val()) &&
			checkLoginPwd($("input[name='newPassword2']").val()) 
		){
		    $.post(
				"modifyPwd.do",
				$("#pwdForm").serialize(),
				function(data) {
					if(data == 0){
						var flag = confirm("修改成功！是否重新登录？");
						if(flag){
							location.href="../login/logout.do";	
						}
					}else if(data == 2){
						alert("原密码错误");
					}else{
						alert("修改失败");
					}
				}
			);
		}
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
	        <li><a href="../dept/findAllDept.do" class="fee_off"></a></li>
	        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
	        <li><a href="../attend/findAllAttend.do" class="service_off"></a></li>
	        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_on"></a></li>
	    </ul>
	</div>
	<!--导航区域结束-->
	<div id="main">      
	    <form action="modifyPwd.do" method="post" class="main_form" id="pwdForm">
	        <div class="text_info clearfix"><span>原密码：</span></div>
	        <div class="input_info">
	            <input type="password" class="width200" name="password" maxlength="8"/><span class="required">*</span>
	            <div class="validate_msg_medium">8长度以内的字母、数字组合</div>
	        </div>
	        <div class="text_info clearfix"><span>新密码：</span></div>
	        <div class="input_info">
	            <input type="password" class="width200" name="newPassword" maxlength="8"/><span class="required">*</span>
	            <div class="validate_msg_medium">8长度以内的字母、数字组合</div>
	        </div>
	        <div class="text_info clearfix"><span>密码确认：</span></div>
	        <div class="input_info">
	            <input type="password" class="width200" name="newPassword2" maxlength="8"/><span class="required">*</span>
	        </div>
	        <div class="button_info clearfix">
	            <input type="button" value="保存" class="btn_save" onclick="modifyPwd();" />
	            <input type="reset" value="重置" class="btn_save" />
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