#!/bin/sh

echo "The application will start in ${KVLOAD_BALANCER_SLEEP}s..." && sleep ${KVLOAD_BALANCER_SLEEP}
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Duser.timezone=Asia/Dhaka -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "bd.ac.buet.kvloadbalancer.KvloadBalancerApplication"  "$@"
