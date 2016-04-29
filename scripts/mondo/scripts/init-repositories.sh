#!/bin/bash

function help {
  echo "Usage: $(basename $0) <gold repository name> (--apache2 | --apache) [--delete-gold]"
  echo "  gold repository name: the name of the gold repository"
  echo "  --apache | --apache2: Decide the username of Apache. For Apache: apache.apache, For Apache2: www-data:www-data"
  echo "  -dg | --delete-gold: Delete the gold repository if it already exist"
  exit
}

if [ $# -lt 1 -o "$1" == "--help" -o "$1" == "-h" ]; then
  help
fi
set -e

if [ "$2" == "--apache" ]; then
  APACHE_USER="apache.apache"
elif [ "$2" == "--apache2" ]; then
  APACHE_USER="www-data:www-data"
else
  help
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




DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

GOLD_REPO_NAME=$1

GOLD_REPO_URL=$(concate_path_parts $URL $SVN_URL_PATH $GOLD_REPO_NAME)

# Load config file using the source command
. $DIR/../config/$GOLD_REPO_NAME/config.properties

# Check parameter
if [ "$3" == "--delete-gold" ]; then
  # Delete if exists
  if [ -d $SVN_PATH_OS/$GOLD_REPO_NAME ]; then
    echo "Delete existing gold repository in $SVN_PATH_OS/$GOLD_REPO_NAME"
    rm -rf $SVN_PATH_OS/$GOLD_REPO_NAME
  fi
fi

if [ ! -d $SVN_PATH_OS/$GOLD_REPO_NAME ]; then
  echo "Create Gold Repository: $SVN_PATH_OS/$GOLD_REPO_NAME"
  svnadmin create $SVN_PATH_OS/$GOLD_REPO_NAME
else
  echo "Gold Repository already exists: $SVN_PATH_OS/$GOLD_REPO_NAME"
fi

if [ -z $APACHE_USER ]; then
  echo "Apache username:"
  APACHE_USER=$(cat)
fi
chown -R $APACHE_USER /$SVN_PATH_OS/$GOLD_REPO_NAME

echo "#!/bin/sh" > $SVN_PATH_OS/$GOLD_REPO_NAME/hooks/pre-commit
echo "set -e" >> $SVN_PATH_OS/$GOLD_REPO_NAME/hooks/pre-commit
echo "$DIR/../hooks/gold-pre-commit.sh \$1 \$2 $GOLD_REPO_NAME" >> $SVN_PATH_OS/$GOLD_REPO_NAME/hooks/pre-commit
chmod 755 $SVN_PATH_OS/$GOLD_REPO_NAME/hooks/pre-commit
chown -R $APACHE_USER $SVN_PATH_OS/$GOLD_REPO_NAME/hooks/pre-commit
echo "Create Post-Commit hook"

#go on even if an error is encountered
set +e

if [ -f $DIR/../config/$GOLD_REPO_NAME/gen/user_front.properties ]; then
  echo "User-Front mapping exists in $DIR/../config/$GOLD_REPO_NAME/gen/user_front.properties"
  USER_FRONT_MAPPING=$(cat $DIR/../config/$GOLD_REPO_NAME/gen/user_front.properties)
  for entry in $USER_FRONT_MAPPING; do
    oldIFS=$IFS
    IFS='='
    set x $entry
    USER=$2
    REPO=$3

    $DIR/../scripts/add-front-repository.sh $REPO $USER $GOLD_REPO_NAME $APACHE_USER
  done
fi
