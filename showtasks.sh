#!/usr/bin/env bash

export SAFARI=/Applications/Safari.app
export RUNCRUD=runcrud.sh

start_runcrud() {
  sh ./runcrud.sh
}

open_page() {
  open -a $SAFARI http://localhost:8080/crud/v1/task/getTasks
  echo "Opening a website..."
}

fail() {
  echo "File runcrud.sh was not found"
}

if test -f "$RUNCRUD"; then
  start_runcrud
  open_page
else
  fail
fi
