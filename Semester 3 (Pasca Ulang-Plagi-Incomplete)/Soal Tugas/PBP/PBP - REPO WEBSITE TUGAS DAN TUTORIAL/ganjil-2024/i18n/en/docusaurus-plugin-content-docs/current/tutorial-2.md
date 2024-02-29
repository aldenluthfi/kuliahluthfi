---
sidebar_label: Tutorial 2
sidebar_position: 2
Path: docs/tutorial-2
---

# Tutorial 2: Form and Data Delivery

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Learning Objectives

After completing this tutorial, students are expected to be able to:

- Understand `XML` and `JSON` as some of the methods of data delivery.
- Understand how data submission provided by users works using the `form` element.
- Understand how to send data using the `XML` and `JSON` formats.
- Understand how to retrieve specific data based on an `ID`.

## Introduction to Data Delivery

In developing a platform, there are times when we need to send data from one stack to another. The data to be sent can take various forms. Some examples of commonly used data formats include HTML, XML, and JSON. The implementation of data delivery in HTML format has been covered in the previous tutorial. In this tutorial, we will learn about XML and JSON.

## XML (Extensible Markup Language)

XML stands for **eXtensible Markup Language**. XML is designed to be self-descriptive. By reading an XML document, we can understand the information that is being conveyed from the data written within it. XML is used in many web and mobile applications to store and transmit data. XML contains information wrapped within tags. We need to write programs to send, receive, store, or display this information.

Example of XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<person>
    <name>Alice Johnson</name>
    <age>25</age>
    <address>
        <street>123 Main St</street>
        <city>Los Angeles</city>
        <zip>90001</zip>
    </address>
