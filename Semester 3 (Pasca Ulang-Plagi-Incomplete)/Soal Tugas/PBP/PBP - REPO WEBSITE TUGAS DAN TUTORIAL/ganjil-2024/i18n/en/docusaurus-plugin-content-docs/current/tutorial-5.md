---
sidebar_label: Tutorial 5
sidebar_position: 7
Path: docs/tutorial-5
---

# Tutorial 5: JavaScript and AJAX

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Learning Objectives

After completing this tutorial, students are expected to be able to:

- Understand the role of JavaScript in front-end development.
- Use JavaScript at a basic level.
- Implement AJAX and the Fetch API.

## JavaScript

### Introduction to JavaScript

JavaScript is a high-level, cross-platform, multi-paradigm programming language. Its multi-paradigm nature makes JavaScript support object-oriented programming, imperative programming, and functional programming paradigm. JavaScript itself is an implementation of ECMAScript, which is the core of the JavaScript language. Some other ECMAScript implementations similar to JavaScript include JScript (Microsoft) and ActionScript (Adobe).

JavaScript, along with HTML and CSS, are the three main technologies used in web development. Basically, the advantages of using JavaScript in web development is its ability to dynamically manipulate web pages and enhance interaction between web pages and users. Therefore, almost all modern websites today use JavaScript in their web pages to provide the best user experience. Some examples of what can be done with JavaScript are displaying information based on time, recognizing the user's browser type, performing form or data validation, creating **HTTP cookies**, dynamically changing styling and **CSS** of an element, and more.

In web development, JavaScript code is generally used on the client-side of a web application (client-side JavaScript). However, some types of JavaScript code are now used on the server-side of a web application (server-side JavaScript), such as in **Node.js**. The term "client-side" indicates that JavaScript code will be executed or run in the user's browser, not on the web server. This means that the complexity of JavaScript code does not impact the performance of the web server, but does affect the performance of the web browser and the user's computer. The more complex the JavaScript code, the more computer memory it consumes in the web browser.

In the PBP course, we will only focus on client-side JavaScript code.

### Stages of JavaScript Execution by Browser

Observe the following diagram of the stages of JavaScript execution by a web browser.

