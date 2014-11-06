#### Compile&Run ####

首先系统中安装maven工具，通过maven安装依赖包，进入目录下执行

```cmd

$ mvn install

```

运行程序，可能需要修改相应的依赖目录。

```cmd

$ run.bat

```

#### jconsole&jvisualvm ####

使用jdk内置的jconcole监控内存状态，通过远程连接127.0.0.1:9010，堆内存、非堆内存、内存池（PS Eden Space、PS Surviver Space、PS Old Gen、Code Cache、PS Perm Gen）。

同样jdk内置的jvisualvm也能监控内存状态，新建jmx远程连接127.0.0.1:9010，可以监控到CPU、堆内存、PermGen内存、类、线程状态。可以heap dump。

#### mat ####

获取到堆dump后，可以通过eclipse的插件mat（memory analyzer tool）进行分析

