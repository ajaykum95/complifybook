var host = window.location.origin;
$("#callBack").validate({
    errorClass: "error fail-alert",
    validClass: "valid success-alert",
    rules: {
        name: {
            required: true,
            minlength: 2,  // Ensure name has at least 2 characters
			validName: true
        },
        mobile: {
            required: true,
            minlength: 10,  // Ensure exactly 10 digits for mobile
            maxlength: 10,  // Maximum 10 digits for mobile
            digits: true,    // Ensure only numeric digits
			phone: true
        },
        otp: {
            required: true,
            minlength: 4,  // Ensure exactly 4 digits for OTP
            maxlength: 4,  // Maximum 4 digits for OTP
            digits: true    // Ensure only numeric digits
        },
		email: {
            required: false,  // Email is optional
            email: true       // Apply email format validation if a value is entered
        }
    },
    messages: {
        name: {
            required: "Please enter your name!",  // Message if name is empty
            minlength: "Name must be at least 2 characters long!", // Message for short names
        },
        mobile: {
            required: "Please enter your mobile number!",  // Message if mobile is empty
            minlength: "Mobile number should be exactly 10 digits!",  // Message for short mobile numbers
            maxlength: "Mobile number should be exactly 10 digits!",  // Message for long mobile numbers
			digits: "Invalid mobile number",
			phone: "Enter a valid 10-digit mobile number!",
        },
        otp: {
            required: "Please enter the OTP!",  // Message if OTP is empty
            minlength: "OTP should be exactly 4 digits!",  // Message for short OTP
            maxlength: "OTP should be exactly 4 digits!",  // Message for long OTP
			digits: "Enter a valid OTP!"
        },
		email: {
            email: "Please enter a valid email address!"  // Message for invalid email format
        }
    },
	errorPlacement: function(error, element) {
		if(element.attr("name") == "name"){
			error.appendTo("#nameErrorContainer");
		}else if (element.attr("name") == "email") {
            error.appendTo("#emailErrorContainer");
		}else if (element.attr("name") == "mobile") {
            error.appendTo("#mobileErrorContainer");
        }else if (element.attr("name") == "otp") {
            error.appendTo("#otpErrorContainer");
        }
    },
    submitHandler: function (form, event) {	
		form.reset();
		$('#callBack #mobile').prop('disabled', false);
		$('#callBack #sendOtp').show();
		$('#submitCallBack').addClass('d-none');
		$("#callBack .otp").removeClass("d-block");
        // form.submit();  // Uncomment this to actually submit the form
		showAlert(ALERT_SUCCESS, HEADING_SUCCESS, ENQUIRY_SUCCESS);
    }
});
$("#callBackM").validate({
    errorClass: "error fail-alert",
    validClass: "valid success-alert",
    rules: {
        nameM: {
            required: true,
            minlength: 2,  // Ensure name has at least 2 characters
			validName: true
        },
        mobileM: {
            required: true,
            minlength: 10,  // Ensure exactly 10 digits for mobile
            maxlength: 10,  // Maximum 10 digits for mobile
            digits: true,    // Ensure only numeric digits
			phone: true
        },
        otpM: {
            required: true,
            minlength: 4,  // Ensure exactly 4 digits for OTP
            maxlength: 4,  // Maximum 4 digits for OTP
            digits: true    // Ensure only numeric digits
        },
		emailM: {
            required: false,  // Email is optional
            email: true       // Apply email format validation if a value is entered
        }
    },
    messages: {
        nameM: {
            required: "Please enter your name!",  // Message if name is empty
            minlength: "Name must be at least 2 characters long!", // Message for short names
        },
        mobileM: {
            required: "Please enter your mobile number!",  // Message if mobile is empty
            minlength: "Mobile number should be exactly 10 digits!",  // Message for short mobile numbers
            maxlength: "Mobile number should be exactly 10 digits!",  // Message for long mobile numbers
			digits: "Invalid mobile number",
			phone: "Enter a valid 10-digit mobile number!",
        },
        otpM: {
            required: "Please enter the OTP!",  // Message if OTP is empty
            minlength: "OTP should be exactly 4 digits!",  // Message for short OTP
            maxlength: "OTP should be exactly 4 digits!",  // Message for long OTP
			digits: "Enter a valid OTP!"
        },
		emailM: {
            email: "Please enter a valid email address!"  // Message for invalid email format
        }
    },
	errorPlacement: function(error, element) {
		if(element.attr("name") == "nameM"){
			error.appendTo("#nameErrorContainerM");
		}else if (element.attr("name") == "emailM") {
			error.appendTo("#emailErrorContainerM");
		}else if (element.attr("name") == "mobileM") {
            error.appendTo("#mobileErrorContainerM");
        }else if (element.attr("name") == "otpM") {
            error.appendTo("#otpErrorContainerM");
        }
    },
    submitHandler: function (form) {
		form.reset();
		$('#callBackM #mobileM').prop('disabled', false);
		$('#callBackM #sendOtpM').show();
		$('#submitCallBackM').addClass('d-none');
		$("#callBackM .otp").removeClass("d-block");
		$(".card .call-button.toggle-box").click();
        // form.submit();  // Uncomment this to actually submit the form
		showAlert(ALERT_SUCCESS, HEADING_SUCCESS, ENQUIRY_SUCCESS);
    }
});
var timer;
$(document).ready(function() {
	$('#mobile,#name,#mobileM,#nameM').on('keydown', function(event) {
	  if (event.key === 'Enter') {
		event.preventDefault(); // Prevent form submission
	  }
	});
	$("#sendOtp").click(function() {	
		event.preventDefault();	
		var regex = /^[6-9]\d{9}$/;
		var mobileNumber = $('#mobile').val();			
		if (regex.test(mobileNumber)) {
			$('#callBack #mobile').prop('disabled', true);
			$('#callBack #sendOtp').hide();
			$('#submitCallBack').removeClass('d-none');
			//call to send otp
			$("#callBack .otp").addClass("d-block");
			clearInterval(timer);
			startTimer(60,"#timer","#resendButton");		
		}else{
			$("#callBack").valid();
		}
	});
	$("#sendOtpM").click(function() {	
		event.preventDefault();	
		var regex = /^[6-9]\d{9}$/;
		var mobileNumber = $('#mobileM').val();			
		if (regex.test(mobileNumber)) {
			$('#callBackM #mobileM').prop('disabled', true);
			$('#callBackM #sendOtpM').hide();
			$('#submitCallBackM').removeClass('d-none');
			//call to send otp
			$("#callBackM .otp").addClass("d-block");
			clearInterval(timer);
			startTimer(60,"#timerM","#resendButtonM");			
		}else{
			$("#callBackM").valid();
		}
	});
    
	function startTimer(duration, timerSelector, resendSelector) {
    var timeLeft = duration;
    var intervalId;  // This will store the interval ID
    
    $(timerSelector).text(formatTime(timeLeft)); // Set initial time
    $(resendSelector).hide(); // Hide resend button initially
    $(timerSelector).show();  // Show timer

    intervalId = setInterval(function() {
        timeLeft--;
        $(timerSelector).text(formatTime(timeLeft));

        if (timeLeft <= 0) {
            clearInterval(intervalId); // Stop the timer
            $(timerSelector).hide();   // Hide timer
            $(resendSelector).show();  // Show resend button
            $(timerSelector).text(''); // Clear the timer text
        }
    }, 1000);
}

	function formatTime(secondsLeft) {
		var minutes = Math.floor(secondsLeft / 60);
		var seconds = secondsLeft % 60;
		return minutes + ':' + (seconds < 10 ? '0' : '') + seconds;
	}

	$('#resendButton').click(function() {
		$('#otp').val('');
		startTimer(60,"#timer","#resendButton");
	});
	$('#resendButtonM').click(function() {
		$('#otpM').val('');
		startTimer(60,"#timerM","#resendButtonM");
	});
});

$("#subscription").validate({
    errorClass: "error fail-alert",
    validClass: "valid success-alert",
    rules: {
        email: {
            required: true,
            email: true
        }
    },
    messages: {
        email: {
            required: "Email is required!",
            email: "Please enter a valid email address!"
        }
    },
    submitHandler: function (form, event) {
		$.ajax({
            url: host + '/subscriber/new',
            type: 'POST',
            data: buildSubscriptionSaveData(),
            success: function(response) {
                if (response){
                    form.reset();
                    if(response.result == PASS){
                        showAlert(ALERT_SUCCESS, HEADING_SUCCESS, response.message);
                    }else if (response.result == FAIL){
                        showAlert(ALERT_DANGER, HEADING_ERROR, response.message);
                    }else if(response.result == EXIST){
                        showAlert(ALERT_INFO, HEADING_INFO, response.message);
                    }else {
                        showAlert(ALERT_DANGER, HEADING_ERROR, response.message);
                    }
                }
            },
            error: function(xhr, status, error) {
                console.error('AJAX Error: ' + status + error);
            }
        });
    }
});
function buildSubscriptionSaveData(){
    return {
      email: $('#email').val(),
      url: window.location.pathname
    };
}
