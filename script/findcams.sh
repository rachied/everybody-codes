#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ]
then
        echo "2 arguments required!"
        exit 1
fi

if [ "$1" != "--name" ]
then
        echo "--name is the only supported option!"
        exit 1;
fi

tail -n +2 ./data/cameras.csv | grep -in "$2" | grep --invert-match "ERROR" | sed 's/:/ | /' | sed 's/;/ | /g'

# tail -n +2 ./data/cameras.csv
# Used to ignore first line of file (csv header)

# grep -in "$2"
# used to match the name we're searching for with the following options:
# -i (case insensitive search)
# -n (adds line numbers)

# grep --invert-match "ERROR"
# Remove lines containing the word "ERROR"

# sed 's/:/ | /'
# Replace the line number separator : with | and adds padding

# sed 's/;/ | /g'
# Turns the csv separator ; into | and adds padding
