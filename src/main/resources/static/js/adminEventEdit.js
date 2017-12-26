$('#daterange').daterangepicker({
	        autoApply:true
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

