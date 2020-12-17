#!/bin/bash
set -e
service mysql start
mysql < /mysql/init_dbuser_table.sql
#service mysql stop