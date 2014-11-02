#!/bin/bash
#I do not own the rights of this code
#source: http://stackoverflow.com/questions/392022/best-way-to-kill-all-child-processes 
#by zhigang

killtree() {
    local _pid=$1
    local _sig=${2:--TERM}
    kill -stop ${_pid} 
    for _child in $(ps -o pid --no-headers --ppid ${_pid}); do
        killtree ${_child} ${_sig}
    done
    kill -${_sig} ${_pid}
}

if [ $# -eq 0 -o $# -gt 2 ]; then
    echo "Usage: $(basename $0) <pid> [signal]"
    exit 1
fi

killtree $@