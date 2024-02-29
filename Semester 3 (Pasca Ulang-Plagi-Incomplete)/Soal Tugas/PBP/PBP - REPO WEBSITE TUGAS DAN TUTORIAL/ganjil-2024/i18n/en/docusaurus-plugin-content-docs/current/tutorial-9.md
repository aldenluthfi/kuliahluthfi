---
sidebar_label: Tutorial 9
sidebar_position: 11
Path: docs/tutorial-9
---

# Tutorial 9: Flutter Deployment with GitHub Actions and Microsoft App Center

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Learning Objectives

After completing this tutorial, students are expected to be able to:

- Understand the concepts of continuous integration and continuous deployment.
- Understand the concepts and usage of GitHub Actions.
- Understand the concepts and usage of Microsoft App Center.
- Implement continuous integration and continuous deployment on a Flutter application using GitHub Actions and Microsoft App Center.
- Implement GitHub Actions to automatically build and release Flutter applications.

## Introduction to CI/CD

CI/CD, which stands for Continuous Integration and Continuous Deployment, is an essential concept in software development related to GitHub Actions. This concept provides a way to automate and improve the quality and speed of software development.

Continuous Integration (CI) focuses on continuously integrating code changes into a shared repository by team members. When a developer makes changes to the code and submits it to the repository (as we do with GitHub), the CI system will automatically run a series of tests and verifications to ensure that the changes do not break or disrupt existing functionality. In other words, CI helps identify issues earlier in the development cycle.

Continuous Deployment (CD), on the other hand, involves automating the release of code changes that have passed the CI process to a production or testing environment. When code changes are deemed safe after passing a series of CI tests, CD allows for the automatic deployment of those changes to servers or other environments without manual intervention. This speeds up the development process and improves the speed of responding to business requirements.

When using GitHub Actions in CI/CD, every time there is a change in the repository, GitHub Actions can trigger a CI workflow to run tests and verifications. If everything is successful, a CD workflow can be activated to release those changes to the production or testing environment.

By using CI/CD, development teams can ensure that changes made do not compromise the quality or performance of the application. It also accelerates product release time and improves efficiency in managing the software development lifecycle overall.

Here is a visualization of the CI/CD flow diagram:

