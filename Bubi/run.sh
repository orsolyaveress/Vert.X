export JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000 -Djavax.net.ssl.trustStore=cacerts_with_startcom.jks -Djavax.net.ssl.trustStorePassword=changeit"

#export JAVA_OPTS="-Djavax.net.ssl.trustStore=cacerts_with_startcom.jks -Djavax.net.ssl.trustStorePassword=changeit"

java $JAVA_OPTS -jar target/*-fat.jar -conf config.json
