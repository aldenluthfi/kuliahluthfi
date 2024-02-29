name: Deploy

on:
  push:
    branches:
      - main
      - master

jobs:
  Deployment:
    if: github.ref == 'refs/heads/main'
    runs-on: ubuntu-latest
    steps:
    - name: Cloning repo
      uses: actions/checkout@v4
      with:
        fetch-depth: 0

    - name: Push to Dokku server
      uses: dokku/github-action@master
      with:
        branch: 'main'
        git_remote_url: ssh://dokku@${{ secrets.DOKKU_SERVER_IP }}/${{ secrets.DOKKU_APP_NAME }}
        ssh_private_key: ${{ secrets.DOKKU_SSH_PRIVATE_KEY }}