#!/bin/bash

here="$(dirname $0)/"
cd "${here}"

mongod --rest --directoryperdb --dbpath ../../sandbox/db
#mongod --dbpath ../../sandbox/db
