var myValidation = {};
myValidation.settings = {};
myValidation.settings.showInfo = function(ele, mes){
	alert(mes);
}

myValidation.commonValid = function(id, settings) {
	$.extend(myValidation.settings,settings);	
	var result = true;
	$("#" + id + " .notEmpty").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(item);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (!value || value.trim().length == 0) {
			myValidation.settings.showInfo($this, placeholder+"\u4e0d\u80fd\u4e3a\u7a7a");
			result = false;
			return false
		}
	});
	if (!result)
		return false;
	$("#" + id + " .price").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(this);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (isNaN(value)) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u4e3a\u6570\u5b57");
			result = false;
			return false
		}
		value = parseFloat(value);
		if (value < 0) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u5927\u4e8e0");
			result = false;
			return false
		}
	});
	if (!result)
		return false;
	$("#" + id + " .rate").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(this);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (isNaN(value)) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u4e3a\u6570\u5b57");
			result = false;
			return false
		}
		value = parseFloat(value);
		if (value > 1) {
			myValidation.settings.showInfo($this, "\u4e0d\u80fd\u5927\u4e8e1");
			result = false;
			return false
		}
	});
	if (!result)
		return false;
	$("#" + id + " .positive").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(this);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (isNaN(value)) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u4e3a\u6570\u5b57");
			result = false;
			return false
		}
		value = parseFloat(value);
		if (value < 0) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u4e3a\u6b63\u6570");
			result = false;
			return false
		}
	});
	if (!result)
		return false;
	$("#" + id + " .integer").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(this);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (isNaN(value)) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u4e3a\u6570\u5b57");
			result = false;
			return false
		}
		var fValue = parseFloat(value);
		var iValue = parseInt(value, 10);
		if (fValue != iValue) {
			myValidation.settings.showInfo($this, placeholder+"\u5fc5\u987b\u4e3a\u6574\u6570");
			result = false;
			return false
		}
	});
	if (!result)
		return false;
	$("#" + id + " .phone").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(this);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (!CommValidate.checkPhone(value)) {
			myValidation.settings.showInfo($this, placeholder+"手机号码格式错误");
			result = false;
			return false
		}
		
	});
	if (!result)
		return false;
	$("#" + id + " .idCard").each(function(i, item) {
		if (!result)
			return false;
		var $this = $(this);
		var value = $this.val();
		var placeholder = $this.attr("placeholder");
		if (!CommValidate.checkCard(value)) {
			myValidation.settings.showInfo($this, placeholder+"身份证格式错误");
			result = false;
			return false
		}
	});
	if (!result)
		return false;
	
	
	if (!result)
		return false;
	return true
};
