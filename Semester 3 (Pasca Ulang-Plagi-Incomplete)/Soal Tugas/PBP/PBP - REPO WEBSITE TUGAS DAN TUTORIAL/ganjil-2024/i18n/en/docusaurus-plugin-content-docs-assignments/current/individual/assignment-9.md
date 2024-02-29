---
sidebar_label: Assignment 9
sidebar_position: 9
Path: assignment/individual/assignment-9
---

# Assignment 9: Integrating Django Web Service with Flutter Application

Platform-Based Programming (CSGE602022) — organized by the Faculty of Computer Science, University of Indonesia, Odd Semester 2023/2024

---

## Assignment Description

In this assignment, you will integrate the Django services you have created in previous assignments with the Flutter application you created earlier.

The checklist for this assignment is as follows:

- [ ] Ensure the deployment of your Django project is running smoothly.
- [ ] Create a login page in the Flutter project.
- [ ] Integrate the Django authentication system with the Flutter project.
- [ ] Create a custom model according to your Django application project.
- [ ] Create a page containing a list of all items available at the `JSON` endpoint in Django that you have deployed.
    - [ ] Display the `name`, `amount`, and `description` of each item on this page.
- [ ] Create a detail page for each item listed on the Item list page.
    - [ ] This page can be accessed by tapping on any item on the Item list page.
    - [ ] Display all attributes of your item model on this page.
    - [ ] Add a button to return to the item list page.
- [ ] Answer the following questions in the `README.md` in the root folder (please modify the `README.md` you previously created; add subheadings for each assignment):
    - [ ] Can we retrieve JSON data without creating a model first? If yes, is it better than creating a model before retrieving JSON data?
    - [ ] Explain the function of CookieRequest and explain why a CookieRequest instance needs to be shared with all components in a Flutter application.
    - [ ] Explain the mechanism of fetching data from JSON until it can be displayed on Flutter.
    - [ ] Explain the authentication mechanism from entering account data on Flutter to Django authentication completion and the display of menus on Flutter.
    - [ ] List all the widgets you used in this assignment and explain their respective functions.
    - [ ] Explain how you implement the checklist above step by step! (not just following the tutorial).
- [ ] Perform `add`-`commit`-`push` to GitHub.

## Deadline

The deadline for Assignment 9 is **Wednesday, 22th November 2023, at 12:00 PM**.

The teaching assistants will check the last commit of the lab assignment repository, so you do not need to submit the repository link separately.

## Bonus 

You will get a bonus score in this assignment if you implement the following features.

- [ ] Implement an account registration feature in the Flutter application.
- [ ] Filter the item list page by only displaying items associated with the logged-in user.
