/**
 * 
 */
$(window).on("load", function () {
	
	// Login Fail
	var text = $("#login-fail").html(); 
	console.log(text);
	if (text != '' && text != null) {		
		$('#btn-sign-in').click();			
	}
	
	
	$('header').height($(window).height() + 80);
	$('section .cut-top').css('border-right-width', $('section .cut-top').parent().width() + 'px');
	$('section .cut-bottom').css('border-left-width', $('section .cut-bottom').parent().width() + 'px');

	// Navbar Init
	$('nav').addClass('original').clone().insertAfter('nav').addClass('navbar-fixed-top').css('position', 'fixed').css('top', '0').css('margin-top', '0').removeClass('original');
	$('.mobile-nav ul').html($('nav .navbar-nav').html());
	$('nav.navbar-fixed-top .navbar-brand img').attr('src', $('nav.navbar-fixed-top .navbar-brand img').data("active-url"));

	// Onepage Nav
	$('.navbar.navbar-fixed-top .navbar-nav').onePageNav({
		currentClass: 'active',
		changeHash: false,
		scrollSpeed: 400,
		filter: ':not(.btn)'
	});
	
	// Sliders Init
	$('.owl-schedule').owlCarousel({
		singleItem: true,
		pagination: true
	});
	$('.owl-testimonials').owlCarousel({
		singleItem: true,
		pagination: true
	});
	$('.owl-twitter').owlCarousel({
		singleItem: true,
		pagination: true
	});
});

$(window).resize(function() {
		$('header').height($(window).height());
	});

//window scroll
function onScroll() {
	if ($(window).scrollTop() > 50) {
		$('nav.original').css('opacity', '0');
		$('nav.navbar-fixed-top').css('opacity', '1');
	} else {
		$('nav.original').css('opacity', '1');
		$('nav.navbar-fixed-top').css('opacity', '0');
	}
}

$(window).on('scroll', onScroll);

//show login form
function centerModal(){	
	$(this).css('display','block');
	var $dialog=$(this).find(".modal-dialog"),
	offset=($(window).height()-$dialog.height()) / 2,
	bottomMargin = parseInt($dialog.css('marginBottom'),10);
	
	if (offset < bottomMargin) offset = bottomMargin;
	$dialog.css("margin-top", offset);
	console.log("Center Model");
	$(".nav.navbar").css("z-index", "1");
}

$('.modal').on('show.bs.modal', centerModal);
$('.modal-popup .close-link').click(function(event){
	event.preventDefault();
	$('#loginModal').modal('hide');
});

$(window).on("resize", function() {
	$('.modal:visible').each(centerModal);
});