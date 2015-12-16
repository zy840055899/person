<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加员工</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script language="javascript" type="text/javascript">
	function initDeptSelect(){
		/* 初始化入职日期 */
		var date = new Date();
		$("input[name='enterDate']").val(date.toLocaleDateString());
	}
	function addEmp(){
	    if(
		    checkTrueName($("input[name='name']").val().trim()) &&
		    checkIdentityId($("input[name='identityId']").val().trim()) &&
		    checkNative($("input[name='nativee']").val().trim()) && 
		    checkMobile($("input[name='mobile']").val().trim()) &&
		    checkEmail($("input[name='email']").val().trim())
		)
	    {
			// 先检查手机号是否被占用
			$.post(
				"checkMobile.do",
				{
					"mobile" : $("input[name='mobile']").val().trim(),
					"empId" : -1
				},
				function(data){
					if(data == 0){
					    // 再提交到员工添加交易
					    $.post(
						    "addEmp.do",
						    $("#infoForm").serialize(),
						    function(data){
								if(data == 0){
								    location.href="findAllEmp.do";
								}else{
								    alert("该部门已经设置经理");
								}
						    }
					    );
					}else{
						alert("此手机号已被占用");
					}
				}
			);
	    }
	}
</script>
</head>
<body onload="initDeptSelect();">
	<!--Logo区域开始-->
	<div id="header">
	    <img src="../images/logo.png" alt="logo" class="left"/>
	    <a href="../login/logout.do">[退出]</a>            
	</div>
	<div id="navi">
	    <ul id="menu">
	        <li><a href="../login/toIndex.do" class="index_off"></a></li>
	        <li><a href="../role/findAllRole.do" class="role_off"></a></li>
	        <li><a href="../emp/findAllEmp.do" class="admin_on"></a></li>
	        <li><a href="../dept/findAllDept.do" class="fee_off"></a></li>
	        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
	        <li><a href="../attend/findAllAttend.do" class="service_off"></a></li>
	        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
	    </ul>
	</div>
	<div id="main" style="height: 400px;">            
	    <div id="save_result_info" class="save_success">保存成功！</div>
	    <form action="addEmp.do" method="post" class="main_form" id="infoForm">
	            <div class="text_info clearfix"><span>姓名：</span></div>
	            <div class="input_info">
	                <input type="text" name="name" maxlength="5" />
	                <span class="required">*</span>
	                <div class="validate_msg_long">2-4长度汉字组合</div>
	            </div>
	            
	            <div class="text_info clearfix"><span>身份证号码：</span></div>
                <div class="input_info">
                    <input type="text" name="identityId" maxlength="18"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">18位身份证号</div>
                </div>
             
                <div class="text_info clearfix"><span>籍贯：</span></div>
	            <div class="input_info">
	                <input type="text" name="nativee" maxlength="15"/>
	                <span class="required">*</span>
	                <div class="validate_msg_long">20长度以内的汉字组合</div>
	            </div>
	            
	            <div class="text_info clearfix"><span>部门：</span></div>
                <div class="input_info">
                	<select name="deptId">
                		<c:forEach items="${depts }" var="d">
							<option value="${d.deptId }">${d.deptName }</option>                			
	                	</c:forEach>
                	</select>
                    <span class="required">*</span>
                </div>
                
                <div class="text_info clearfix"><span>职位：</span></div>
                <div class="input_info">
                	<select name="title">
                		<option>经理</option>
                		<option>秘书</option>
                		<option>职员</option>
                	</select>
                	<span class="required">*</span>
                </div>
                
                <div class="text_info clearfix"><span>角色类型：</span></div>
                <div class="input_info">
                	<select name="roleType">
                		<c:forEach items="${roles }" var="r">
							<option value="${r.roleId }">${r.roleName }</option>                			
                		</c:forEach>
                	</select>
                    <span class="required">*</span>
                </div>
                
                <div class="text_info clearfix"><span>入职薪资等级：</span></div>
                <div class="input_info">
                	<select name="salGrade">
                		<option value="A">A</option>
                		<option value="B">B</option>
                		<option value="C">C</option>
                		<option value="D">D</option>
                		<option value="E">E</option>
                	</select>
                    <span class="required">*</span>
                </div>
                
                <div class="text_info clearfix"><span>手机号码：</span></div>
                <div class="input_info">
                    <input type="text" name="mobile" maxlength="11"/>
                    <span class="required">*</span>
                </div>
                
                <div class="text_info clearfix"><span>电子邮件：</span></div>
                <div class="input_info">
                    <input type="text" name="email" maxlength="50" />
                    <span class="required">*</span>
                </div>
                
                <div class="text_info clearfix"><span>教育程度：</span></div>
                <div class="input_info">
                	<select name="education">
						<option>研究生</option>                    	
                		<option>本科</option>
                		<option>专科</option>
                		<option>高中</option>
                		<option>初中</option>
                		<option>其他</option>
                	</select>
                	<span class="required">*</span>
                </div>
	            
	            <div class="text_info clearfix"><span>居住地址：</span></div>
                <div class="input_info">
                	<input type="text" name="address" maxlength="50"/>
                	<span class="norequired">选填</span>
                </div>
                
	            <div class="text_info clearfix"><span>工作电话：</span></div>
                <div class="input_info">
                    <input type="text" name="workPhone" maxlength="20"/>
                    <span class="norequired">选填</span>
                </div>
	           
                <div class="text_info clearfix"><span>婚姻状态：</span></div>
                <div class="input_info">
                	<input type="radio" value="已婚" name="marry">已婚
                	<input type="radio" value="未婚" name="marry" checked="checked">未婚
                </div>
                
	            <div class="button_info clearfix">
	                <input type="button" value="保存" class="btn_save" onclick="addEmp()" />
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