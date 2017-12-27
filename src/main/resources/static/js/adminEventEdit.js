$('#daterange').daterangepicker({
	autoApply:true,
	locale: {
		format: 'MM/DD/YYYY'
	}
});



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



// UPDATE EVENT FUNCTION

$('#btn-update-event').on('click', function(){
	
	var eventId = $('#eventId').val();
	console.log(eventId);
	var name = $("#eventName").val();
	if (name.length == 0 || name == null) {
		toastr.warning("Name Of Event is required");
		$("#eventName").focus();
		return;
	}

	var date = $("#daterange").val();
	var description = $("#eventDescription").val();
	var isPublic = $('#cb-public').prop('checked');
	var categoryId = $('#select-type-event').val();

	
	var notPublic = !isPublic;

	var tokenDate = date.split(' - '); 
	var dateStart = tokenDate[0]; 
	var dateEnd = tokenDate[1]; 

	console.log(name);
	console.log(dateStart);
	console.log(dateEnd);
	console.log(description);
	console.log(notPublic);
	console.log(categoryId);

	

	var request = $.ajax({
		url: '/admin/api/event/update',
		data: {
			eventId: eventId,
			name: name,
			dateStart: dateStart,
			dateEnd: dateEnd,
			description: description,
			notPublic: notPublic,
			categoryId: categoryId
		},
		cached: false
	});

	request.done(function(msg){
		if (msg === 'success') {
			toastr.success('Update Successfully');
		}
		if (msg === 'fail') {
			toastr.error('Cannot Update');
		}
	});

	request.fail(function(msg){
		toastr.error('Cannot Update');
	});

	

});


// ADD NEW TIMELINE FUNCTION
$('#btn-new-timeline').on('click', function(){

	var eventId = $('#eventId').val();
	var datetime = $('#datetime').val(); 
	var name = $('#input-timeline-name').val();
	if (name.length == 0 || name == null) {

		toastr.warning('Timeline Name is required !');
		$('#input-timeline-name').focus();
		return;
	}
	var description = $('#input-timeline-description').val(); 

	var token = datetime.split(' - ');
	var start = token[0]; 
	var end = token[1]; 

	// console.log(start)
	// console.log(end);
	// console.log(name);
	// console.log(description);


	var request = $.ajax({
		url: '/admin/api/event/add/timeline',
		data: {
			name: name,
			description: description, 
			eventId: eventId,
			dateStart: start, 
			dateEnd: end,
		},
		cached: false
	}); 

	request.done(function(msg){
		console.log(msg)
			if (msg === 'success'){					
				location.reload();
			}
			else {
				toastr.error('Date invalid !');
			}

	});

	request.fail(function(msg){
		toastr.error('Add Fail');
		console.error(msg);
	});



	


});
