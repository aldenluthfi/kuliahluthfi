---
sidebar_label: Tutorial 4
sidebar_position: 6
Path: docs/tutorial-4
---

# Tutorial 4: Web Design using HTML and CSS3 & Data Update and Delete Methods

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

## Learning Objectives

After completing this tutorial, students are expected to be able to:

- Understand the concept of data update and delete.
- Understand how to update and delete data in Django.
- Understand the tag arrangement in HTML5.
- Know the variety of HTML5 tags.
- Understand CSS syntax.
- Understand the static files concept in Django.
- Understand the usage of selector in CSS.

## Introduction to HTML

HyperText Markup Language (HTML) is a standard markup language for a document shown on a web browser. HTML defines the structure of the content of a website.

You can learn and try HTML for yourself [here](https://www.w3schools.com/html/default.asp).

You can read the differences between HTML and HTML5 [here](https://www.geeksforgeeks.org/difference-between-html-and-html5/).

## Introduction to CSS

### What is CSS?

Cascading Style Sheets (CSS) is a language used to describe the appearance and format of a website written in a markup language (such as HTML). CSS is useful to enhance the appearance of a website, making it more attractive. 

You can learn the differences between CSS and CSS3 [here](https://www.geeksforgeeks.org/difference-between-css-and-css3/).

### CSS Syntax

In general, CSS is written as follows:

```css
selector {
  properties: value;
}
```

You can learn and try CSS for yourself [here](https://www.w3schools.com/css/).

There are 3 types of CSS:

1. **Inline Styles**
2. **Internal Style Sheet**
3. **External Style Sheet**

You can learn more about those 3 types here [ini](https://www.geeksforgeeks.org/types-of-css-cascading-style-sheet/).

Note that if you are creating an External Style Sheet, you have to to add the tag `{% load staticfiles %}` in your Django HTML file. The example is as follows:

```html
{% load staticfiles %}
<html>
  <head>
    <title>CSS Tutorial Yay</title>
    <link rel="stylesheet" href="{% static 'css/tutorial.css' %}" />
  </head>
  <body>
    <div>
      <h1>CSS Tutorial Yay</h1>
    </div>
    <div id="main">
      <div>
        <p>published: 27 September 2023</p>
        <h1><a href="">My CSS Tutorial</a></h1>
        <p>T!</p>
      </div>
    </div>
  </body>
</html>
```

This needs to be done because CSS is a static file in Django. Static files will be explained in the next section.

### Additional Note

If there is more than one style defined for an element/tag, then the applied style for that element/tag is the one that has the higher priority. The priority order is as follows (number 1 has the highest priority):

1. Inline style
2. Internal style
3. External style
4. Browser default

## Static files in Django

In Django, there are some files called static files. Static files are supporting files used in an HTML file of a website. Examples of static files are CSS, JavaScript, and images.

The setting for static files is located in **`settings.py`**:

```py 
...
# Static files (CSS, JavaScript, Images)
# httpsdocs.djangoproject.comen1.9howtostatic-files
STATIC_ROOT = os.path.join(PROJECT_ROOT, 'static')
STATIC_URL = 'static'
...
```
 
The `settings.py` contains:
- `STATIC_ROOT`, which is used to tell Django the absolute path of the directory location where the command `collectstatic` will collect static files.
- `STATIC_URL`, which is a URL that can be publicly accessed to obtain the static files.

The `collectstatic` command is used to collect static files from all app so that the files can be easily accessed.

You can read more about static files [here](https://docs.djangoproject.com/en/4.1/ref/contrib/staticfiles/).

## CSS Selectors

In this tutorial, we will explore three types of CSS selectors: **Element Selector, ID Selector, and Class Selector**.

1. **Element Selector**

    The Element Selector allows us to apply styles to all elements with the same HTML tag.

    Example of using the Element Selector:
    
    ```html 
    <body>
      <div>
        <h1>Tutorial CSS Yay :D</h1>
        <h2>Tutorial CSS Yay Yay :D</h2>
      </div>
      ...
    </body>
    ```
    
    We can use the element itself as a selector in a **CSS file**. The Element Selector uses the format ***[tag_name]*** (without any preceding symbol).
    
    ```html 
    h1 {
      color: #fca205;
      font-family: "Monospace";
      font-style: italic;
    }
    ```
    
2. **ID Selector**

    The ID Selector uses the ID of an HTML tag as its selector. IDs are unique within a single web page and can be added to HTML template pages.

    Example of using the ID Selector in an HTML template:
    
    ```html 
    <body>
      <div id="header">
        <h1>Tutorial CSS Yay :D</h1>
      </div>
      ...
    </body>
    ```
    
    Then, we can use the ID as a selector in a **CSS file**. ID selectors use the format ***#[id_name]*** (always preceded by **#**).
    
    ```html 
    #header {
      background-color: #f0f0f0;
      margin-top: 0;
      padding: 20px 20px 20px 40px;
    }
    ```
    
3. **Class Selector**

    The Class Selector allows us to group elements with similar characteristics.

    Example of using Class Selector in an HTML template:
    
    ```html
    ...
    <div id="main">
        <div class="content_section">
            <p class="date">published: 28 September 2022</p>
            <h2><a href="">My CSS tutorial</a></h2>
            <p id="content_1">So ez!</p>
        </div>
        <div class="content_section">
            <p class="date ">published: 29 September 2022</p>
            <h2><a href="">Your CSS tutorial</a></h2>
            <p id="content_2">So ez ez!</p>
        </div>
        <div class="content_section">
            <p class="date">published: 30 September 2022</p>
            <h2><a href="">Our CSS tutorial</a></h2>
            <p id="content_3">So ez ez ez!</p>
        </div>
    </div>
    ...
    ```
    
    Then, we can use the Class as a selector in a **CSS file**. Class selectors use the format ***.[class_name]*** (preceded by **.** ).
    
    ```html
    .content_section {
      background-color: #3696e1;
      margin-bottom: 30px;
      color: #000000;
      font-family: cursive;
      padding: 20px 20px 20px 40px;
    }
    ```
    
    For a deeper understanding of CSS Selector Reference, you can read the reference [here](https://www.w3schools.com/cssref/css_selectors.php).

## CSS Tips & Tricks

### Understanding CSS Combinators

Combinators in CSS connect two or more selectors to further refine the selected elements.

There are four types of combiners in CSS. Here's a summary of their usage:

| Combinator | Example | Explanation |
| -------- | -------- | -------- |
| Descendant selector (space) | `div p`     | Selects all `p` elements that are descendants of `div` elements. |
| Child selector (>) | `div > p`     | Selects all `p` elements that are children of `div` elements. |
| Adjacent sibling selector (+) | `div + p` | Selects the first `p` element that directly follows a `div` element (must share the same parent). |
| General sibling selector (~) | `div ~ p` | Selects all `p` elements that are siblings and come after a `div` element. |

You can learn more about combinators [here](https://www.w3schools.com/css/css_combinators.asp).

### Understanding CSS Pseudo-classes

Pseudo-classes are used to define a special state of an element. The syntax for using pseudo-classes is as follows:

```css
selector:pseudo-class {
  property: value;
}
```

Here are some examples of pseudo-classes:
| Pseudo-class | Applies Styles to ... |
| -------- | -------- |
| `:link` | Links that have not been visited yet. |
| `:visited` | Links that have been visited. |
| `:hover` | Elements when a user hovers their cursor over them. |
| `:active` | Elements when a user hovers their cursor over them. |
| `:focus` | Elements when they are in focus, like an input field when clicked. |
| `:checked` | Elements like checkboxes or radio buttons that are checked. |
| `:disabled` | Elements that are unresponsive, like disabled buttons. |

You can learn more about pseudo-classes [here](https://www.w3schools.com/css/css_pseudo_classes.asp).

### Understanding the CSS Box Model

![css box model](https://hackmd.io/_uploads/B1QiTx9ya.png)

The CSS box model essentially wraps each HTML element and consists of:

* **Content**: The content inside the box (where text and images appear).
* **Padding**: The transparent space around the content.
* **Border**: The border around the content and padding.
* **Margin**: The transparent space around the border.

You can learn more about margin, border, and padding [here](https://www.w3schools.com/css/css_boxmodel.asp).

## Introduction to Bootstrap & Tailwind

In the field of web development, there are many CSS frameworks that developers commonly use. The primary purpose of a CSS framework is to streamline the work of programmers. Two popular CSS frameworks today are **Bootstrap** and **Tailwind**. Both of these frameworks offer various advantages compared to using plain CSS. Here are the benefits of using a framework compared to regular CSS:

1. **Faster Development**: Frameworks like Bootstrap provide pre-built components, eliminating the need to write CSS from scratch.
2. **Built-in Responsiveness**: Frameworks like Bootstrap and Tailwind are designed with responsiveness in mind.
3. **Scalability**: CSS frameworks provide a well-organized structure for growing projects over time.

Bootstrap and Tailwind, as frameworks, have significant differences:

| Tailwind | Bootstrap |
| -------- | --------- |
| Tailwind CSS builds layouts by combining pre-defined utility classes.     | Bootstrap uses predefined styles and components with ready-made designs that can be used directly.      |
| Tailwind CSS has a smaller CSS file compared to Bootstrap and only loads the utility classes used. | Bootstrap has a larger CSS file due to the inclusion of many predefined components. |
| Tailwind CSS offers high flexibility and adaptability to projects. | Bootstrap often produces more consistent designs throughout a project as it uses predefined components. |
| Learning Tailwind CSS can have a steeper learning curve because it requires understanding and combining available utility classes. | Bootstrap offers a quicker learning curve for beginners as they can start with predefined components. |

## Responsive Web Design

Responsive web design is a web design approach that aims to create websites that look good on all devices, including desktops, tablets, mobile phones, and more. Responsive web design does not change the content of the website but adapts its appearance and layout based on the device's screen width and capabilities. In responsive web design, specific views may require CSS assistance, such as resizing or enlarging elements.

One way to test if a website uses responsive web design is to access the website and enable the `Toggle Device Mode` feature in your web browser. This feature allows you to see how the website appears on various devices, such as desktops, tablets, or smartphones, without changing the browser window's size.

Here's how to access this feature in **Google Chrome**:

* Windows/Linux : `CTRL + SHIFT + M`
* Mac : `Command + Shift + M`

Another practical way is to right-click in the browser and select "Inspect Element/Inspect" to open the Dev Tools, which can be very helpful.

To learn more about Responsive Web Design, you can refer to this [guide](https://web.dev/responsive-web-design-basics/).

## Tutorial: Adding Bootstrap

During this PBP course, we will focus on learning CSS using the Bootstrap framework. Here are the steps you need to take to complete this tutorial.

1. Open your Django project **shopping_list** and then open the `base.html` file that was previously created in the templates folder located in your project's root directory.

2. Inside the `templates/base.html` file, add the `<meta name="viewport">` tag. With this tag, your web page will adapt to the size and behavior of mobile devices.

    ```html
    <head>
        {% block meta %}
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1">
        {% endblock meta %}
    </head>
    ```
    
3. Add Bootstrap and JavaScript

    **CSS:**
    ```html
    <head>
        {% block meta %}
            ...
        {% endblock meta %}
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    ```

    **JS:**
    ```html
    <head>
        ...
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KyZXEAg3QhqLMpG8r+J4jsl5c9zdLKaUk5Ae5f5b1bw6AUn5f5v8FZJoMxm6f5cH1" crossorigin="anonymous"></script>
    </head>
    ```
    
4. **Optional** If you want to use the dropdowns, popovers, and tooltips provided by the Bootstrap framework, you need to add these two lines of JavaScript below the JavaScript code you've previously created.

    ```html
    <head>
        ...
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    </head>
    ```

Reference: [Get Started with Bootstrap 5.3](https://getbootstrap.com/docs/5.3/getting-started/introduction/)

## Tutorial: Adding Navbar

Add a navigation bar (you can use Bootstrap) to your `main.html` page. Display your name and a logout button on the navigation bar. You can refer to how to create a navigation bar in Bootstrap with the [following documentation](https://getbootstrap.com/docs/5.3/components/navbar/) and customize the navigation bar as you like.

## Tutorial: Adding Edit Function to the Application

1. Open `views.py` in the `main` subdirectory, and create a new function called `edit_product` that accepts `request` and `id` parameters.

2. Add the following code to the `edit_product` function

    ```python
    def edit_product(request, id):
        # Get product by ID
        product = Product.objects.get(pk = id)

        # Set product as instance of form
        form = ProductForm(request.POST or None, instance=product)

        if form.is_valid() and request.method == "POST":
            # Save the form and return to home page
            form.save()
            return HttpResponseRedirect(reverse('main:show_main'))

        context = {'form': form}
        return render(request, "edit_product.html", context)
    ```

3. Create a new HTML file called `edit_product.html` in the `main/templates` subdirectory. Add the following code.

    ```html 
    {% extends 'base.html' %}

    {% load static %}

    {% block content %}

    <h1>Edit Product</h1>

    <form method="POST">
        {% csrf_token %}
        <table>
            {{ form.as_table }}
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Edit Product"/>
                </td>
            </tr>
        </table>
    </form>

    {% endblock %}
    ```

4. Open `urls.py` in the `main` directory and import the `edit_product` function that has been created.

    ```python
    from main.views import edit_product
    ```

5. Add the path url to `urlpatterns` to access the function that was imported earlier.

    ```python 
    ...
    path('edit-product/<int:id>', edit_product, name='edit_product'),
    ...
    ```

6. Open `main.html` in the `main/templates` subdirectory. Add the following code to the last `<td>` element to make the *edit* button visible on each row of the table.

    ```html 
    ...
    <tr>
        ...
        <td>
            <a href="{% url 'main:edit_product' product.pk %}">
                <button>
                    Edit
                </button>
            </a>
        </td>
    </tr>
    ...
    ```

7. Run your Django project with the command `python manage.py runserver` and open http://localhost:8000 in your favorite browser. After logging in, try to *edit* the data for a product. If no errors occur, then congratulations, you have successfully added the *edit* feature!


## Tutorial: Adding Delete Function to the Application

Here are the steps to create a delete function:

1. Create a new function called `delete_product` in the `views.py` file within the main folder. This function should take parameters `request` and `id`. You can use the following code below to create such function:

    > Try to understand the code first! 😉

    ```python
    def delete_product(request, id):
        # Get data by ID
        product = Product.objects.get(pk=id)
        # Delete data
        product.delete()
        # Return to the main page
        return HttpResponseRedirect(reverse('main:show_main'))
    ```

2. Open the `urls.py` file in the main folder and import the function you created earlier:

    ```python
    from main.views import delete_product
    ```

3. Add a path URL to the `urlpatterns` to access the imported function:

    ```python
    ...
    path('delete/<int:id>', delete_product, name='delete_product'),  # adjust the function name
    ...
    ```

4. Open the `main.html` file in the `main/templates` folder and modify the existing code to include a delete button for each product:

    ```html 
    ...
    <tr>
        ...
        <td>
            <a href="{% url 'main:edit_product' product.pk %}">
                <button>
                    Edit
                </button>
            </a>
            <a href="{% url 'main:delete_product' product.pk %}">
                  <button>
                      Delete
                  </button>
              </a>
        </td>
    </tr>
    ...
    ```

Run your Django project and try deleting a product from your favorite browser.

## Further Reading

You can open the links below to see some code options that you can use to add a navigation bar:
- If you use Bootstrap, refer to [this link](https://getbootstrap.com/docs/5.2/components/navbar/)
- If you use vanilla CSS, refer to [this link](https://www.w3schools.com/css/css_navbar.asp)

## Contributors

* Alanna
* Alvaro Austin
* Naila Shafirni Hidayat
* Shayna Putri Fitria
* Aidah Novallia Putri (EN Translator)
* Bonaventura Galang (EN Translator)
* Ferry (EN Translator)

## Credits
This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
