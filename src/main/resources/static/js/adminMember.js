// Handle Create new Account

$(document).ready(function(){
    
    // VALIDATION
    isEmpty = function(value){
        if (value === null || value.length == 0) return true; 
        return false;
    }
    // END VALIDATION

    // AJAX
    callAJAX = function(studentCode, fullName, email, phone){
        
         // CALL AJAX 
        var request = $.ajax({
            url: '/admin/api/member/new',
            data: {
                studentCode: studentCode,
                fullName: fullName,
                email: email,
                phone: phone
            },
            method: 'POST',
            cached: false
        });         
        request.done(function(msg){
            
            if (msg === 'success'){
                // success                
                location.reload();
                

            } else if (msg === 'duplicate'){
                // existed account

                $('form#form-new-member .error.studentCode').text('StudentCode is already existed'); 
                $('form#form-new-member .error.studentCode').css('display','block');
                
            } else if (msg === 'failure'){
                // error-server
                toastr.error('Adding occurs error');  
            }
            // $('form#form-new-member input[type=reset]').click();           
        });
        request.fail(function(status){
            toastr.error('Adding occurs error');              
        });
        
    }
    // END AJAX
    

    $('#btn-new-member').on('click', function(){
        $('form#form-new-member .error').css('display', 'none');
        var beError = false; 
        // Get All Field of the form 
        // StudentCode, Fullname, Email, Phone         
        var studentCode = $('form#form-new-member input[name=studentCode]').val();        
        if (isEmpty(studentCode) === true) {
            $('form#form-new-member .error.studentCode').text('Non-empty field required'); 
            $('form#form-new-member .error.studentCode').css('display','block');
            beError = true;         
        }
        var fullName = $('form#form-new-member input[name=fullname]').val(); 
        if (isEmpty(fullName) === true) {
            $('form#form-new-member .error.fullname').text('Non-empty field required'); 
            $('form#form-new-member .error.fullname').css('display','block');
            beError = true;
                       
        }
        // Check non-empty
        if (beError == true) {                                    
            return;
        }


        var email = $('form#form-new-member input[name=email]').val();
        var phone = $('form#form-new-member input[name=phone]').val();
        
        var msg = callAJAX(studentCode, fullName, email, phone);        
       
    })

});

