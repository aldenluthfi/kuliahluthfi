---
sidebar_label: Tutorial 8
sidebar_position: 10
Path: docs/tutorial-8
---

# Tutorial 8: Flutter Networking, Authentication, and Integration

Platform-Based Programming (CSGE602022) — organized by the Faculty of Computer Science, University of Indonesia, Odd Semester 2023/2024

---

## Learning Objectives

After completing this tutorial, students are expected to:

- Understand the structure and creation of models in Flutter.
- Understand how to fetch, process, and display data from web services.
- Understand basic state management using Provider in Flutter.
- Able to authenticate with the Django web service with the Flutter application.

## Model in Flutter

In this tutorial, we will call a web service and display the results on the Flutter page we created. However, before making the web service call, we need to define the model we use for the web service call. In Flutter, models are defined using a class, similar to what has been learned in the OOP section of Programming Foundations 2.

> The code below is just an example. However, we highly recommend you to read it because the concepts will be used in the following sections.

Here is an example of class in Flutter.

```dart
class Car {
    Car({
        this.id,
        this.brand,
        this.model,
        this.color,
    });

    int id;
    String brand;
    String model;
    String color;
}
```

Note: If you encounter errors while creating the class, add the `required` keyword to each class parameter in the constructor section.

So far, we have successfully created the class. Next, we will add some codes to form a `Car` model. This `Car` model represents the response from the web service call.

Import `dart:convert` at the top of the file.

```dart
import 'dart:convert';
...
```

In the `Car` class, add the following code.

```dart
factory Car.fromJson(Map<String, dynamic> json) => Car(
    id: json["id"],
    brand: json["brand"],
    model: json["model"],
    color: json["color"],
);

Map<String, dynamic> toJson() => {
    "id": id,
    "brand": brand,
    "model": model,
    "color": color,
};
```

Add the following code outside the `Car` class.

```dart
Car carFromJson(String str) => Car.fromJson(json.decode(str));
String carToJson(Car data) => json.encode(data.toJson());
```

The final code will look like this. It will display a single `Car` object from the web service.

```dart
import 'dart:convert';

Car carFromJson(String str) => Car.fromJson(json.decode(str));
String carToJson(Car data) => json.encode(data.toJson());

class Car {
    Car({
        this.id,
        this.brand,
        this.model,
        this.color,
    });

    int id;
    String brand;
    String model;
    String color;

    factory Car.fromJson(Map<String, dynamic> json) => Car(
        id: json["id"],
        brand: json["brand"],
        model: json["model"],
        color: json["color"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "brand": brand,
        "model": model,
        "color": color,
    };
}
```

Here is an explanation of the above code.

There are additional codes such as the `toJson` and `fromJson` methods inside the `Car` class. This is because when we make a request to a web service with the **GET** method, we usually receive the result in the form of JSON. Therefore, we need to convert the data with the `fromJson` method so that Flutter recognizes this JSON as an object of the `Car` class. Additionally, there is also the `toJson` method, which will be used when sending data to the web service (such as **POST** or **PUT**).

Here is an example response from a web service with the **GET** method that can be converted into the `Car` model.

```json
{
   "id": 1,
   "brand": "Honda",
   "model": "Civic",
   "color": "Yellow"
}
```

Now, what if the response from the web service consists of a collection of JSON objects? It is actually the same as the code above, but with some modifications to the `carFromJson` and `carToJson` methods.

The code is as follows.

```dart
List<Car> carsFromJson(String str) => List<Car>.from(json.decode(str).map((car) => Car.fromJson(car)));

String carsToJson(List<Car> data) => json.encode(List<dynamic>.from(data.map((car) => car.toJson())));
```

Here is an example response from a web service with the **GET** method that can be converted into the `Car` model.

```json
[
  {
    "id": 1,
    "brand": "Honda",
    "model": "Civic",
    "color": "Yellow"
  },
  {
    "id": 2,
    "brand": "Toyota",
    "model": "Supra",
    "color": "Red"
  }
]
```

