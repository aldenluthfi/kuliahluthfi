---
sidebar_label: Assignment 2
sidebar_position: 1
Path: assignment/individual/assignment-2
---

# Assignment 2: Model-View-Template (MVT) Implementation on Django

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

## Assignment Description

In this assignment, you have to implement the Model-View-Template concept, alongside other things that you have learned in class and tutorial. Please note that the project created in this assignment is **different** from the project used in the tutorial.

## Application Theme

The main theme for this semester's PBP assignment is **inventory management application**. This semester, you are free to choose the name and the subtheme for your application, while ensuring your application's model fulfills these attributes:

- `name` as the name of the item, with type `CharField`.
- `amount` as the amount/count of the item, with type `IntegerField`.
- `description` as the description of the item, with type `TextField`.

Feel free to add other attributes to your model (such as `price`, `power`, `category`, etc.), as long as the three mandatory attributes (`name`, `amount`, `description`) are present.

Some app ideas for your management app are:

- Store product stock management.
- Character's inventory and/or item in a game.
- Trading card collection management.
- Library collection management.

## Assignment Checklist

The checklist for this assignment are:

- [ ] Create a new Django project.
- [ ] Create an app with the name `main` on that project.
- [ ] Create a URL routing configuration to access the `main` app.
- [ ] Create a model on the `main` app with name `Item` and these mandatory attributes:
  - `name` as the name of the item, with type `CharField`.
  - `amount` as the amount/count of the item, with type `IntegerField`.
  - `description` as the description of the item, with type `TextField`.
- [ ] Create a function in `views.py` that returns an HTML template containing your application name, your name, and your class.
- [ ] Create a routing in `urls.py` to map the function in`views.py` to an URL.
- [ ] Deploy your app to Adaptable so it can be accessed through the internet.
- [ ] Create a `README.md` that contains a URL to access your deployed app and answers to these questions:
  - How do you implement the tasks in the checklist? Explain in a step-by-step manner (not just copy-paste from the tutorial).
  - Create a diagram explaining the flow of client requests to a Django web app and its response. Also in the diagram, explain the connections between `urls.py`, `views.py`, `models.py`, and the `HTML` file(s).
  - What is the purpose of a **virtual environment**? Can we create a Django web app without a **virtual environment**?
  - What is MVC, MVT, and MVVM? Explain the differences between the three.

Note that to do this assignment, you must **create a new repository** and do your work in the new repository.

## Deadline

The deadline for Assignment 2 is **Wednesday, 13th September 2023, at 12:00 p.m.**

## Bonus

You will gain bonus points for this assignment if you successfully implement and demonstrate basic testing that **differs from the testing taught in the tutorial**. Therefore, you will not gain bonus points if you only implement tests for URL and template checking.

> Hint: Read [this documentation](https://docs.djangoproject.com/en/4.2/topics/testing/) or other sources to learn how to create tests in Django.
