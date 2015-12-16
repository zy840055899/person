<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训列表</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
    function delTrain(id){
    	var flag = confirm("确定要删除该培训吗？");
    	if(flag){
    		location.href="delTrainById.do?trainId="+id;
    	}
    }
    
    function skipToPage(page){
    	var number = $("input[name='page']").val().trim();
    	var regex = /^\d{1,}$/;
		if(regex.test(number) && number >= 1 && number <= page){
			location.href="findAllTrain.do?currentPage="+number;
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
		        <li><a href="../train/findAllTrain.do" class="bill_on"></a></li>
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
                    	<input type="button" value="新建培训" class="btn_add" onclick="location.href='toAddTrain.do';" />
                  	</c:if>
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>培训名称</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>培训人数</th>
                            <th>培训教师</th>
                            <th>备注</th>
                            <c:if test="${flag==1 }">
                            <th>操作</th>
	                    	</c:if>
                        </tr>   
                        <c:if test="${fn:length(trains) == 0}">
                        <tr>
                        	<td colspan="8"><span style="color: red;font-weight: bold;">暂时没有培训</span></td>
                        </tr>
						</c:if>
                        <c:forEach items="${trains}" var="t">
                        <tr>
                            <td>${t.tname }</td>
                            <td>${t.startDate }</td>
                            <td>${t.endDate }</td>
                            <td>${t.number }</td>
                            <td>${t.teacher }</td>
                            <td>${t.descr }</td>
                            <c:if test="${flag==1 }">
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toModifyTrain.do?trainId=${t.trainId}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="delTrain(${t.trainId});" />
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
                    		<a href="findAllTrain.do?currentPage=${page.currentPage-1}">上一页</a>
                    	</c:otherwise>
                    </c:choose>
					第<input class="page" type="text" name="page" value="${page.currentPage }"/>页（共<span class="pageNo">${page.totalPage }</span>页）
					<a href="javascript:skipToPage(${page.totalPage })" class="go">GO</a>
                    <c:choose>
                    	<c:when test="${page.currentPage==page.totalPage}">
                    		<a href="#">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="findAllTrain.do?currentPage=${page.currentPage+1}">下一页</a>
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