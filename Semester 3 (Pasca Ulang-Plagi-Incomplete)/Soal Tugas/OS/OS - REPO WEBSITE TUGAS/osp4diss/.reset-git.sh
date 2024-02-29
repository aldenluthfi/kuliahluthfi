# REV02: Sun 28 Aug 2022 07:00
# REV01: Sat 27 Aug 2022 22:00
# START: Sat 17 Jul 2021 18:00
#  Checkout
git checkout --orphan latest_branch
# Add all the files
git add -A
# Commit the changes
git commit -am "TOTAL RESET" --allow-empty
# Delete the branch
git branch -D master
# Rename the current branch to master
git branch -m master
# Finally, force update your repository
git push -f origin master
# prune
git gc --aggressive --prune=all
# upstream
git push --set-upstream origin master
# BACKUP
git push -f BACKUP

