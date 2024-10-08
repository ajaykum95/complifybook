(function() {
  "use strict";

  /**
   * Apply .scrolled class to the body as the page is scrolled down
   */
  function toggleScrolled() {
    const selectBody = document.querySelector('body');
    const selectHeader = document.querySelector('#header');
    if (!selectHeader.classList.contains('scroll-up-sticky') && !selectHeader.classList.contains('sticky-top') && !selectHeader.classList.contains('fixed-top')) return;
    window.scrollY > 100 ? selectBody.classList.add('scrolled') : selectBody.classList.remove('scrolled');
  }

  document.addEventListener('scroll', toggleScrolled);
  window.addEventListener('load', toggleScrolled);

  /**
   * Preloader
   */
  const preloader = document.querySelector('#preloader');
  if (preloader) {
    window.addEventListener('load', () => {
      preloader.remove();
    });
  }

  /**
   * Scroll top button
   */
  let scrollTop = document.querySelector('.scroll-top');

  function toggleScrollTop() {
    if (scrollTop) {
      window.scrollY > 100 ? scrollTop.classList.add('active') : scrollTop.classList.remove('active');
    }
  }
  scrollTop.addEventListener('click', (e) => {
    e.preventDefault();
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  });

  window.addEventListener('load', toggleScrollTop);
  document.addEventListener('scroll', toggleScrollTop);

  /**
   * Animation on scroll function and init
   */
  function aosInit() {
    AOS.init({
      duration: 600,
      easing: 'ease-in-out',
      once: true,
      mirror: false
    });
  }
  window.addEventListener('load', aosInit);

  /**
   * Initiate glightbox
   */
  const glightbox = GLightbox({
    selector: '.glightbox'
  });

  /**
   * Init swiper sliders
   */
  function initSwiper() {
    document.querySelectorAll(".init-swiper").forEach(function(swiperElement) {
      let config = JSON.parse(
        swiperElement.querySelector(".swiper-config").innerHTML.trim()
      );

      if (swiperElement.classList.contains("swiper-tab")) {
        initSwiperWithCustomPagination(swiperElement, config);
      } else {
        new Swiper(swiperElement, config);
      }
    });
  }

  window.addEventListener("load", initSwiper);

  /**
   * Frequently Asked Questions Toggle
   */
  document.querySelectorAll('.faq-item h3, .faq-item .faq-toggle').forEach((faqItem) => {
    faqItem.addEventListener('click', () => {
      faqItem.parentNode.classList.toggle('faq-active');
    });
  });

  /**
   * Animate the skills items on reveal
   */
  let skillsAnimation = document.querySelectorAll('.skills-animation');
  skillsAnimation.forEach((item) => {
    new Waypoint({
      element: item,
      offset: '80%',
      handler: function(direction) {
        let progress = item.querySelectorAll('.progress .progress-bar');
        progress.forEach(el => {
          el.style.width = el.getAttribute('aria-valuenow') + '%';
        });
      }
    });
  });

  /**
   * Init isotope layout and filters
   */
  document.querySelectorAll('.isotope-layout').forEach(function(isotopeItem) {
    let layout = isotopeItem.getAttribute('data-layout') ?? 'masonry';
    let filter = isotopeItem.getAttribute('data-default-filter') ?? '*';
    let sort = isotopeItem.getAttribute('data-sort') ?? 'original-order';

    let initIsotope;
    imagesLoaded(isotopeItem.querySelector('.isotope-container'), function() {
      initIsotope = new Isotope(isotopeItem.querySelector('.isotope-container'), {
        itemSelector: '.isotope-item',
        layoutMode: layout,
        filter: filter,
        sortBy: sort
      });
    });

    isotopeItem.querySelectorAll('.isotope-filters li').forEach(function(filters) {
      filters.addEventListener('click', function() {
        isotopeItem.querySelector('.isotope-filters .filter-active').classList.remove('filter-active');
        this.classList.add('filter-active');
        initIsotope.arrange({
          filter: this.getAttribute('data-filter')
        });
        if (typeof aosInit === 'function') {
          aosInit();
        }
      }, false);
    });

  });

  /**
   * Correct scrolling position upon page load for URLs containing hash links.
   */
  window.addEventListener('load', function(e) {
    if (window.location.hash) {
      if (document.querySelector(window.location.hash)) {
        setTimeout(() => {
          let section = document.querySelector(window.location.hash);
          let scrollMarginTop = getComputedStyle(section).scrollMarginTop;
          window.scrollTo({
            top: section.offsetTop - parseInt(scrollMarginTop),
            behavior: 'smooth'
          });
        }, 50);
      }
    }
  });

  /**
   * Navmenu Scrollspy
   */
  let navmenulinks = document.querySelectorAll('.navmenu a');

  function navmenuScrollspy() {
    navmenulinks.forEach(navmenulink => {
      if (!navmenulink.hash) return;
      let section = document.querySelector(navmenulink.hash);
      if (!section) return;
      let position = window.scrollY + 200;
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        document.querySelectorAll('.navmenu a.active').forEach(link => link.classList.remove('active'));
        navmenulink.classList.add('active');
      } else {
        navmenulink.classList.remove('active');
      }
    })
  }
  window.addEventListener('load', navmenuScrollspy);
  document.addEventListener('scroll', navmenuScrollspy);
  $(document).ready(function(){
	  $('.toggle-box').click(function(){
        $('.sidebar-contact').toggleClass('active');
        $('.toggle').toggleClass('active');
      })
	  $('#whatsappMobileChat,#whatsappWebChat').on('click', function(e) {
            e.preventDefault();
            
            // The phone number in international format and the pre-filled message
            var phoneNumber = '6287048066';  // Replace with your phone number
            var message = 'Hello, I would like to chat!';

            // Construct the WhatsApp URL based on whether the user is on mobile or desktop
            var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
            var whatsappUrl = isMobile ? 
                'whatsapp://send?phone=' + phoneNumber + '&text=' + encodeURIComponent(message) :
                'https://web.whatsapp.com/send?phone=' + phoneNumber + '&text=' + encodeURIComponent(message);

            // Open URL in a new tab (desktop only)
			  if (!isMobile) {
				window.open(whatsappUrl, '_blank');
			  } else {
				// Redirect for mobile
				window.location.href = whatsappUrl;
			  }
        });
		/* $('#whatsappMobileChat,#whatsappWebChat').on('click', function(e) {
		  e.preventDefault();

		  // Phone number and message
		  var phoneNumber = '6287048066';
		  var message = 'Hello, I would like to chat!';

		  // Construct WhatsApp URL with fallback message
		  var whatsappUrl = 'whatsapp://send?phone=' + phoneNumber + '&text=' + encodeURIComponent(message);

		  // Redirect for mobile
		  window.location.href = whatsappUrl;
		}); */
		$('#callBackHover,#whatsappWebChat').addClass("hover");
		setTimeout(function() {
            $('#callBackHover,#whatsappWebChat').removeClass("hover");
        }, 3000);

		    var url = window.location.pathname;
		    if(url.includes("service/") && !url.includes("service/all")){
		    $(window).scroll(function() {
                var divTop = $('#side-nav').offset().top;
                var scrollPos = $(window).scrollTop();
                var topHeight = divTop - scrollPos;
                if(topHeight <= 36){
                    $('#side-nav').addClass("pt-80");
                }else{
                    $('#side-nav').removeClass("pt-80");
                }

                // Loop through each section to check which one is in view
                $('.page-header').each(function() {
                    var sectionTop = $(this).offset().top - 110; // Adjust 80 for header offset
                    var sectionBottom = sectionTop + $(this).outerHeight();
                    var id = $(this).attr('id');

                    // Check if the current scroll position is within the section's range
                    if (scrollPos >= sectionTop && scrollPos < sectionBottom) {
                        // Remove the active class from all the links
                        $('#side-nav a').removeClass('active');
                        // Add the active class to the corresponding navigation link
                        $('#side-nav a[href="#' + id + '"]').addClass('active');
                    }
                });

                $('#side-nav a').on('click', function(event) {
                    event.preventDefault(); // Prevent the default action of the anchor

                    var targetId = $(this).attr('href'); // Get the ID of the target section
                    var targetSection = $(targetId);

                    // Scroll smoothly to the target section
                    $('html, body').animate({
                        scrollTop: targetSection.offset().top - 105 // Adjust for any fixed header
                    },0);
                });
               });
		    }
    }); 
})();