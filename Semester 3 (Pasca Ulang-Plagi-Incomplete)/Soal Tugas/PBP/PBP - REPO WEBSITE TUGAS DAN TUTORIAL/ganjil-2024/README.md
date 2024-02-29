# Docubase

![grogu](https://images3.alphacoders.com/110/thumb-1920-1108129.jpg)

A Template base interface for Docusaurus Projects in CBK2000 Group

This website is built using [Docusaurus 2](https://docusaurus.io/)

## Prerequisites
1. [Node Js](https://nodejs.org/en) version 18 or above
2. Yarn

## Development

1. Package installation

    ```
    $ yarn
    ```

2. Run the application

    ```
    $ yarn start
    ```

3. Translate plugin data

    ```
    $ yarn write-translations --locale en
    ```

    Please refer to [i18n Tutorial](https://docusaurus.io/docs/next/i18n/tutorial#translate-plugin-data).

## Ground Rules
1. No Direct Commits to Main (Contribute using branch and pull requests)
    ```
    git checkout -b "Branch Name"
    ```
    Branch naming is preferably on the feature that is being implemented/a bug that is being fixed/a chore that is being done
2. Make sure the production branch is named 'main'
3. Deployment to github pages is done automatically through github runners (Reference point master branch)
4. For developers in windows, make sure to configure git to use LF ending instead of CRLF using either
    a. Locally
    ```
    git config core.autocrlf false
    ```
    b. Globally
    ```
    git config --global core.autocrlf false
    ```
5. Make sure to make a clear commit message

## Resources

[doit-revamp](https://github.com/cbk2000/doit-revamp)
