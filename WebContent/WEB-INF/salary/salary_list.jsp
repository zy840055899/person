<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪资查看</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
	function init() {
	    initWorkdayCount();
	    initialDate();
	}
	
	function initWorkdayCount(){
	    var index = 0;
	    var workdayCount = $("#workdayCount")[0];
	    for(var i = 15; i <= 25; i++){
			var opt = new Option(i, i);
			workdayCount.options[index++] = opt;
	    }
	}
	
	function initialDate()
	{
		//声明option对象的索引
		var index = 0;
		var yearObj = document.getElementById("year");
		var endYear = (new Date()).getFullYear();
		for(var startYear = endYear-1; startYear <= endYear; startYear++){
			var opt = new Option(startYear, startYear);//Option(插入的option的文本, 插入的option的value属性的值)
			yearObj.options[index++] = opt;
		}
		
		//初始化月份选择框
		var monthObj = document.getElementById("month");
		index = 0;
		for (var i = 1; i <= 12; i++)
		{
			var opt = new Option(i, i);
			monthObj.options[index++] = opt;
		}
	}	
	
	function dateChange(){
		//得到所选的年份
		var yearObj = document.getElementById("year");
		var year = yearObj.options[yearObj.selectedIndex].value;
		var endYear = (new Date()).getFullYear();
	
		//删除原有月数并重新添加
		var dateObj = document.getElementById("month");
		dateObj.options.length = 0;
		
		if(endYear == year){
			var endMonth = (new Date()).getMonth();
			var index = 0;
			for (var i = 1; i <= endMonth; i++)
			{
				var opt = new Option(i, i);
				dateObj.options[index++] = opt;
			}
		}else{
			//初始化月份选择框
			var monthObj = document.getElementById("month");
			index = 0;
			for (var i = 1; i <= 12; i++)
			{
				var opt = new Option(i, i);
				monthObj.options[index++] = opt;
			}
		}
	}
	
	function allAddSalary() {
	    // 可在此处加载loading框
	    // ......
	    $.post(
			"addSalary.do",
			{
			    "monthFor" : $("#year").val()+$("#month").val(),
			    "workdayCount" : $("#workdayCount").val()
		    },
			function(data){
			    // 如果该月还没有分发过工资去发工资
			    if(data == 4){
					alert("该月已发过工资");
			    }else{
					location.href = "toAddSal.do";
			    }
			}
	    );
	}
</script>
</head>
<body onload="init();">
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="../login/logout.do">[退出]</a>            
        </div>
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../login/toIndex.do" class="index_off"></a></li>
		        <li><a href="../role/findAllRole.do" class="role_off"></a></li>
		        <li><a href="../emp/findAllEmp.do" class="admin_off"></a></li>
		        <li><a href="../dept/findAllDept.do" class="fee_off"></a></li>
		        <li><a href="../salary/findAllSalary.do" class="account_on"></a></li>
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
            <form action="" method="post">
                <!--查询-->
                <div class="search_add">
<!--                     <div> -->
<!--                        	按 -->
<!--                         <select id="selModules" class="select_search"> -->
<!--                             <option>部门经理</option> -->
<!--                             <option>管理员管理</option> -->
<!--                             <option>资费管理</option> -->
<!--                             <option>账务账号</option> -->
<!--                             <option>业务账号</option> -->
<!--                             <option>账单管理</option> -->
<!--                             <option>报表</option> -->
<!--                         </select>查询： -->
<!--                     </div> -->
<!--                     <div><input type="text" value="" class="text_search width200" /></div> -->
<!--                     <div><input type="button" value="搜索" class="btn_search"/></div> -->
                    <select class="select_search salary_add" id="year" onchange="dateChange();">
                    </select>年
                    <select class="select_search salary_add" id="month" style="width: 45px;">
                    </select>月/
					工作日：
					<select class="select_search" id="workdayCount" style="width: 45px;">
					</select>天
                    <input type="button" value="薪资发放" class="btn_add" onclick="allAddSalary();" />
                    <input type="button" value="返回" class="btn_add" onclick="javascript:history.back()" />
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data">
                	<table id="datalist">
                        <tr>
                            <th>员工号</th>
                            <th>员工姓名</th>
                            <th>考勤月份</th>
                            <th>基本薪资</th>
                            <th>加班补助</th>
                            <th>出差补助</th>
                            <th>罚金</th>
	        				<th>合计</th>
                        </tr>
                        <c:forEach items="${allSalInfo }" var="as">   
                        <tr>
                            <td>${as.empId }</td>
                            <td>${as.name }</td>
                            <td>${as.monthFor }</td>
                            <td>${as.basicSal }</td>
                            <td>${as.extraSal }</td>
                            <td>${as.travelSal }</td>
                            <td>${as.forfeit }</td>
                            <td>${as.sumSal }</td>
                        </tr>
                        </c:forEach>
                    </table>  
                </div>
                <!--分页-->
                <div id="pages">
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[LiangDuanDuan的毕业设计]</p>
            <p>版权所有(C)梁端端 </p>
        </div>
    </body>
</html>