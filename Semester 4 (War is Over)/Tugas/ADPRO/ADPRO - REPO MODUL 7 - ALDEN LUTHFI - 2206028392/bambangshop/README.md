# BambangShop Publisher App
Tutorial and Example for Advanced Programming 2024 - Faculty of Computer Science, Universitas Indonesia

---

## About this Project
In this repository, we have provided you a REST (REpresentational State Transfer) API project using Rocket web framework.

This project consists of four modules:
1.  `controller`: this module contains handler functions used to receive request and send responses.
    In Model-View-Controller (MVC) pattern, this is the Controller part.
2.  `model`: this module contains structs that serve as data containers.
    In MVC pattern, this is the Model part.
3.  `service`: this module contains structs with business logic methods.
    In MVC pattern, this is also the Model part.
4.  `repository`: this module contains structs that serve as databases and methods to access the databases.
    You can use methods of the struct to get list of objects, or operating an object (create, read, update, delete).

This repository provides a basic functionality that makes BambangShop work: ability to create, read, and delete `Product`s.
This repository already contains a functioning `Product` model, repository, service, and controllers that you can try right away.

As this is an Observer Design Pattern tutorial repository, you need to implement another feature: `Notification`.
This feature will notify creation, promotion, and deletion of a product, to external subscribers that are interested of a certain product type.
The subscribers are another Rocket instances, so the notification will be sent using HTTP POST request to each subscriber's `receive notification` address.

## API Documentations

You can download the Postman Collection JSON here: https://ristek.link/AdvProgWeek7Postman

After you download the Postman Collection, you can try the endpoints inside "BambangShop Publisher" folder.
This Postman collection also contains endpoints that you need to implement later on (the `Notification` feature).

Postman is an installable client that you can use to test web endpoints using HTTP request.
You can also make automated functional testing scripts for REST API projects using this client.
You can install Postman via this website: https://www.postman.com/downloads/

## How to Run in Development Environment
1.  Set up environment variables first by creating `.env` file.
    Here is the example of `.env` file:
    ```bash
    APP_INSTANCE_ROOT_URL="http://localhost:8000"
    ```
    Here are the details of each environment variable:
    | variable              | type   | description                                                |
    |-----------------------|--------|------------------------------------------------------------|
    | APP_INSTANCE_ROOT_URL | string | URL address where this publisher instance can be accessed. |
2.  Use `cargo run` to run this app.
    (You might want to use `cargo check` if you only need to verify your work without running the app.)

## Mandatory Checklists (Publisher)
-   [x] Clone https://gitlab.com/ichlaffterlalu/bambangshop to a new repository.
-   **STAGE 1: Implement models and repositories**
    -   [x] Commit: `Create Subscriber model struct.`
    -   [x] Commit: `Create Notification model struct.`
    -   [x] Commit: `Create Subscriber database and Subscriber repository struct skeleton.`
    -   [x] Commit: `Implement add function in Subscriber repository.`
    -   [x] Commit: `Implement list_all function in Subscriber repository.`
    -   [x] Commit: `Implement delete function in Subscriber repository.`
    -   [x] Write answers of your learning module's "Reflection Publisher-1" questions in this README.
-   **STAGE 2: Implement services and controllers**
    -   [x] Commit: `Create Notification service struct skeleton.`
    -   [x] Commit: `Implement subscribe function in Notification service.`
    -   [x] Commit: `Implement subscribe function in Notification controller.`
    -   [x] Commit: `Implement unsubscribe function in Notification service.`
    -   [x] Commit: `Implement unsubscribe function in Notification controller.`
    -   [x] Write answers of your learning module's "Reflection Publisher-2" questions in this README.
-   **STAGE 3: Implement notification mechanism**
    -   [x] Commit: `Implement update method in Subscriber model to send notification HTTP requests.`
    -   [x] Commit: `Implement notify function in Notification service to notify each Subscriber.`
    -   [x] Commit: `Implement publish function in Program service and Program controller.`
    -   [x] Commit: `Edit Product service methods to call notify after create/delete.`
    -   [x] Write answers of your learning module's "Reflection Publisher-3" questions in this README.

## Your Reflections
This is the place for you to write reflections:

### Mandatory (Publisher) Reflections

#### Reflection Publisher-1

Based on the usage of the observer design pattern, if we have observers with many types and various classes, then using traits to create observers is the right step. However, in the case of BambangShop, its observer, namely Subscriber, consists of only one class. Therefore, there is currently no need to use traits unless there will be the addition of new observers in the future.

Using DashMap is more suitable than HashMap because DashMap provides direct mapping between the type of product and the subscriber who needs it. By using DashMap, we can avoid the need to create two separate vectors to store product URLs and subscribers, which would complicate data management.

DashMap is chosen over HashMap because DashMap is a built-in data structure suitable for multithreading. In the context of BambangShop using multithreading, this is important because the SUBSCRIBER Map will be accessed by many threads. Singleton usage aims to ensure that there is only one instance of that object while the program is running. This helps ensure that the list of subscribers to our products is consolidated into one DashMap, avoiding dispersion in various data structures.

#### Reflection Publisher-2

Separating the Service from the Repository is important to fulfill the single responsibility principle. The Service is responsible for fetching and processing data from the Repository, while the Repository is responsible for accessing and managing the database. This helps in code development and maintenance, avoiding high coupling and enabling easier changes.

The use of Postman is also very useful for testing applications and ensuring that the response generated matches expectations based on the requests made. Postman allows customization of methods such as CRUD, making it easier to check the accuracy of data retrieved through the application.

#### Reflection Publisher-3

In this tutorial, there is a discussion about the push model used. When changes occur in objects such as creation, deletion, or updating, the notification service will call a method that will send updates to all its subscribers.

However, in the NotificationService code, when it needs to notify each subscriber, there may be a long queue if there are many subscribers. This can hinder notification delivery due to computational limitations.

On the other hand, if the pull method is used, each subscriber must actively determine whether the changes in data are relevant to them. The advantage is that subscribers have the freedom to determine what data they fetch and when they do it. However, the downside is that subscribers need to have knowledge of the data source's structure to do this.
