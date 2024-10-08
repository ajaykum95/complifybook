(function($) {
  'use strict';
  $(function() {
    if ($('#calendar').length) {
      $('#calendar').fullCalendar({
        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'month,basicWeek,basicDay'
        },
        defaultDate: '2022-07-12',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: [{
            title: 'All Day Event',
            start: '2022-07-08'
          },
          {
            title: 'Long Event',
            start: '2022-07-01',
            end: '2022-07-07'
          },
          {
            id: 999,
            title: 'Repeating Event',
            start: '2022-07-09T16:00:00'
          },
          {
            id: 999,
            title: 'Repeating Event',
            start: '2022-07-16T16:00:00'
          },
          {
            title: 'Conference',
            start: '2022-07-11',
            end: '2022-07-13'
          },
          {
            title: 'Meeting',
            start: '2022-07-12T10:30:00',
            end: '2022-07-12T12:30:00'
          },
          {
            title: 'Lunch',
            start: '2022-07-12T12:00:00'
          },
          {
            title: 'Meeting',
            start: '2022-07-12T14:30:00'
          },
          {
            title: 'Happy Hour',
            start: '2022-07-12T17:30:00'
          },
          {
            title: 'Dinner',
            start: '2022-07-12T20:00:00'
          },
          {
            title: 'Birthday Party',
            start: '2022-07-13T07:00:00'
          },
          {
            title: 'Click for Company',
            url: 'http://localhost:8080/company',
            start: '2022-07-28'
          }
        ],
        color: 'yellow',   // an option!
  		textColor: 'black' // an option!
      })
    }
  });
})(jQuery);