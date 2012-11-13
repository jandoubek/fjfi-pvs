#! /bin/bash

echo "Starting crawling for emails"
echo "on URL: $1"

SCRIPTHOME=`pwd`

CLASSPATH="$SCRIPTHOME/../lib/*:$SCRIPTHOME/../config:$CLASSPATH"

java -cp $CLASSPATH cz.fjfi.pvs.web.EmailCrawlerSophisticated $1