![javascript-works](https://cdn.discordapp.com/attachments/1125384367305400381/1158739517847654440/147E06B1-3E47-4BF4-894F-88BBF21D8124.png?ex=651d57c5&is=651c0645&hm=cb37b22af3f7d83b388cc26da3728016553519a6d4317113e61b9ecb4c78a7fb&)

After the browser downloads the HTML web page, it will eventually reach the `<script></script>` tags, and within these tags, it will either find embedded JavaScript code or a reference to an external JavaScript file. If it references an external JavaScript file, the browser will download that file first.

### Writing JavaScript

JavaScript can be written using **embedded JavaScript** or **external JavaScript**. JavaScript code can be defined or written directly within an HTML file or separately in its own file. When written in a separate file, the file extension commonly used for JavaScript files is `.js`. Here are some examples of JavaScript definitions:

JavaScript can be placed in either the head or the body of an HTML page. Furthermore, JavaScript code must be placed between the `<script>` and `</script>` tags. You can include more than one `<script>` tag containing JavaScript in an HTML file.

#### Embedded JavaScript in HTML

`index.html`

```html
<script type="text/JavaScript">
  alert("Hello World!");
</script>
```

#### External JavaScript in HTML

`index.html`

```html
<script type="text/JavaScript" src="js/script.js"></script>
```

`js/script.js`

```javascript
alert("Hello World!");
```

In an external JavaScript file, the `<script>` tag does not need to be added.

Separating JavaScript into its own file can provide several advantages, such as the code being reusable in other HTML files, keeping JavaScript and HTML code separate for better focus during development, and speeding up the page loading process. JavaScript files with a `.js` extension are typically cached by the browser, so if you revisit the same page and there are no changes to the `.js` file, the browser would not request the `.js` file from the server again but will use the cached file that was stored previously.

### JavaScript Execution

Once the JavaScript has been loaded completely, the browser will immediately start executing the JavaScript code. If the code is NOT an *event-triggered*, then the code is immediately executed. If the code is *event-triggered*, then the code will only be executed if the defined *event* is triggered (*triggered*).

```javascript
// executed immediately
alert("Hello World");

// executed immediately
var obj = document.getElementById("object");
// executed immediately, adding  event handler onclick for 'obj' element
obj.onclick = function () {
  // executed only if 'object' element is clicked
  alert("You just clicked the object!");
};
```

### JavaScript Syntax

#### Variable

Defining variables in JavaScript is quite easy. For example:

```javascript
var example = 0; // var example is an integer
var example = "example"; // var example is a string
var example = true; // var example is a boolean
```

JavaScript can hold various data types, such as string, integer, and object. Unlike Java, where data type declaration is done with the variable declaration (e.g., if you want to create a variable of type int, you'd write `int x = 9`), JavaScript has the characteristic of being a **loosely typed** or **dynamic language**. This means that you don't need to specify data types in the variable declaration in JavaScript, and JavaScript will automatically determine the data type based on the value you assign (as shown in the example above).

There are some rules for choosing identifiers or variable names in JavaScript. The first character must be a letter, underscore (`_`), or dollar sign (`$`). Additionally, JavaScript identifiers are **case-sensitive**.

#### String Concatenation

In JavaScript, we can concatenate `string` with another `string`, just like in Java.

```javascript
var str1 = "PBP" + " " + "Fun";
var str2 = "PBP";
var str3 = "Fun";
var str4 = str2 + " " + str3;
var str5 = "Fun";
var str6 = `PBP ${str5}`;  // Same as "PBP" + " " + str5
```

### JavaScript Scope

#### Local Variables

Variables defined **inside** a function are local and can only be accessed by code within that function.

```javascript
// Code outside thisFunction() cannot access the variable courseName
function thisFunction() {
  var courseName = "PBP";
  // Code inside this function can access the variable courseName
}
```

#### Global Variables

Variables defined **outside** a function are global and can be accessed by other code in the JavaScript file.

```javascript
var courseName = "PBP";
function thisFunction() {
  // Code inside this function can access the variable courseName
}
```

#### Automatic Global Variables

Values assigned to variables that are not declared become global variables automatically, even if they are inside a function.

```javascript
thisFunction(); // The function thisFunction() needs to be called first
console.log(courseName); // Prints "PBP" to the JavaScript console
function thisFunction() {
  courseName = "PBP";
}
```

#### Accessing Global Variables from HTML

You can access variables defined in a JavaScript file in the HTML file that includes the JavaScript file.

```html
...
<input type="text" onclick="this.value=courseName" />
...
```

```javascript
...
var courseName = "PBP";
...
```

### Function and Event

A function is a group of code that can be called anywhere in the program (similar to a method in Java). Functions reduce code redundancy and allow for dynamic function calls. Functions can be called within other functions and can also be triggered by events, as explained below. For example, in the `index.html` file:

```html
...
<input type="button" value="magicButton" id="magicButton" onclick="hooray();" />
...
```

And in the `javascript.js` file:

```javascript
...
function hooray(){
    alert("Yahoo!");
}
...
```

When the `magicButton` is clicked, the `onclick` event will execute the `hooray()` function from `javascript.js`, displaying an alert as previously defined.

The `onclick` attribute is an example of JavaScript's ability to handle events. Events in JavaScript allow for dynamic web pages. Other event examples include `onchange`, `onmouseover`, `onmouseout`, and more, which you can read about [here](https://www.w3schools.com/js/js_events.asp).

### JavaScript DOM

#### HTML DOM

The HTML DOM (Document Object Model) is a standard for how to get, change, add, or delete HTML elements. The HTML DOM can be accessed using JavaScript or other programming languages. Details can be found [here](https://www.w3schools.com/js/js_htmldom.asp).

Here is an implementation example:

```html
...     
<div>
  <p onclick="myFunction()" id="demo">Example of HTML DOM</p>
      
</div>
...
```

```javascript
...
    function myFunction() {
document.getElementById("demo").innerHTML = "YOU CLICKED ME!";
    }
...
```

#### CSS DOM

Similar to the HTML DOM, the CSS DOM can be used to dynamically change CSS using JavaScript. Details can be found [here](https://www.w3schools.com/js/js_htmldom_css.asp).

Here is an implementation example:

`index.html`

```html
...
<p id="blueText" onclick="changeColor()">Click me v2</p>
...
```

`javascript.js`

```javascript
...
function changeColor(){
    document.getElementById("blueText").style.color="blue";
}
...
```

## AJAX

### Introduction to AJAX

AJAX stands for **A**synchronous **J**avaScript **A**nd **X**ML. It's not a programming language but rather a technology that combines web browsers (for requesting data from a web server) with JavaScript and HTML DOM (for displaying data). AJAX can use XML to send data, but it can also use text or JSON. AJAX allows web pages to update data asynchronously by sending data to the server behind the scenes. This means you can update parts of a web page without having to reload the entire page.

Here is the diagram illustrating how AJAX works:

![ajax-works](https://www.w3schools.com/js/pic_ajax.gif)

1. An event occurs on the web page (e.g., a button like `submit data` is pressed).
2. An `XMLHttpRequest` object is created by JavaScript.
3. The `XMLHttpRequest` object sends a request to the server.
4. The server processes the request.
5. The server returns a response to the web page.
6. The response is read by JavaScript.
7. Subsequent actions are triggered by JavaScript based on the defined steps (e.g., updating data on the page).

In this course, you will perform AJAX in a web browser using the `fetch()` function provided by JavaScript. In general, the usage `fetch()` to make AJAX calls can be seen more [here](https://www.w3schools.com/jsref/api_fetch.asp).

### Fetch API

The Fetch API is a new API introduced in ECMAScript 2020 as a new standard for making requests with `Promise`. It provides an interface for fetching resources (including across the network). The Fetch API is a more powerful and flexible replacement for the traditional [`XMLHttpRequest`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest). It generally simplifies AJAX requests compared to traditional `XMLHttpRequest`. The Fetch API also supports more HTTP methods and HTTP headers than regular AJAX.

The `fetch()` function has several parameters:

- `url`: The URL of the resource you want to fetch.
- `method`: The HTTP method to use.
- `headers`: The HTTP headers to send.
- `body`: The body of the HTTP request.

The `fetch()` function returns a `Response` object. The `Response` object has properties such as:

- `status`: The HTTP status code of the response.
- `headers`: The HTTP headers of the response.
- `body`: The body content of the response.

You can learn more about the Fetch API [here](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API).

### Async and Await Functions

Before learning about the usage of the `fetch()` function, it's essential to understand `async` and `await` functions, which allow for asynchronous programming without the need for external libraries like [jQuery](https://jquery.com/).

`async` and `await` functions are relatively new and were introduced in ECMAScript 2017. The `async` function is used to mark a function as capable of returning values asynchronously, while the `await` function is used to await the results of an `async` function.

You can learn more about `async` and `await` [here](https://www.w3schools.com/js/js_async.asp).

### Using the Fetch API

The Fetch API provides a JavaScript interface for accessing and manipulating parts of the protocol, such as requests and responses. This API also provides the global `fetch()` method, which offers an easy and logical way to asynchronously retrieve resources across the network. It also integrates advanced HTTP concepts like CORS and other extensions into HTTP.

Unlike the callback-based approach of `XMLHttpRequest`, the Fetch API is `Promise`-based and provides a cleaner and more straightforward way to perform asynchronous operations, making it a better choice for modern web development.

Here is an example of using the Fetch API with `async` and `await` functions to perform AJAX:

```javascript
async function fetchData() {
  const response = await fetch("https://jokes-bapack2-api.yuana.id/v1/text/random");
  const data = await response.json();
  return data;
}

const joke = await fetchData();
console.log(joke);
```

The code above asynchronously requests data from the "Indonesian Dad Jokes" API and stores the result in the `joke` variable.

You can learn more about using the Fetch API [here](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch).

## Tutorial: Implementing a Function to Return Data as JSON

In this section, you will implement a `views` function to return data as JSON. This function will be used to show product data in HTML using `fetch`.

1. Create a new function in `views.py` called `get_product_json` that accepts a `request` as a parameter.

2. Filll the function with these codes:

    ```python
    def get_product_json(request):
        product_item = Product.objects.all()
        return HttpResponse(serializers.serialize('json', product_item))
    ```

## Tutorial: Implementing a Function to Add Product using AJAX

In this section, you will implement a `views` function to add new product to the database using AJAX.

1. Create a new function in `views.py` called `add_product_ajax` that accepts a `request` as a parameter.

2. Import `from django.views.decorators.csrf import csrf_exempt` in `views.py`.

3. Add decorator `@csrf_exempt` above the `add_product_ajax` function.

4. Fill the function with these codes:

    ```python
    def add_product_ajax(request):
        if request.method == 'POST':
            name = request.POST.get("name")
            price = request.POST.get("price")
            description = request.POST.get("description")
            user = request.user

            new_product = Product(name=name, price=price, description=description, user=user)
            new_product.save()

            return HttpResponse(b"CREATED", status=201)

        return HttpResponseNotFound()
    ```

    **Code Explanation:**
    - `name = request.POST.get("name")` is used to get the value of `name` from the `request`.
    - `new_product`is used to create a new Product object with attributes corresponding to the request values.

## Tutorial: Add Routings for the `get_product_json` and `add_product_ajax`

1. Open `urls.py` inside the folder `main` and import the `get_product_json` and `add_product_ajax` functions.

2. Add the URL path for both functions inside the `urlpatterns` list.

    ```python
    ...
    path('get-product/', get_product_json, name='get_product_json'),
    path('create-product-ajax/', add_product_ajax, name='add_product_ajax')
    ```

## Tutorial: Showing the Product Data using `fetch()` API

1. Open `main.html` inside the `main/templates` and delete the **table code** you have created in the previous tutorial.

2. Add this code as the table structure:

    ```html
    <table id="product_table"></table>
    ```

3. Create a `<script>` tag block at the end of your file and create a new function called `getProducts()`.

    ```JavaScript
    <script>
        async function getProducts() {
            return fetch("{% url 'main:get_product_json' %}").then((res) => res.json())
        }
    </script>
    ```

    **Code Explanation:**
    - This function is using `fetch()` API to get the JSON data asynchronously.
    - After the data is fetched (that isinside the `then()` function), the JSON data is parsed to a JavaScript object.

4. Create a new function in the `<script>` tag block called `refreshProducts()`. This function will be used to refresh the product data asynchronously.

    ```js
    <script>
        ...
        async function refreshProducts() {
            document.getElementById("product_table").innerHTML = ""
            const products = await getProducts()
            let htmlString = `<tr>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Date Added</th>
            </tr>`
            products.forEach((item) => {
                htmlString += `\n<tr>
                <td>${item.fields.name}</td>
                <td>${item.fields.price}</td>
                <td>${item.fields.description}</td>
                <td>${item.fields.date_added}</td>
            </tr>` 
            })
            
            document.getElementById("product_table").innerHTML = htmlString
        }

        refreshProducts()
    </script>
    ```

    **Code Explanation:**
    - `document.getElementById("product_table")` is used to get an HTML element using its ID. In this line, the element is the `<table>` with ID `product_table` that have been created before.
    - `innerHTML` is used to change the child element of an element. `innerHTML = ""` means the child element will be emptied.
    - `products.forEach((item))` is used to do a for each loop on the product data that have been fetched by using the `getProducts()` function. The `htmlString` is concatenated with the product data to fill the table.
    - `refreshProducts()` is used to call the function and refresh the table everytime we open the web page.

## Tutorial: Creating a Modal as a Form to Add Products

1. Add this code to implements a form modal ([Bootstrap](https://getbootstrap.com/docs/5.3/components/modal/)) in your app.

    ```html
    ...
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Add New Product</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="form" onsubmit="return false;">
                        {% csrf_token %}
                        <div class="mb-3">
                            <label for="name" class="col-form-label">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"></input>
                        </div>
                        <div class="mb-3">
                            <label for="price" class="col-form-label">Price:</label>
                            <input type="number" class="form-control" id="price" name="price"></input>
                        </div>
                        <div class="mb-3">
                            <label for="description" class="col-form-label">Description:</label>
                            <textarea class="form-control" id="description" name="description"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="button_add" data-bs-dismiss="modal">Add Product</button>
                </div>
            </div>
        </div>
    </div>
    ...
    ```

    > The form in that modal has been adjusted for the `shopping_list` app model.

    > You can read more about Bootstrap modal [here](https://getbootstrap.com/docs/5.3/components/modal/).

2. Add a `button` to show the modal.

    ```html
    ...
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Add Product by AJAX</button>
    ...
    ```

## Tutorial: Using AJAX to Add a Product

The modal form you created before can be used to add new product data. We have to create a new JavaScript function to add a new product using the form's data using AJAX.

1. Create a new function inside the `<script>` tag block called `addProduct()`. Fill the function with:

    ```js
    <script>
        ...
        function addProduct() {
            fetch("{% url 'main:add_product_ajax' %}", {
                method: "POST",
                body: new FormData(document.querySelector('#form'))
            }).then(refreshProducts)

            document.getElementById("form").reset()
            return false
        }
    </script>
    ```

    **Code Explanation:**
    - `new FormData(document.querySelector('#form'))` is used to create a new FormData with data taken from the form modal. The FormData object is used to wrap the data before sending it to the server.
    - `document.getElementById("form").reset()` is used to empty the fields of the form modal after it has been submitted.

2. Set the `addProduct()` function as the `onclick` function of the modal's "Add Product" button:

    ```js
    <script>
    ...
    document.getElementById("button_add").onclick = addProduct
    </script>
    ```

Congratulations! You have successfully created an app that can add data using AJAX. Open <http://localhost:8000/> and try to add new products to the app. The app should not be refreshed now every time new data has been added.

## Closing

Run the following commands to `add`, `commit`, and `push`:

```shell
git add .
git commit -m "<commit_message>"
git push -u origin <main_branch>
```

- Replace `<commit_message>` with your desired message. For example: `git commit -m "Completed tutorial 5"`.
- Replace `<main_branch>` with your main branch name. For example: `git push -u origin main` or `git push -u origin master`.

## Additional References

- [Async/Await Function in JavaScript](https://www.geeksforgeeks.org/async-await-function-in-javascript/)
- [How to Use Fetch to Make AJAX Calls in JavaScript](https://www.freecodecamp.org/news/how-to-use-fetch-api/)
- [Modal Bootstrap 5.3](https://getbootstrap.com/docs/5.3/components/modal/)

## Contributors

- Muhammad Athallah
- Muhammad Iqbal Dwitama
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.
