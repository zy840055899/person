<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
	function deleteRole(id) {
	    var flag = confirm("确定要删除此角色吗？");
	    if(flag){
	    	$.post(
    			"deleteRole.do",
    			{"roleId" : id},
    			function(data) {
    				if(data == 0){
    					location.href="findAllRole.do";	
    				}else if(data == 4){
    					alert("该角色仍有员工使用，不可删除");
    				}else{
    					location.href="../login/noPower.do";
    				}
    			}
    		);
	    }
	}
	
	function skipToPage(page){
    	var number = $("input[name='page']").val().trim();
    	var regex = /^\d{1,}$/;
		if(regex.test(number) && number >= 1 && number <= page){
			location.href="findAllRole.do?currentPage="+number;
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
	<!--导航区域开始-->
	<div id="navi">                        
		<ul id="menu">
	        <li><a href="../login/toIndex.do" class="index_off"></a></li>
	        <li><a href="../role/findAllRole.do" class="role_on"></a></li>
	        <li><a href="../emp/findAllEmp.do" class="admin_off"></a></li>
	        <li><a href="../dept/findAllDept.do" class="fee_off"></a></li>
	        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
	        <li><a href="../attend/findAllAttend.do" class="service_off"></a></li>
	        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
	        <li><a href="../recruit/findAllRec.do" class="report_off"></a></li>
	        <li><a href="../user/toModifyUserInfo.do" class="information_off"></a></li>
	        <li><a href="../user/toModifyPwd.do" class="password_off"></a></li>
        </ul>
	</div>
	<!--主要区域开始-->
	<div id="main">
	    <form action="" method="">
	        <!--查询-->
	        <div class="search_add">
        		<c:if test="${flag==1 }">
		            <input type="button" value="增加角色" class="btn_add" onclick="location.href='toAddRole.do';" />
        		</c:if>
	        </div>  
			<div id="data">                
				<table id="datalist">
	                <tr>                            
						<th>角色类型</th>
						<th>角色名称</th>
	                    <th class="width600">拥有的权限</th>
        				<c:if test="${flag==1 }">
                    		<th class="td_modi">权限操作</th>
                    	</c:if>
	                </tr>                      
	                <c:forEach items="${roles}" var="r">
	                <tr>
	                    <td>${r.roleId}</td>
	                    <td>${r.roleName}</td>
	                    <td>
	                    	<c:forEach items="${r.modules}" var="m">
	                    		${m.moduleName}&nbsp;
	                    	</c:forEach>
	                    </td>
        				<c:if test="${flag==1 }">
        				<td>
	                        <input type="button" value="修改" class="btn_modify" onclick="location.href='toModifyRole.do?roleId=${r.roleId}';"/>
	                        <input type="button" value="删除" class="btn_delete" onclick="deleteRole(${r.roleId});" />
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
                   		<a href="findAllRole.do?currentPage=${page.currentPage-1}">上一页</a>
                   	</c:otherwise>
                   </c:choose>
				第<input class="page" type="text" name="page" value="${page.currentPage }"/>页（共<span class="pageNo">${page.totalPage }</span>页）
				<a href="javascript:skipToPage(${page.totalPage })" class="go">GO</a>
                   <c:choose>
                   	<c:when test="${page.currentPage==page.totalPage}">
                   		<a href="#">下一页</a>
                   	</c:when>
                   	<c:otherwise>
                   		<a href="findAllRole.do?currentPage=${page.currentPage+1}">下一页</a>
                   	</c:otherwise>
                   </c:choose>
	        </div>
	    </form>
	</div>
	<!--主要区域结束-->
	<div id="footer">
	    <span>[LiangDuanDuan的毕业设计]</span>
	    <br/>
	    <span>版权所有(C)梁端端 </span>
	</div>
</body>
</html>