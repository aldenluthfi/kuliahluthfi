---
sidebar_label: Assignment 6
sidebar_position: 6
Path: assignment/individual/assignment-6
---

# Assignment 6: JavaScript and Asynchronous JavaScript

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Assignment Description

In this assignment, you have to implement AJAX in the application that you created in the previous assignment.

Checklist for this assignment is as follows:

- [ ] Modify the previously created assignment 5 to use AJAX.
  - [ ] AJAX GET
    - [ ] Modify the codes in data cards to able to use AJAX GET.
    - [ ] Retrieve data using AJAX GET.
  - [ ] AJAX POST
    - [ ] Create a button that opens a modal with a form for adding an item.

        > The modal is triggered by clicking a button on the main page. When adding an item successfully, the modal should be closed, and the form input should be cleared from the data entered before. The behavior of the modal must meet these requirements.

    - [ ] Create a new view function to add a new item to the database.
    - [ ] Create a `/create-ajax/` path that points to the new view function you created.
    - [ ] Connect the form you created inside the modal to the `/create-ajax/` path.
    - [ ] Perform asynchronous refresh on the main page to display the latest item list without reloading the entire main page.
  - [ ] Run the `collectstatic` command.
    - This command is used to collect static files from each of your applications into a folder that can be easily served in production.
- [ ] Answer the following questions in `README.md` in the root folder (please modify the `README.md` you have created; add subheadings for each task).
    - Explain the difference between **asynchronous programming** and **synchronous programming**.
    - In the implementation of JavaScript and AJAX, there is an implemented paradigm called **the event-driven programming paradigm**. Explain what this paradigm means and give one example of its implementation in this assignment.
    - Explain the implementation of asynchronous programming in AJAX.
    - In this semester, the implementation of AJAX is done using the Fetch API rather than the jQuery library. Compare the two technologies and write down your opinion which technology is better to use.
    - Explain how you implemented the checklist above step-by-step (not just following the tutorial).

- [ ] Perform `add`-`commit`-`push` to GitHub.

- [ ] Deploy your app to PaaS PBP Fasilkom UI and include the deployment link in `README.md`.

    - DOKKU_APP_NAME = `UsernameSSO-tugas`

      > Also change the period to a dash. Example: `muhammad-athallah01-tugas`.

## Deadline

The deadline for Assignment 6 is **Friday, 13th October 2023, at 12:00 p.m.**

The teaching assistants will check the last commit of the lab assignment repository, so you do not need to submit the repository link separately.

## Bonus

You will gain bonus points for this assignment if you implement the following feature.

- [ ] Add delete function using AJAX DELETE
