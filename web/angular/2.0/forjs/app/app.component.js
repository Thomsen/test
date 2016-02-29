(function(app) {
  app.AppComponent = ng.core
    .Component({
      selector: 'my-app',
      template: '<h1 id="output">My First Angular2 JavaScript App</h1>'
    })
    .Class({
      constructor: function() {

      }
    });
})(window.app || (window.app = {}));
