---
sidebar_label: Tutorial 7
sidebar_position: 9
Path: docs/tutorial-7
---

# Tutorial 7: Flutter Navigation, Layouts, Forms, and Input Elements

## Learning Objectives

After completing this tutorial, students are expected to be able to:

* Understand basic navigation and routing in Flutter.
* Understand input and form elements in Flutter.
* Understand the process of creating forms and handling data in Flutter.
* Understand and implementing simple clean architecture.

## Page Navigation in Flutter

When learning web development, you've probably already learned that on a website, you can navigate between pages based on the accessed URL. The same concept applies to app development, where you can move from one 'page' to another. However, in an application, navigations is not done by accessing different URLs.

To implement navigation in Flutter, a fairly complete system has been provided to navigate between pages. One way to navigate between pages is by using the `Navigator` widget. The `Navigator` widget displays pages as if they were in a stack. To navigate to a new page, you can access the `Navigator` through the `BuildContext` and call functions like `push()`, `pop()`, and `pushReplacement()`.

> Note: In Flutter, screens and pages are often referred to as *route*.

We will explain some of the most frequently encountered uses of `Navigator` in application development.

### Push (`push()`)

```dart
...
if (item.name == "Add Product") {
    Navigator.push(context,
        MaterialPageRoute(builder: (context) => const ShopFormPage()));
}
...
```


The `push()` method adds a route to the route stack managed by `Navigator`. This method causes the added route to be at the top of the stack, so that the newly added route will appear and be displayed to the user.

### Pop (`pop()`)

```dart
...
onPressed: () {
    Navigator.pop(context);
},
...
```

The `pop()` method removes the route currently displayed to the user (or in other words, the route at the top of the *stack*) from the stack routes managed by the Navigator. This method causes the application to move from the *route* currently displayed to the user to the *route* that is below it in the stack managed by `Navigator`.

### Push Replacement (`pushReplacement()`)

```dart
...
onTap: () {
    Navigator.pushReplacement(
    context,
    MaterialPageRoute(
        builder: (context) => MyHomePage(),
    ));
},
...
```

The `pushReplacement()` method removes the currently displayed route and replaces it with a new route. This method causes the application to transition from the currently displayed route to the provided route. In the managed route stack by the `Navigator`, the old route at the top of the stack is directly replaced by the new route without altering the state of the stack elements beneath it.

Although `push()` and `pushReplacement()` may seem similar , the key difference lies in what they do to the route at the top of the stack. `push()` adds a new route on top of the existing routes, while `pushReplacement()` replaces the existing route at the top of the stack with the new route. It's important to consider the order and contents of the stack because if the stack is empty, and you press the **Back** button on the device, the system will exit the application.

In addition to these three `Navigator` methods, there are other methods that can facilitate routing in app development, such as `popUntil()`, `canPop()`, and `maybePop()`. Feel free to explore these methods on your own. For a deeper understanding of `Navigator`, you can refer to the documentation at the following link: <https://api.flutter.dev/flutter/widgets/Navigator-class.html>

## Input and Form in Flutter

Just like a website, an application can also interact with users through input and forms. Flutter provides a `Form` widget that serves as a container for multiple input field widgets you create. Similar to web input fields, Flutter offers various types of input fields, including the `TextField` widget.

To try a Form widget, run the following command:

```bash
flutter create --sample=widgets.Form.1 form_sample
```

