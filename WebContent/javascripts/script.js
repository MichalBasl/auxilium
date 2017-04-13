$(document).ready( function() {
	$('tr.highlighted').hover( function() {
		$(this).css('background', '#FBF1D0')
	}, function() {
		$(this).css('background', 'transparent')
	});
});

$( function() {
	$('.date-pick').attr('readonly', true);
	$('.date-pick').datePicker().trigger('change');
});

function helpFormItem(t) {
	$.ajax( {
		type :"POST",
		url :"Main.action?helpFormItem",
		data :"formItem=" + $(t).attr("id"),
		success : function(data) {
			$("#helpFormItem").html(data);
		}
	});
}

function helpFormItemPicker(t) {
	$.ajax( {
		type :"POST",
		url :"Main.action?helpFormItem",
		data :"formItem=" + t,
		success : function(data) {
			$("#helpFormItem").html(data);
		}
	});
}
