String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "")
};
String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g, "")
};
String.prototype.rtrim = function() {
    return this.replace(/(\s*$)/g, "")
};
function CommValidate() {}
CommValidate.isNumber = function(a) {
    return a.match(/\D/) == null
};
CommValidate.isDouble = function(a) {
    if (a.toString().match(/^[-+]?\d+(\.\d+){1}$/g) == null) return false;
    return true
};
CommValidate.isPositiveDouble = function(a) {
    if (a.match(/^[+]?\d+(\.\d+){1}$/g) == null) return false;
    return true
};
CommValidate.checkNo = function(a) {
    var b = /^[A-Za-z0-9]+$/;
    return b.test(a)
};
CommValidate.isInteger = function(a) {
    if (a.toString().match(/^[-+]?\d+$/) == null) return false;
    return true
};
CommValidate.isPositiveInteger = function(a) {
    if (a.match(/^[+]?\d*$/) == null) return false;
    return true
};
CommValidate.isRealname = function(b) {
    var a = /^[\u4e00-\u9fa5]+$/;
    return a.test(b)
};
CommValidate.isUsername = function(b) {
    if (b.match(/\D/) == null) return false;
    var a = /^\w+$/;
    return a.test(b)
};
CommValidate.isPassword = function(a) {
    var b = /^[\w~!@#$%^&*()_+-=\[\]{};'\:"|,.`\/<>?\\]+$/;
    if (!b.test(a)) return false;
    return true
};
CommValidate.isNullStr = function(a) {
    return a.trim().length == 0 ? true: false
};
CommValidate.isNotNullStr = function(a) {
    return ! CommValidate.isNullStr(a)
};
CommValidate.isStr = function(a) {
    return /[^\x00-\xff]/g.test(a) ? false: true
};
CommValidate.contrainChinese = function(a) {
    if (escape(a).indexOf("%u") != -1) return true;
    else return false
};
CommValidate.checkPassword = function(a) {
    if (escape(a).indexOf("%u") > -1) return false;
    if (a.indexOf(" ") > -1) return false
};
CommValidate.checkPsswEnglish = function(b) {
    var a = /^(?![0-9]*$)/;
    return a.test(b)
};
CommValidate.checkMoney = function(b) {
    var a = /^\d{1,12}(?:\.\d{1,4})?$/;
    return a.test(b)
};
CommValidate.checkEmail = function(a) {
    var b = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    return b.test(a)
};
CommValidate.checkTel = function(a) {
    var b = /^([0-9]|[-])+$/g;
    if (a.length < 7 || a.length > 18) return false;
    else return b.exec(a)
};
CommValidate.checkPhone = function(a) {
    var b = /^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/;
    return b.test(a)
};
CommValidate.checkCard = function(c) {
    var b = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    var a = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Za-z])$/;
    if (c.match(b) == null && c.match(a) == null) return false;
    else return true
};
CommValidate.checkIPAddress = function(b) {
    var a = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    if (b.match(a) == null) return false;
    else return true
};
CommValidate.checkURL = function(a) {
    if (a.match(/^(http|https|ftp):\/\/([\w-]+\.)+[\w-]+(\/[\w-./ ? %&=] * ) ? $ / ) == null) return false;
    else return true
};CommValidate.checkQuote = function(c) {
    var a = new Array("~", "`", "!", "@", "#", "$", "%", "^", "\x26", "*", "{", "}", "[", "]", "(", ")");
    a.push(":", ";", "'", "|", "\\", "\x3c", "\x3e", "?", "/", "\x3c\x3c", "\x3e\x3e", "||", "//");
    a.push("admin", "administrators", "administrator", "\u7ba1\u7406\u5458", "\u7cfb\u7edf\u7ba1\u7406\u5458");
    a.push("select", "delete", "update", "insert", "create", "drop", "alter", "trancate");
    a.push("\u00b7");
    c = c.toLowerCase();
    for (var b = 0; b < a.length; b++) if (c.indexOf(a[b]) >= 0) return true;
    return false
};CommValidate.checkQuote2 = function(c) {
    var a = new Array("~", "`", "!", "@", "#", "$", "%", "^", "\x26", "*", "{", "}", "[", "]", "\uff08", "\uff09");
    a.push(":", ";", "'", "|", "\\", "\x3c", "\x3e", "?", "/", "\x3c\x3c", "\x3e\x3e", "||", "//");
    a.push("admin", "administrators", "administrator", "\u7ba1\u7406\u5458", "\u7cfb\u7edf\u7ba1\u7406\u5458");
    a.push("select", "delete", "update", "insert", "create", "drop", "alter", "trancate");
    a.push("\u00b7");
    c = c.toLowerCase();
    for (var b = 0; b < a.length; b++) if (c.indexOf(a[b]) >= 0) return true;
    if (c.indexOf("(") > 0 && c.indexOf(")") > c.indexOf("(") + 1) return false;
    if (c.indexOf("(") >= 0) return true;
    if (c.indexOf(")") >= 0) return true;
    return false
};CommValidate.Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];CommValidate.ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2];CommValidate.IdCardValidate = function(a) {
    if (a.length == 15) return CommValidate._isValidityBrithBy15IdCard(a);
    else if (a.length == 18) {
        var b = a.split("");
        if (CommValidate._isValidityBrithBy18IdCard(a) && CommValidate._isTrueValidateCodeBy18IdCard(b)) return true;
        else return false
    } else return false
};CommValidate._isValidityBrithBy15IdCard = function(d) {
    var c = d.substring(6, 8);
    var e = d.substring(8, 10);
    var a = d.substring(10, 12);
    var b = new Date(c, parseFloat(e) - 1, parseFloat(a));
    if (b.getYear() != parseFloat(c) || b.getMonth() != parseFloat(e) - 1 || b.getDate() != parseFloat(a)) return false;
    else return true
};CommValidate._isValidityBrithBy18IdCard = function(b) {
    var d = b.substring(6, 10);
    var e = b.substring(10, 12);
    var a = b.substring(12, 14);
    var c = new Date(d, parseFloat(e) - 1, parseFloat(a));
    if (c.getFullYear() != parseFloat(d) || c.getMonth() != parseFloat(e) - 1 || c.getDate() != parseFloat(a)) return false;
    else return true
};CommValidate._isTrueValidateCodeBy18IdCard = function(a) {
    var c = 0;
    if (a[17].toLowerCase() == "x") a[17] = 10;
    for (var b = 0; b < 17; b++) c += CommValidate.Wi[b] * a[b];
    valCodePosition = c % 11;
    if (a[17] == CommValidate.ValideCode[valCodePosition]) return true;
    else return false
};
function CommTool() {}
CommTool.getBrowserName = function() {
    var a = {};
    var b = navigator.userAgent.toLowerCase();
    var c; (c = b.match(/rv:([\d.]+)\) like gecko/)) ? a.ie = c[1] : (c = b.match(/msie ([\d.]+)/)) ? a.ie = c[1] : (c = b.match(/firefox\/([\d.]+)/)) ? a.firefox = c[1] : (c = b.match(/chrome\/([\d.]+)/)) ? a.chrome = c[1] : (c = b.match(/opera.([\d.]+)/)) ? a.opera = c[1] : (c = b.match(/version\/([\d.]+).*safari/)) ? a.safari = c[1] : 0;
    if (a.ie) return "ie";
    if (a.firefox) return "firefox";
    if (a.chrome) return "chrome";
    if (a.opera) return "opera";
    if (a.safari) return "safari"
};CommTool.getBrowserVersion = function() {
    var a = {};
    var b = navigator.userAgent.toLowerCase();
    var c; (c = b.match(/rv:([\d.]+)\) like gecko/)) ? a.ie = c[1] : (c = b.match(/msie ([\d.]+)/)) ? a.ie = c[1] : (c = b.match(/firefox\/([\d.]+)/)) ? a.firefox = c[1] : (c = b.match(/chrome\/([\d.]+)/)) ? a.chrome = c[1] : (c = b.match(/opera.([\d.]+)/)) ? a.opera = c[1] : (c = b.match(/version\/([\d.]+).*safari/)) ? a.safari = c[1] : 0;
    if (a.ie) return a.ie;
    if (a.firefox) return a.firefox;
    if (a.chrome) return a.chrome;
    if (a.opera) return sys.opera;
    if (a.safari) return a.safari
};CommTool.toFixed = function(d) {
    d = d.toString();
    var c = parseFloat(d);
    var b = c.toString().indexOf(".");
    if (b != -1) {
        var a = d.substring(b + 1, c.toString().length);
        if (a.length > 2) return c.toFixed(2)
    }
    return c
};CommTool.hideMobile = function(b) {
    var a = b.length;
    if (a - 8 <= 0) return b;
    var c = b.substr(0, 3) + "****" + b.substr(7, a - 7);
    return c
};CommTool.hideCardId = function(b) {
    var a = b.length;
    if (a == 15) {
        var c = b.substr(0, 8) + "****" + b.substr(12, a - 12);
        return c
    } else {
        var c = b.substr(0, 6) + "*********" + b.substr(15, a - 15);
        return c
    }
};CommTool.fbBackspace = function(h) {
    var d = h || window.event;
    var g = d.target || d.srcElement;
    var c = g.type || g.getAttribute("type");
    var f = g.getAttribute("readonly");
    var i = g.getAttribute("enabled");
    f = f == null ? false: f;
    i = i == null ? true: i;
    var b = d.keyCode == 8 && (c == "password" || c == "text" || c == "textarea") && (f == true || i != true) ? true: false;
    var a = d.keyCode == 8 && c != "password" && c != "text" && c != "textarea" ? true: false;
    if (a) return false;
    if (b) return false
};CommTool.convertUrl = function(a) {
    if (/(http:\/\/)?hylc.com(:)?(\d)*\//.test(a)) {
        a = a.replace(/(http:\/\/)?hylc.com(:)?(\d)*\//, "");
        return a.replace(/;JSESSIONID=[A-Za-z0-9-]{36}/, "")
    }
    return a.replace(/;JSESSIONID=[A-Za-z0-9-]{36}/, "")
};Date.prototype.Format = function(a) {
    var c = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        S: this.getMilliseconds()
    };
    if (/(y+)/.test(a)) a = a.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var b in c) if ((new RegExp("(" + b + ")")).test(a)) a = a.replace(RegExp.$1, RegExp.$1.length == 1 ? c[b] : ("00" + c[b]).substr(("" + c[b]).length));
    return a
};CommTool.beforeNow = function(b) {
    var e = (new Date).Format("yyyy-MM-dd");
    var d = b.Format("yyyy-MM-dd");
    var c = e.split("-");
    var a = d.split("-");
    return [Date.parse(new Date(c[0], c[1], c[2])) <= Date.parse(new Date(a[0], a[1], a[2]))]
};CommTool.bfNow = function(b) {
    var e = (new Date).Format("yyyy-MM-dd");
    var d = b.Format("yyyy-MM-dd");
    var c = e.split("-");
    var a = d.split("-");
    return [Date.parse(new Date(c[0], c[1], c[2])) >= Date.parse(new Date(a[0], a[1], a[2]))]
};
function getDateDiff(a, e) {
    var c = a.getTime();
    var b = e.getTime();
    var d = Math.abs(c - b) / (1E3 * 60 * 60 * 24);
    return d
}
function allTrim(str) {
    str = str.replace(/\s+/g, "");
    return str
}
function trim(s) {
    return rtrim(ltrim(s))
}
function ltrim(s) {
    return s.replace(/^\s*/, "")
}
function rtrim(s) {
    return s.replace(/\s*$/, "")
}
function isPhone(s) {
    s = allTrim(s);
    var p = /^1[3|4|5|7|8][0-9]\d{8}$/;
    return p.test(s)
};
// 英文字母跟数字
function isLetterAndNum(value){
    var strExp=/^[A-Za-z0-9]+$/;
   if(strExp.test(value)){
	   return true;
   }else{
	   return false;
   }
}
// 检查银行卡
function checkBankCard(bankno){
	if (bankno.length<16 || bankno.length >19) {
		alert("银行卡号长度必须在16到19之间");
		return false;
	}
	var num = /^\d*$/;  // 全数字
	if (!num.exec(bankno)) {
		alert("银行卡号必须全为数字");
		return false;
	}
	// 开头6位
	var strBin="10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";    
	if (strBin.indexOf(bankno.substring(0, 2))== -1) {
		alert("银行卡号开头6位不符合规范");
		return false;
	}
    var lastNum=bankno.substr(bankno.length-1,1);// 取出最后一位（与luhm进行比较）

    var first15Num=bankno.substr(0,bankno.length-1);// 前15或18位
    var newArr=new Array();
    for(var i=first15Num.length-1;i>-1;i--){    // 前15或18位倒序存进数组
        newArr.push(first15Num.substr(i,1));
    }
    var arrJiShu=new Array();  // 奇数位*2的积 <9
    var arrJiShu2=new Array(); // 奇数位*2的积 >9
    
    var arrOuShu=new Array();  // 偶数位数组
    for(var j=0;j<newArr.length;j++){
        if((j+1)%2==1){// 奇数位
            if(parseInt(newArr[j])*2<9)
            arrJiShu.push(parseInt(newArr[j])*2);
            else
            arrJiShu2.push(parseInt(newArr[j])*2);
        }
        else // 偶数位
        arrOuShu.push(newArr[j]);
    }
    
    var jishu_child1=new Array();// 奇数位*2 >9 的分割之后的数组个位数
    var jishu_child2=new Array();// 奇数位*2 >9 的分割之后的数组十位数
    for(var h=0;h<arrJiShu2.length;h++){
        jishu_child1.push(parseInt(arrJiShu2[h])%10);
        jishu_child2.push(parseInt(arrJiShu2[h])/10);
    }        
    
    var sumJiShu=0; // 奇数位*2 < 9 的数组之和
    var sumOuShu=0; // 偶数位数组之和
    var sumJiShuChild1=0; // 奇数位*2 >9 的分割之后的数组个位数之和
    var sumJiShuChild2=0; // 奇数位*2 >9 的分割之后的数组十位数之和
    var sumTotal=0;
    for(var m=0;m<arrJiShu.length;m++){
        sumJiShu=sumJiShu+parseInt(arrJiShu[m]);
    }
    
    for(var n=0;n<arrOuShu.length;n++){
        sumOuShu=sumOuShu+parseInt(arrOuShu[n]);
    }
    
    for(var p=0;p<jishu_child1.length;p++){
        sumJiShuChild1=sumJiShuChild1+parseInt(jishu_child1[p]);
        sumJiShuChild2=sumJiShuChild2+parseInt(jishu_child2[p]);
    }      
    // 计算总和
    sumTotal=parseInt(sumJiShu)+parseInt(sumOuShu)+parseInt(sumJiShuChild1)+parseInt(sumJiShuChild2);
    
    // 计算Luhm值
    var k= parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;        
    var luhm= 10-k;
    
    if(lastNum==luhm){
    return true;
    }
    else{
  // alert("银行卡号必须符合Luhm校验");
 	 alert("银行卡号输入有误");
    return false;
    }        
}
// 简单验证是否身份证号
function isCardNo(card)  
{  
   //  身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(card) === false)  
   {   
       return  false;  
   }
return true;  
}
function updateUrl(url,key){
    var key= (key || 't') +'=';  //默认key是"t",可以传入key自定义
    var reg=new RegExp(key+'\\d+');  //正则：t=1472286066028
    var timestamp=+new Date();
    if(url.indexOf(key)>-1){ //有时间戳，直接更新
        return url.replace(reg,key+timestamp);
    }else{  //没有时间戳，加上时间戳
        if(url.indexOf('\?')>-1){
            var urlArr=url.split('\?');
            if(urlArr[1]){
                return urlArr[0]+'?'+key+timestamp+'&'+urlArr[1];
            }else{
                return urlArr[0]+'?'+key+timestamp;
            }
        }else{
            if(url.indexOf('#')>-1){
                return url.split('#')[0]+'?'+key+timestamp+location.hash;
            }else{
                return url+'?'+key+timestamp;
            }
        }
    }
}
//重新加载页面
function reload(){
	window.location.href=updateUrl(window.location.href);
}