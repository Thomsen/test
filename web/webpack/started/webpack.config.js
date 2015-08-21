module.exports = {
  entry: "./entry.js",
  output: {
    path: __dirname,
    filename: "bundle.js"
  },
  module: {
    loaders: [
      { test: /\.css$/, loader:"style!css" }
    ]
  }
};

// webpack --progress --colors --watch

// webpack-dev-server --progress --colors
