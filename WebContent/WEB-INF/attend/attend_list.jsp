<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤</title>
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
		        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
		        <li><a href="../attend/findAllAttend.do" class="service_on"></a></li>
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
<!--                         	<span style="font-weight: bold;">审批考勤：</span>按 -->
<!--                         <select id="selModules" class="select_search"> -->
<!--                             <option>员工姓名</option> -->
<!--                             <option>日期</option> -->
<!--                             <option>审批状态</option> -->
<!--                         </select>查找： -->
<!--                     </div> -->
<!--                     <div><input type="text" value="" class="text_search width200" /></div> -->
<!--                     <div><input type="button" value="搜索" class="btn_search"/></div> -->
                    <input type="button" value="返回" class="btn_add" onclick="javascript:history.back();" />
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data"> 
                	<table id="datalist">
                        <tr>
                            <th>员工号</th>
                            <th>姓名</th>
                            <th>出勤日期</th>
                            <th>工作时长</th>
                            <th>加班时长</th>
                            <th>是否出差</th>
                            <th>备注</th>
	        				<th>操作</th>
                        </tr>  
                        <c:forEach items="${attends }" var="attend">
                        <tr>
                            <td>${attend.empId }</td>
                            <td>${attend.name }</td>
                            <td>${attend.dutyDate }</td>
                            <td>${attend.workTime }</td>
                            <td>${attend.extraTime }</td>
                            <td>${attend.isTravel }</td>
                            <td>${attend.remark }</td>
                    		<td class="td_modi">
                    		<c:if test="${attend.isApproved==0 }">
                    			<input type="button" value=" 通过" class="btn_refuse" onclick="location.href='checkAttend.do?attendId=${attend.attendId}&status=1'" />
                    		</c:if>
                    		<c:if test="${attend.isApproved==1 }">
                    			<input type="button" value=" 拒绝" class="btn_approve" onclick="location.href='checkAttend.do?attendId=${attend.attendId}&status=2'" />
                    		</c:if>
                    		<c:if test="${attend.isApproved==2 }">
                    			<input type="button" value=" 通过" class="btn_refuse" onclick="location.href='checkAttend.do?attendId=${attend.attendId}&status=1'" />
                    		</c:if>
                            </td>
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