<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工信息</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script language="javascript" type="text/javascript">
    function modifyEmp(){
		if(
		    checkTrueName($("input[name='name']").val().trim()) &&
		    checkMobile($("input[name='mobile']").val().trim()) &&
		    checkEmail($("input[name='email']").val().trim())
		){
		    // 先检查修改的手机号码是否已经被占用
		    $.post(
	    		"checkMobile.do",
	    		{"mobile" : $("input[name='mobile']").val(),
	    		 "empId" : $("input[name='empId']").val()},
	    		function(data){
	    			if(data == 0){
	    			    // 再提交到修改员工信息交易
	    			    $.post(
	    					"empModify.do",
	    					$("#empForm").serialize(),
	   						function(data){
	    					    if(data == 0){
	    							location.href="findAllEmp.do";
	    					    }else{
	    							alert("该部门已经设置经理");
	    					    }
	    					}
	    			    );
	    			}else{
	    				alert("新填写的手机号已被使用");
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
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main" style="height: 400px;">            
            <form action="empModify.do" method="post" class="main_form" id="empForm">
            		<!-- 员工号 -->
            		<input type="hidden" value="${emp.empId }" name="empId"/>
            
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" value="${emp.name }" name="name" maxlength="4"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">5长度以内的汉字组合</div>
                    </div>
                    
                    <div class="text_info clearfix"><span>籍贯：</span></div>
                    <div class="input_info">
                        <input type="text" value="${emp.nativee }" disabled="disabled"/>
                        <span class="required">*</span>
                    </div>
                    
                    <div class="text_info clearfix"><span>身份证号码：</span></div>
                    <div class="input_info">
                        <input type="text" value="${emp.identityId }" disabled="disabled"/>
                        <span class="required">*</span>
                    </div>
                    
                    <div class="text_info clearfix"><span>入职时间：</span></div>
                    <div class="input_info">
                        <input type="text" value="${emp.enterDate }" disabled="disabled" />
                        <span class="required">*</span>
                    </div>
                    
                    <div class="text_info clearfix"><span>部门：</span></div>
                    <div class="input_info">
                    	<select name="deptId">
                    		<c:forEach items="${depts }" var="d">
								<option value="${d.deptId }"
									<c:if test="${d.deptId==emp.deptId}">selected="selected"</c:if>
								>${d.deptName }</option>                			
	                		</c:forEach>
                    	</select>
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    
                    <div class="text_info clearfix"><span>职位：</span></div>
                    <div class="input_info">
                    	<select name="title">
                    		<option <c:if test="${emp.title=='经理' }">selected="selected"</c:if>>经理</option>
                    		<option <c:if test="${emp.title=='秘书' }">selected="selected"</c:if>>秘书</option>
                    		<option <c:if test="${emp.title=='职员' }">selected="selected"</c:if>>职员</option>
                    	</select>
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    
                    <div class="text_info clearfix"><span>初始化权限：</span></div>
	                <div class="input_info">
	                	<select name="roleType">
	                		<c:forEach items="${roles }" var="r">
								<option value="${r.roleId }"
									<c:if test="${r.roleId==emp.roleType}">selected="selected"</c:if>
								>${r.roleName }</option>                			
	                		</c:forEach>
	                	</select>
	                    <span class="required">*</span>
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
                        <div class="validate_msg_long"></div>
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
                        <div class="validate_msg_long error_msg">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    
                    <div class="text_info clearfix"><span>居住地址：</span></div>
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
                        <input type="button" value="保存" class="btn_save" onclick="modifyEmp()"/>
                        <input type="button" value="取消" class="btn_save" onclick="javascript:history.back()" />
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[LiangDuanDuan的毕业设计]</span>
            <br />
            <span>版权所有(C)梁端端 </span>
        </div>
</body>
</html>