</person>
```

The XML provided above is very self-descriptive:

- There is information about the name (`name`).
- There is information about the age (`age`).
- There is information about the address (`address`).
  - There is information about the street (`street`).
  - There is information about the city (`city`).
  - There is information about the zip code (`zip`).

XML documents form a tree-like structure, starting from the root, branching out, and ending at the leaves. XML documents **must contain a root element** that serves as the parent of other elements. In the example above, `<person>` is the root element.

The line `<?xml version="1.0" encoding="UTF-8"?>` is commonly referred to as **XML Prolog**. The XML Prolog is optional, but if present, it must appear at the beginning of the XML document. In XML document, **all elements must have a closing tag**. **Tags** in XML are **case-sensitive**, so the tag `<person>` is different from the tag `<Person>`.

## JSON (JavaScript Object Notation)

JSON stands for **JavaScript Object Notation**. JSON is designed to be self-describing, making it very easy to understand. JSON is used in many web and mobile applications for storing and transmitting data. JSON syntax is derived from JavaScript Objects. However, the JSON format is text-based, so code for reading and creating JSON is available in many programming languages.

Example of JSON:

```json
{
    "name": "Alice Johnson",
    "age": 25,
    "address": {
        "street": "123 Main St",
        "city": "Los Angeles",
        "zip": "90001"
    }
}
```

Data in JSON format is stored in the form of **key** and **value** pairs. In the example above, the **keys** are `name`, `age`, and `address`. The **values** can be primitive data types (such as strings, numbers, booleans) or objects.

## Tutorial: Configure Routing from `main/` to `/`

Before we talk about form, we will change our main routing from `main/` to `/`. We do this to follow the general convention. Hopefully, this step will also answer some of your questions about this.

> Note: In this tutorial, you will use your project created in previous tutorial.

1. Start the virtual environment.

    - Windows:

        ```batch
        env\Scripts\activate.bat
        ```

    - Unix (Mac/Linux):

        ```bash
        source env/bin/activate
        ```

2. Open `urls.py` inside the `shopping_list` folder. In the `urlpatterns` list, change the path `main/` to `''`:

    ```python
    urlpatterns = [
        path('', include('main.urls')),
        path('admin/', admin.site.urls),
    ]
    ```

3. Run your Django project with `python manage.py runserver` and open <http://localhost:8000> in your browser to view the result.

## Tutorial: Implementing a Skeleton as Views Structure

Before we create a registration form, we need to create a skeleton as our web's views structure. With this structure, we can ensure the design consistency in our website and reduce code redundancy. In this tutorial, we will create a skeleton for our web that will be used in upcoming tutorials.

1. Create a folder named `templates` inside the **root directory**. Also, create a file named `base.html` inside the `template` folder. This file will function as a base template that can be used as a general structure for our website's page. Fill `base.html` with:

    ```html
    {% load static %}
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8" />
            <meta
                name="viewport"
                content="width=device-width, initial-scale=1.0"
            />
            {% block meta %}
            {% endblock meta %}
        </head>

        <body>
            {% block content %}
            {% endblock content %}
        </body>
    </html>
    ```

2. Open `settings.py` inside the **`shopping_list` folder** and find the line that contains `TEMPLATES`. Adjust the code as follows to enable the detection of `base.html`  as a template file.

    ```python
    ...
    TEMPLATES = [
        {
            'BACKEND': 'django.template.backends.django.DjangoTemplates',
            'DIRS': [BASE_DIR / 'templates'], # add this line
            'APP_DIRS': True,
            ...
        }
    ]
    ...
    ```

3. In the `templates` subdirectory **inside the `main` folder**, change the `main.html` that have been created in the previous tutorial as follows.

    ```html
    {% extends 'base.html' %}

    {% block content %}
        <h1>Shopping List Page</h1>

        <h5>Name:</h5>
        <p>{{name}}</p>

        <h5>Class:</h5>
        <p>{{class}}</p>
    {% endblock content %}
    ```

    > Notice that the code is still the same as the `main.html` created in the previous tutorial. The difference is we used `base.html` as a template.

## Tutorial: Creating a Data Input Form and Showing Product Data in HTML

Previously, there has been no data added to the application. Now, we will create a simple form to input an item into the application and show it on the main page.

1. Create a new file inside the **`main` folder** named `forms.py`. This file will be used to create a form structure that accepts new item data. Fill the file with:

    ```python
    from django.forms import ModelForm
    from main.models import Product

    class ProductForm(ModelForm):
        class Meta:
            model = Product
            fields = ["name", "price", "description"]
    ```

    **Code Explanation:**

    - `model = Product` is used to point to a model used by the form. Later, when an instance of this form is saved, the content of the form will be saved as an object of `Product`.
    - `fields = ["name", "price", "description"]` is used to select attributes of the model `Product`. The form will use these selected attributes as form fields. The attribute `date_added` is not included because the date will be added automatically.

2. Open `views.py` inside the **`main` folder** and add some imports at the start of the file:

    ```python
    from django.http import HttpResponseRedirect
    from django.urls import reverse
    from main.forms import ProductForm
    from main.models import Product
    ```

3. In the same file, create a new function called `create_product` that accepts a parameter `request`. Fill the function with the following code to automatically add a new product when the form is submitted.

    ```python
    def create_product(request):
        form = ProductForm(request.POST or None)

        if form.is_valid() and request.method == "POST":
            form.save()
            return HttpResponseRedirect(reverse('main:show_main'))

        context = {'form': form}
        return render(request, "create_product.html", context)
    ```

    **Code Explanation:**

    - `form = ProductForm(request.POST or None)` is used to create a new `ProductForm`. The form is filled with user's input stored in `request.POST` as a `QueryDict`.
    - `form.is_valid()` is used to validate the content of the form.
    - `form.save()` is used to save the content of the form to the application's database.
    - `return HttpResponseRedirect(reverse('main:show_main'))` is used to redirect the page after the form is successfully saved.
    - The function `reverse('main:show_main')` is used to resolve a string pattern to a URL. One of the commonly used string patterns is `app_name:url_name`. In this case, the `app_name` is `main` and the `url_name` is `show_main`. Therefore, Django will find a URL named `show_main` in the `urls.py` of app `main` (which is `''`).

4. Change the `show_main` function inside `views.py` as follows.

    ```python
    def show_main(request):
        products = Product.objects.all()

        context = {
            'name': 'Pak Bepe', # Your name
            'class': 'PBP A', # Your PBP Class
            'products': products
        }

        return render(request, "main.html", context)
    ```

    **Code Explanation:**

    The function call `Product.objects.all()` is used to fetch all `Product` object from the application's database.

5. Open `urls.py` inside the **`main` folder** and import the previously created `create_product` function.

    ```python
    from main.views import show_main, create_product
    ```

6. In the same file, add a new url path inside the `urlpatterns` list to access the previously imported function.

    ```python
    path('create-product', create_product, name='create_product'),
    ```

7. In the `templates` subdirectory inside the **`main` folder**, create a new HTML file named `create_product.html`. Fill the file as follows.

    ```html
    {% extends 'base.html' %} 

    {% block content %}
    <h1>Add New Product</h1>

    <form method="POST">
        {% csrf_token %}
        <table>
            {{ form.as_table }}
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Add Product"/>
                </td>
            </tr>
        </table>
    </form>

    {% endblock %}
    ```

    **Code Explanation:**

    - `<form method="POST">` is used to tag a form with a `POST` method.
    - `{% csrf_token %}` is a token used as a security. This token is automatically generated by Django to prevent malicious attacks on your website through a form.
    - `{{ form.as_table }}` is used to show previously created form fields as a table.
    - `<input type="submit" value="Add Product"/>`  is used as a submit button. Upon submit, a request will be sent to  the function `create_product(request)`.

8. Open `main.html` and add this code somewhere between the `{% block content %}` and `{% endblock content %}` to show product data as a table and a button to redirect to the form page.

    ```html
    ...
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Date Added</th>
        </tr>

        {% comment %} Below is how to show the product data {% endcomment %}

        {% for product in products %}
            <tr>
                <td>{{product.name}}</td>
                <td>{{product.price}}</td>
                <td>{{product.description}}</td>
                <td>{{product.date_added}}</td>
            </tr>
        {% endfor %}
    </table>

    <br />

    <a href="{% url 'main:create_product' %}">
        <button>
            Add New Product
        </button>
    </a>
    
    {% endblock content %}
    ```

9. Run your Django project with `python manage.py runserver` and open <http://localhost:8000> in your browser. Try to add some new products. Your newly created products should be shown on your website's main page.

## Tutorial: Returning Data as XML

1. Open `views.py` inside the **`main` folder** and add some imports at the start of the file.

    ```python
    from django.http import HttpResponse
    from django.core import serializers
    ```

2. In the same file, create a new function named `show_xml`. This function accepts a request as the parameter. Create a variable to store all fetched `Product` objects.

    ```python
    def show_xml(request):
        data = Product.objects.all()
    ```

3. Add a return statement to return the previously fetched data as XML.

    ```python
    def show_xml(request):
        data = Product.objects.all()
        return HttpResponse(serializers.serialize("xml", data), content_type="application/xml")
    ```

    **Code Explanation:**

    `serializers` is used to translate an object to a different format, in this case, XML.

4. Open `urls.py` inside the `main` directory and import the previously created function.

    ```python
    from main.views import show_main, create_product, show_xml
    ```

5. Add a new url path to the `urlpatterns` list to access the previously imported function.

    ```python
    ...
    path('xml/', show_xml, name='show_xml'),
    ...
    ```

6. Run your Django project with `python manage.py runserver` and open <http://localhost:8000/xml> in your browser to view the result.

## Tutorial: Returning Data as JSON

1. Open `views.py` inside the **`main` folder** and create a new function named `show_json`. This function accepts a request as the parameter. Create a variable to store all fetched `Product` objects:

    ```python
    def show_json(request):
        data = Product.objects.all()
    ```

2. Add a return statement to return the previously fetched data as JSON.

    ```python
    def show_json(request):
        data = Product.objects.all()
        return HttpResponse(serializers.serialize("json", data), content_type="application/json")
    ```

3. Open `urls.py` inside the `main` directory and import the previously created function.

    ```python
    from main.views import show_main, create_product, show_xml, show_json
    ```

4. Add a new url path to the `urlpatterns` list to access the previously imported function.

    ```python
    ...
    path('json/', show_json, name='show_json'),
    ...
    ```

5. Run your Django project with `python manage.py runserver` and open <http://localhost:8000/json> in your browser to view the result.

## Tutorial: Retrieving Data Based on ID in XML and JSON Formats

1. Open the `views.py` file located in `main` folder and create two new functions that accept the `request` parameter and an `id` parameter named `show_xml_by_id` and `show_json_by_id`.

2. Create a variable inside each function to store the query result of data with a specific ID from the `Product` model.

    ```python
    data = Product.objects.filter(pk=id)
    ```

3. Add a return statement that returns an `HttpResponse` containing the serialized data in either JSON or XML format and set the `content_type` parameter to `"application/xml"` (for XML format) or `"application/json"` (for JSON format).

    - XML

        ```python
        def show_xml_by_id(request, id):
            data = Product.objects.filter(pk=id)
            return HttpResponse(serializers.serialize("xml", data), content_type="application/xml")
        ```

    - JSON

        ```python
        def show_json_by_id(request, id):
            data = Product.objects.filter(pk=id)
            return HttpResponse(serializers.serialize("json", data), content_type="application/json")
        ```

4. Open the `urls.py` file located in the `main` folder and import the functions you created earlier.

    ```python
    from main.views import show_main, create_product, show_xml, show_json, show_xml_by_id, show_json_by_id
    ```

5. Add URL paths to the `urlpatterns` list to access the imported functions.

    ```python
    ...
    path('xml/<int:id>/', show_xml_by_id, name='show_xml_by_id'),
    path('json/<int:id>/', show_json_by_id, name='show_json_by_id'),
    ...
    ```

6. Run your Django project using the `python manage.py runserver` command and open <http://localhost:8000/xml/[id]> or <http://localhost:8000/json/[id]> in your favorite browser to see the results.

    **Notes:**
    `[id]` represents a variable part of the URL. It's a placeholder for a specific value that you can provide when accessing a particular endpoint. This value is typically an identifier or unique key associated with a specific resource in your application. For example, an item with id `1` can be viewed on <http://localhost:8000/xml/1>.

## Tutorial: Using Postman as Data Viewer

1. Ensure that your server is running using the `python manage.py` runserver command.

2. Open Postman and create a new request with the `GET` method and the URL <http://localhost:8000/xml> or <http://localhost:8000/json> to test if data is being sent correctly.

    > Installation instructions for Postman can be found on the [Official Postman Website](https://learning.postman.com/docs/getting-started/installation/installation-and-updates/#:~:text=Postman%20is%20available%20on%20the,select%20Download%20for%20your%20platform.).

    Example:
    ![Postman Page View](https://cdn.discordapp.com/attachments/923523971226435584/1150063777648214168/image.png)

3. Click the `Send` button to send the request.

4. You will see the response from the request at the bottom of the Postman interface.

    ![Sent](https://cdn.discordapp.com/attachments/923523971226435584/1150063647599624333/image.png)

5. You can also modify the URL to <http://localhost:8000/xml/[id]> or <http://localhost:8000/json/[id]> to test the function of retrieving product data by ID.

    ![Json By ID](https://cdn.discordapp.com/attachments/923523971226435584/1150064359658238033/image.png)

## Tutorial: Adding Deployment Configuration to PBP Fasilkom UI PaaS

In this tutorial, you will configure your Django application for deployment to the Platform-as-a-Service (PaaS) provided by PBP Fasilkom UI. However, the migration to PBP Fasilkom UI's PaaS will be done gradually, so you will also continue deploying to Adaptable while preparing for the PBP Fasilkom UI's PaaS deployment.

Make sure you have filled out the deployment data form provided earlier because PBP Fasilkom UI's PaaS still requires additional configuration on the administrator's side.

1. Open the `requirements.txt` file in the root folder and add `django-environ` to the last line of the file. Don't forget to save the file after making changes.

2. Run the `pip install -r requirements.txt` command to install the changes in the `requirements.txt` file.

3. Create a new file named `Procfile` (**without a file extension**) in the root folder and add the following code to it.

    ```yaml
    release: django-admin migrate --noinput
    web: gunicorn project_name.wsgi
    ```

    > Replace `project_name` with your Django project name (e.g., `shopping_list`).

4. Create a folder named `.github` (with a dot at the beginning of the folder name) in the root folder, and inside the `.github` folder, create a new folder named `workflows`.

5. Create a new file named `pbp-deploy.yml` inside the `workflows` folder and add the following code to it.

    ```yaml
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
    ```

6. Create a new file named `.dockerignore` (**without a file extension**) in the root folder and fill the file with the following code.

    ```yaml
    **/*.pyc
    **/*.pyo
    **/*.mo
    **/*.db
    **/*.css.map
    **/*.egg-info
    **/*.sql.gz
    **/__pycache__/
    .cache
    .project
    .idea
    .pydevproject
    .idea/workspace.xml
    .DS_Store
    .git/
    .sass-cache
    .vagrant/
    dist
    docs
    env
    logs
    src/{{ project_name }}/settings/local.py
    src/node_modules
    web/media
    web/static/CACHE
    stats
    Dockerfile
    .gitignore
    Dockerfile
    db.sqlite3
    **/*.md
    logs/
    ```

7. Create a file named `Dockerfile` (**without a file extension**) in the root folder and add the following code to it.

    ```dockerfile
    FROM python:3.10-slim-buster

    WORKDIR /app

    ENV PYTHONUNBUFFERED=1 \
        PYTHONPATH=/app \
        DJANGO_SETTINGS_MODULE=shopping_list.settings \
        PORT=8000 \
        WEB_CONCURRENCY=2

    # Install system packages required Django.
    RUN apt-get update --yes --quiet && apt-get install --yes --quiet --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

    RUN addgroup --system django \
        && adduser --system --ingroup django django

    # Requirements are installed here to ensure they will be cached.
    COPY ./requirements.txt /requirements.txt
    RUN pip install -r /requirements.txt

    # Copy project code
    COPY . .

    RUN python manage.py collectstatic --noinput --clear

    # Run as non-root user
    RUN chown -R django:django /app
    USER django

    # Run application
    # CMD gunicorn shopping_list.wsgi:application
    ```

8. Open the `settings.py` file inside the `shopping_list` folder.

9. Add the line `import environ` and `import os` after the line `from pathlib import Path`.

    ```python
    from pathlib import Path
    import environ # Add this line
    import os # Add this line
    ```

10. Add the line `env = environ.Env()` after the line `BASE_DIR`.

    ```python
    BASE_DIR = Path(__file__).resolve().parent.parent

    env = environ.Env() # Add this line
    ```

11. Add the following code after the `SECRET_KEY` section.

    ```python
    # Automatically determine environment by detecting if DATABASE_URL variable.
    # DATABASE_URL is provided by Heroku if a database add-on is added (e.g. Heroku Postgres).
    PRODUCTION = env.bool('PRODUCTION', False)
    ```

12. Add the following code after the `DATABASES` section.

    ```python
    # Set database settings automatically using DATABASE_URL.
    if PRODUCTION:
        DATABASES = {
            'default': env.db('DATABASE_URL')
        }
        DATABASES["default"]["ATOMIC_REQUESTS"] = True
    ```

13. Add the following code after the `STATIC_URL` section.

    ```python
    STATIC_URL = 'static/'

    STATIC_ROOT = os.path.join(BASE_DIR, 'static')
    ```

At this point, you have configured your Django application for deployment to PBP Fasilkom UI's PaaS. Next, you will configure your GitHub repository to enable automatic deployment.

1. Open your GitHub repository for your application and go to the `Settings` menu on the far right.

2. Navigate to the `Secrets and variables` section and select the `Actions` submenu.

    ![Action secrets and variables](https://media.discordapp.net/attachments/923523971226435584/1150303039882801234/image.png)

3. Click the `New repository secret` button on the right to add new secret variables.

4. Create three new secret variables with the following specifications:

    | **Name**              | **Secret**                            |
    |-----------------------|---------------------------------------|
    | DOKKU_SERVER_IP       | pbp.cs.ui.ac.id                       |
    | DOKKU_APP_NAME        | UsernameSIAK-tutorial                 |
    | DOKKU_SSH_PRIVATE_KEY | [The content of your SSH private key] |

    > Replace `UsernameSIAK-tutorial` with your own data and dots with dashes. For example: muhammad-iqbal111-tutorial.

    > (13 September 2023) For now, **do not fill `DOKKU_SERVER_IP` with the defined specification**. Only fill `DOKKU_APP_NAME` and `DOKKU_SSH_PRIVATE_KEY` as we are experiencing issues in the server for now.

    > Refer to [this tutorial](https://docs.google.com/document/d/1ZVsfUBaEGvuioYMmP-gboKLFd_2c_EPTYyC-urev5UQ/edit?usp=sharing) for instructions on how to copy your private key.

    Example results will look like this:

    ![Example of secrets](https://media.discordapp.net/attachments/923523971226435584/1150303593874849813/image.png)

You have now configured your GitHub repository for automatic deployment. Congratulations! you have configured your project for deployment to PBP Fasilkom UI's PaaS!

To access your application's deployment at PBP Fasilkom UI's PaaS, **use HTTP protocol** and `UsernameSIAK-tutorial` as the URL. Example: <http://muhammad-athallah01-tutorial.pbp.cs.ui.ac.id>.

## Closing

1. After completing this tutorial, your web page should look like this:

    ![web Page View](https://cdn.discordapp.com/attachments/923523971226435584/1149953373177651251/image.png)

2. At the end of this tutorial, your local directory structure should look like this:

    ![Local Directory Structure](https://cdn.discordapp.com/attachments/923523971226435584/1151130212784943144/image.png)

3. Before performing this step, **ensure that your local directory structure is correct**. Next, perform `add`, `commit`, and `push` to update your GitHub repository. Once the GitHub repository is updated, Adaptable will automatically perform another deployment. If successful, the features you created in this tutorial should be accessible to the public.

4. Run the following commands to `add`, `commit`, and `push`:

    ```shell
    git add .
    git commit -m "<commit_message>"
    git push -u origin <your_main_branch>
    ```

    - Replace `<commit_message>` with your desired message. For example: `git commit -m "Completed tutorial 2"`.
    - Replace `<your_main_branch>` with your main branch name. For example: `git push -u origin main` or `git push -u origin master`.

## Additional References

- [How to install Postman](https://learning.postman.com/docs/getting-started/installation/installation-and-updates/#:~:text=Postman%20is%20available%20on%20the,select%20Download%20for%20your%20platform.)

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
