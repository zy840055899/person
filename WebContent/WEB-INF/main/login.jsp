<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script type="text/javascript">
	//更换图片
	function change() {
		$("#verifyCode").attr("src","createImage.do?date="+new Date().getTime());
	}
	function checkLogin(){
	    if($("input[name='username']").val().trim() == ""){
			alert("手机号不能为空");
			return;
	    }
	    if($("input[name='password']").val().trim() == ""){
			alert("密码不能为空");
			return;
    	}
	    if($("input[name='verifyCode']").val().trim() == ""){
			alert("验证码不能为空");
			return;
		}
		$.post(
			"checkLogin.do",
			$("#login_form").serialize(),
			function(data) {
				if(data == 1){
					$("#code_msg").text("用户名不存在");
				}else if(data == 2){
					$("#code_msg").text("密码错误");
				}else if(data == 3){
					$("#code_msg").text("验证码不正确");			//验证码错
					change();
				}else{
					location.href="toIndex.do";					//登录成功，跳转至系统首页
				}
			}
		);
	}
</script>
</head>
<body  class="index">
	<div class="login_box">
		 <form action="checkLogin.do" method="post" id="login_form">
         <table>
             <tr>
                 <td class="login_info">手机号：</td>
                 <td colspan="2"><input name="username" type="text" class="width150" placeholder="请输入您的手机号"/></td>
             </tr>
             <tr>
                 <td class="login_info">密&nbsp;&nbsp;&nbsp;码：</td>
                 <td colspan="2"><input name="password" type="password" class="width150" placeholder="请输入您的密码" /></td>
             </tr>
             <tr>
                 <td class="login_info">验证码：</td>
                 <td class="width70"><input name="verifyCode" type="text" class="width70" /></td>
                 <td><img src="createImage.do" id="verifyCode" alt="验证码" title="点击更换" onclick="change()"/></td>  
                 <td><span class="required" id="code_msg"></span></td>              
             </tr>            
             <tr>
                 <td></td>
                 <td class="login_button" colspan="2">
                     <a href="javascript:checkLogin();"><img src="../images/login_btn.png" /></a>
                 </td>    
                 <td><span class="required"></span></td>                
             </tr>
         </table>
         </form>
     </div>
</body>
</html>