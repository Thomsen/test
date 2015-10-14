mkdir target

set m2_repo=%USERPROFILE%/.m2/repository
set spring=%m2_repo%\org\springframework
set slf4j=%m2_repo%\org\slf4j
set logging=%m2_repo%\commons-logging
set classpath="%JAVA_HOME%\jre\lib\rt.jar;%spring%\spring-beans\4.0.6.RELEASE\spring-beans-4.0.6.RELEASE.jar;%spring%\spring-context\4.0.6.RELEASE\spring-context-4.0.6.RELEASE.jar;%spring%\spring-core\4.0.6.RELEASE\spring-core-4.0.6.RELEASE.jar;%spring%\spring-expression\4.0.6.RELEASE\spring-expression-4.0.6.RELEASE.jar;%slf4j%\slf4j-log4j12\1.7.5\slf4j-log4j12-1.7.5.jar;%logging%\commons-logging\1.1.3\commons-logging-1.1.3.jar;.;.\target"

set prop=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9010 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false 

javac -d target -cp %classpath% SpringMain.java Order.java OrderFeed.java OrderRecord.java OrderQueueMonitor.java
java %prop% -cp %classpath% SpringMain