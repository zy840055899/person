<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息修改</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script language="javascript" type="text/javascript">
	function submitInfo(){
	    if(
			checkMobile($("input[name='mobile']").val().trim()) &&
			checkEmail($("input[name='email']").val().trim())
	    ){
			$.post(
				"modifyUserInfo.do",
				$("#infoForm").serialize(),
				function(data) {
					if(data == 0){
						alert("修改成功！");
						location.href="toModifyUserInfo.do";	
					}else if(data == 1){
						alert("该手机号已被注册");
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
	        <li><a href="../user/toModifyUserInfo.do" class="information_on"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
	    </ul>            
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main" style="height: 400px;">            
	    <form action="modifyUserInfo.do" method="post" class="main_form" id="infoForm">
	    	<!-- 员工号 -->
            <input type="hidden" value="${emp.empId }" name="empId"/>
	    
	        <div class="text_info clearfix"><span>姓名：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.name }" name="name" maxlength="4" disabled="disabled"/>
            </div>
            
            <div class="text_info clearfix"><span>籍贯：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.nativee }" disabled="disabled"/>
            </div>
            
            <div class="text_info clearfix"><span>身份证号码：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.identityId }" disabled="disabled"/>
            </div>
            
            <div class="text_info clearfix"><span>入职时间：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.enterDate }" disabled="disabled" />
            </div>
            
            <div class="text_info clearfix"><span>部门：</span></div>
            <div class="input_info">
            	<input type="text" value="${emp.deptName }" disabled="disabled"/>
            </div>
            
            <div class="text_info clearfix"><span>职位：</span></div>
            <div class="input_info">
            	<input type="text" value="${emp.title }" disabled="disabled"/>
            </div>
            
            <div class="text_info clearfix"><span>手机号码：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.mobile }" name="mobile" maxlength="11"/>
                <span class="required">*</span>
            </div>
            
            <div class="text_info clearfix"><span>电子邮件：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.email }" name="email" maxlength="50" />
                 <span class="required">*</span>
             </div>
             
             <div class="text_info clearfix"><span>教育程度：</span></div>
             <div class="input_info">
             	<select name="education">
					<option <c:if test="${emp.education=='研究生' }">selected="selected"</c:if>>研究生</option>                    	
             		<option <c:if test="${emp.education=='本科' }">selected="selected"</c:if>>本科</option>
             		<option <c:if test="${emp.education=='专科' }">selected="selected"</c:if>>专科</option>
             		<option <c:if test="${emp.education=='高中' }">selected="selected"</c:if>>高中</option>
             		<option <c:if test="${emp.education=='初中' }">selected="selected"</c:if>>初中</option>
             		<option <c:if test="${emp.education=='其他' }">selected="selected"</c:if>>其他</option>
             	</select>
             </div>
             
             <div class="text_info clearfix"><span>现居地址：</span></div>
             <div class="input_info">
             	<input type="text" value="${emp.address }" name="address" maxlength="50"/>
            	<span class="norequired">选填</span>
            </div>
            
            <div class="text_info clearfix"><span>婚姻状态：</span></div>
            <div class="input_info">
            	<input type="radio" value="已婚" name="marry" <c:if test="${emp.marry=='已婚' }"> checked="checked"</c:if>>已婚
            	<input type="radio" value="未婚" name="marry" <c:if test="${emp.marry=='未婚' }"> checked="checked"</c:if>>未婚
            </div>
            
            <div class="text_info clearfix"><span>工作电话：</span></div>
            <div class="input_info">
                <input type="text" value="${emp.workPhone }" name="workPhone" maxlength="20"/>
                <span class="norequired">选填</span>
            </div>
	        <div class="button_info clearfix">
	            <input type="button" value="保存" class="btn_save" onclick="submitInfo();" />
	            <input type="reset" value="重置" class="btn_save"/>
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
