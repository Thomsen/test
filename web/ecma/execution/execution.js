console.log(this);  // {}

console.log(a);  // undefined, enter execution stack
var a = 10;

var msg = 'global msg';

function f1() {
  // console.log(this); // global object
  // { DTRACE_NET_SERVER_CONNECTION: [Function],
  // DTRACE_NET_STREAM_END: [Function],
  // DTRACE_HTTP_SERVER_REQUEST: [Function],
  // DTRACE_HTTP_SERVER_RESPONSE: [Function],
  // DTRACE_HTTP_CLIENT_REQUEST: [Function],
  // DTRACE_HTTP_CLIENT_RESPONSE: [Function],
  // COUNTER_NET_SERVER_CONNECTION: [Function],
  // COUNTER_NET_SERVER_CONNECTION_CLOSE: [Function],
  // COUNTER_HTTP_SERVER_REQUEST: [Function],
  // COUNTER_HTTP_SERVER_RESPONSE: [Function],
  // COUNTER_HTTP_CLIENT_REQUEST: [Function],
  // COUNTER_HTTP_CLIENT_RESPONSE: [Function],
  // global: [Circular],
  // process:
  //  process {
  //    title: 'MINGW32:/c/Users/~/codes/mygit/test/web/ecma',
  //    version: 'v6.9.1',
  //    moduleLoadList:
  //     [ 'Binding contextify',
  //       'Binding natives',
  //       'NativeModule events',
  //       'NativeModule util',
  //       'Binding uv',
  //       'NativeModule buffer',
  //       'Binding buffer',
  //       'Binding util',
  //       'NativeModule internal/util',
  //       'NativeModule timers',
  //       'Binding timer_wrap',
  //       'NativeModule internal/linkedlist',
  //       'NativeModule assert',
  //       'NativeModule internal/process',
  //       'Binding config',
  //       'NativeModule internal/process/warning',
  //       'NativeModule internal/process/next_tick',
  //       'NativeModule internal/process/promises',
  //       'NativeModule internal/process/stdio',
  //       'Binding constants',
  //       'NativeModule path',
  //       'NativeModule module',
  //       'NativeModule internal/module',
  //       'NativeModule vm',
  //       'NativeModule fs',
  //       'Binding fs',
  //       'NativeModule stream',
  //       'NativeModule _stream_readable',
  //       'NativeModule internal/streams/BufferList',
  //       'NativeModule _stream_writable',
  //       'NativeModule _stream_duplex',
  //       'NativeModule _stream_transform',
  //       'NativeModule _stream_passthrough',
  //       'Binding fs_event_wrap',
  //       'NativeModule console',
  //       'Binding tty_wrap',
  //       'NativeModule tty',
  //       'NativeModule net',
  //       'NativeModule internal/net',
  //       'Binding cares_wrap',
  //       'Binding tcp_wrap',
  //       'Binding pipe_wrap',
  //       'Binding stream_wrap',
  //       'Binding signal_wrap' ],
  //    versions:
  //     { http_parser: '2.7.0',
  //       node: '6.9.1',
  //       v8: '5.1.281.84',
  //       uv: '1.9.1',
  //       zlib: '1.2.8',
  //       ares: '1.10.1-DEV',
  //       icu: '57.1',
  //       modules: '48',
  //       openssl: '1.0.2j' },
  //    arch: 'x64',
  //    platform: 'win32',
  //    release:
  //     { name: 'node',
  //       lts: 'Boron',
  //       sourceUrl: 'https://nodejs.org/download/release/v6.9.1/node-v6.9.1.tar.gz',
  //       headersUrl: 'https://nodejs.org/download/release/v6.9.1/node-v6.9.1-headers.tar.gz',
  //       libUrl: 'https://nodejs.org/download/release/v6.9.1/win-x64/node.lib' },
  //    argv:
  //     [ 'd:\\mingw\\msys\\1.0\\local\\nodejs\\node.exe',
  //       'c:\\Users\\~\\codes\\mygit\\test\\web\\ecma\\execution.js' ],
  //    execArgv: [],
  //    env:
  //     { '!::': '::\\',
  //       ALLUSERSPROFILE: 'C:\\ProgramData',
  //       ANDROID_HOME: 'D:\\Android\\android-sdk',
  //       ANDROID_SDK_HOME: 'D:\\Android\\',
  //       APPDATA: 'C:\\Users\\~\\AppData\\Roaming',
  //       APR_ICONV_PATH: 'D:\\Program Files (x86)\\Subversion\\iconv',
  //       'COMMONPROGRAMFILES(X86)': 'C:\\Program Files (x86)\\Common Files',
  //       COMMONPROGRAMFILES: 'C:\\Program Files\\Common Files',
  //       COMMONPROGRAMW6432: 'C:\\Program Files\\Common Files',
  //       COMPUTERNAME: 'WCS',
  //       COMSPEC: 'C:\\WINDOWS\\SysWOW64\\cmd.exe',
  //       CONFIGSETROOT: 'C:\\WINDOWS\\ConfigSetRoot',
  //       FPS_BROWSER_APP_PROFILE_STRING: 'Internet Explorer',
  //       FPS_BROWSER_USER_PROFILE_STRING: 'Default',
  //       FP_NO_HOST_CHECK: 'NO',
  //       HISTFILE: 'D:/mingw/msys/1.0/home/~/.bash_history',
  //       HOME: 'C:\\Users\\~',
  //       HOMEDRIVE: 'C:',
  //       HOMEPATH: '\\',
  //       JAVA7_HOME: 'D:\\Program Files\\Java\\jdk1.7.0_71',
  //       JAVA8_HOME: 'D:\\Program Files\\Java\\jdk1.8.0_25',
  //       JAVA_HOME: 'D:\\Program Files\\Java\\jdk1.8.0_25',
  //       LOCALAPPDATA: 'C:\\Users\\~\\AppData\\Local',
  //       LOGNAME: '~',
  //       LOGONSERVER: '\\\\WCS',
  //       MAKE_MODE: 'unix',
  //       MSYSCON: 'sh.exe',
  //       MSYSTEM: 'MINGW32',
  //       NDK_HOME: 'D:\\Android\\android-ndk-r9d',
  //       NLS_LANG: 'SIMPLIFIED CHINESE_CHINA.ZHS16GBK',
  //       NUMBER_OF_PROCESSORS: '4',
  //       OLDPWD: 'c:/Users/~/codes/mygit/test/web/angular',
  //       ONEDRIVE: 'C:\\Users\\~\\OneDrive',
  //       OS: 'Windows_NT',
  //       PATH: '',
  //       PATHEXT: '.COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC',
  //       PROCESSOR_ARCHITECTURE: 'AMD64',
  //       PROCESSOR_IDENTIFIER: 'Intel64 Family 6 Model 60 Stepping 3, GenuineIntel',
  //       PROCESSOR_LEVEL: '6',
  //       PROCESSOR_REVISION: '3c03',
  //       PROGRAMDATA: 'C:\\ProgramData',
  //       'PROGRAMFILES(X86)': 'C:\\Program Files (x86)',
  //       PROGRAMFILES: 'C:\\Program Files',
  //       PROGRAMW6432: 'C:\\Program Files',
  //       PROMPT: '$P$G',
  //       PS1: '\\[\\033]0;$MSYSTEM:\\w\\007\n\\033[32m\\]\\u@\\h \\[\\033[33m\\w\\033[0m\\]\n$ ',
  //       PSMODULEPATH: 'C:\\WINDOWS\\system32\\WindowsPowerShell\\v1.0\\Modules\\',
  //       PUBLIC: 'C:\\Users\\Public',
  //       PWD: 'c:/Users/~/codes/mygit/test/web/ecma',
  //       SESSIONNAME: 'Console',
  //       SHLVL: '1',
  //       SYSTEMDRIVE: 'C:',
  //       SYSTEMROOT: 'C:\\WINDOWS',
  //       TEMP: 'C:/Users/WANGCH~1/AppData/Local/Temp',
  //       TERM: 'cygwin',
  //       TMP: 'C:/Users/WANGCH~1/AppData/Local/Temp',
  //       USERDOMAIN: 'WCS',
  //       USERDOMAIN_ROAMINGPROFILE: 'WCS',
  //       USERNAME: '~',
  //       USERPROFILE: 'C:\\Users\\~',
  //       VAGRANT_HOME: 'D:\\HashiCorp\\Vagrant',
  //       VBOX_MSI_INSTALL_PATH: 'D:\\Program Files\\Oracle\\VirtualBox\\',
  //       VS110COMNTOOLS: 'C:\\Program Files (x86)\\Microsoft Visual Studio 11.0\\Common7\\Tools\\',
  //       VS120COMNTOOLS: 'C:\\Program Files (x86)\\Microsoft Visual Studio 12.0\\Common7\\Tools\\',
  //       VS140COMNTOOLS: 'D:\\Program Files (x86)\\Microsoft Visual Studio 14.0\\Common7\\Tools\\',
  //       VSSDK140INSTALL: 'D:\\Program Files (x86)\\Microsoft Visual Studio 14.0\\VSSDK\\',
  //       WD: 'D:\\mingw\\msys\\1.0\\\\bin\\',
  //       WINDIR: 'C:\\WINDOWS',
  //       WINDOWS_TRACING_FLAGS: '3',
  //       WINDOWS_TRACING_LOGFILE: 'C:\\BVTBin\\Tests\\installpackage\\csilogfile.log',
  //       _: 'd:/mingw/msys/1.0/local/nodejs/node' },
  //    pid: 46528,
  //    features:
  //     { debug: false,
  //       uv: true,
  //       ipv6: true,
  //       tls_npn: true,
  //       tls_alpn: true,
  //       tls_sni: true,
  //       tls_ocsp: true,
  //       tls: true },
  //    _needImmediateCallback: false,
  //    execPath: 'd:\\mingw\\msys\\1.0\\local\\nodejs\\node.exe',
  //    debugPort: 5858,
  //    _startProfilerIdleNotifier: [Function: _startProfilerIdleNotifier],
  //    _stopProfilerIdleNotifier: [Function: _stopProfilerIdleNotifier],
  //    _getActiveRequests: [Function: _getActiveRequests],
  //    _getActiveHandles: [Function: _getActiveHandles],
  //    reallyExit: [Function: reallyExit],
  //    abort: [Function: abort],
  //    chdir: [Function: chdir],
  //    cwd: [Function: cwd],
  //    umask: [Function: umask],
  //    _kill: [Function: _kill],
  //    _debugProcess: [Function: _debugProcess],
  //    _debugPause: [Function: _debugPause],
  //    _debugEnd: [Function: _debugEnd],
  //    hrtime: [Function: hrtime],
  //    cpuUsage: [Function: cpuUsage],
  //    dlopen: [Function: dlopen],
  //    uptime: [Function: uptime],
  //    memoryUsage: [Function: memoryUsage],
  //    binding: [Function: binding],
  //    _linkedBinding: [Function: _linkedBinding],
  //    _setupDomainUse: [Function: _setupDomainUse],
  //    _events:
  //     { warning: [Function],
  //       newListener: [Function],
  //       removeListener: [Function],
  //       SIGWINCH: [Object] },
  //    _rawDebug: [Function],
  //    _eventsCount: 4,
  //    domain: null,
  //    _maxListeners: undefined,
  //    _fatalException: [Function],
  //    _exiting: false,
  //    assert: [Function],
  //    config: { target_defaults: [Object], variables: [Object] },
  //    emitWarning: [Function],
  //    nextTick: [Function: nextTick],
  //    _tickCallback: [Function: _tickCallback],
  //    _tickDomainCallback: [Function: _tickDomainCallback],
  //    stdout: [Getter],
  //    stderr: [Getter],
  //    stdin: [Getter],
  //    openStdin: [Function],
  //    exit: [Function],
  //    kill: [Function],
  //    argv0: 'd:\\mingw\\msys\\1.0\\local\\nodejs\\node.exe',
  //    mainModule:
  //     Module {
  //       id: '.',
  //       exports: {},
  //       parent: null,
  //       filename: 'c:\\Users\\~\\codes\\mygit\\test\\web\\ecma\\execution.js',
  //       loaded: false,
  //       children: [],
  //       paths: [Object] } },
  // Buffer:
  //  { [Function: Buffer]
  //    poolSize: 8192,
  //    from: [Function],
  //    alloc: [Function],
  //    allocUnsafe: [Function],
  //    allocUnsafeSlow: [Function],
  //    isBuffer: [Function: isBuffer],
  //    compare: [Function: compare],
  //    isEncoding: [Function],
  //    concat: [Function],
  //    byteLength: [Function: byteLength] },
  // clearImmediate: [Function],
  // clearInterval: [Function],
  // clearTimeout: [Function],
  // setImmediate: [Function],
  // setInterval: [Function],
  // setTimeout: [Function],
  // console: [Getter] }

  console.log(global.msg);  // undefined

  function f2() {
    // console.log(this); // global object

  }
  f2();
}

