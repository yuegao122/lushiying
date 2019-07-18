#!/bin/sh
echo update source code...

git pull

echo package application...

mvn -U -T 1C -P dev clean package -pl lushiying-team-four-web -am

echo stop application...

PID=$(ps -ef | grep lushiying-team-four-web.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo application is already stopped...
else
    kill -9  $PID
    echo killed $PID.
fi

echo start application...

cd lushiying-team-four-web/target/lushiying-team-four/lib

nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7018,suspend=n -jar house-intelligent-tools-web.jar > /dev/null 2>&1 &

echo open log...

tail -f  /data0/www/applogs/dev-lushiying-team-four/lushiying-team-four.log
