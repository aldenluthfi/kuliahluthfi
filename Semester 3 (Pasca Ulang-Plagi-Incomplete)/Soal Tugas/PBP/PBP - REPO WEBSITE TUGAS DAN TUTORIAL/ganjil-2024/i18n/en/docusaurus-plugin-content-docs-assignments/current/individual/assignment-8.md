---
sidebar_label: Assignment 8
sidebar_position: 8
Path: assignment/individual/assignment-8
---

# Assignment 8: Flutter Navigation, Layouts, Forms, and Input Elements

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Assignment Description

In this assignment, you will implement navigation, layouts, forms, and form input elements in the Flutter application you created in the previous assignment.

Checklist for this assignment is as follows:

- [ ] Create at least one new page in the application, a page for adding a new item with the following requirements:
    - [ ] Use at least three input elements: `name`, `amount`, `description`. Add input elements according to the model in your Django assignment.
    - [ ] Have a `Save` button.
    - [ ] Validate each input element in the form with the following requirements:
        - [ ] Each input element must not be empty.
        - [ ] Each input element must contain data of the same data type as its model attribute.
    - [ ] Direct users to the new item addition form page when clicking the `Add Item` button on the main page.
    - [ ] Display data as entered in the form in a pop-up after clicking the `Save` button on the new item addition page.
    - [ ] Create a drawer in the application with the following requirements:
        - [ ] The drawer must have at least two options: `Home` and `Add Item`.
        - [ ] When choosing the `Home` option, the application will direct the user to the main page.
        - [ ]  When choosing the `Add Item` option, the application will direct the user to the new item addition form page.
    - [ ] Answer the following questions in the `README.md` in the root folder (please modify the `README.md` you previously created; add subheadings for each assignment):
        - [ ] Explain the difference between `Navigator.push()` and `Navigator.pushReplacement()`, accompanied by examples of the correct usage of both methods!
        - [ ] Explain each layout widget in Flutter and their respective usage contexts!
        - [ ] List the form input elements you used in this assignment and explain why you used these input elements!
        - [ ] How is clean architecture implemented in a Flutter application?
        - [ ] Explain how you implemented the *checklist* above step-by-step! (not just following the tutorial)
    - [ ] Perform `add`-`commit`-`push` to GitHub.

## Deadline

The deadline for Assignment 8 is **Wednesday, 15th November 2023, at 12:00 p.m**.

The teaching assistants will check the last commit of the lab assignment repository, so you do not need to submit the repository link separately.

## Bonus

You will gain bonus points for this assignment if you implement the following feature.

- [ ] Create a new page, a product list page, with the contents being every product data that has been created.

    > You can use the model objects to implement this feature.

- [ ] Direct users to this page when they click the View Products button on the main page or in the drawer.
