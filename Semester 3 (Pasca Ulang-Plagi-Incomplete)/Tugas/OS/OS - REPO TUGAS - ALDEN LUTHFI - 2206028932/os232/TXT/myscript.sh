#!/bin/bash
# Copyright (C) 2020-2023 Cicak Bin Kadal

# This free document is distributed in the hope that it will be 
# useful, but WITHOUT ANY WARRANTY; without even the implied 
# warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

# REV33: Mon 11 Sep 2023 17:00
# REV29: Mon 13 Feb 2023 13:30
# REV19: Sun 05 Feb 2023 20:00
# REV11: Sun 08 May 2022 06:00
# REV02: Sun 19 Sep 2021 15:00
# START: Mon 28 Sep 2020 21:00

# ATTN:
# You new to set "REC2" with your own Public-Key Identity!
# Check it out with "gpg --list-key"
# ####################### Replace REC2 ####
REC2="268508DA1B4C59B78D805E931989F2BF9BEE8AE3"
# ####################### ####### #### ####
# REC1: public key
REC1="63FB12B215403B20"
# WEEKURL="http://localhost:4000/WEEK/WEEK.txt"
WEEKURL="https://os.vlsm.org/WEEK/WEEK.txt"
FILES="my*.asc my*.txt my*.sh"
SHA="SHA256SUM"
RESDIR="$HOME/RESULT/"
usage()  { echo "Usage: $0 [-w <WEEK>]" 1>&2; exit 1; }
nolink() { echo "No LINK $1"            1>&2; exit 1; }

# Check current WEEK
unset WEEK DEFAULT
if [ ! -z "${1##*[!0-9]*}" ] ; then
  WEEK=$1
elif [ -z $1 ] ; then
  DEFAULT=1
else while getopts ":w:W:" varTMP
  do
    case "${varTMP}" in
     w|W)
       WEEK=${OPTARG}
       [ ! -z "${WEEK##*[!0-9]*}" ] || usage ;;
    esac
  done
  [ -z $WEEK ] && usage
fi

if [ $DEFAULT ] ; then
  [[ $(wget $WEEKURL -O- 2>/dev/null) ]] || nolink $WEEKURL
  intARR=($(wget -q -O - $WEEKURL | awk '/\| Week / { 
    cmd = "date -d " $2 " +%s"
    cmd | getline mydate
    close(cmd)
    print mydate + (86400 * 6)
  }'))
  DATE=$( LANG=en_us_8859_1;date -d $(date +%d-%b-%Y) +%s)
  for II in ${!intARR[@]} ; do
    (( $DATE > ${intARR[$II]} )) || break;
  done
  WEEK=$II
  # echo "DEBUG:TMP:$DEFAULT:W[$WEEK]:$1:$DATE:"
fi

(( WEEK > 11 )) && WEEK=11
WEEK=$(printf "W%2.2d\n" $WEEK)

# echo $WEEK ; exit

# Is this the correct WEEK?
read -r -p "Is this WEEK $WEEK ? [y/N] " response
case "$response" in
    [yY][eE][sS]|[yY]) 
        ;;
    *)
        echo "It is not Week $WEEK!"
        usage
        ;;
esac

# TXT
[ -d $RESDIR ] || mkdir -p $RESDIR
pushd $RESDIR
for II in W?? ; do
    [ -d $II ] || continue
    TARFILE=my$II.tar.xz
    TARFASC=$TARFILE.asc
    rm -vf $TARFILE $TARFASC
    echo "tar cfJ $TARFILE $II/"
    tar cfJ $TARFILE $II/
    echo "gpg --armor --output $TARFASC --encrypt --recipient $REC1 --recipient $REC2 $TARFILE"
    gpg --armor --output $TARFASC --encrypt --recipient $REC1 --recipient $REC2 $TARFILE
done
popd

if [[ "$WEEK" != "W00" ]] && [[ "$WEEK" != "W01" ]] ; then
    II="${RESDIR}my$WEEK.tar.xz.asc"
    echo "Check and move $II..."
    [ -f $II ] && mv -vf $II .
fi

echo "rm -f $SHA $SHA.asc"
rm -f $SHA $SHA.asc

echo "sha256sum $FILES > $SHA"
sha256sum $FILES > $SHA

echo "# ################ CHECKSUM ###### #########"
echo "sha256sum -c $SHA"
sha256sum -c $SHA

echo "# ################# SIGNING CHECKSUM ######### ######### ########"
echo "gpg --output $SHA.asc --armor --sign --detach-sign $SHA"
gpg --output $SHA.asc --armor --sign --detach-sign $SHA

echo "# ################# VERIFY ######### ######### ######### ########"
echo "gpg --verify $SHA.asc $SHA"
gpg --verify $SHA.asc $SHA

echo ""
echo "==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ===="
echo "==== ==== ==== ATTN: is this WEEK $WEEK ?? === ==== ==== ===="
echo "==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ===="
echo ""

exit 0
