---
sidebar_label: Tutorial 6
sidebar_position: 8
Path: docs/tutorial-6
---

# Tutorial 6: Introduction to Flutter

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Learning Objectives

After completing this tutorial, students are expected to be able to:

* Understand Flutter installation.
* Understand and perform basic Flutter commands.
* Understand the basic flow of the development and execution of a Flutter application.
* Understand basic elements in Flutter.

## Introduction to Flutter

Flutter is an open source mobile application development framework, created by Google in 2017. Flutter is used to develop an Android and iOS application. Flutter also support developing web-based application, Windows, Linux, and MacOS natively.

The main benefit of Flutter is the ability to develop applications for different platforms with just one codebase. Besides that, the JIT (Just in Time) feature also allows the developer to view the live changes in the codebase without the need to recompile the application.

## Flutter Installation

1. Access the following link based on your operating system.

    a. [Mac OS](https://docs.flutter.dev/get-started/install/macos)
  
    If you use Homebrew, you can install Flutter with `brew install --cask flutter`.
  
    b. [Windows](https://docs.flutter.dev/get-started/install/windows)

    c. [Linux](https://docs.flutter.dev/get-started/install/linux)

2. Install the latest version of Flutter by following the guide in the link above.
  
    For Mac users, you can skip the `iOS Setup` step and go straight to the `Android Setup` step.

3. Install an IDE of your choice that will be used to develop Flutter applications.

    a. [Android Studio (Recommended)](https://developer.android.com/studio)

    b. [Visual Studio Code](https://code.visualstudio.com/)

   > You can use Visual Studio Code for Flutter development  by installing extensions [Dart](https://marketplace.visualstudio.com/items?itemName=Dart-Code.dart-code) and [Flutter](https://marketplace.visualstudio.com/items?itemName=Dart-Code.flutter).
   > 
   > You can also read the IDE functionality provided by the Flutter extension in the link above.

## Tutorial: Getting Started with Flutter

1. Open The Terminal or Command Prompt.

2. Navigate to the directory where you want to save your Flutter project.

3. Generate a new Flutter Project with the name **`shopping_list`**, then navigate into the project directory.

    ```bash
    flutter create <APP_NAME>
    cd <APP_NAME>
    ```

4. Run the project using the Terminal or Command Prompt.

    ```bash
    flutter run
    ```

    For macOS user, there are multiple options to run a Flutter Project. The easiest way is:
    - Use [**emulator in Android Studio**](https://docs.flutter.dev/get-started/install/macos#set-up-the-android-emulator)
    - Use **Google Chrome**

        - Run the following command to enable web support **(only needs to be done once)**:

        ```bash
        flutter config --enable-web
        ```

        - Then, in your project directory, run the project in Google Chrome with the command:

        ```bash
        flutter run -d chrome
        ```

5. You will see a screen like the one below.

    ![First App](https://docs.flutter.dev/assets/images/docs/get-started/ios/starter-app.png)

6. Perform `git init` in the root folder and `add`-`commit`-`push` the project to a new GitHub repository. You can name your new repository `shopping-list-mobile`.

## Tutorial: Organizing Project Structure

Before delving further into Flutter, you will organize the file structure of your project to make the code more understandable. This is a best practice in application development, known as [clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

1. Create a new file named `menu.dart` in the `shopping_list/lib` directory. At the first line of this file, add the following code:

    ```dart
    import 'package:flutter/material.dart';
    ```

2. From the `main.dart` file, move (cut) the code from line 39 to the end, which includes the two classes below:

    ```dart
    class MyHomePage ... {
        ...
    }
    
    class _MyHomePageState ... {
        ...
    }
    ```

    Move this code to the newly created `menu.dart` file.

3. You will notice that in the `main.dart` file, there will be an error on line 34, which contains the following code:

    ```dart
    home: const MyHomePage(title: 'Flutter Demo Home Page'),
    ```

    This error occurs because the `main.dart` file no longer recognizes the `MyHomePage` class, which has been moved to another file, `menu.dart`. To resolve this issue, add the following code at the beginning of the file:

    ```dart
    import 'package:shopping_list/menu.dart';
    ```

4. Run the project through the Terminal or Command Prompt to ensure that the application still runs correctly.

## Tutorial: Creating a Simple Widget in Flutter

In this tutorial, you will learn how to create a simple widget in Flutter. You will display **the name of your shop** as a header and **create cards representing items for sale**. When the "Buy" button is pressed, a notification will appear at the bottom of the screen.

First, you can change the application's theme color to indigo.

1. Open the `main.dart` file.

2. Change the code in your application's theme section that has the type `Material Color` to:

    ```dart
    colorScheme: ColorScheme.fromSeed(seedColor: Colors.indigo),
    ```

    Try running your project to see if the application's theme color has changed to indigo.

After changing the application's theme color, you will convert the widget of the menu page to a stateless widget.

1. In the `main.dart` file, remove `MyHomePage(title: 'Flutter Demo Home Page')` so that it becomes:

    ```dart
    MyHomePage()
    ```

2. In the `menu.dart` file, you will change the nature of the page's widget from stateful to stateless. Make the following changes in the widget section:
    - Replace `({super.key, required this.title})` with `({Key? key}) : super(key: key);`.
    - Remove `final String title;` and add the `Widget build` method so that the code looks like this:

    ```dart
    class MyHomePage extends StatelessWidget {
        MyHomePage({Key? key}) : super(key: key);

        @override
        Widget build(BuildContext context) {
            return Scaffold(
                ...
            );
        }
    }
    ```

    Don't forget to remove the **State** class that is located below the stateless widget section.

After changing the nature of the menu page widget to stateless, you will add text and cards to represent items for sale.

1. To add text and cards, define the types of items you are selling. You can start by defining the type in your list:

    ```dart
    class ShopItem {
      final String name;
      final IconData icon;

      ShopItem(this.name, this.icon);
    }
    ```

    Under the `MyHomePage({Key? key}) : super(key: key);` code, add items for sale (name, price, and the item's icon):

    ```dart
    final List<ShopItem> items = [
        ShopItem("View Products, Icons.checklist),
        ShopItem("Add Product", Icons.add_shopping_cart),
        ShopItem("Logout", Icons.logout),
    ];
    ```

2. Next, add the following code inside the `Widget build` method:

    ```dart
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Shopping List',
        ),
      ),
      body: SingleChildScrollView(
        // Scrolling wrapper widget
        child: Padding(
          padding: const EdgeInsets.all(10.0), // Set padding for the page
          child: Column(
            // Widget to display children vertically
            children: <Widget>[
              const Padding(
                padding: EdgeInsets.only(top: 10.0, bottom: 10.0),
                // Text widget to display text with center alignment and appropriate style
                child: Text(
                  'PBP Shop', // Text indicating the shop name
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 30,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              // Grid layout
              GridView.count(
                // Container for our cards.
                primary: true,
                padding: const EdgeInsets.all(20),
                crossAxisSpacing: 10,
                mainAxisSpacing: 10,
                crossAxisCount: 3,
                shrinkWrap: true,
                children: items.map((ShopItem item) {
                  // Iteration for each item
                  return ShopCard(item);
                }).toList(),
              ),
            ],
          ),
        ),
      ),
    );
    ```

3. Although there may have been an error previously, you only need to create a new stateless widget to display the card.

    ```dart
    class ShopCard extends StatelessWidget {
      final ShopItem item;

      const ShopCard(this.item, {Key? key}); // Constructor

      @override
      Widget build(BuildContext context) {
        return Material(
          color: Colors.indigo,
          child: InkWell(
            // Responsive touch area
            onTap: () {
              // Show a SnackBar when clicked
              ScaffoldMessenger.of(context)
                ..hideCurrentSnackBar()
                ..showSnackBar(SnackBar(
                    content: Text("You pressed the ${item.name} button!")));
            },
            child: Container(
              // Container to hold Icon and Text
              padding: const EdgeInsets.all(8),
              child: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(
                      item.icon,
                      color: Colors.white,
                      size: 30.0,
                    ),
                    const Padding(padding: EdgeInsets.all(3)),
                    Text(
                      item.name,
                      textAlign: TextAlign.center,
                      style: const TextStyle(color: Colors.white),
                    ),
                  ],
                ),
              ),
            ),
          ),
        );
      }
    }
    ```

The result of your work will look like this:

![](https://hackmd.io/_uploads/SJU7d_CGT.png)

## Closing

1. Congratulations! You have successfully completed Tutorial 6. 😄

2. Run the following commands to `add`, `commit`, and `push`:

    ```shell
    git add .
    git commit -m "<pesan_commit>"
    git push -u origin <branch_utama>
    ```

    - Replace `<commit_message>` with your desired message. For example: `git commit -m "Completed tutorial 6"`.
    - Replace `<your_main_branch>` with your main branch name. For example: `git push -u origin main` or `git push -u origin master`.

## Additional References

- [Flutter Docs](https://https://docs.flutter.dev/)
- [Write your first Flutter app, part 1](https://docs.flutter.dev/get-started/codelab)
- [An Introduction to Flutter: The Basics by FreeCodeCamp](https://www.freecodecamp.org/news/an-introduction-to-flutter-the-basics-9fe541fd39e2/)
- [Flutter Course for Beginners – 37-hour Cross Platform App Development Tutorial by FreeCodeCamp](https://www.youtube.com/watch?v=VPvVD8t02U8)
- [An Introduction to Flutter Clean Architecture](https://medium.com/ruangguru/an-introduction-to-flutter-clean-architecture-ae00154001b0)

## Contributors

- Alanna
- Alvaro Austin
- Naila Shafirni Hidayat
- Shayna Putri Fitria
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
