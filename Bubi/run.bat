set JAVA_OPTS=
rem set JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=6000

set JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=6000 -Dfile.encoding=UTF-8 -Djavax.net.ssl.trustStore=cacerts_with_startcom_and_letsencrypt.jks -Djavax.net.ssl.trustStorePassword=changeit

set JAVA_OPTS=%JAVA_OPTS% -jar target\bubi-1.0-SNAPSHOT-fat.jar

java %JAVA_OPTS%  -conf config.json