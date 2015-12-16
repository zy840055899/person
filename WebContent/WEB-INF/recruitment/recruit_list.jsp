<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息列表</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
    function delRec(id){
    	var flag = confirm("确定要删除该招聘信息吗？");
    	if(flag){
    		location.href="delRecById.do?recId="+id;
    	}
    }
    
    function skipToPage(page){
    	var number = $("input[name='page']").val().trim();
    	var regex = /^\d{1,}$/;
		if(regex.test(number) && number >= 1 && number <= page){
			location.href="findAllRec.do?currentPage="+number;
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
		        <li><a href="../dept/findAllDept.do" class="fee_off"></a></li>
		        <li><a href="../salary/findAllSalary.do" class="account_off"></a></li>
		        <li><a href="../attend/findAllAttend.do" class="service_off"></a></li>
		        <li><a href="../train/findAllTrain.do" class="bill_off"></a></li>
		        <li><a href="../recruit/findAllRec.do" class="report_on"></a></li>
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
                   		<input type="button" value="新建招聘" class="btn_add" onclick="location.href='toAddRec.do';" />
                   	</c:if>
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="width70">职位</th>
                            <th>要求</th>
                            <th class="width70">招聘人数</th>
                            <th class="width80">最低学历</th>
                            <th>薪水</th>
                            <th class="width110">联系邮箱</th>
	        				<c:if test="${flag==1 }">
                           		<th class="width200">操作</th>
	                    	</c:if>
                        </tr>   
                        <c:if test="${fn:length(recruits) == 0}">
                        <tr>
                        	<td colspan="8"><span style="color: red;font-weight: bold;">暂时没有招聘信息</span></td>
                        </tr>
						</c:if>
                        <c:forEach items="${recruits}" var="r">
                        <tr>
                            <td>${r.job }</td>
                            <td>${r.content }</td>
                            <td>${r.number }</td>
                            <td>${r.lowestEdu }</td>
                            <td>${r.sal }</td>
                            <td>${r.email }</td>
	        				<c:if test="${flag==1 }">
	                            <td class="td_modi">
	                            	<input type="button" 
	                            	<c:if test="${r.status==1 }">
	                            		value="撤回" class="btn_start"
	                            	</c:if>
	                            	<c:if test="${r.status==0 }">
	                            		value="启用" class="btn_pause"
	                            	</c:if>
	                            	 onclick="location.href='changeStatus.do?recId=${r.recId }&status=${r.status }'"/>
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toModifyRec.do?recId=${r.recId}';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="delRec(${r.recId});" />
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
                    		<a href="findAllRec.do?currentPage=${page.currentPage-1}">上一页</a>
                    	</c:otherwise>
                    </c:choose>
					第<input class="page" type="text" name="page" value="${page.currentPage }"/>页（共<span class="pageNo">${page.totalPage }</span>页）
					<a href="javascript:skipToPage(${page.totalPage })" class="go">GO</a>
                    <c:choose>
                    	<c:when test="${page.currentPage==page.totalPage}">
                    		<a href="#">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="findAllRec.do?currentPage=${page.currentPage+1}">下一页</a>
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