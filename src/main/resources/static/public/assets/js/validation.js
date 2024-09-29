function AjaxCallBuilder() {
    let successCallback = $.Callbacks();
    let errorCallback = $.Callbacks();
    let option = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "",
        dataType: "json",
        data: "",
        timeout: 10000,
        url: '',
        success: function (response) {
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    };

    return {
        addSuccessHandler: function (handler) {
            successCallback.add(handler);
            return this;
        },
        removeSuccessHandler: function (handler) {
            successCallback.remove(handler);
            return this;
        },
        addErrorHandler: function (handler) {
            errorCallback.add(handler);
            return this;
        },
        removeErrorHandler: function (handler) {
            errorCallback.remove(handler);
            return this;
        },
        withData: function (data) {
            option.data = JSON.stringify(data);
            return this;
        },
        withMethodType: function (type) {
            option.type = type;
            return this;
        },
        withUrl: function (url) {
            option.url = url;
            return this;
        },
        withHeader: function (headerType, value) {
            if (!option.headers) {
                option.headers = {}
            }
            option.headers[headerType] = value;
            return this;
        },
        deleteHeader: function (headerType) {
            delete option.headers[headerType];
            return this;
        },
        removeHeaders: function () {
            delete option.headers;
            return this;
        },
        removeTimeOut: function () {
            delete option.timeout;
            return this;
        },
        withTimeOut: function (timeOut) {
            option.timeout = timeOut;
            return this;
        },
        build: function () {
            option.success = function (response) {
                successCallback.fire(response);
            };
            option.error = function (XMLHttpRequest, textStatus, errorThrown) {
                errorCallback.fire(XMLHttpRequest, textStatus, errorThrown);
            };
            return {
                call: function () {
                    $.ajax(option);
                },
                setData: function (data) {
                    option.data = JSON.stringify(data);
                    return this;
                }
            };
        }
    }
}

function fetchDataError(xhr, status, err) {
    if(typeof xhr===undefined)
        showErrorMessage("Something Went-Wrong, Please Try-again later !!");
    else {
        // let error = eval("(" + xhr.responseText + ")");
        showErrorMessage(xhr.responseText);
    }
}

function showErrorMessage(error){
    console.log("Error message")
    alert(error);
    // $("#alert_danger label").html(error);
    // $("#alert_danger").show();
    // setTimeout(function(){$("#alert_danger").slideUp();},10000);
}

function showSuccessMessage(message){
    console.log("Success message")
    alert(message);
    // $("#alert_success label").html(message);
    // $("#alert_success").show();
    // setTimeout(function(){$("#alert_success").slideUp();},10000);
}

jQuery.validator.addMethod("phone", function (phone_number, element) {
    phone_number = phone_number.replace(/\s+/g, "");
    return this.optional(element) || phone_number.length > 9 &&
        phone_number.match(/[6-9][0-9]{9}/);
}, "Invalid phone number");

$.validator.addMethod("validName", function (value, element) {
    // Check if the name contains only letters and spaces
    return this.optional(element) || /^[A-Za-z\s]+$/.test(value);
}, "Please enter a valid name!");

jQuery.validator.addMethod("email", function (email, element) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return this.optional(element) || email.length > 0 &&
        re.test(String(email).toLowerCase());
}, "The email should be in the format: abc@domain.tld");

jQuery.validator.addMethod("passwordValid", function (password, element) {
    password = password.replace(/\s+/g, "");
    return this.optional(element) || password.length > 5 && password.length < 29 &&
        password.match('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,26}$');
}, "Invalid password");

jQuery.validator.addMethod("passwordMatch", function (password, element) {
    password = password.replace(/\s+/g, "");
    return this.optional(element) || password.length > 5 && password.length < 29 &&
        this.value() == $("#password").val();
}, "Password Not Matched");


$(".number").on("input", function (event) {
    event.target.value = event.target.value.replace(/[^0-9]*/g, '');
})

$(".number-comma").on("input", function (event) {
    event.target.value = event.target.value.replace(/[^0-9]*/g, '');
    event.target.value = event.target.value.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
})

$(".password").keyup(function (event) {
    let pass = $(this).val();
    $(".pass-error").remove();
    let errorMsg = "";
    if (!pass.match(".*\\d.*"))
        errorMsg += "<li>Include at least one digit</li>";
    if (!pass.match(".*[a-z].*"))
        errorMsg += "<li>Include at least one a-z letter</li>";
    if (!pass.match(".*[A-Z].*"))
        errorMsg += "<li>Include at least one A-Z letter</li>";
    if (!pass.match(".*[@#$%^&+=].*"))
        errorMsg += "<li>Include at least one special character</li>";
    if (pass.length < 6 || pass.length > 28) {
        errorMsg += "<li>Password length should min 6 and max 28 characters</li>";
    }
    if (errorMsg != "")
        $(this).after("<ul class='pass-error error'>" + errorMsg + "</ul>");
});