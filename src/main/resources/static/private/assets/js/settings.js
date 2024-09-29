(function($) {
  'use strict';

  $(".ms-settings-toggle").on('click', function(e){
    $('body').toggleClass('ms-settings-open');
  });

  $("#dark-mode").on('click', function(){
    $('body').toggleClass('ms-dark-theme');
  });
  $("#remove-quickbar").on('click', function(){
    $('body').toggleClass('ms-has-quickbar');
  });
  $("#hide-aside-left").on('click', function(){
    $('body').toggleClass('ms-aside-left-open');
  });
  
  $("#addCompanyGuide").on('click', function(){
    $("#companyGuideModal").modal("show");
  });
  
  $('.modal-overflow-h').css('height', $(window).height()-170);

})(jQuery);

function openGuide(type){
   $("#guide_head").html("Add "+type+" guide");
   $("#guideModal").modal("show");
}
function openCompany(id){
  console.log("company id="+id)
  var target = $(".ms-company").data('target');

  $(target).toggleClass('ms-aside-open');
  $(".ms-aside-overlay.ms-overlay-right").toggleClass('d-block');
          
}

$("#signup").validate({
	rules:{
		name: "required",
		email: "required",
		password: "required",
		term: "required",
	},
	messages:{	
		name: "Please enter your name..",
		email: "Please enter email address..",
		password: "Please enter password..",
		term: "Please accept term-conditions..",
	},
	submitHandler:function(e){
		var user={};
		user.name=$("#name").val();
		user.email=$("#email").val();
		user.password=$("#password").val();
		user=JSON.stringify(user)
//		console.log(user)
		
	  var user = {
            name: $("#name").val(),
            email:$("#email").val(),
            password:$("#password").val()
        }

        $.ajax({
            url: 'http://localhost:8081/signup',
            crossDomain : true,
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(user),
            /*statusCode: {
		      200: function (response) {
		         alert("200="+response.email);
		      },
		      302: function (response) {
		         console.log(response.RestMessage);			    
		      },
		      201: function (response) {
		        alert("201="+response.email);
		      },
		      400: function (response) {
		         alert("400="+response.email);
		      },
		      404: function (response) {
		        alert("404="+response.email);
		      }
		   },*/
            success: function (result, textStatus, jqXHR) {
				console.log(result);
				console.log(textStatus);
                console.log(jqXHR);
            },
           error: function (data) {
	        console.log(data.responseJSON.content);	  
	        console.log(data.responseJSON.type);	      
	      }
            
        });
	}
});

$("#user_login").validate({
	rules:{
		user_email: "required",
	},
	messages:{	
		user_email: "Please enter you email..",
	},
	submitHandler:function(form){
		form.submit();
	}
});

$("#company_add").validate({
	rules:{
		company_name: "required",
	},
	messages:{	
		company_name: "Please enter company name..",
	},
	submitHandler:function(form){
		form.submit();
	}
});