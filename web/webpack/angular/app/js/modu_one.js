var oneExports = {};

oneExports.test = function(onSuccess, onFail) {
  console.log('module one test');
};

module.exports = oneExports;
