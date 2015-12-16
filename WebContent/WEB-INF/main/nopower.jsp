<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限错误</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script language="javascript" type="text/javascript">
	var timer;
	//启动跳转的定时器
	function startTimes()
	{
		timer = window.setInterval(showSecondes, 1000);
	}

	var i = 5;
	function showSecondes()
	{
		if (i > 0)
		{
			i--;
			document.getElementById("secondes").innerHTML = i;
		} else
		{
			window.clearInterval(timer);
			location.href = "toIndex.do";
		}
	}

	//取消跳转
	function resetTimer()
	{
		if (timer != null && timer != undefined)
		{
			window.clearInterval(timer);
			location.href = "toIndex.do";
		}
	}
</script>
</head>
<body class="error_power_page" onload="startTimes();">
	<h1 id="error">
	 您无权访问此页面，&nbsp;<span id="secondes">5</span>&nbsp;秒后将自动跳转，立即跳转请点击&nbsp;
	    <a class="index.dol" href="javascript:resetTimer();">返回</a>
	</h1>
</body>
</html>