## Fetching Data from Web Service in Flutter

During application development, there are times when we need to fetch external data from outside our application (the Internet) to display in our application. This tutorial aims to understand how to fetch data from a web service in Flutter.

In general, there are several steps when you want to display data from another web service to a Flutter application, namely:

1. Add the `http` dependency to the project; this dependency is used for exchanging HTTP requests.

2. Create a model according to the response from the data originating from that web service.

3. Make an HTTP request to the web service using the `http` dependency.

4. Convert the object obtained from the web service to the model we created in step two.

5. Display the converted data to the application using `FutureBuilder`.

For further explanation, you can read the details [here](http://docs.flutter.dev/cookbook/networking/fetch-data#5-display-the-data).

## Basic State Management using Provider

`Provider` is a wrapper around `InheritedWidget` to make `InheritedWidget` easier to use and more reusable. `InheritedWidget` itself is the basic class for Flutter widgets that efficiently spread information to other widgets in one tree.

The benefits of using `provider` are as follows:

- Allocating resources becomes simpler.
- Lazy-loading.
- Reducing boilerplate every time a new class is created.
- Supported by Flutter Devtools so that `provider` can be tracked from Devtool.
- Scalability improvement for classes that utilize complex built-in listening mechanisms.

For more information about `provider`, please visit the [Provider package page](http://pub.dev/packages/provider).

## Tutorial: Django-Flutter Authentication Integration

### Set Up Authentication in Django for Flutter

Follow the steps below to integrate the authentication system in **Django**.

1. Create a `django-app` named `authentication` in your Django project you created earlier.

2. Add `authentication` to `INSTALLED_APPS` in the main project `settings.py` file of your Django application.

3. Run the command `pip install django-cors-headers` to install the required library.

4. Add `corsheaders` to `INSTALLED_APPS` in the main project `settings.py` file of your Django application.

5. Add `corsheaders.middleware.CorsMiddleware` to `MIDDLEWARE` in the main project `settings.py` file of your Django application.

6. Add the following variables to the main project `settings.py` file of your Django application.

    ```python
    CORS_ALLOW_ALL_ORIGINS = True
    CORS_ALLOW_CREDENTIALS = True
    CSRF_COOKIE_SECURE = True
    SESSION_COOKIE_SECURE = True
    CSRF_COOKIE_SAMESITE = 'None'
    SESSION_COOKIE_SAMESITE = 'None'
    ```
    
7. Create a view method for login in `authentication/views.py`.

    ```python
	from django.shortcuts import render
	from django.contrib.auth import authenticate, login as auth_login
	from django.http import JsonResponse
	from django.views.decorators.csrf import csrf_exempt
	
	@csrf_exempt
    def login(request):
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username=username, password=password)
        if user is not None:
            if user.is_active:
                auth_login(request, user)
                # Successful login status.
                return JsonResponse({
                    "username": user.username,
                    "status": True,
                    "message": "Login successful!"
                    # Add other data if you want to send data to Flutter.
                }, status=200)
            else:
                return JsonResponse({
                    "status": False,
                    "message": "Login failed, account disabled."
                }, status=401)

        else:
            return JsonResponse({
                "status": False,
                "message": "Login failed, check email or password again."
            }, status=401)
	```

8. Create a `urls.py` file in the `authentication` folder and add URL routing to the function created with the `login/` endpoint.

    ```python
	from django.urls import path
	from authentication.views import login
	
	app_name = 'authentication'
	
	urlpatterns = [
	    path('login/', login, name='login'),
	]
    ```
    
9. Finally, add `path('auth/', include('authentication.urls'))`, to the `shopping_list/urls.py` file.

### Integrate Authentication System in Flutter

To facilitate the creation of the authentication system, the teaching assistant team has created a Flutter package that can be used to contact the Django web service (including `GET` and `POST` operations).

The package can be accessed via the following link: [pbp_django_auth](http://pub.dev/packages/pbp_django_auth)

Follow the steps below to integrate the authentication system into **Flutter**.

1. Install the package provided by the teaching assistant team by running the following commands in the Terminal.

    ```bash
	flutter pub add provider
	flutter pub add pbp_django_auth
	```
    
2. To use the package, you need to modify the root widget to provide the `CookieRequest` library to all child widgets using `Provider`.

    For example, if your application was like this before:
    
    ```dart
    class MyApp extends StatelessWidget {
            const MyApp({Key? key}) : super(key: key);

            @override
            Widget build(BuildContext context) {
                return MaterialApp(
                    title: 'Flutter App',
                    theme: ThemeData(
                            colorScheme: ColorScheme.fromSeed(seedColor: Colors.indigo),
                            useMaterial3: true,
                    ),
                    home: MyHomePage()),
                );
            }
        }
    ```
    
    Change it to:
    
    ```dart
    class MyApp extends StatelessWidget {
        const MyApp({Key? key}) : super(key: key);

        @override
        Widget build(BuildContext context) {
            return Provider(
                create: (_) {
                    CookieRequest request = CookieRequest();
                    return request;
                },
                child: MaterialApp(
                    title: 'Flutter App',
                    theme: ThemeData(
                        colorScheme: ColorScheme.fromSeed(seedColor: Colors.indigo),
          				useMaterial3: true,
                    ),
                    home: MyHomePage()),
                ),
            );
        }
    }
    ```

    This will create a new `Provider` object that will share an instance of `CookieRequest` with all components in the application.

3. Create a new file in the screens folder named `login.dart`.

4. Fill the `login.dart` file with the following code.

    ```dart
    import 'package:shopping_list/screens/menu.dart';
    import 'package:flutter/material.dart';
    import 'package:pbp_django_auth/pbp_django_auth.dart';
    import 'package:provider/provider.dart';

    void main() {
        runApp(const LoginApp());
    }

    class LoginApp extends StatelessWidget {
    const LoginApp({super.key});

    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            title: 'Login',
            theme: ThemeData(
                primarySwatch: Colors.blue,
        ),
        home: const LoginPage(),
        );
        }
    }

    class LoginPage extends StatefulWidget {
        const LoginPage({super.key});

        @override
        _LoginPageState createState() => _LoginPageState();
    }

    class _LoginPageState extends State<LoginPage> {
        final TextEditingController _usernameController = TextEditingController();
        final TextEditingController _passwordController = TextEditingController();

        @override
        Widget build(BuildContext context) {
            final request = context.watch<CookieRequest>();
            return Scaffold(
                appBar: AppBar(
                    title: const Text('Login'),
                ),
                body: Container(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                            TextField(
                                controller: _usernameController,
                                decoration: const InputDecoration(
                                    labelText: 'Username',
                                ),
                            ),
                            const SizedBox(height: 12.0),
                            TextField(
                                controller: _passwordController,
                                decoration: const InputDecoration(
                                    labelText: 'Password',
                                ),
                                obscureText: true,
                            ),
                            const SizedBox(height: 24.0),
                            ElevatedButton(
                                onPressed: () async {
                                    String username = _usernameController.text;
                                    String password = _passwordController.text;

                                    // Check credentials
                                    // TODO: Change the URL and don't forget to add a trailing slash (/) at the end of the URL!
                                    // To connect the Android emulator to Django on localhost,
                                    // use the URL http://10.0.2.2/
                                    final response = await request.login("http://<YOUR_APP_URL>/auth/login/", {
                                    'username': username,
                                    'password': password,
                                    });

                                    if (request.loggedIn) {
                                        String message = response['message'];
                                        String uname = response['username'];
                                        Navigator.pushReplacement(
                                            context,
                                            MaterialPageRoute(builder: (context) => MyHomePage()),
                                        );
                                        ScaffoldMessenger.of(context)
                                            ..hideCurrentSnackBar()
                                            ..showSnackBar(
                                                SnackBar(content: Text("$message Welcome, $uname.")));
                                        } else {
                                        showDialog(
                                            context: context,
                                            builder: (context) => AlertDialog(
                                                title: const Text('Login Failed'),
                                                content:
                                                    Text(response['message']),
                                                actions: [
                                                    TextButton(
                                                        child: const Text('OK'),
                                                        onPressed: () {
                                                            Navigator.pop(context);
                                                        },
                                                    ),
                                                ],
                                            ),
                                        );
                                    }
                                },
                                child: const Text('Login'),
                            ),
                        ],
                    ),
                ),
            );
        }
    }
    ```
    
5. In the `main.dart` file, in the `MaterialApp(...)` widget, change `home: MyHomePage()` to `home: LoginPage()`.

6. Run your Flutter application and try to log in.

## Custom Model Creation

In creating a model that adapts to JSON data, we can use the [Quicktype](http://app.quicktype.io/) website with the following steps.

1. Open the `JSON` endpoint you created earlier in tutorial 2.

2. Copy the `JSON` data and open the [Quicktype](http://app.quicktype.io/) website.

3. On the Quicktype website, change the setup name to `Product`, source type to `JSON`, and language to `Dart`.

4. Paste the previously copied `JSON` data into the textbox provided on Quicktype.

5. Click the `Copy Code` option on Quicktype.

    Here is an example result.
    
    ![Quicktype Example](http://i.ibb.co/09pzyBp/product.png)

After obtaining the model code through Quicktype, open the Flutter project again, create a new file in the `lib/models` folder named `product.dart`, and paste the copied code from Quicktype.

## Fetch Data from Django and Show Data in the Flutter App

### Add HTTP Dependency

To create HTTP requests, we need the [http](http://pub.dev/packages/http) package.

1. Run `flutter pub add http` in your Flutter project terminal to add the `http` package.

2. In the file `android/app/src/main/AndroidManifest.xml`, add this code to allow your Flutter app to access the internet.

    ```xml
    ...
        <application>
        ...
        </application>
        <!-- Required to fetch data from the Internet. -->
        <uses-permission android:name="android.permission.INTERNET" />
    ...
    ```

### Fetch Data from Django

1. Create a new file in the `lib/screens` directory with name `list_product.dart`.

2. In the file `list_product.dart`, import the necessary libraries. Change the `<APP_NAME>` to your Flutter project app name.

    ```dart
    import 'package:flutter/material.dart';
    import 'package:http/http.dart' as http;
    import 'dart:convert';
    import 'package:<APP_NAME>/models/product.dart';
    ...
    ```
3. Add this code to the file`list_product.dart`. Do not forget to import the necessary files.

    ```dart
    ...
    import 'package:<APP_NAME>/widgets/left_drawer.dart';

    class ProductPage extends StatefulWidget {
        const ProductPage({Key? key}) : super(key: key);

        @override
        _ProductPageState createState() => _ProductPageState();
    }

    class _ProductPageState extends State<ProductPage> {
    Future<List<Product>> fetchProduct() async {
        // TODO: Change the URL to your Django app's URL. Don't forget to add the trailing slash (/) if needed.
        var url = Uri.parse(
            'http://<YOUR_DJANGO_APP_URL>/json/');
        var response = await http.get(
            url,
            headers: {"Content-Type": "application/json"},
        );

        // decode the response to JSON
        var data = jsonDecode(utf8.decode(response.bodyBytes));

        // convert the JSON to Product object
        List<Product> list_product = [];
        for (var d in data) {
            if (d != null) {
                list_product.add(Product.fromJson(d));
            }
        }
        return list_product;
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
            title: const Text('Product'),
            ),
            drawer: const LeftDrawer(),
            body: FutureBuilder(
                future: fetchProduct(),
                builder: (context, AsyncSnapshot snapshot) {
                    if (snapshot.data == null) {
                        return const Center(child: CircularProgressIndicator());
                    } else {
                        if (!snapshot.hasData) {
                        return const Column(
                            children: [
                            Text(
                                "No product data available.",
                                style:
                                    TextStyle(color: Color(0xff59A5D8), fontSize: 20),
                            ),
                            SizedBox(height: 8),
                            ],
                        );
                    } else {
                        return ListView.builder(
                            itemCount: snapshot.data!.length,
                            itemBuilder: (_, index) => Container(
                                    margin: const EdgeInsets.symmetric(
                                        horizontal: 16, vertical: 12),
                                    padding: const EdgeInsets.all(20.0),
                                    child: Column(
                                    mainAxisAlignment: MainAxisAlignment.start,
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                        Text(
                                        "${snapshot.data![index].fields.name}",
                                        style: const TextStyle(
                                            fontSize: 18.0,
                                            fontWeight: FontWeight.bold,
                                        ),
                                        ),
                                        const SizedBox(height: 10),
                                        Text("${snapshot.data![index].fields.price}"),
                                        const SizedBox(height: 10),
                                        Text(
                                            "${snapshot.data![index].fields.description}")
                                    ],
                                    ),
                                ));
                        }
                    }
                }));
        }
    }
    ```

4. Add the page `list_product.dart` to `widgets/left_drawer.dart`.

    ```dart
    // ListTile Menu Code
    ...
    ListTile(
        leading: const Icon(Icons.shopping_basket),
        title: const Text('Product List'),
        onTap: () {
            // Route menu to product page
            Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const ProductPage()),
            );
        },
    ),
    ...
    ```
    
5. Change the function of the `View Products` button in the main page to redirect the page to `ProductPage`. You can add the redirection by adding `else if` after the `if(...){...}` code at the end of the `onTap: () {...}` code in the`widgets/shop_card.dart` file.

    ```dart
    ...
    else if (item.name == "View Products") {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const ProductPage()));
          }
    ...
    ```
    
6.  Import the necessary files while adding the `ProductPage` to the `left_drawer.dart` and `shop_card.dart`.

7. Run the app and try to add some products. Check the result in the new `List Product` page.

## Integrate Flutter Form with the Django Service

Do these steps in your **Django project**.

1. Create a new views function in the `main/views.py` file. Fill it with this code.

    ```python
    @csrf_exempt
    def create_product_flutter(request):
        if request.method == 'POST':
            
            data = json.loads(request.body)

            new_product = Product.objects.create(
				user = request.user,
                name = data["name"],
                price = int(data["price"]),
                description = data["description"]
            )

            new_product.save()

            return JsonResponse({"status": "success"}, status=200)
        else:
            return JsonResponse({"status": "error"}, status=401)
    ```

2. Add new path in the `main/urls.py` file with this code.

    ```python
    path('create-flutter/', create_product_flutter, name='create_product_flutter'),
    ```

3. Rerun (and redeploy) your app. If you have deployed your app before, any data and transactions will be lost after the redeployment.

Do these steps in your **Flutter project**.

1. Connect the page `shoplist_form.dart` to `CookieRequest` with this code.

    ```dart
    ...
    @override
    Widget build(BuildContext context) {
        final request = context.watch<CookieRequest>();

        return Scaffold(
    ...
    ```

2. Change the logic in the `onPressed: ()` block to:

    ```dart
    ...
    onPressed: () async {
        if (_formKey.currentState!.validate()) {
            // Send request to Django and wait for the response
            // TODO: Change the URL to your Django app's URL. Don't forget to add the trailing slash (/) if needed.
            final response = await request.postJson(
            "http://<YOUR_DJANGO_APP_URL>/create-flutter/",
            jsonEncode(<String, String>{
                'name': _name,
                'price': _price.toString(),
                'description': _description,
                // TODO: Adjust the fields with your Django model
            }));
            if (response['status'] == 'success') {
                ScaffoldMessenger.of(context)
                    .showSnackBar(const SnackBar(
                content: Text("New product has saved successfully!"),
                ));
                Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(builder: (context) => MyHomePage()),
                );
            } else {
                ScaffoldMessenger.of(context)
                    .showSnackBar(const SnackBar(
                    content:
                        Text("Something went wrong, please try again."),
                ));
            }
        }
    },
    ...
    ```

4. Do some quick fixes by importing the necessary files.

5. Rerun your app and try add new transactions from your Flutter app.

## Implement Logout Feature

Do these steps in your **Django project**.

1. Add a new views method for logout in the `authentication/views.py`.

    ```python
    from django.contrib.auth import logout as auth_logout
    ...
    @csrf_exempt
    def logout(request):
        username = request.user.username

        try:
            auth_logout(request)
            return JsonResponse({
                "username": username,
                "status": True,
                "message": "Logged out successfully!"
            }, status=200)
        except:
            return JsonResponse({
            "status": False,
            "message": "Logout failed."
            }, status=401)
    ```

2. Add a new path in the `authentication/urls.py` file.

    ```python
    path('logout/', logout, name='logout'),
    ```

Do these steps in the **Flutter project**.

1. Add the `lib/widgets/shop_card.dart` file and add this code. Do not forget to resolve the import issues.

    ```dart
    ...
    @override
    Widget build(BuildContext context) {
        final request = context.watch<CookieRequest>();
        return Material(
    ...
    ```

2. Change the `onTap: () {...}` for the widget `Inkwell` to `onTap: () async {...}`. This allows `Inkwell` do the logout process asynchronously.

3. Add this code to the last part of the `async {...}` block:

    ```dart
    ...
	// previous if statement
    // add the else if statement below
    else if (item.name == "Logout") {
            final response = await request.logout(
                // TODO: Change the URL to your Django app's URL. Don't forget to add the trailing slash (/) if needed.
                "http://<YOUR_DJANGO_APP_URL>/auth/logout/");
            String message = response["message"];
            if (response['status']) {
              String uname = response["username"];
              ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                content: Text("$message Good bye, $uname."),
              ));
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (context) => const LoginPage()),
              );
            } else {
              ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                content: Text("$message"),
              ));
            }
          }
    ...
    ```

4. Rerun the app and try to logout.

## Closing

Congratulations! You have completed the Tutorial 8! Hopefully with this tutorial, you can understand more about models, data fetching, basic state management, and Django-Flutter integration. 😄

1. **Don't forget to complete the TODO!!**

2. Run the following commands to `add`, `commit`, and `push`:

    ```shell
    git add .
    git commit -m "<commit_message>"
    git push -u origin <main_branch>
    ```

    - Replace `<commit_message>` with your desired message. For example: `git commit -m "Completed tutorial 8"`.
    - Replace `<your_main_branch>` with your main branch name. For example: `git push -u origin main` or `git push -u origin master`.

## Additional References

- [Fetch Data From the Internet](http://docs.flutter.dev/cookbook/networking/fetch-data)
- [How to create models in Flutter Dart](http://thegrowingdeveloper.org/coding-blog/how-to-create-models-in-flutter-dart)
- [Simple app state management | Flutter](http://docs.flutter.dev/development/data-and-backend/state-mgmt/simple)
- [Flutter State Management with Provider](http://blog.devgenius.io/flutter-state-management-with-provider-5a57eca108f1)
- [Pengenalan State Management Flutter dan Jenis-jenisnya](http://caraguna.com/pengenalan-state-management-flutter/)

## Contributor

- Yudi Putra Sabri
- Muhammad Falensi Azmi
- James Smith Wigglesworth
- Adjie Djaka Permana
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
