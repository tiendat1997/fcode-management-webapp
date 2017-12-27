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




$('#btn-update-event').on('click', function(){
	
	var eventId = $('#eventId').val();
	console.log(eventId);
	var name = $("#eventName").val();
	var date = $("#daterange").val();
	var description = $("#eventDescription").val();
	var isPublic = $('#cb-public').prop('checked');
	
	var notPublic = !isPublic;

	var tokenDate = date.split(' - '); 
	var dateStart = tokenDate[0]; 
	var dateEnd = tokenDate[1]; 

	console.log(name);
	console.log(dateStart);
	console.log(dateEnd);
	console.log(description);
	console.log(notPublic);

	

	var request = $.ajax({
		url: '/admin/api/event/update',
		data: {
			eventId: eventId,
			name: name,
			dateStart: dateStart,
			dateEnd: dateEnd,
			description: description,
			notPublic: notPublic
		},
		cached: false
	});

	request.done(function(msg){
		console.log(msg);		
	});

	request.fail(function(msg){
		console.log(msg);
	});





	

});
