---
sidebar_label: Tutorial 1
sidebar_position: 2
Path: docs/tutorial-1
---

# Tutorial 1: Introduction to Django and Model-View-Template (MVT) in Django

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

## Learning Objectives

After completing this tutorial, students are expected to be able to:

- Understand the concept of MVT in Django applications.
- Understand how Django renders HTML pages.
- Understand the configuration of routing in `urls.py`.
- Understand the relationship between models, views, and templates in Django.
- Understand unit tests in Django.

## Summary of Tutorial 0

To help you in doing this tutorial, we expect the results of Tutorial 0 as follows.

1. On your local computer, there should exist a main directory called `shopping_list` that was initialized as a local repository.
2. Inside the main directory `shopping_list`, there are several files and the following subdirectories.

   - `env` subdirectory.
   - Project subdirectory called `shopping_list`. Unlike the main directory, this subdirectory is created after running the command

     ```bash
     django-admin startproject shopping_list .
     ```

   - `.gitignore` file.
   - `manage.py` file.
   - `requirements.txt` file.
   - (Optional) `db.sqlite3` file.

   The project structure of `shopping_list` in the local directory is as follows.

   ![Local Directory Structure](https://cdn.discordapp.com/attachments/881129085403811840/1146994121974755448/image.png)

3. On your GitHub repository, make sure the `shopping-list` repository contains the following files and directories:

   - Project directory called `shopping_list`. This directory is the result of running the command

     ```bash
     django-admin startproject shopping_list .
     ```

   - `.gitignore` file.
   - `manage.py` file.
   - `requirements.txt` file.

   The project structure of `shopping_list` on your GitHub is as follows.

   ![Github Repository Structure](https://cdn.discordapp.com/attachments/881129085403811840/1147077783017758720/image.png)

## Introduction to the MVT Concept

In the world of web development, there are various concepts and architectures that help in designing and developing applications. One commonly used concept is MVT (Model-View-Template).

### What is MVT?

![MVT Diagram](https://cdn.discordapp.com/attachments/1142468662461214771/1146996248268775484/3._Python_Django_-_Modul_2_Page2_Image5.jpg)

**MVT** stands for **_Model-View-Template_**. MVT is an architectural concept used in web development to separate the key components of an application. This concept enables web developers to organize and manage code more systematically.

### What is Model?

**_Model_** is a component in the MVT concept that responsible for managing and organizing data within an application. Model represents the data structure and application logic behind the scenes. The Model connects the application to the database and manages interactions with that data.

### What is View?

**_View_** is a component that handles presentation logic in the MVT concept. Views control how data managed by the Model will be presented to the user. In the context of MVT, the View acts as a presentation manager and retrieves data from the Model to present it to the user.

### What is Template?

**_Template_** is a component that is responsible for managing the user interface in web application. Templates separate HTML code from the application logic. In MVT, Templates are used to design the interface, which will be filled with data from the _Model_ through the _View_.

### Relationship Between MVT Components

In summary, the concept of MVT operates within the following framework:

- Model: Stores data and application logic.
- View: Displays data from Model and connects it to the template.
- Template: Determines the user interface's appearance.

### Benefits of MVT

- **Separation of Concerns**

  MVT separates tasks between application logic, views, and data, allowing developers to work on each component separately.

- **Maintainability**

  With clear task separation, the code becomes more organized and easier to maintain.

- **Reusability**

  Code can be reused in different parts of the application.

- **Scalability**

  The MVT structure supports scalability by enabling parallel development on each component.

**Notes:**

- The MVT concept is closely related to the Django framework for web development using Python.
- In practice, a good understanding of the MVT concept will help you design more structured and manageable web applications.

## Tutorial: Creating a Django Application and Configuring Models

In this tutorial, we will learn the concept of applications and projects in Django.

**What Are Projects and Applications in Django?**

- **Project** in Django is the entire web project that you build using Django. **A project contains multiple applications** that work together to create a complete website or web application.

- **Applications** are modular units that perform specific tasks within a Django project. Each application can have models, views, templates, and related URLs. Applications allow you to divide the functionality of a project into separate, independently manageable parts.

Before getting started, it is important to remember that the **main directory** is the **outermost** directory, while the **project directory** is a directory **inside** the main directory. Please note that they both have the same name, which is **`shopping_list`**.

### Step 1: Initial Setup

1. Open the **`shopping_list`** project directory.

   - Before starting, make sure you are inside main directory **`shopping_list`** that was created in the previous tutorial.
   - Inside this directory, you will continue developing the Django project.

2. Open a terminal or command prompt and ensure that your working directory is the main **`shopping_list`** directory.

3. Activate the _virtual environment_ that was created previously by running the following command.

   - **Windows:**

     ```batch
     env\Scripts\activate.bat
     ```

   - **Unix (Linux & Mac OS):**

     ```bash
     source env/bin/activate
     ```

### Step 2: Creating `main` Application in the Shopping List Project

You will create a new application called `main` in the _shopping list_ project.

1. Run the following command to create a new application.

   ```shell
   python manage.py startapp main
   ```

   After the command is executed, a new directory named `main` will be created which will contain the initial structure for your **application**.

   > Right now, you might be a bit confused about the terms **main** directory, **project** directory, and **application** directory. However, as time goes on, you will definitely understand it!

2. Register the `main` application into the project.

   - Open the `settings.py` file in the `shopping_list` project directory.
   - Find `INSTALLED_APPS` and add `'main'` to the list of existing applications.

     ```python
     INSTALLED_APPS = [
         ...,
         'main',
         ...
     ]
     ```

By doing these steps, you have registered the `main` application in your _shopping list_ project.

## Tutorial: Implementing a Basic Template

You will create a `main.html` template inside the `templates` directory within the `main` application. This template will be used to display your shopping list data.

> Currently, the shopping list application does not display any data. Data will be displayed in tutorial 2.

### Step 1: Creating `main.html`

Before we begin, let's briefly understand HTML. HTML (Hypertext Markup Language) is a **markup language** used to create the structure and appearance of content on web pages.

> Hint: You will learn more about HTML in tutorial 4.

1. Create a new directory called `templates` inside the `main` application.
2. Inside the `templates` directory, **create a new file** named `main.html` with the following content. Replace the values with your own data.

   ```html
   <h1>Shopping List Page</h1>

   <h5>Name:</h5>
   <p>Pak Bepe</p> <!-- Change it to your name -->
   
   <h5>Class:</h5>
   <p>PBP A</p> <!-- Change it to your class -->
   ```

3. Open the HTML file in a web browser.

- Before connecting it to the application, try opening the `main.html` file in your web browser.

- This step is done **only for checking** the basic HTML display and **is not yet connected to Django**.

- Here's an example of the expected HTML display.

![main.html](https://cdn.discordapp.com/attachments/923523971226435584/1147448582295793705/image.png)

## Tutorial: Implementing Basic Model

### Step 1: Modifying the `models.py` File in the `main` Application

In this step, you will modify the `models.py` file located within the `main` application to define a new model.

1. Open the `models.py` file in the `main` application directory.

2. Fill the `models.py` file with the following code:

   ```python
   from django.db import models

   class Product(models.Model):
       name = models.CharField(max_length=255)
       date_added = models.DateField(auto_now_add=True)
       price = models.IntegerField()
       description = models.TextField()
   ```

   **Code Explanation:**

   - `models.Model` is the base class used to define models in Django.
   - `Product` is the name of the model you're defining.
   - `name`, `date_added`, `price`, and `description` are attributes or fields of the model. Each field has an appropriate data type, such as `CharField`, `DateField`, `IntegerField`, and `TextField`.

### Step 2: Creating and Applying Model Migrations

**What is model migration?**

- Model migration is how Django keeps track of changes to your database schema.
- These migrations are instructions to change the database schema to match the changes you've made to your models in your code.

**How to perform model migration?**

1. Run the following command to create model migrations:

   ```shell
   python manage.py makemigrations
   ```

   > `makemigrations` creates a migration file that contains the changes to the model that haven't been applied to the database.

2. Run the following command to apply migrations to the local database:

   ```shell
   python manage.py migrate
   ```

   > `migrate` applies the model changes listed in the migration file to the database.

**Whenever you make changes to the model**, such as adding or modifying attributes, **you need to perform migrations** to reflect those changes.

## Tutorial: Connecting Views to Templates

In this tutorial, you will connect a view to a template using Django. These steps will explain how to create a `show_main` view function within the `main` application and render a template with data retrieved from the model.

### Step 1: Integrating MVT Components

You will import the necessary modules and create the `show_main` view function.

1. Open the `views.py` file located in the `main` application.

2. Add the following import statements at the top of the file:

   ```python
   from django.shortcuts import render
   ```

   **Code Explanation:**

   `from django.shortcuts import render` is used to import the render function from the `django.shortcuts` module. The render function is used to render an HTML template with the provided data.

3. Add the `show_main` function below the imports:

   ```python
   def show_main(request):
       context = {
           'name': 'Pak Bepe',
           'class': 'PBP A'
       }

       return render(request, 'main.html', context)
   ```

   **Code Explanation:**

   - `def show_main(request)` is the declaration of the `show_main` function, which takes the `request` parameter. This function handles the HTTP request and returns the appropriate view.
   - `context` is a dictionary that contains data to be passed to the view. In this context, two pieces of data are included:

     - `name`: Name data.
     - `class`: Class data.

   - `return render(request, 'main.html', context)` is used to render the `main.html` template using the `render` function. The `render` function takes three arguments:

     - `request`: This is the HTTP request object sent by the user.
     - `main.html`: This is the name of the template file to be used for rendering.
     - `context`: This is the dictionary containing data to be passed to the view for dynamic rendering.

## Step 2: Modifying the Template

Now, you will modify the `main.html` template to display data retrieved from the model.

1. Open the `main.html `file that you created earlier in the `templates` directory within the `main` application.

2. Replace the statically created name and class with Django code to display the data:

   ```html
   ...
   <h5>Name:</h5>
   <p>{{ name }}</p>
   
   <h5>Class:</h5>
   <p>{{ class }}</p>
   ...
   ```

   **Code Explanation:**

   Django syntax `{{ name }}` and `{{ class }}` are used to display the values of variables defined in the context.

## Tutorial: Configuring URL Routing

You will configure URL routing to allow the `main` application accessible through a web browser.

### Step 1: Configuring URL Routing for the `main` Application

1. Create a `urls.py` file **inside** the `main` application directory.

2. Fill the `urls.py` file with the following code:

   ```python
   from django.urls import path
   from main.views import show_main

   app_name = 'main'

   urlpatterns = [
       path('', show_main, name='show_main'),
   ]
   ```

   **Code Explanation:**

   - The `urls.py` file in the `main` application is responsible for configuring URL patterns related to the `main` application.
   - Import `path` from `django.urls` to define URL patterns.
   - Use the `show_main` function from the `main.views` module as the view to be displayed when the related URL is accessed.
   - `app_name` is provided to give a unique name to URL patterns within the application.

### Step 2: Configuring URL Routing for the Project

You will add URL routing in the `urls.py` file of the project to connect it to the `main` view.

1. Open the `urls.py` file **inside the project's `shopping_list` directory, not within the `main` application**.

2. Import the `include` function from `django.urls`:

   ```python
   ...
   from django.urls import path, include
   ...
   ```

3. Add the URL pattern as follows to direct it to the `main` view within the `urlpatterns` variable:

   ```python
   urlpatterns = [
       ...
       path('main/', include('main.urls')),
       ...
   ]
   ```

   **Code Explanation:**

   - The `urls.py` file at the project level is responsible for configuring top-level project URL routes.
   - The `include` function is used to import URL routes from other applications (in this case, from the `main` application) into the project's `urls.py` file.
   - The URL path `'main/'` will be directed to the route defined in the `urls.py` file of the `main` application.

4. Run the Django project using the `python manage.py runserver` command.

5. Open <http://localhost:8000/main/> in your web browser to view the created page.

By following the above steps, you have successfully implemented a basic view in the `main` application and connected it to the project's URL routing. Make sure you understand each step and the provided information.

### What is the Difference Between `urls.py` in an Application and `urls.py` in a Project?

- The `urls.py` file within an application manages specific URL routes for the features within that application.
- The `urls.py` file at the project level directs top-level project URL routes and can import URL routes from application-specific `urls.py` files, allowing Django applications within the project to be modular and separated.

## Tutorial: Introduction to Django Unit Testing

Unit testing can be used to check if your code behaves as expected. It is also useful when making code changes to ensure that the changes do not introduce unexpected behavior into the application.

### Step 1: Creating a Unit Test

1. Open the `tests.py` file in the `main` application directory.

2. Fill the `tests.py` file with the following code:

   ```python
   from django.test import TestCase, Client

   class MainTest(TestCase):
       def test_main_url_is_exist(self):
           response = Client().get('/main/')
           self.assertEqual(response.status_code, 200)

       def test_main_using_main_template(self):
           response = Client().get('/main/')
           self.assertTemplateUsed(response, 'main.html')
   ```

   **Code Explanation**

   - `test_main_url_is_exist` is a test to check if the `/main/` URL path can be accessed.
   - `test_main_using_main_template` is a test to check if the `/main/` page is rendered using the `main.html` template.

### Step 2: Running the Tests

1. Run the tests using the following command:

   ```shell
   python manage.py test
   ```

2. If the tests pass, you will see the following information:

   ```text
   Found 2 test(s).
   Creating test database for alias 'default'...
   System check identified no issues (0 silenced).
   ..
   ----------------------------------------------------------------------
   Ran 2 tests in 0.016s

   OK
   Destroying test database for alias 'default'...
   ```

**Congratulations!** You have successfully written Django tests and executed them.

## Closing

1. At the end of this tutorial, your local directory structure will look like this:

   ![Local Directory Structure](https://cdn.discordapp.com/attachments/881129085403811840/1147078488667475978/image.png)

2. Before proceeding with these steps, **ensure that your local directory structure is correct**. Next, perform `add`, `commit`, and `push` to update your GitHub repository. Once the GitHub repository is updated, Adaptable will automatically redeploy your project. If successful, the features you created in this tutorial will be accessible to the public.

3. Run the following commands to perform `add`, `commit`, and `push`:

   ```shell
   git add .
   git commit -m "<commit_message>"
   git push -u origin <your_main_branch>
   ```

   - Replace `<commit_message>` with your desired message, e.g., `git commit -m "Tutorial 1 completed"`.
   - Replace `<your_main_branch>` with the name of your main branch, e.g., `git push -u origin main` or `git push -u origin master`.

4. Here is the GitHub repository structure after completing this tutorial:

   ![GitHub Repository Structure](https://cdn.discordapp.com/attachments/881129085403811840/1147081165031882815/image.png)

## Additional Reference

- [Django Unit Testing](https://docs.djangoproject.com/en/4.2/topics/testing/)

## Contributors

- Kevin Alexander
- Ivan Rabbani Cezeliano
- Fariz Eda
- Dafi Nafidz Radhiyya
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
