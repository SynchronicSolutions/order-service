#!/usr/bin/env bash
set -Eeuo pipefail

CONTAINER_NAME=items_db
DB_USER=items_admin
DB_PASS=items_admin
DB_NAME=items_db
PG_IMAGE=postgres:14.11

healthcheck() {
    until docker exec ${CONTAINER_NAME} pg_isready -U users
            do
                echo "Waiting for ${CONTAINER_NAME}..."
    sleep 2
    done
    echo "Done!"
}

remove_untagged_images() {

    if [[ $(docker images -f "dangling=true" -q | wc -c) -ne 0 ]]
    then
    echo "Clean untagged images"
    docker rmi "$(docker images -f "dangling=true" -q)" -f
    fi
}

start() {

    docker run \
    --name ${CONTAINER_NAME} \
    -e POSTGRES_USER=${DB_USER} \
    -e POSTGRES_PASSWORD=${DB_PASS} \
    -e POSTGRES_DB=${DB_NAME} \
    -p 5432:5432 \
    -d ${PG_IMAGE}
}

stop() {
    docker rm -f ${CONTAINER_NAME}
}

case $1 in

"start")
stop
start
healthcheck
remove_untagged_images
;;

"stop")
stop
;;

*)
echo "Unknown command: ${1}. Exiting."
;;
esac