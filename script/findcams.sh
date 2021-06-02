#!/bin/bash

example="Example: findcams.sh --name plein"
if [ -z "$1" ] || [ -z "$2" ]
  then
      echo -e "Error: 2 arguments required!\n${example}"
      exit 1
fi

if [ "$1" != "--name" ]
  then
      echo -e "Error: --name is the only supported option!\n${example}"
      exit 1
fi

cd "$(dirname "${BASH_SOURCE[0]}")"
regex="CM-([0-9]+)"

tail -n +2 './data/cameras.csv' | grep -i "$2" | grep --invert-match "ERROR" | sed 's/;/ | /g' | while read -r line; do
  if [[ $line =~ $regex ]]
      then
        number="${BASH_REMATCH[1]}"
        echo "${number} | ${line}"
  fi
done

# cd "$(dirname "${BASH_SOURCE[0]}")"
# set working directory to this scripts location, in order to access files using relative paths

# tail -n +2 ./data/cameras.csv
# Used to ignore first line of file (csv header)

# grep -i "$2"
# searches lines matching the name with case insensitive setting

# grep --invert-match "ERROR"
# Remove malformed entries (lines containing the word "ERROR")

# sed 's/;/ | /g'
# Turns the csv separator ; into | and adds padding
