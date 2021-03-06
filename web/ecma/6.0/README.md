[ES.next][]

[ES.next]: http://wiki.ecmascript.org/doku.php?id=harmony:specification_drafts

Learn from [Rev 38 Final Draft](http://wiki.ecmascript.org/lib/exe/fetch.php?id=harmony%3Aspecification_drafts&cache=cache&media=harmony:ecma-262_6th_edition_final_draft_-04-14-15.pdf).

各大浏览器对ES6的最新[支持](http://kangax.github.io/compat-table/es6/)。

使用node 0.12版可以体验ES6的部分特性，通过[nvm](https://github.com/creationix/nvm)安装node 0.12。

使用io.js可以体验ES6的更多特性，通过源码安装（配置环境变量）：

$  xz -d iojs-v2.3.0-linux-x64.tar.xz &  tar xvf iojs-v2.3.0-linux-x64.tar

io.js 2.3.0集成了V8 4.2.77.20版本，其中包含的ES6特性远超joyent/node@0.12.x集成的3.26.33版本所包含的。

查看io.js所集成的v8版本 iojs -p process.versions.v8

查看io.js正在开发的特性 iojs --v8-options | grep 'in progress'

模块 | 知识点 | 示例
:------------- | :------------- | :-----------------
变量 | let、const、deconstruction | [let demo](../ecma/variable/letdemo.js) [const demo](../ecma/variable/constdemo.js) [deconstruction](../ecma/variable/deconstruction.js)
字符串 | codePointAt、fromCodePoint、at、Unicode、normalize、  includes、startsWith、endsWith、repeat、template string | [string demo](../ecma/variable/strdemo.js)
数值 | binary、octal、isiFnite、isNaN、parseInt、parseFloat、  isInteger、Math extends | [number demo](../ecma/variable/numberdemo.js)
数组 | Array.from | [array demo](../ecma/variable/arraydemo.js)
对象 | promise | [promise demo](../ecma/object/promisedemo.js)
函数 | generator | [generator demo](../ecma/function/generatordemo.js) 
集合 | map、set、weakmap、weakset | [set demo](../ecma/collection/setdemo.js) [map demo](../ecma/collection/mapdemo.js)
异步 | |


