<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建部门</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script language="javascript" type="text/javascript">
	function addTrain() {
	    if($("input[name='tname']").val().trim() == ""){
			alert("培训名称不能为空");
			return;
	    }
	    if($("input[name='startDateStr']").val().trim() == ""){
			alert("培训开始时间不能为空");
			return;
	    }
	    if($("input[name='endDateStr']").val().trim() == ""){
			alert("培训结束时间不能为空");
			return;
	    }
	    if($("input[name='number']").val().trim() == ""){
			alert("培训人数不能为空");
			return;
	    }
	    if($("input[name='teacher']").val().trim() == ""){
			alert("培训教师不能为空");
			return;
	    }
	    if(
			checkCount($("input[name='number']").val().trim()) && 
			checkTime($("input[name='startDateStr']").val().trim(), $("input[name='endDateStr']").val().trim())
	    ){
			$("#trainForm").submit();
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
	        <li><a href="../train/findAllTrain.do" class="bill_on"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
	    </ul>
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main">            
	    <form action="addTrain.do" method="post" class="main_form" id="trainForm">
            <div class="text_info clearfix"><span>培训名称：</span></div>
            <div class="input_info">
                <input type="text" name="tname" maxlength="10" />
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>培训开始时间：</span></div>
            <div class="input_info">
            	<input class="Wdate" type="text" name="startDateStr" onClick="WdatePicker()">
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>培训结束时间：</span></div>
            <div class="input_info">
            	<input class="Wdate" type="text" name="endDateStr" onClick="WdatePicker()">
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>培训人数：</span></div>
            <div class="input_info">
                <input type="text" name="number" maxlength="3" />
                <span class="required">*</span>
                <div class="validate_msg_long">1-999人</div>
            </div>
            
            <div class="text_info clearfix"><span>培训教师：</span></div>
            <div class="input_info">
                <input type="text" name="teacher" maxlength="5" />
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>备注：</span></div>
            <div class="input_info">
				<input type="text" name="descr" maxlength="30" />        	
                <span class="required" style="color: green;">选填</span>
            </div>
           
            <div class="button_info clearfix">
                <input type="button" value="保存" class="btn_save" onclick="addTrain()"/>
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