console.log(global.msg); // undefined
f1(); // 执行到函数时，函数的环境会被推入环境栈，执行完从栈中弹出

var name = 'global user';

var person = {
  name: 'person user',
  son: {
    name: 'son user',
    getName: function () {
      console.log(this);
      return this.name;
    }
  },
  getName: function () {
    console.log(this);
    return this.name;
  }
}

console.log(person.son.getName());  // son user, this -> { name: 'son user', getName: [Function: getName] }
var son = person.son.getName();
console.log(son);                // son user, this -> { name: 'son user', getName: [Function: getName] }
var son2 = person.son.getName;
console.log(son2());             // node: undefined this -> global object, firefox: global user
var people = person.getName();
console.log(people);             // person user, this -> { name: 'person user', son: { name: 'son user', getName: [Function: getName] }, getName: [Function: getName] }
var people2 = person.getName;
console.log(people2());          // node: undefined, this -> global object, firefox: global user

// node

console.log();
console.log('------------- function execution ---------------------');
console.log();
(function () {
  console.log(this);  // global object
  var name = 'global user';

  var person = {
    name: 'person user',
    son: {
      name: 'son user',
      getName: function () {
        console.log(this);
        return this.name;
      }
    },
    getName: function () {
      console.log(this);
      return this.name;
    }
  }

  console.log(person.son.getName());  // son user, this -> { name: 'son user', getName: [Function: getName] }
  var son = person.son.getName();
  console.log(son);                // son user, this -> { name: 'son user', getName: [Function: getName] }
  var son2 = person.son.getName;
  console.log(son2());             // node: undefined this -> global object, firefox: global user
  var people = person.getName();
  console.log(people);             // person user, this -> { name: 'person user', son: { name: 'son user', getName: [Function: getName] }, getName: [Function: getName] }
  var people2 = person.getName;
  console.log(people2());          // node: undefined, this -> global object, firefox: global user

} ());
