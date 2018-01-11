/**
 * 
 */ 
 $(document).ready(function(){
    loadCatergory();
	
	
	
	
});


    



loadCatergory = function(){
	var request = $.ajax({
			url: '/user/api/category/get/all',
			cached: false	
		}); 
		var select = $('#select-category');
		request.done(function(list){
			//console.log(list);
			if (list != null) {
				
				
				list.forEach(function(category, index){
					
					var node = $('<option>', {
						value: category.categoryId,
						text: category.name					
					});
					
					if (index == 0){
						console.log(0);
						node.attr('selected','selected');
					}
											
					
					select.append(node);				
					
				});
				
				
				var el = document.getElementById('select-category');  

			    var a = new selecty(el);
			}
			else {
				toastr.warning('There are nothing in list');
			}
			
		});
		
		request.fail(function(msg){
			toastr.error('Cannot Load Categories');
		})
}

