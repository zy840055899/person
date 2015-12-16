<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门列表</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
    function delDept(id)
	{
		var flag = confirm("该部门的所有员工也将删除,确定删除吗？");
		if(flag){
			$.post(
				"delDeptById.do",
				{"deptId" : id},
				function(data){
					if(data == 0){
						location.href="findAllDept.do";
					}else{
						alert("不能删除您当前所在部门");
					}
				}
			);
		}
	}
    
    function skipToPage(page){
    	var number = $("input[name='page']").val().trim();
    	var regex = /^\d{1,}$/;
		if(regex.test(number) && number >= 1 && number <= page){
			location.href="findAllDept.do?currentPage="+number;
		}else{
			alert("请输入正确的页码！");
			$("input[name='page']").val();
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
		        <li><a href="../dept/findAllDept.do" class="fee_on"></a></li>
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
        <div id="main">
            <form action="" method="post">
                <!--查询-->
                <div class="search_add">
		       		<c:if test="${flag==1 }">
		       			<input type="button" value="新建部门" class="btn_add" onclick="location.href='toAddDept.do';" />
		       		</c:if>
                </div>
                <!--数据区域：用表格展示数据--> 
                <div id="data">
                    <table id="datalist">
                        <tr>
                            <th>部门名称</th>
                            <th>部门所在地</th>
                            <th>部门经理</th>
                            <th>部门人数</th>
	        				<c:if test="${flag==1 }">
	                    		<th>操作</th>
	                    	</c:if>
                        </tr>   
                        <c:forEach items="${depts}" var="d">
                        <tr>
                            <td>${d.deptName }</td>
                            <td>${d.deptAddress }</td>
                            <td>
                            	<c:if test="${empty d.name }"><span class="pageNo">暂未设置</span></c:if>
								<c:if test="${not empty d.name }">${d.name }</c:if>
                            </td>
                            <td>${d.empSum }</td>
	        				<c:if test="${flag==1 }">
		        				<td class="td_modi">
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toModifyDept.do?deptId=${d.deptId}';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="delDept(${d.deptId});" />
	                            </td>
	        				</c:if>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
                <!--分页-->
				<div id="pages">
                	<c:choose>
                    	<c:when test="${page.currentPage==1}">
                    		<a href="#">上一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="findAllDept.do?currentPage=${page.currentPage-1}">上一页</a>
                    	</c:otherwise>
                    </c:choose>
					第<input class="page" type="text" name="page" value="${page.currentPage }"/>页（共<span class="pageNo">${page.totalPage }</span>页）
					<a href="javascript:skipToPage(${page.totalPage })" class="go">GO</a>
                    <c:choose>
                    	<c:when test="${page.currentPage==page.totalPage}">
                    		<a href="#">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="findAllDept.do?currentPage=${page.currentPage+1}">下一页</a>
                    	</c:otherwise>
                    </c:choose>
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