/**
 * 
 */
$(function() {
		$('#datetimepicker1').datetimepicker({

			sideBySide : true
		});

		$("#submit").click(function() {
			$("[name=startDate]").val($('#datetimepicker1').data("date"));
			/*alert($("[name=startDate]").val());*/

			$("#myform").submit();

		});

	});
$(function() {
	$('#datetimepicker2').datetimepicker({

		sideBySide : true
	});

	$("#submit").click(function() {
		$("[name=regDate]").val($('#datetimepicker2').data("date"));
		/*alert($("[name=regDate]").val());*/

		$("#myform").submit();

	});

});
$(function() {
	$('#datetimepicker3').datetimepicker({

		sideBySide : true
	});

	$("#submit").click(function() {
		$("[name=eventDateAndTime]").val($('#datetimepicker3').data("date"));
		/*alert($("[name=regDate]").val());*/

		$("#myform").submit();

	});

});
$(function() {
	$('#datetimepicker4').datetimepicker({

		sideBySide : true
	});

	$("#submit").click(function() {
		$("[name=date]").val($('#datetimepicker4').data("date"));
		/*alert($("[name=regDate]").val());*/

		$("#myform").submit();

	});

});