For further information about the Form widget, you can refer to the following link: [Flutter Form Class](https://api.flutter.dev/flutter/widgets/Form-class.html).

## Tutorial: Adding a Menu Drawer for Navigation

To simplify navigation in a Flutter application, you can add a drawer menu. A drawer menu is a menu that appears from the left or right side of the screen and typically contains navigation links to other pages in the application.

1. Open the project that you previously created in tutorial 6 using your favorite IDE.

2. Create a new file in a new directory called `widgets` with the name `left_drawer.dart`. Add the following code to the `left_drawer.dart` file.

    ```dart
    import 'package:flutter/material.dart';

    class LeftDrawer extends StatelessWidget {
      const LeftDrawer({super.key});

      @override
      Widget build(BuildContext context) {
        return Drawer(
          child: ListView(
            children: [
              const DrawerHeader(
                // TODO: drawer header section
              ),
              // TODO: routing section
            ],
          ),
        );
      }
    }
    ```
3. Next, add imports for the pages you want to include in the navigation drawer. In this example, we will add navigation to the `MyHomePage` and `ShopFormPage` pages.

    ```dart
    import 'package:flutter/material.dart';
    import 'package:shopping_list/menu.dart';
    // TODO: Import the ShopFormPage page here
    ```

4. After importing, add routing for the imported pages to the `TODO: Routing section`.

    ```dart
    ...
    ListTile(
      leading: const Icon(Icons.home_outlined),
      title: const Text('Homa Page'),
      // redirect to MyHomePage
      onTap: () {
        Navigator.pushReplacement(
            context,
            MaterialPageRoute(
              builder: (context) => MyHomePage(),
            ));
      },
    ),
    ListTile(
      leading: const Icon(Icons.add_shopping_cart),
      title: const Text('Add Product'),
      // redirect to ShopFormPage
      onTap: () {
        /*
        TODO: Create routing to ShopFormPage here
        */
      },
    ),
    ...
    ```

5. Then, decorate the drawer by adding a drawer header in the `TODO: Drawer header section`.

    ```dart
    ...
    const DrawerHeader(
      decoration: BoxDecoration(
        color: Colors.indigo,
      ),
      child: Column(
        children: [
          Text(
            'Shopping List',
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 30,
              fontWeight: FontWeight.bold,
              color: Colors.white,
            ),
          ),
          Padding(padding: EdgeInsets.all(10)),
          Text("Write all your shopping needs here!",
              // TODO: Add a text style with center alignment, font size 15, white color, and regular weight
              ),
        ],
      ),
    ),
    ...
    ```

6. Congratulations, you have successfully created a drawer menu. Now, add the drawer to the page where you want to include the drawer. For this step, we will provide an example of adding it to the `menu.dart` page.

    ```dart
    ...
    // import drawer widget
    import 'package:shopping_list/widgets/left_drawer.dart';
    ...
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Shopping List',
        ),
        backgroundColor: Colors.indigo,
        foregroundColor: Colors.white,
      ),
      // Add drawer as parameter of the Scaffold widget
      drawer: const LeftDrawer(),
    ...
    ```

7. Congratulations, the drawer and navigation have been set up perfectly. Run the program to see the results. Don't forget to complete the remaining `TODO` tasks **before submitting the tutorial** (submitted tutorials should have **no** `TODO` left). Also, don't forget to add the drawer to the `ShopFormPage` if that page has been created.

## Tutorial: Adding Forms and Input Elements

Now, we will create a simple form to enter product data into the application, allowing you to add new data to be displayed later.

1. Create a new file in the `lib` directory named `shoplist_form.dart`. Add the following code to the `shoplist_form.dart` file.

    ```dart
    import 'package:flutter/material.dart';
    // TODO: Import the previously created drawer

    class ShopFormPage extends StatefulWidget {
        const ShopFormPage({super.key});

        @override
        State<ShopFormPage> createState() => _ShopFormPageState();
    }

    class _ShopFormPageState extends State<ShopFormPage> {
        @override
        Widget build(BuildContext context) {
            return Placeholder();
        }
    }
    ```
    
2. Replace the `Placeholder` widget with the following code.

    ```dart
    Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            'Add Product Form',
          ),
        ),
        backgroundColor: Colors.indigo,
        foregroundColor: Colors.white,
      ),
      // TODO: Add the previously created drawer here
      body: Form(
        child: SingleChildScrollView(),
      ),
    );
    ```
    
    Code Explanation:

    1. The `Form` widget serves as a container for several input field widgets that we will create later.

    2. The `SingleChildScrollView` widget makes the child widget inside it scrollable.

3. Create a new variable named `_formKey` and add it to the `key` attribute of the `Form` widget. The `key` attribute serves as the handler for form state, form validation, and form storage.

    ```dart
   ...
   class _ShopFormPageState extends State<ShopFormPage> {
      final _formKey = GlobalKey<FormState>();
   ...
   ```
  
   ```dart
   ...
   body: Form(
    key: _formKey,
    child: SingleChildScrollView(),
   ),
   ...
   ```
   
 4. Next, we will start adding input fields to the `Form` widget. Create some variables to store input from each field you're going to create.

    ```dart
      ...
      class _ShopFormPageState extends State<ShopFormPage> {
        final _formKey = GlobalKey<FormState>();
        String _name = "";
        int _price = 0;
        String _description = "";
      ...
    ```
    
5. Create a `Column` widget as a child of the `SingleChildScrollView`.

    ```dart
    ...
    body: Form(
      key: _formKey,
      child: SingleChildScrollView(
        child: Column()
      ),
    ...
    ```
    
6. Create a `TextFormField` widget wrapped in `Padding` as one of the children of the `Column`. Then, add the `crossAxisAlignment` attribute to control the alignment of the `Column`'s children.

    ```dart
      ...
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: TextFormField(
                decoration: InputDecoration(
                  hintText: "Product Name",
                  labelText: "Product Name",
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(5.0),
                  ),
                ),
                onChanged: (String? value) {
                  setState(() {
                    _name = value!;
                  });
                },
                validator: (String? value) {
                  if (value == null || value.isEmpty) {
                    return "Name cannot be empty!";
                  }
                  return null;
                },
              ),
            ),
        ...
    ```
      
      Code Explanation:

      - `onChanged` is called whenever there is a change in the `TextFormField`.
      - `validator` is used to validate the content of the `TextFormField` and return a `String` in case of an error.
      - `null-safety` is implemented in the code with the use of `String?` and `value!`. The `?` operator indicates that the variable can contain either a `String` or `null`. The `!` operator indicates that the variable is guaranteed not to be `null`.

    To learn more about null safety, you can refer to the Dart `null-safety` documentation [here](https://dart.dev/null-safety/understanding-null-safety).

7. Create two more `TextFormField` widgets wrapped in `Padding` as children of the `Column` for the `price` and `description` fields.

    ```dart
    ...
    Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextFormField(
        decoration: InputDecoration(
          hintText: "Price",
          labelText: "Price",
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(5.0),
          ),
        ),
        onChanged: (String? value) {
          setState(() {
            _price = int.parse(value!);
          });
        },
        validator: (String? value) {
          if (value == null || value.isEmpty) {
            return "Price cannot be empty!";
          }
          if (int.tryParse(value) == null) {
            return "Price must be a number!";
          }
          return null;
        },
      ),
    ),
    Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextFormField(
        decoration: InputDecoration(
          hintText: "Description",
          labelText: "Description",
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(5.0),
          ),
        ),
        onChanged: (String? value) {
          setState(() {
            _description = value!;
          });
        },
        validator: (String? value) {
          if (value == null || value.isEmpty) {
            return "Description cannot be empty!";
          }
          return null;
        },
      ),
    ),
    ...
    ```
    
8. Create a button as the next child of the `Column`. Wrap the button with `Padding` and `Align`. This time, we won't save data to the database, but we will display it in a popup that appears after clicking the button.

    ```dart
    ...
    Align(
      alignment: Alignment.bottomCenter,
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: ElevatedButton(
          style: ButtonStyle(
            backgroundColor:
                MaterialStateProperty.all(Colors.indigo),
          ),
          onPressed: () {
            if (_formKey.currentState!.validate()) {}
          },
          child: const Text(
            "Save",
            style: TextStyle(color: Colors.white),
          ),
        ),
      ),
    ),
    ...
     ```
      
## Tutorial: Displaying Data

1. Add the `showDialog()` function inside the `onPressed()` section of the button and display an `AlertDialog` widget in this function. Also, add a function to reset the form.

    ```dart
    ...
    child: ElevatedButton(
      style: ButtonStyle(
        backgroundColor:
            MaterialStateProperty.all(Colors.indigo),
      ),
      onPressed: () {
        if (_formKey.currentState!.validate()) {
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: const Text('Product successfully saved'),
                content: SingleChildScrollView(
                  child: Column(
                    crossAxisAlignment:
                        CrossAxisAlignment.start,
                    children: [
                      Text('Name: $_name'),
                      // TODO: Display other values
                    ],
                  ),
                ),
                actions: [
                  TextButton(
                    child: const Text('OK'),
                    onPressed: () {
                      Navigator.pop(context);
                    },
                  ),
                ],
              );
            },
          );
        _formKey.currentState!.reset();
        }
      },
      child: const Text(
        "Save",
        style: TextStyle(color: Colors.white),
      ),
    ),
    ...
    ```
2. Run your program, use the form you created, and see the results.

## Tutorial: Adding Navigation to Buttons

Up to this point, we've successfully created a drawer that can navigate to other pages in the application, as well as a form page. In the previous tutorial, we also created three button widgets that can perform certain actions when clicked. Now, we'll add navigation functionality to these buttons so that when pressed, the user will be shown other pages.

1. In the `ShopItem` widget in the `menu.dart` file created in the previous tutorial, we will modify the code within the `onTap` attribute of `InkWell` to navigate to another route (add additional code below the `ScaffoldMessenger` code that displays a snackbar).

    ```dart
    ...
      // Area responsive to touch
      onTap: () {
        // Show SnackBar when clicked
        ScaffoldMessenger.of(context)
          ..hideCurrentSnackBar()
          ..showSnackBar(SnackBar(
              content: Text("You pressed the ${item.name} button!")));

        // Navigate to the appropriate route (depending on the button type)
        if (item.name == "Add Product") {
          // TODO: Use Navigator.push to navigate to a MaterialPageRoute that encompasses ShopFormPage.
        }
      },
    ...
    ```
    
    Note that for this button, we use `Navigator.push()`, so users can press the **Back** button to return to the menu page. In addition, by using `Navigator.pop()`, you can code the program to return to the menu page.
    
2. Run your program, use the buttons with the new functionality, and see what happens. Compare it to what happens when you navigate through the drawer (**of course, after completing all the TODOs in the drawer**).

## Tutorial: Refactoring Files

After creating the `shoplist_form.dart` page, our application has become more extensive. Let's move the pages we've created so far into a `screens` folder to make things easier in the future.

1. Before starting, make sure you have the **Flutter extension installed** in your IDE or text editor.

2. Create a new file named `shop_card.dart` in the `widgets` directory.

3. Move the `ShopItem` widget's contents from `menu.dart` to the `widgets/shop_card.dart` file.

4. Make sure to import the `shoplist_form.dart` page into the `widgets/shop_card.dart` file and import the `shop_card.dart` page into the `menu.dart` file.

5. Create a new folder named `screens` in the `lib` directory.
    ![Make screens folder](https://cdn.discordapp.com/attachments/923523971226435584/1170530937885442108/image.png?ex=655960e3&is=6546ebe3&hm=812a7ea28f6f7aec80b0c75222e0f45824ffa2ee1c850480ad78adb13334eb20&)

6. Move the `menu.dart` and `shoplist_form.dart` files to the `screens` folder. Make sure to move these files through **your IDE or text editor that has the Flutter extension or plugin**, not through a regular file manager (such as File Explorer or Finder). This is done so that your IDE or text editor can perform automatic refactoring.
    
    View in Visual Studio Code
    
    ![Move files in vscode](https://cdn.discordapp.com/attachments/923523971226435584/1170529781599719464/image.png?ex=65595fcf&is=6546eacf&hm=5019998eac2e4b0b8b3d0cb49ab0e14a9829e51fa20ef5860255b57fc56485d6&)

    View in Android Studio
    
    ![Move files in Android Studio](https://cdn.discordapp.com/attachments/923523971226435584/1170530165433061426/image.png?ex=6559602a&is=6546eb2a&hm=27b1aeb2b988193fd2e3bfb034491e6b0a36835bcfb95c7a917016ead5b53a9b&)
    
After refactoring the files, your `lib` directory structure should look like this:

![Final Files Structure](https://cdn.discordapp.com/attachments/923523971226435584/1170531163769667625/image.png?ex=65596118&is=6546ec18&hm=c6b0bf529735ac9d891f38d88b1e9be3b1e2db741e1f53f443525a4d4162b601&)

## Closing

1. Congratulations! You have successfully completed Tutorial 7. 😄


2. Run the following commands to `add`, `commit`, and `push`:

    ```shell
    git add .
    git commit -m "<commit_message>"
    git push -u origin <main_branch>
    ```

    - Replace `<commit_message>` with your desired message. For example: `git commit -m "Completed tutorial 7"`.
    - Replace `<your_main_branch>` with your main branch name. For example: `git push -u origin main` or `git push -u origin master`.

## Additional References

- [Flutter Navigation Basics Cookbook](https://docs.flutter.dev/cookbook/navigation/navigation-basics)
- [Add Drawer to a Screen in Flutter](https://docs.flutter.dev/cookbook/design/drawer)

## Contributors

- Muhammad Raditya Hanif
- Hugo Sulaiman Setiawan
- Andi Muhamad Dzaky Raihan
- Alek Yoanda Partogi Tampubolon
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
