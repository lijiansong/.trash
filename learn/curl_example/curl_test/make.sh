#!/bin/bash
# compile scripts


g++ -o bdvoice bdvr.cpp -L . -l json_linux-gcc-4.8_libmt -l curl
