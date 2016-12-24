$(document).ready(function() {
  $('.dropdown-button').dropdown({hover: false});
  $('.button-collapse').sideNav();
});

var lockScreen = function() {
  $('#lock').toggleClass('hide');
};
