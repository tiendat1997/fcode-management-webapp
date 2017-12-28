//date time picker
$('#datetime').on('click', function(){
	$('.daterangepicker.dropdown-menu').addClass('z-index');
});

$('#datetime').daterangepicker({
    timePicker: true,
    timePickerIncrement: 30,   
    locale: {
        format: 'MM/DD/YYYY h:mm A'
    }  
});



// UPDATE TIMELINE FUNCTION 
$('#btn-update-timeline').on('click', function(){
	var datetime = $('#datetime').val();
	var token = datetime.split(' - ');
	var dateStart = token[0]; 
	var dateEnd = token[1]; 

	var name = $('#timelineName').val(); 
	if (name == null || name.length == 0 ){
		$('#timelineName').focus(); 
		toastr.warning('Timeline Name is required');
		return; 
	}

	var description = $('#timelinetDescription').val(); 
	var id = $('#timelineId').val(); 
	var eventId = $('#eventId').val(); 

	// console.log(id);
	// console.log(name);
	// console.log(description);
	// console.log(eventId);
	// console.log(eventId);
	// console.log(dateStart);
	// console.log(dateEnd);


	var request = $.ajax({
		url: '/admin/api/event/update/timeline',
		data: {
			id: id,
			name: name, 
			description: description, 
			eventId: eventId, 
			dateStart: dateStart,
			dateEnd: dateEnd
		},
		cached: false
	}); 
	request.done(function(msg){
		if (msg === 'success'){
			toastr.success("Update Successfully");
		}
		else {
			toastr.error("Update Fail");
		}
	}); 

	request.fail(function(msg){
		toastr.error("Update Fail");
	});

});
