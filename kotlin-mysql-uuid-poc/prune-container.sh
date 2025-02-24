#!/bin/bash

podman container rm kotlin_mysql_uuid_poc
podman volume rm kotlin-mysql-uuid-poc_kotlin_mysql_uuid_poc_data
podman network rm kotlin-mysql-uuid-poc_kotlin_mysql_uuid_poc_net