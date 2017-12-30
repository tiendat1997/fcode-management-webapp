/* off-canvas sidebar toggle */
$('[data-toggle=offcanvas]').click(function() {
	$('#main').toggleClass('col-md-12');
	$('#profile-header').toggleClass('displayNone');	
    $('.row-offcanvas').toggleClass('active');
    $('span.collapse').toggleClass('in');
    


    
});

$('[data-toggle=offcanvas-in]').click(function() {	
	console.log($('#main'));
    $('.row-offcanvas').addClass('active');
    $('span.collapse').addClass('in');

});