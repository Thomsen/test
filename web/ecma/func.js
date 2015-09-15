(function() {
  var callback = function(r) {
    console.log("callback");
  };

  console.log("!!callback is " + (!!callback));

  var FunOne, FunTwo;

  var queue = [];

  var fgLocks = {};

  FunOne = function(flag, success, error) {
    this.flag = flag;
  };

  FunOne.prototype.addQueue = function(t) {
    //console.log("this.flag " + JSON.stringify(this.flag));
    console.log("addQueue t " + JSON.stringify(t));

    queue.push(t);
    //console.log("queue added " + JSON.stringify(queue));

    if (true) {
      this.startExec();
    }
  };

  FunOne.prototype.addLockQueue = function(t) {
    if (!fgLocks[this.flag]) {
      fgLocks[this.flag] = {
        isProgress: false,
        queue: []
      };
    }

    fgLocks[this.flag].queue.push(t);
    console.log("fgLocks added " + JSON.stringify(fgLocks));

    if (true) {
      this.startLockExec();
    }
  };

  FunOne.prototype.startExec = function() {
    var self = this;
    setTimeout(function(_this) {
      console.log("queue " + JSON.stringify(queue));
      if (queue.length > 0) {
        var q = queue.shift();
        //console.log("queue shift " + JSON.stringify(q));
        q.start();
      } else {
        console.log("queue cannot exec task");
      }
    }, 1000);
  };

  FunOne.prototype.startLockExec = function() {
    var self = this;
    setTimeout(function(_this) {
      console.log("fgLocks " + JSON.stringify(fgLocks));
      console.log("flag " + self.flag); // not use this.flag
      var fg = fgLocks[self.flag];
      console.log("fg " + JSON.stringify(fg));
      if (!fg) {
        console.log('fg queue cannot exec task');
        return;
      } else if (fg.queue.length > 0 && !fg.isProgress) {
        fg.isProgress = true;
        fg.queue.shift().start();
      }
    }, 2000);
  };

  FunOne.prototype.queue = function(fn, success, error) {
    var task = new FunTwo(this, fn, success, error);
    //console.log("queue task " + JSON.stringify(task));
    this.addLockQueue(task);
  };

  FunOne.prototype.addTask = function(msg, success, error) {
    var myfun, mysuccess, myerror;
    mysuccess = function(t, r) {
      if (!!success) {
        return success(r);
      }
    };

    myerror = function(t, e) {
      if (!!error) {
        return error(e);
      }
    };

    myfun = function(tx) {
      tx.addTask(msg, mysuccess, myerror);
    };

    this.addQueue(new FunTwo(this, myfun, success, error));
  };

  FunTwo = function(task, fn, success, error) {
    //this.task = task;  // need, otherwise Object is {}, but {flag: ''}
    this.task = task.flag;
    this.fn = fn;
  };

  FunTwo.prototype.start = function() {
    try {
      this.fn(this); // use param by fun. recursion
      this.run();
    } catch (e) {
      console.log("FunTwo start catch " + e.message);
      fgLocks[this.task].isProgress = false;
    }
  };

  FunTwo.prototype.run = function() {
    console.log("FunTwo run");
    if (this.msg) {
      console.log("msg " + this.msg);
    }
    console.log("FunTwo run fgLocks " + JSON.stringify(fgLocks));
    console.log("FunTwo run task " + JSON.stringify(this.task));
    if (fgLocks) {
      fgLocks[this.task].isProgress = false;
    }
  };

  FunTwo.prototype.addTask = function(msg, success, error) {
    this.msg = msg;
  };

  //////////////// test ///////////////////////
  var fo = new FunOne("fo", function(r) {
    console.log("fo success");
  }, function(e) {
    console.log("fo error");
  });
  fo.queue(function(r) {
    //console.log("fo function r " + JSON.stringify(r));
    console.log("task one queue");
  }, null, null);

  fo.queue(function(tx) {
    tx.addTask("task two queue");
  });

}).call(this); // need call this
