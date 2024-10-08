function alertShow(alertClass,heading,message,redirectUrl,time){
	let div='<div class="alert '+alertClass+'" role="alert">'+
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '<strong>'+heading+' !&nbsp;</strong>'+message+'</div>';
    $(div).insertBefore(ALERT_INSERT_ID);
    startAlertTimer(alertClass,redirectUrl,time);
}
function showAlert(alertClass,heading,message){
    alertShow(alertClass,heading,message,NO_REDIRECT,10000);
}
function startAlertTimer(alertClass,redirectUrl,time){
    window.setTimeout(function() {
        $("."+alertClass).fadeTo(500, 0).slideUp(500, function(){
            $(this).remove();
        });
        redirectTo(redirectUrl);
    }, Number(time));
}
$(document).on("click", ".close", function(e) {
    e.preventDefault();
    $(this).closest(".alert").remove();
});

function redirectTo(redirectUrl){
    if(redirectUrl!="NA"){
        let urlParts=window.location.href.split('/');
        window.location.href =urlParts[0]+ '//' + urlParts[2] + redirectUrl;
    }
}