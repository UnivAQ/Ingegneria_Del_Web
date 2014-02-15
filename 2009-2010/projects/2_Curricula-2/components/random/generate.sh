#!/bin/bash

here="$(dirname $0)/"
cd "${here}"

#random=$(md5 -q -s "${RANDOM}${RANDOM}")
random=$(echo -n "${RANDOM}${RANDOM}" | md5sum)

echo ${random:0:16}
