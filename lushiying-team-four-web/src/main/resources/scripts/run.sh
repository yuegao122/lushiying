#!/bin/sh

if [ "$JAVA_HOME" = "" ]; then
    echo "Error: JAVA_HOME is not set."
    exit 1
fi
APPLICATION_DOMAIN="domain.layer.mls.lianjia.com"
APP_DIR="/data0/www/htdocs/${APPLICATION_DOMAIN}/"
APP_CONF_DIR=${APP_DIR}"conf/"
INIT_FILE_PATH="${APP_DIR}""system/MATRIX_ENV_CONF.ini"
#LOG_PATH=`awk -F '=' '/'base'/{a=1}a==1&&$1~/'MATRIX_APPLOGS_DIR'/{print $2;exit}' ${INIT_FILE_PATH}`
LOG_PATH="/data0/www/applogs/${APPLICATION_DOMAIN}/"
CACHE_PATH="/data0/www/cache/${APPLICATION_DOMAIN}/"
STDOUT_LOG="${LOG_PATH}""console.log"
#java命令
JAVA_COMMENT="$JAVA_HOME/bin/java"
#jvm 参数
JAVA_OPTS=" -server -Xmn1024m -Xms4096m -Xmx4096m -XX:PermSize=64M -XX:MaxPermSize=128M  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:${LOG_PATH}gc.log"
#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=11455"

#待启动jar
JARFILE=`find "$APP_DIR/lib" -name *.jar`

ACTIVE_ENV="--spring.profiles.active=prod"
if [ ! -z $2 ]; then
	ACTIVE_ENV="--spring.profiles.active=$2"
fi
PROCESS_COUNT=`ps -ef|grep java|grep ${APPLICATION_DOMAIN}|awk '{print $2}'|wc -l`
if [[ $PROCESS_COUNT -gt 0 ]];then
        ps -ef|grep java|grep ${APPLICATION_DOMAIN}|awk '{print $2}'|xargs kill
fi

if [ ! -d ${LOG_PATH} ]; then
    mkdir -p ${LOG_PATH}
fi
exec java $JAVA_OPTS -jar $JARFILE  $ACTIVE_ENV --spring.config.location=${APP_CONF_DIR} > /dev/null 2>&1
echo "java $JAVA_OPTS -jar $JARFILE $ACTIVE_ENV --spring.config.location=${APP_CONF_DIR}"
echo $! > ${LOG_PATH}server.pid
