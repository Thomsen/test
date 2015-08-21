// webpack ./entry.js bundle.js

document.write("It works entry. <br/>");

document.write(require("./js/content.js"));

//require("!style!css!./css/style.css");
require("./css/style.css"); // webpack ./entry.js bundle.js --module-bind "css=style\!css"
