#!/bin/bash
set -e -u
# Makes a copy of the primes

cd `dirname $0`;
mkdir -p primes/raw
mkdir -p primes/extract

pushd primes/raw
for i in {1..50}; do
    wget --no-check-certificate https://primes.utm.edu/lists/small/millions/primes$i.zip
    pushd ../extract
    unzip ../raw/primes$i.zip
    popd
done
popd


