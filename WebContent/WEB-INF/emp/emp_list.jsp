<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script language="javascript" type="text/javascript">
    //显示角色详细信息
    function showDetail(flag, a) {
        var detailDiv = a.parentNode.getElementsByTagName("div")[0];
        if (flag) {
            detailDiv.style.display = "block";
        }
        else
            detailDiv.style.display = "none";
    }
    
    function delEmp(id){
    	var flag = confirm("确定要删除该员工吗？");
    	if(flag){
    	    $.post(
    			"delEmpById.do",
    			{"empId" : id},
    			function(data){
    			    if(data == 0){
    					location.href="findAllEmp.do";
    			    }else{
    					alert("不可以删除自己");
    			    }
    			}
    	    );
    	}
    }
    
    function skipToPage(page){
    	var number = $("input[name='page']").val().trim();
    	var regex = /^\d{1,}$/;
		if(regex.test(number) && number >= 1 && number <= page){
			location.href="findAllEmp.do?currentPage="+number;
		}else{
			alert("请输入正确的页码！");
			$("input[name='page']").val();
		}
    }
    
    // 重置密码
    function resetPwd() {
        alert("请至少选择一条数据！");
    }
   
    // 全选
    function selectAdmins(inputObj) {
        var inputArray = document.getElementById("datalist").getElementsByTagName("input");
        for (var i = 1; i < inputArray.length; i++) {
            if (inputArray[i].type == "checkbox") {
                inputArray[i].checked = inputObj.checked;
            }
        }
    }
    
    function changeSearchText(){
		var key = $("#selModules").val();
		if("name" == key){
		    $("#textSearch").attr("placeholder","请输入员工姓名");
		}
		if("gender" == key){
		    $("#textSearch").attr("placeholder","请输入“男”或“女”");
		}
		if("dept" == key){
		    $("#textSearch").attr("placeholder","请输入部门名称");
		}
    }
    
    function searchEmps(){
		var key = $("#selModules").val();
		var value = $("#textSearch").val();
		var name = "";
		var gender = "";
		var dept = "";
		if("name" == key)
		    name = value;
		if("gender" == key)
		    gender = value;
		if("dept" == key)
		    dept = value;
		location.href="findAllEmp.do?name="+name+"&gender="+gender+"&dept="+dept;
    }
</script>
</head>
<body onload="changeSearchText()">
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="../login/logout.do">[退出]</a>            
        </div>
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
        <div id="main">
            <form action="" method="post">
                <!--查询-->
                <div class="search_add">
                    <div>
                        	按
                        <select id="selModules" class="select_search" onchange="changeSearchText();" style="width: 70px">
                            <option value="name">姓名</option>
                            <option value="gender">性别</option>
                            <option value="dept">部门</option>
                        </select>查找：
                    </div>
                    <div><input type="text" class="text_search width200" id="textSearch" placeholder=""/></div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="searchEmps();"/></div>
       				<c:if test="${flag==1 }">
<!--                    		 <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" /> -->
                   		 <input type="button" value="添加员工" class="btn_add" onclick="location.href='toAddEmp.do';" />
                   	</c:if>
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
	        				<c:if test="${flag==1 }">
	                    		 <th class="th_select_all">
	                                <input type="checkbox" onclick="selectAdmins(this);" />
	                                <span>全选</span>
	                             </th>
	                    	</c:if>
	                    	<th>员工号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>部门</th>
                            <th>职位</th>
                            <th>手机号</th>
                            <th>邮箱</th>
                            <th class="width100">更多</th>
	        				<c:if test="${flag==1 }">
	        					<th>操作</th>
	                    	</c:if>
                        </tr>   
                        <c:forEach items="${emps}" var="e">
                        <tr>
                        	<c:if test="${flag==1 }">
                            <td><input type="checkbox" /></td>
                            </c:if>
                            <td>${e.empId }</td>
                            <td>${e.name }</td>
                            <td>${e.gender }</td>
                            <td>${e.deptName }</td>
                            <td>${e.title }</td>
                            <td>${e.mobile }</td>
                            <td>${e.email }</td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">更多...</a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
			                        <table>
			                        	<tr>
			                        		<td>籍贯</td>
			                        		<td>生日</td>
			                        		<td>工作电话</td>
			                        		<td>学历</td>
			                        		<td>婚姻状态</td>
			                        		<td>身份证号</td>
			                        		<td>住址</td>
			                        		<td>入职时间</td>
			                        		<td>角色类型</td>
			                        	</tr>
			                        	<tr>
			                        		<td>${e.nativee }</td>
			                        		<td>${e.birthday }</td>
			                        		<td>${e.workPhone }</td>
			                        		<td>${e.education }</td>
			                        		<td>${e.marry }</td>
			                        		<td>${e.identityId }</td>
			                        		<td>${e.address }</td>
			                        		<td>${e.enterDate }</td>
			                        		<td>${e.roleType }</td>
			                        	</tr>
			                        </table>           
                                </div>
                            </td>
	        				<c:if test="${flag==1 }">
	                    		 <td class="td_modi">
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toEmpModify.do?empId=${e.empId}';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="delEmp(${e.empId})" />
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
                    		<a href="findAllEmp.do?currentPage=${page.currentPage-1}">上一页</a>
                    	</c:otherwise>
                    </c:choose>
					第<input class="page" type="text" name="page" value="${page.currentPage }"/>页（共<span class="pageNo">${page.totalPage }</span>页）
					<a href="javascript:skipToPage(${page.totalPage })" class="go">GO</a>
                    <c:choose>
                    	<c:when test="${page.currentPage==page.totalPage}">
                    		<a href="#">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="findAllEmp.do?currentPage=${page.currentPage+1}">下一页</a>
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