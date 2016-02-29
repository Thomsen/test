(function(app) {
  console.log("main app");
  document.addEventListener('DOMContentLoaded', function() {
    console.log("dom content loaded");
    ng.platform.browser.bootstrap(app.AppComponent);
  });
})(window.app || (window.app = {}));
