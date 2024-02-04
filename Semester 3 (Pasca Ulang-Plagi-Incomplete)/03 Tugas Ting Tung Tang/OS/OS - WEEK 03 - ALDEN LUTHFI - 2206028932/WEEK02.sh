cat 'inputSCRAP.txt' | grep -o '[0-9]\{10\}' | sort | uniq > 'outputSCRAP.txt'
