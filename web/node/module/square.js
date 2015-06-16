module.exports = function(width, height) {
  return {
    area: function() {
      return width * height;
    }
  };
};

// module.exports = function(width) {
//   return width * width;
// };  // module.exports override
