---
sidebar_label: Tutorial 3
sidebar_position: 5
Path: docs/tutorial-3
---

# Tutorial 3: Authentication, Session, and Cookies

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

## Learning Objectives

After completing this tutorial, students are expected to be able to:

* Understand the basic concept of authentication in web development.
* Understand the roles and functions of cookies and sessions in web development.
* Understand how cookies and sessions work in web development.
* Implement cookies and sessions in a web project.

## Introduction to HTTP

HTTP (HyperText Transfer Protocol) is a protocol in communication between a client and a server. HTTP is stateless, which means that each transaction/activity is treated as an entirely new transaction/activity. Therefore, no previous data is stored for the current transaction/activity.

Here are some basic concepts about HTTP:

1. **Client/Server**: Interaction is carried out between client or server. The client is the party who makes the request and the server is the party who provides the response.

2. **Stateless**: Each activity (request or response) is independent, not stored in previous activities.

3. **OSI Layer/Model**: The Open Systems Interconnection (OSI) model describes the 7 layers that computer systems use to communicate over networks. The 7-layer OSI model consists of Application Layer, Presentation Layer, Session Layer, Transport Layer, Network Layer, Data Link Layer, and Physical Layer.

4. **Application Layer**: In the OSI Model mentioned above, websites operate at the application layer. Meanwhile, the request/response process occurs at the transport layer which generally uses the TCP protocol which determines how data is transmitted. The application layer is not concerned with what the transport layer does (how data is sent, processed, etc.) because the application layer only focuses on request and response.

     > Other OSI layers will be taught in the Computer Networks course. You can look for them yourself if you are curious. 😉

