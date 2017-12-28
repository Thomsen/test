if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'browser'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'browser'.");
}
var browser = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function main(args) {
    println('hello kotlin javascript');
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('browser', _);
  return _;
}(typeof browser === 'undefined' ? {} : browser, kotlin);
