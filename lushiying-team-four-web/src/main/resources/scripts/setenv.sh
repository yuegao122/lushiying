#!/bin/sh

# 设置监听端口
USER_OPTS=" -Dserver.port=$PORT"

# 使用G1垃圾回收器
GC_OPTS="-XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+UnlockExperimentalVMOptions -XX:G1NewSizePercent=25 -XX:G1MaxNewSizePercent=75 -XX:+ParallelRefProcEnabled -XX:InitiatingHeapOccupancyPercent=50 -XX:+PrintGCTimeStamps -XX:+PrintAdaptiveSizePolicy -XX:+PrintClassHistogram -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime -XX:+PrintReferenceGC -XX:+HeapDumpOnOutOfMemoryError"

# 设置启动模式
if [ "$ENVTYPE" != "" ]; then
    USER_ARGS=" --spring.profiles.active=$ENVTYPE"
else
    USER_OPTS="$USER_OPTS -DCAT_HOME=/cat-prod/"
    USER_ARGS=" --spring.profiles.active=prod"
fi

# 设置配置路径
USER_ARGS="$USER_ARGS --spring.config.location=$MATRIX_CODE_DIR/conf/"