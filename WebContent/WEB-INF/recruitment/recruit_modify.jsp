<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改培训信息</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script language="javascript" type="text/javascript">
	function modifyRecruit() {
	    if($("input[name='job']").val().trim() == ""){
			alert("职位名称不能为空");
			return;
	    }
	    if($("input[name='content']").val().trim() == ""){
			alert("职位要求不能为空");
			return;
	    }
	    if($("input[name='sal']").val().trim() == ""){
			alert("薪资不能为空");
			return;
	    }
	    if(
		    checkCount($("input[name='number']").val().trim()) &&
		    checkEmail($("input[name='email']").val().trim())
		){
			$("#recruitForm").submit();
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
	        <li><a href="../recruit/findAllRec.do" class="report_on"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
	    </ul>
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main" style="height: 400px;">            
	    <form action="modifyRec.do" method="post" class="main_form" id="recruitForm">
	    	<input type="hidden" value="${rec.recId }" name="recId"/>
            <div class="text_info clearfix"><span>职位名称：</span></div>
            <div class="input_info">
                <input type="text" name="job" value="${rec.job }" maxlength="10" />
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>职位要求：</span></div>
            <div class="input_info">
            	<input type="text" name="content" value="${rec.content }"/>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>招聘人数：</span></div>
            <div class="input_info">
            	<input type="text" name="number" value="${rec.number }" maxlength="3"/>
                <span class="required">*</span>
                <div class="validate_msg_long">（1-999）</div>
            </div>
            
            <div class="text_info clearfix"><span>最低学历：</span></div>
            <div class="input_info">
                <select name="lowestEdu">
					<option>研究生</option>                    	
               		<option>本科</option>
               		<option>专科</option>
               		<option>高中</option>
               		<option>初中</option>
               		<option>其他</option>
                </select>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>薪资：</span></div>
            <div class="input_info">
                <input type="text" name="sal" maxlength="5" value="${rec.sal }"/>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>联系邮箱：</span></div>
            <div class="input_info">
				<input type="text" name="email" maxlength="30" value="${rec.email }"/>        	
                <span class="required">*</span>
            </div>
           
            <div class="button_info clearfix">
                <input type="button" value="保存" class="btn_save" onclick="modifyRecruit();"/>
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