![Diagram CI/CD](https://cdn.discordapp.com/attachments/923523971226435584/1175755032008872006/CICD20V4.png?ex=656c6234&is=6559ed34&hm=26b3b31e2835db4a2a893ee5cae3b956ccf94018e4052874f36c4bcaa6071d7b&)

## Introduction to Github Actions

GitHub Actions is a feature provided by GitHub to enable automation in the software development cycle. In other words, GitHub Actions allows us to create and customize automated workflows to perform specific tasks whenever there is a change in a GitHub repository.

These workflows can be set to execute various actions or scripts automatically, such as running tests, building applications, or releasing new versions. The goal is to help developers automate these processes, allowing them to focus on writing code and developing features without being overly concerned with administrative steps.

For example, when there is a code change in a GitHub repository, GitHub Actions can automatically run the specified workflow. This workflow may include steps such as testing whether the change does not break existing functionality, building a new application, and even releasing a new version if necessary.

It's important to note that GitHub Actions utilizes a special configuration file (usually named `.github/workflows/filename.yml`) in the repository. This file contains a description of the steps to be executed by GitHub Actions.

With GitHub Actions, collaboration in software development can become more efficient because many tasks can be automated. This provides additional flexibility to developers and teams to customize their workflows according to project needs.

Here is an example of a simple workflow that we can use to build and test a JavaScript-based application using the Yarn package manager.

```yaml
name: Build and test

on:
push:
  branches:
    - main

jobs:
build:
  runs-on: ubuntu-latest
  steps:
    - uses: actions/checkout@v4
    - name: Install dependencies
      run: yarn install
    - name: Build
      run: yarn build

test:
  runs-on: ubuntu-latest
  steps:
    - uses: actions/checkout@v4
    - uses: actions/setup-node@v4
      with:
        node-version: 16
    - name: Install dependencies
      run: yarn install
    - name: Run tests
      run: yarn test
```

## Introduction to Microsoft App Center

Microsoft App Center is a cloud service that provides a variety of features to facilitate the build, test, release, and monitoring processes of applications. This service can be used for various platforms such as Android, iOS, Windows, macOS, and more. In this tutorial, we will use this service to automatically build and release Flutter applications.

Microsoft App Center provides many cool features for free, such as continuous integration, UI testing, continuous delivery, detailed crash and error reports from the application, and Analytics features. This time, we will not use all the available features because Flutter is not officially supported by App Center yet. Currently, the languages and frameworks that are officially supported are Kotlin, Java, React Native, Xamarin, and Unity. In this tutorial, we will focus on creating and releasing applications on App Center only. For more details, you can read the [App Center documentation](https://docs.microsoft.com/en-us/appcenter/).

## Tutorial: App Center Basic Configuration

In this tutorial, you will deploy your Flutter app that you have created in the previous tutorials to the App Center.

1. Create an [App Center](https://appcenter.ms/) account using your GitHub.

  ![Visual Studio App Center Registration Page](https://cdn.discordapp.com/attachments/987024899296088125/1174745256567263282/halaman_registrasi_app_center.png?ex=6568b5c6&is=655640c6&hm=352398be2a5f97a12f3d6a2f559d65e7797abaffc7c466a36c5435a46012f0a9&)
  
2. Create a new organization by clicking `Add new` -> `Add new organization`. Fill out the desired organization name.

  ![Add Organization](https://cdn.discordapp.com/attachments/987024899296088125/1174745257078964234/petunjuk_add_organization.png?ex=6568b5c6&is=655640c6&hm=0b329d563fde78dcb3aacfd4800048dc3f8c3883cf69e9873788440fa4784fb6&)

3. Create a new application slot by clicking the `Add app` button.

  ![Add App](https://cdn.discordapp.com/attachments/987024899296088125/1174745257494204446/petunjuk_add_app.png?ex=6568b5c7&is=655640c7&hm=c2be80e2a5e3af64fb5e19991d64c115c1113b6a65c08addd3d02b0457681588&)

4. Fill out the application name with `Shopping List`. You can skip the release type. Choose `Android` as the OS and `Java / Kotlin` as the platform.

  ![App Registration](https://cdn.discordapp.com/attachments/987024899296088125/1174745257884266597/pendaftaran_app.png?ex=6568b5c7&is=655640c7&hm=fecefbf76dcca3f1becba71801f3e2e62ecf5c269965ccec65f294319ef19ab5&)
  
5. Open the `Distribute` menu and select `Groups`.

  ![Add Group](https://cdn.discordapp.com/attachments/987024899296088125/1174775000784453783/petunjuk_add_group.png?ex=6568d17a&is=65565c7a&hm=a6dc96491190ee10685b41148a12d1b4f242d1650f674b08e15b3de30b378c8f&)

6. Create a new group by clicking the `Add Group` button. Set the group name as `Public` and give it pulic access by selecting the `Allow public access` toggle button. Click the `Create Group` button to create the group. This steps ensure that our released APK can be publicly accessed.

  ![Create Public Group](https://cdn.discordapp.com/attachments/987024899296088125/1174774965669740716/pembuatan_public_group.png?ex=6568d172&is=65565c72&hm=e4a286bc9472a991b4868500c1f51eb9d7a96dd210c48a8004f050b6ba72681f&)

At this point, you have successfully configure your App Center. Next, you will configure the scripts for deployment and signing your Flutter app.

## Tutorial: Signing Your Flutter App

Before publishing your app on the App Center, your Flutter app must be signed using a key to ensure the validity of your app. So, we will create a key and set up a CI/CD script to automatically sign your app.

1. Create a keystore.

    For Mac OS and Linux user, run this command in the Terminal.

    ```bash
    keytool -genkey -v -keystore ~/release-keystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias release
    ```

    For Windows user, run this command in the Command Prompt.

    ```bash
    keytool -genkey -v -keystore %userprofile%\release-keystore.jks -storetype JKS -keyalg RSA -keysize 2048 -validity 10000 -alias release
    ```
    
    This command is used to create a keystore file with name `release-keystore.jks` in your home directory with `release` as an alias. Move that file to your project's root folder. If your Terminal or Command Prompt does not recognize the `keytool` command, please follow [this guide](https://docs.flutter.dev/deployment/android#create-an-upload-keystore).

2. Add the following patterns to the `.gitignore` file in your project's root directory. These patterns ensure that the keystore file is not tracked by `git`. This step is necessary because the keystore file is confidential and should be treated like a password.

    ```yaml
    # Remember to never publicly share your keystore.
    # See https://flutter.dev/docs/deployment/android#reference-the-keystore-from-the-app
    *.keystore
    *.jks
    ```

3. Open the `/android/app/build.gradle` file and go to the `buildTypes` section.

    ```gradle
    buildTypes {
         release {
             // TODO: Add your own signing config for the release build.
             // Signing with the debug keys for now,
             // so `flutter run --release` works.
             signingConfig signingConfigs.debug
         }
    }
    ```

    Change that part as follows.

    ```gradle
    signingConfigs {
         release {
                 storeFile file("../../release-keystore.jks")
                 storePassword = "$System.env.KEY_PASSWORD"
                 keyAlias = "release"
                 keyPassword = "$System.env.KEY_PASSWORD"
         }
    }
     buildTypes {
         release {
             signingConfig signingConfigs.release
         }
    }
    ```

At this point, you have configured the signing for your app. Next, you will modify the Github Actions script and create a script to build the app file on App Center.

## Tutorial: Creating GitHub Actions Scripts

1. Generate a base64 string as a representation of the keystore file. This string will be stored as an environment variable later.

    Run the command `openssl base64 -in release-keystore.jks` on your project's root directory to create a base64 string. For now, save the generated string.
    
    Here is the example of the command result.

    ![Example Openssl](https://i.ibb.co/3kmxky1/Screenshot-2023-11-16-213743.jpg)

2. Create three repository secrets for your GitHub repository.

    i. `GH_TOKEN` with the value of your Github Personal Access Token. This will be used for the automated release.

    ii. `KEY_JKS` with the value of the base64 string you have generated before.

    iii. `KEY_PASSWORD`  with the value of the password you used when you create the keystore file.

3. Create a directory called `.github/workflows` on your project's root directory (if not yet created).

4. Create three new files inside the `.github/workflows` directory.
    
    > We **assume** that the `staging` branch is used to store your application code kode before release and the `main` branch is used to release your application.
   
    i. `staging.yml`. This script checks your codebase to ensure that the `staging` branch does not produce any error when the command `flutter analyze` is called. This script only triggered if there is any commit in the `staging` branch.

    ```yaml
    name: Staging

    # Controls when the workflow will run
    on:
      # Triggers the workflow on push or pull request events but only for the develop branch
      push:
        branches: [staging]
      pull_request:
        branches: [staging]
    
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "build"
      build:
        name: Analyze
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
        steps:
          - name: Checkout the code
            uses: actions/checkout@v4
    
          - name: Setup Java
            uses: actions/setup-java@v3
            with:
              distribution: 'zulu'
              java-version: '11'
    
          - name: Setup Flutter
            uses: subosito/flutter-action@v2
            with:
              channel: 'stable'
          
          - name: Get packages
            run: flutter pub get
            
          - name: Analyze
            run: flutter analyze
    ```

    ii. `pre-release.yml`. This script is used to check that the application build process can be run without error. If there is no error, the APK file can be accessed as an artifact. This script only triggered when there is a pull request created from the `staging` branch to the `main` branch.

    ```yaml
    name: Pre-Release

    # Controls when the workflow will run
    on: 
      # Triggers the workflow on pull request events but only for the main branch
      pull_request:
        branches: [main]
      
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "Build and Pre-Release APK"
      releases:
        name: Build and Pre-Release APK
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
        steps:
          - name: Checkout the code
            uses: actions/checkout@v4
    
          - name: Setup Java
            uses: actions/setup-java@v3
            with:
              distribution: 'zulu'
              java-version: '11'
    
          - name: Setup Flutter
            uses: subosito/flutter-action@v2
            with:
              channel: 'stable'
          
          - name: Get packages
            run: flutter pub get
    
          - name: Generate Java keystore
            env: 
              KEY_JKS: ${{ secrets.KEY_JKS }}
            run: echo "$KEY_JKS" | base64 --decode > release-keystore.jks 
                  
          - name: Build APK
            env:
              KEY_PASSWORD: ${{ secrets.KEY_PASSWORD }}
            run: flutter build apk --split-per-abi
          
          - name: Pre-release APK by uploading it to Artifacts
            uses: actions/upload-artifact@v3
            with:
              name: APKS
              path: build/app/outputs/flutter-apk/*.apk
    ```

    iii. `release.yml`. This script is used to build your application and release it as `Releases` on your GitHub repository. This script only triggered when there is a commit created in the `main` branch.

    ```yaml
    # This is a basic workflow to help you get started with Actions
    name: Release

    # Controls when the workflow will run
    on: 
      # Triggers the workflow on push events but only for the main branch
      push:
        branches: [main]
      
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "Build and Release APK"
      releases:
        name: Build and Release APK
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
        steps:
          - name: Checkout the code
            uses: actions/checkout@v4
          
          - name: Get version from pubspec.yaml
            id: version
            run: echo "::set-output name=version::$(grep "version:" pubspec.yaml | cut -c10-)"
    
          - name: Setup Java
            uses: actions/setup-java@v3
            with:
              distribution: 'zulu'
              java-version: '11'
    
          - name: Setup Flutter
            uses: subosito/flutter-action@v2
            with:
              channel: 'stable'
          
          - name: Get packages
            run: flutter pub get
                  
          - name: Generate Java keystore
            env: 
              KEY_JKS: ${{ secrets.KEY_JKS }}
            run: echo "$KEY_JKS" | base64 --decode > release-keystore.jks 
                  
          - name: Build APK
            env:
              KEY_PASSWORD: ${{ secrets.KEY_PASSWORD }}
            run: flutter build apk --split-per-abi
    
          - name: Get current date
            id: date
            run: echo "::set-output name=date::$(TZ='Asia/Jakarta' date +'%A %d-%m-%Y %T WIB')"
          
          - name: Release APK
            uses: ncipollo/release-action@v1
            with:
              allowUpdates: true
              artifacts: "build/app/outputs/flutter-apk/*.apk"
              body: "Published at ${{ steps.date.outputs.date }}"
              name: "v${{ steps.version.outputs.version }}"
              token: ${{ secrets.GH_TOKEN }}
              tag: ${{ steps.version.outputs.version }}
    ```

5. Save those files and push it to the GitHub repository. Check whether your app has successfully built and released by GitHub Actions automatically.

If your app have successfully built and released, congrats! At this point, we have created a workflow in GitHub. Next, we will create a new script to build your app on the App Center and do more configuration on the App Center.

Below is the structure of your Flutter app after doing this tutorial.

  ![Project structure](https://i.ibb.co/4pD8Jbw/Screenshot-2023-11-16-205934.png)

## Tutorial: Adding CI/CD Scripts for App Center

In this section, we will add continuous integration and continuous delivery (CI/CD) scripts to the Flutter application repository so that App Center can automatically build and generate the APK application files.

1. Open the `/android/app` folder.

2. Create a new file named `appcenter-post-clone.sh` and fill the file with the following code.

    ```bash
    #!/usr/bin/env bash
    # Place this script in project/android/app/

    cd ..

    # fail if any command fails
    set -e
    # debug log
    set -x

    cd ..
    git clone -b beta https://github.com/flutter/flutter.git
    export PATH=`pwd`/flutter/bin:$PATH

    flutter channel stable
    flutter doctor

    echo "Installed flutter to `pwd`/flutter"

    # export keystore for release
    echo "$KEY_JKS" | base64 --decode > release-keystore.jks

    # build APK
    # if you get "Execution failed for task ':app:lintVitalRelease'." error, uncomment next two lines
    # flutter build apk --debug
    # flutter build apk --profile
    flutter build apk --release

    # copy the APK where AppCenter will find it
    mkdir -p android/app/build/outputs/apk/; mv build/app/outputs/apk/release/app-release.apk $_
    ```

3. Open the `/android/.gitignore` file and modify it as follows. This is done to ensure that App Center recognizes the application as an Android application.

    ```yaml
    # add comment for app center
    # gradle-wrapper.jar
    # /gradlew
    # /gradlew.bat
    /.gradle
    /captures/
    /local.properties
    GeneratedPluginRegistrant.java

    # Remember to never publicly share your keystore.
    # See https://flutter.dev/docs/deployment/android#reference-the-keystore-from-the-app
    key.properties
    **/*.keystore
    **/*.jks
    ```

4. Save the file and push it to the repository. Make sure that both this script and the updated `.gitignore` have been pushed to the main branch.

Once the scripts are created, we will configure the application on App Center to enable automatic building and releasing.

## Tutorial: Further Configuration on App Center

1. Open the App Center website and navigate to your application project.

   ![Step 1](https://i.ibb.co/58WpRNT/langkah-1.png)
   
2. Go to the `Build` menu and select GitHub as the repository service. Choose your project group's application repository.

   ![Step 2](https://i.ibb.co/Rv3rFJV/langkah-2.png)
   
3. Open your main branch (`main` or `master`, as applicable) and click the `Configure` button.

   ![Step 3](https://i.ibb.co/25SGyMv/langkah-3.png)
   
4. Follow the settings below.

   ![Step 4-1](https://i.ibb.co/mCQ8dR2/langkah-4-1.png)
   ![Step 4-2](https://i.ibb.co/wYfJG19/langkah-4-2.png)
   ![Step 4-3](https://i.ibb.co/L1fPRM6/langkah-4-3.png)
   
   Note:
     - Do not forget to replace `KEY_JKS` and `KEY_PASSWORD` with their actual values.
     - Remember to create a `Public` group for public application distribution.
     - Copy the build badge link in Markdown format and paste it into your `README.md`.

       ![Step 4-4](https://i.ibb.co/LvpM03f/advance.png)

       ![Step 4-5](https://i.ibb.co/xfpKLDh/develop.png)
       
5. Click the `Save & Build` button to save the configuration and initiate the initial build process.

   You can check the public link for the application publication on App Center through the *Distribute* -> *Groups* -> *Public* menu.

   ![Publication Link](https://i.ibb.co/5Y2K3NB/distribute.png)

   Here is an example of the App Center interface when users access the public link for the application publication.

   ![Installation Window](https://cdn.discordapp.com/attachments/987024899296088125/1175025198546829382/windows_install.png?ex=6569ba7e&is=6557457e&hm=05cfb7004b48f30f2bf19a5b2d3cb10ddd3cc8d30111fb399ce76dbb462ce7f3&)

6. Copy the public link from the application publication and paste it into your `README.md`.

## Closing Words

Congratulations, you have successfully deployed your Flutter application to App Center. You can check the deployed application by downloading the APK file from App Center and installing it on your smartphone.

And with that, we officially conclude the tutorial for PBP odd semester 2023-2024! Thank you for following and completing all the tutorials for PBP odd Ssemester 2023-2024. We would like to express appreciation to all students who have participated and contributed to this course. We observed the effort and dedication you have shown in facing the challenges of multiplatform application development in this course.

Throughout the lab sessions and assignments, we have explored the fundamental concepts and principles underlying web and mobile application development using Django and Flutter. You have learned about the architectures, features, and tools that can help in building robust and responsive applications on both of these platforms.

We hope that all the tutorials and assignments provided have given you a deeper understanding of the potential and challenges in multiplatform application development and equipped you with valuable skills applicable to your careers as software developers.

However, learning does not end here. The development world is rapidly evolving, and it is important to stay up-to-date with the latest developments in this industry. The teaching team encourages you to continue learning and keep your skills relevant by reading other references, taking advanced courses, and participating in real-world projects.

In conclusion, remember that multiplatform application development is an exciting and promising field. Keep exploring and innovating, and we are confident that you have a bright future as software developers. Thank you, and may you succeed in your journey!

またね~ 頑張ってね!

## Contributors

- Fadhlan Hasyim
- Muhammad Vicky Maulana
- Stenly Yosua Saputra
- Steven Yosua Saputra
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on a [blog entry](https://determinedguy.github.io/cecoret/flutter-at-app-center/) written by [Muhammad Athallah](https://github.com/determinedguy/). All tutorials and instructions provided in this repository are designed in such a way that students taking the Platform-Based Programming course can complete the tutorials during lab sessions.
