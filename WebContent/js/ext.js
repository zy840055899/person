/** 外部的js校验格式文件 2015年4月26日 12:00:01*/
// 检查身份证号格式
function checkIdentityId(id) {
    var regexp = /^\d{17}[xX0-9]{1}$/;
    if(regexp.test(id)){
	return true;
    }
    alert("请输入格式正确的身份证号码");
    return false;
}

// 检查手机号码格式
function checkMobile(mobile) {
    var regex = /^[1-9][0-9]{10}$/;
    if(regex.test(mobile)){
	return true;
    }
    alert("请输入格式正确的手机号码");
    return false;
}

// 检查真实姓名（2到4个字）
function checkTrueName(name) {
    var regex = /^[\u4e00-\u9fa5]{2,4}$/;
    if(regex.test(name)){
	return true;
    }
    alert("请输入格式正确的姓名");
    return false;
}

// 检查籍贯(2到15个汉字)
function checkNative(native) {
    var regex = /^[\u4e00-\u9fa5]{2,15}$/;
    if(regex.test(native)){
	return true;
    }
    alert("请输入格式正确的籍贯");
    return false;
}

// email格式检查
function checkEmail(email) {
    var regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(regex.test(email)){
	return true;
    }
    alert("请输入格式正确的邮箱");
    return false;
}

// 登录密码检查(4~8位)
function checkLoginPwd(pwd) {
    var regex = /^[A-Za-z0-9]{4,8}$/;
    if(regex.test(pwd)){
	return true;
    }
    alert("请输入格式正确的登录密码");
    return false;
}

// 检查培训人数
function checkCount(count){
    var regex = /^[1-9][0-9]{0,2}$/;
    if(regex.test(count)){
	return true;
    }
    alert("请输入合理的培训人数");
    return false;
}

// 检查培训开始时间和结束时间是否合理
function checkTime(startDate, endDate){
    var start = startDate.replace(/-/g, "");
    var end = endDate.replace(/-/g, "");
    if((end-start) > 0){
	return true;
    }else{
	alert("培训结束时间必须晚于开始时间");
	return false;
    }
}

