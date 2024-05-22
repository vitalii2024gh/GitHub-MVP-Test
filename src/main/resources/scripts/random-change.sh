#!/bin/sh
CURRENT_DATE=`date`
echo "Writing date ${CURRENT_DATE} to file ${1}\n"
echo "${CURRENT_DATE}" > "${1}"
