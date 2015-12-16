<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加考勤信息</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script language="javascript" type="text/javascript">
	function addAttend() {
	    var dutyDateStr = $("input[name='dutyDateStr']").val().trim();
	    if(dutyDateStr == ""){
	    	alert("请填写考勤日期");
	    	return;
	    }
	    // 先查找该天用户有没有考勤如果没有则添加考勤信息
	    $.post(
			"queryIfAttend.do",
			{"dutyDateStr" : dutyDateStr},
			function(data){
				if(data==0)
				    $("#attendForm").submit();
				else
				    alert("该日期已考勤");
			}
	    );
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
	        <li><a href="../attend/findAllAttend.do" class="service_on"></a></li>
	        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
	    </ul>
	</div>
	<!--主要区域开始-->
	<div id="main">            
	    <form action="addAttend.do" method="post" class="main_form" id="attendForm">
            <div class="text_info clearfix"><span>考勤日期：</span></div>
            <div class="input_info">
            	<input class="Wdate" type="text" name="dutyDateStr" onClick="WdatePicker()">
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>工作时长（小时）：</span></div>
            <div class="input_info">
            	<select name="workTime">
            		<option value="0">0</option>
            		<option value="1">1</option>
            		<option value="2">2</option>
            		<option value="3">3</option>
            		<option value="4">4</option>
            		<option value="5">5</option>
            		<option value="6">6</option>
            		<option value="7">7</option>
            		<option value="8" selected="selected">8</option>
            	</select>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>加班时长（小时）：</span></div>
            <div class="input_info">
	            <select name="extraTime">
            		<option value="0" selected="selected">0</option>
            		<option value="1">1</option>
            		<option value="2">2</option>
            		<option value="3">3</option>
            		<option value="4">4</option>
            		<option value="5">5</option>
            		<option value="6">6</option>
            		<option value="7">7</option>
            		<option value="8">8</option>
            	</select>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>是否出差：</span></div>
            <div class="input_info">
            	<select name="isTravel">
            		<option value="0">否</option>
            		<option value="1">是</option>
            	</select>
            	<span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>备注：</span></div>
            <div class="input_info">
				<input type="text" name="remark" maxlength="30" />        	
                <span class="required" style="color: green;">选填</span>
            </div>
           
            <div class="button_info clearfix">
                <input type="button" value="保存" class="btn_save" onclick="addAttend()"/>
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