5. **Client Actions Method**: There are methods used by the client when making a request, for example: GET, POST, PUT, DELETE, etc. More detailed explanation can be found [here](https://www.restapitutorial.com/lessons/httpmethods.html).

6. **Server Status Code**: This is the status code provided by the server for a request on a web page. Example: 200 (OK), 404 (Page Not Found), 500 (Internal Server Error), etc. A more detailed explanation can be found [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

7. **Headers**: These are small pieces of information sent along with the request and response. This information is useful as additional data used to process request/response. For example, in the headers, there is `content-type:json`, indicating that the requested/sent content type is `json`. Headers also store `cookies` data.

## Introduction to Cookies & Session

All communication between the client and server is done through the HTTP protocol, a stateless protocol. This means that one state is not related to another (independent). This requires the computer on client side to establish a TCP connection to the server every time it makes a request.

Without a persistent connection between the client and server, the software on each side (endpoint) cannot rely solely on the TCP connection to hold a state or hold session state.

### What does holding a state mean?

Suppose that you want to access a page A on a website that requires users to be logged in. Then, you log in to the website and successfully access page A. When you want to move to page B on the same website, without a process of holding a state, **you will be prompted to log in again**. This will happen every time you access a different page on the same website.

The process of informing "who" is currently logged in and storing that information is the basis of a session (a semi-permanent exchange of information). It is difficult to make HTTP hold a state (because HTTP is a stateless protocol). Therefore, other techniques are needed to address this issue, namely `cookies` and `sessions`.

### How to hold a state?

One of the widely used methods for holding a state is storing a **session ID** as a **cookie** in the client's browser. Session ID can be considered as a token (line of characters) that identifies a unique session of a website. Instead of storing the client's information as a cookie (e.g. username, name, password, etc.), only the session ID is stored.

Beside as a cookie, the session ID is also stored in a data structure on the website's server side. The data structure also stores the corresponding client's information. This approach of storing client's information is much safer instead of directly storing the information as a cookie. Furthermore, this approach is considered as the "correct" way to store a bunch of client's information. This is due to the size limit of a cookie, which is 4KB of data.

But how is session ID used to hold a state exactly? Imagine that you already logged in to the website and your session ID is stored in your browser as a cookie. To hold a state in the stateless HTTP, the browser usually will attach stored cookies to the HTTP request. Therefore, every time the server processes an incoming request, it can read the session ID in the request's cookie. After some processing, the server will find the information related to the session ID in the memory or database, and then returning the requested data.

The main difference that needs to be remembered is cookie's data is stored on the client side, while session's data is stored on the server side. For more detailed explanation about stateless, stateful, cookie, and session can be read [here](https://sethuramanmurali.wordpress.com/2013/07/07/stateful-stateless-cookie-and-session/).

Below is a table briefly explaining the differences between cookies, sessions, and local storage.

|                        | **Cookies**         | **Local Storage** | **Sessions**       |
|------------------------|---------------------|-------------------|--------------------|
| **Capacity**           | 4 KB                | 5 MB              | 5 MB               |
| **Browser Technology** | HTML4/HTML5         | HTML5             | HTML5              |
| **Accessibility**      | All window          | All window        | Same tab           |
| **Expiry**             | Manually configured | No expiration     | When tab is closed |


Below are some videos that can enrich your knowledge about this material:

- [Session & Cookies](https://www.youtube.com/watch?v=64veb6tKTm0&t=10s)
- [Cookies History](https://www.youtube.com/watch?v=I01XMRo2ESg)
- [Cookies vs. Local Storage vs. Session](https://www.youtube.com/watch?v=AwicscsvGLg)

## Tutorial: Creating Registration Form and Function

> Note: In this tutorial, you will use your project created in previous tutorial.

In the previous tutorial, we have created a form to add products. Easy, right? In this tutorial, we will restrict the access to the main page. Therefore, only logged in users can access the page. Other user who wants to access the main page have to log in first to gain access.

1. Start the virtual environment.

2. Open `views.py` **inside the `main` folder** and create a function called `register` that accepts `request` as a parameter.

3. Add imports for `redirect`, `UserCreationForm`, dan `messages` at the beginning of the file.

    ```python
    from django.shortcuts import redirect
    from django.contrib.auth.forms import UserCreationForm
    from django.contrib import messages  
    ```

    **Code Explanation:** 

    `UserCreationForm` is a built-in form that eases us to create a user registration form. With this form, we can create a registration form without needing to code it from scratch.

4. Add this code inside the `register` function. This piece of code will create a registration form automatically. If a registration data is submitted, a user account will be created.

    ```python
    def register(request):
        form = UserCreationForm()

        if request.method == "POST":
            form = UserCreationForm(request.POST)
            if form.is_valid():
                form.save()
                messages.success(request, 'Your account has been successfully created!')
                return redirect('main:login')
        context = {'form':form}
        return render(request, 'register.html', context)
    ```

    **Code Explanation:** 

    - `form = UserCreationForm(request.POST)` is used to create a new `UserCreationForm` based on the data in `request.POST`.
    - `form.is_valid()` is used to validate the form data.
    - `form.save()` is used to create a new data (in this case, a new user) from the form.
    - `messages.success(request, 'Your account has been successfully created!')` is used to show a message to the user after an action.
    - `return redirect('main:show_main')` is used to redirect the page after the form is successfully saved.

5. Create a new HTML file named `register.html` **inside the `main/templates` folder**. Fill the file with:

    ```html
    {% extends 'base.html' %}

    {% block meta %}
        <title>Register</title>
    {% endblock meta %}

    {% block content %}  

    <div class = "login">
        
        <h1>Register</h1>  

            <form method="POST" >  
                {% csrf_token %}  
                <table>  
                    {{ form.as_table }}  
                    <tr>  
                        <td></td>
                        <td><input type="submit" name="submit" value="Daftar"/></td>  
                    </tr>  
                </table>  
            </form>

        {% if messages %}  
            <ul>   
                {% for message in messages %}  
                    <li>{{ message }}</li>  
                    {% endfor %}  
            </ul>   
        {% endif %}

    </div>  

    {% endblock content %}
    ```

6. Open `urls.py` **inside the `main` folder** and import the function you created previously.

    ```python
    from main.views import register
    ```

7. Add a new path url to `urlpatterns` to access the imported function.

    ```python
    ...
    path('register/', register, name='register'),
    ...
    ```

We have created an account registration form and a `register` mechanism. Next, we will create a login form.

## Tutorial: Creating a Login Function

1. Open `views.py` **inside the `main` folder** and create a function called `login_user` that accepts a `request` as a parameter.

2. Add imports for `authenticate` and `login` at the beginning of the file.

    ```python
    from django.contrib.auth import authenticate, login
    ```

    **Code Explanation:**

    The imported functions ``authenticate`` and ``login`` are used to authenticate and log in the user if the authentication is success. Read more [here](https://docs.djangoproject.com/en/4.2/topics/auth/default/).

3. Add this code inside the `login` function. This piece of code is used to authenticate a user.

    ```python
    def login_user(request):
        if request.method == 'POST':
            username = request.POST.get('username')
            password = request.POST.get('password')
            user = authenticate(request, username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect('main:show_main')
            else:
                messages.info(request, 'Sorry, incorrect username or password. Please try again.')
        context = {}
        return render(request, 'login.html', context)
    ```

    **Code Explanation:**
  
    `authenticate(request, username=username, password=password` is used to authenticate a user using the provided username and password. The username and password is sent through the request.

4. Create a new HTML file named `login.html` **inside the `main/templates` folder**. Fill the file with:

    ```html
    {% extends 'base.html' %}

    {% block meta %}
        <title>Login</title>
    {% endblock meta %}

    {% block content %}

    <div class = "login">

        <h1>Login</h1>

        <form method="POST" action="">
            {% csrf_token %}
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" placeholder="Username" class="form-control"></td>
                </tr>
                        
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" placeholder="Password" class="form-control"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input class="btn login_btn" type="submit" value="Login"></td>
                </tr>
            </table>
        </form>

        {% if messages %}
            <ul>
                {% for message in messages %}
                    <li>{{ message }}</li>
                {% endfor %}
            </ul>
        {% endif %}     
            
        Don't have an account yet? <a href="{% url 'main:register' %}">Register Now</a>

    </div>

    {% endblock content %}
    ```

5. Open `urls.py` **inside the `main` folder** and import the previously created function.

    ```python
    from main.views import login_user
    ```

6. Add a new path url to `urlpatterns` to access the imported function.

    ```python
    ...
    path('login/', login_user, name='login'),
    ...
    ```

We have created a user login form and `login` mechanism. Next, we will create a logout mechanism and add a logout button on the main page.

## Tutorial: Creating a Logout Function

1. Open `views.py` **inside the `main` folder** and create a function called `logout_user` that accepts a `request` as a parameter.

2. Add import `logout` at the beginning of the file.

    ```python
    from django.contrib.auth import logout
    ```

3. Add this code inside the `logout` function. This piece of code is used to log out a user.

    ```python
    def logout_user(request):
        logout(request)
        return redirect('main:login')
    ```

    **Code Explanation:**
  
    - `logout(request)` is used to delete the currently logged in user's session.
    - `return redirect('main:login')` is used to redirect the page to the login page.

4. Open the `main.html` **inside the `main/templates`**.

5. Add this code **after the _Add New Product_ hyperlink tag**.

    ```html
    ...
    <a href="{% url 'main:logout' %}">
        <button>
            Logout
        </button>
    </a>
    ...
    ```

6. Open `urls.py` **inside the `main` folder** and import the previously created function.

    ```python
    from main.views import logout_user
    ```

7. Add a new path url to `urlpatterns` to access the previously imported function.

    ```python
    ...
    path('logout/', logout_user, name='logout'),
    ...
    ```

Congrats! You have successfully created a `logout` mechanism and completed the authentication system in this project!

## Tutorial: Restricting Access to The Main Page

1. Open the `views.py` file **in the `main` subdirectory** and add the `login_required` import at the top.

    ```python
    from django.contrib.auth.decorators import login_required
    ```

    **Code Explanation:**

    The code `from django.contrib.auth.decorators import login_required` is used to require users to log in before accessing the web page. 
    
2. Add the `@login_required(login_url='/login')` code above the `show_main` function to restrict access to the main page only to authenticated users.

    ```python
    ...
    @login_required(login_url='/login')
    def show_main(request):
    ...
    ```
    
After restricting access to the main page, run your Django project using the `python manage.py runserver` command and open <http://localhost:8000/> in your favorite browser to see the results. You should be redirected to the login page instead of the main product list page if you are not logged in.

## Tutorial: Using Data from Cookies

Now, we will explore the use of cookies by adding a "last login" feature and displaying it on the main page.

1. Log out first if you are currently logged into your Django application.

2. Open the `views.py` file **in the `main` subdirectory** and add imports for `HttpResponseRedirect`, `reverse`, and `datetime` at the very top.

     ```python
    import datetime
    from django.http import HttpResponseRedirect
    from django.urls import reverse
    ```
    
3. In the `login_user` function, modify the code inside the `if user is not None` block as follows:

    ```python
    ...
    if user is not None:
        login(request, user)
        response = HttpResponseRedirect(reverse("main:show_main")) 
        response.set_cookie('last_login', str(datetime.datetime.now()))
        return response
    ...
    ```
    
    **Code Explanation:**
    - `login(request, user)` is used to log in the user.
    - `response = HttpResponseRedirect(reverse("main:show_main"))` creates a response object.
    - `response.set_cookie('last_login', str(datetime.datetime.now()))` creates a 'last_login' cookie and adds it to the response.

4. In the `show_main` function, add the `'last_login': request.COOKIES['last_login']` code to the context variable as shown below:

    ```python
    context = {
        'name': 'Pak Bepe', # Your name
        'class': 'PBP A', # Your PBP class
        'products': products,
        'last_login': request.COOKIES['last_login'],
    }
    ```
    
    **Code Explanation:**
    
    `'last_login': request.COOKIES['last_login']` is used to add the 'last_login' cookie data to the response, which will be displayed on the web page.
    
5. Modify the `logout_user` function as follows:

    ```python
    def logout_user(request):
        logout(request)
        response = HttpResponseRedirect(reverse('main:login'))
        response.delete_cookie('last_login')
        return response
    ```
    
    **Code Explanation**
    
    `response.delete_cookie('last_login')` is used to delete the `last_login` cookie when the user logs out.

6. Open the `main.html` file and add the following code between the table and logout button to display the 'last login' data:

    ```html
    ...
    <h5>Last login session: {{ last_login }}</h5>
    ...
    ```
    
7. Refresh the login page (or run your Django project with the `python manage.py runserver` command if you haven't already) and try logging in. Your 'last login' data should appear on the main page.

8. To view the `last_login` cookie data, you can use the browser's developer tools and go to the "Application" or "Storage" section. Click on "Cookies", and you will see the available cookies. In addition to `last_login` you can also find `sessionid` and `csrftoken` cookies. Here's an example of what it looks like:

    ![Main Page Image along with Cookie Data](https://cdn.discordapp.com/attachments/923523971226435584/1152456209278976011/Tutorial-3.png)
    
9. If you log out and check the cookie history, the previously created cookie will be deleted and recreated when you log in again.

Before proceeding to the next tutorial, try to **create one account** on your website.

## Tutorial: Connecting the `Product` Model to `User` Model

> **You need to follow the tutorials sequentially from the beginning before proceeding with the following sections. If you don't follow the tutorials in order, we cannot guarantee the avoidance of errors beyond the scope of this tutorial.**

Finally, we will link each `Product` object to the user who created it, so that authorized users can only see the products they have created. For that, follow these steps:

1. Open the `models.py` file **in the `main` subdirectory** and add the following code below the code for importing models:

    ```python
    ...
    from django.contrib.auth.models import User
    ...
    ```
    
2. In the existing `Product` model, add the following code:

     ```python
    class Product(models.Model):
        user = models.ForeignKey(User, on_delete=models.CASCADE)
        ...
    ```
    
    **Code Explanation:**

    This code is used to establish a relationship where each product is associated with one user. In other words, each product belongs to a specific user. For a more in-depth explanation of `ForeignKey` in Django, you can refer to [this documentation](https://docs.djangoproject.com/en/4.2/topics/db/examples/many_to_one/).
    
3. In the `create_product` function, modify the code as follows:

    ```python
   def create_product(request):
    form = ProductForm(request.POST or None)

    if form.is_valid() and request.method == "POST":
        product = form.save(commit=False)
        product.user = request.user
        product.save()
        return HttpResponseRedirect(reverse('main:show_main'))
    ...
   ```
   
   **Code Explanation:**

   Using `commit=False` prevents Django from immediately saving the object created from the form to the database. This allows us to modify the object before saving it. In this case, we set the `user` field to the `User` object associated with the currently logged-in user, indicating that the product belongs to that user.

4. Modify the `show_main` function as follows:

    ```python
    def show_main(request):
        products = Product.objects.filter(user=request.user)

        context = {
            'name': request.user.username,
        ...
    ...
    ```
    
    **Code Explanation:**

    - This code is used to display `Product` objects associated with the currently logged-in user. It filters the objects to only retrieve those where the `user` field matches the logged-in user.
    - The `request.user.username` code is used to display the username of the logged-in user on the main page.

5. Save all changes and run the migrations for the model using `python manage.py makemigrations`.

6. You may encounter an error during the migration process. Choose option `1` to set a default value for the `user` field in all existing rows in the database.

    ![Migrations-1](https://cdn.discordapp.com/attachments/923523971226435584/1152471335080046712/image.png)
    
7. Type `1` again to set the `user` to ID 1 (which we created earlier) for the existing models.

    ![Migrations-2](https://cdn.discordapp.com/attachments/923523971226435584/1152471372988170310/image.png)
    
8. Run `python manage.py migrate` to apply the migrations made in the previous steps.

Run your Django project using the `python manage.py runserver` command and open <http://localhost:8000/> in your browser to see the results. Try creating a new account and logging in. You will notice that products created with a previous account are not displayed on the main page when logged in with a new account. This means you have successfully connected the `Product` objects to the `User` who created them.

## Closing

Congratulations! You have successfully completed Tutorial 3. 😄

After completing all the tutorials above, you should now have a better understanding of using forms, authentication, sessions, and cookies in the Django framework.

1. After completing this tutorial, your web page should look like this.

     ![Web Page View](https://cdn.discordapp.com/attachments/923523971226435584/1152473904342900776/image.png)
     
2. At the end of this tutorial, your `templates` directory **in `main` subdirectory** should look like this:

    ![local Directory Structure](https://cdn.discordapp.com/attachments/923523971226435584/1152473607373602846/image.png)
    
3. Before performing this step, **ensure that your local directory structure is correct**. Next, perform `add`, `commit`, and `push` to update your GitHub repository.

4. Run the following commands to `add`, `commit`, and `push`:

    ```shell
    git add .
    git commit -m "<commit_message>"
    git push -u origin <your_main_branch>
    ```

    - Replace `<commit_message>` with your desired message. For example: `git commit -m "Completed tutorial 2"`.
    - Replace `<your_main_branch>` with your main branch name. For example: `git push -u origin main` or `git push -u origin master`.

## Contributors

- Rayhan Putra Randi
- Anindya Lokeswara
- Kade Satrya Noto Sadharma
- Alfredo Austin
- Alya Azhar Agharid
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
