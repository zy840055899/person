<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪资查看</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
	
</script>
</head>
<body>
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
<!--                        	<span style="font-weight: bold;">我的薪资明细：</span>按 -->
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
                    <c:if test="${flag==1 }">
                    	<input type="button" value="薪资管理" class="btn_add" onclick="location.href='toAddSal.do';" />
                    </c:if>
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data">
                	<table id="datalist">
                        <tr>
                            <th>考勤月份</th>
                            <th>基本薪资</th>
                            <th>加班补助</th>
                            <th>出差补助</th>
                            <th>罚款</th>
                            <th>合计</th>
                        </tr>  
                        <c:if test="${fn:length(personalSalInfo) == 0}">
                       	<tr>
                       		<td colspan="6">
                       			<span class="pageNo">暂无薪资发放信息</span>
                       		</td>
                       	</tr>
                        </c:if>
                        <c:forEach items="${personalSalInfo }" var="ps">
                       	<tr>
                           <td>${ps.monthFor }</td>
                           <td>${ps.basicSal }</td>
                           <td>${ps.extraSal }</td>
                           <td>${ps.travelSal }</td>
                           <td>${ps.forfeit }</td>
                           <td>${ps.sumSal }</td>
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