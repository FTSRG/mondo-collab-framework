#!/bin/bash

if [ $# -lt 1 -o "$1" == "--help" -o "$1" == "-h" -o "$1" == "" ]; then
  echo "Usage: $(basename $0) <public_gold_name>"
  echo "   -public_gold_name: the name of the gold repository for which the fronts should be reset"
  exit
fi

# concate path parts and includes a slash "/" if necessary
function concate_path_parts {
  result=$1
  for part in "$@"
  do
      if [ "$part" != "$result" ]; then
        if [[ $part == \/* ]]; then
          part_updated=${part:1}
        else
          part_updated=$part
        fi
        case "$result" in
        */)
            result=$result$part_updated
            ;;
        *)
            result=$result/$part_updated
            ;;
        esac
      fi
  done
  echo $result
}

# Replace from $2 to $3 in $1
function replace {
    echo "${1/$2/$3}"
}

set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

GOLD_REPO_NAME=$1

GOLD_REPO_URL=$(concate_path_parts $URL $SVN_URL_PATH $GOLD_REPO_NAME)

# Load config file using the source command
. $DIR/../config/global-config.properties
. $DIR/../config/$GOLD_REPO_NAME/config.properties



# Delete locks
echo "Removing locks:"
find "$DIR/../lock/" -mindepth 1 -depth -print0 | grep -vEzZ '(\.git(/|$)|/\.gitignore$)' | xargs -0 echo rm -rvf
find "$DIR/../lock/" -mindepth 1 -depth -print0 | grep -vEzZ '(\.git(/|$)|/\.gitignore$)' | xargs -0 rm -rvf >/dev/null


# Regenerating front repositories
GOLD_REPO_URL=$(concate_path_parts $URL $SVN_URL_PATH $GOLD_REPO_NAME)
while IFS='' read -r LINE || [[ -n "$LINE" ]]; do

	USER_NAME=$(echo $LINE | cut -d'=' -f 1)
	FRONT_REPO_NAME=$(echo $LINE | cut -d'=' -f 2)
	echo $USER_NAME
	echo $FRONT_REPO_NAME

	./reset-front-repository.sh $GOLD_REPO_NAME $USER_NAME

done < "$DIR/../config/$GOLD_REPO_NAME/gen/user_front.properties"

