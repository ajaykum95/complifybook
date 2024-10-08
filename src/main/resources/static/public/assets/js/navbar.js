////// sticky nav bar ////////
/* $(document).ready(function () {
  $("#preloader").fadeOut("slow");
  $("body").removeClass("scroll-lock");
  console.log("Preloader ended");
}); */

////// sticky nav bar ////////
$(document).ready(function () {
  $(window).scroll(function () {
    var scroll = $(window).scrollTop();
    if (scroll >= 30) {
      $("nav.navbar").addClass("scroll");
    } else {
      $("nav.navbar").removeClass("scroll");
    }
  });

  ///////// nav open
  $(".navbar-toggler").on("click", function () {
    $("#main-nav").toggleClass("show");
    $(this).toggleClass("collapsed");
    $("body").toggleClass("scroll-lock");
	if($(this).hasClass("collapsed")){
		$(this).children(":first-child").removeClass("bi-x-lg");
		$(this).children(":first-child").addClass("bi-justify");
	}else{
		$(this).children(":first-child").removeClass("bi-justify");
		$(this).children(":first-child").addClass("bi-x-lg");
	}
  });

  // close button
  $(".btn-close").click(function (e) {
    $(".navbar-collapse").removeClass("show");
    $("body").removeClass("scroll-lock");
  });
  ///// init tooltip data
  $(function () {
    $('[data-toggle="tooltip"]').tooltip();
  });
});

//////////////// cards service ///////////
$(document).ready(function () {

  $("#close").click(function () {
    $(this).removeClass("show");
    $(".service.active").removeClass("active");
    $(".service-menu.open").removeClass("open");
    $(".service").show();
    $("#v-all-service").show();
    revertHeight();
  });

  $(".tb-select").click(function () {
    $(".tb-select").removeClass("active");
    $(this).addClass("active");
  });

  function closeMenu($this) {
    $(".service").removeClass("active");
    $(".service-menu").removeClass("open");
  }

  function setServiceHeight($this) {
    console.log(service.height());
    let vHeight = $(".service.active").outerHeight();
    service.parent().height(vHeight);
  }
  function revertHeight() {
    service.parent().height("auto");
  }
});

/////////////// sliding menu
/* menu sliding */
function menuSliding() {
  $(".dropper").on("show.bs.dropdown", function (e) {
    if ($(window).width() > 20) {
      $(this).find(".dropdown-menu").first().stop(true, true).slideDown(300);
    } else {
      $(this).find(".dropdown-menu").first().stop(true, true).show();
    }
  });
  $(".dropper").on("hide.bs.dropdown", function (e) {
    if ($(window).width() > 20) {
      $(this).find(".dropdown-menu").first().stop(true, true).slideUp(300);
    } else {
      $(this).find(".dropdown-menu").first().stop(true, true).hide();
    }

    $(".mega-dropdown .dropdown")
      .removeClass("active")
      .first()
      .addClass("active");
  });
}
$(function () {
  menuSliding();
});

/////////// stop menu from closing //////////////////
$(document).on("click", ".dropdown-menu", function (e) {
  e.stopPropagation();
});

//////// addd active on hover mega link /////////
$(document).ready(function () {
  if ($(window).width() > 1024) {
    $(".mega-dropdown .dropdown")
      .on("mouseenter", function () {
        $(this).addClass("active").siblings().removeClass("active");
      })
      .on("mouseout", function () {
        $(this).removeClass("ac tive");
      })
      .on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
      });
  }
  if ($(window).width() < 768) {
    $(".dropdown-mega").click(function () {
      $(this).next().slideToggle();
    });
  } else {
    console.log("window < 1024", $(window).width());
    $(".mega-dropdown .dropdown").click(function () {
      $(this).addClass("active").siblings().removeClass("active");
    });
  }
  //////// responsive menu /////////////////
});

////////////////// mouse hover .//////////////////////
//////// addd active on hover mega link /////////
$(document).ready(function () {
	console.log($(window).width());
  if ($(window).width() > 767) {
	  $(".drop-downtoggle").on("click", function(event) {
		  event.preventDefault();
		  $(".dropper").not($(this).parent()).removeClass("show");
		  $(".wider").not($(this).next()).hide();
		  $(this).parent().toggleClass("show");	  
		  $(this).next().slideToggle(200);
	 });
	 $(document).click(function(event) {
		if (!$(event.target).closest('.dropper').length) {
			$(".nav-item.dropper").removeClass('show');
			$(".dropdown-menu.wider").hide();
        }
    });
 }else{
	$(".drop-downtoggle").on("click", function(event) {
		event.preventDefault();
		$(this).parent().toggleClass("show");	  
		$(this).next().slideToggle();
	 });
 }
});

