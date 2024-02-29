---
sidebar_label: Midterm Project
sidebar_position: 1
Path: assignment/individual/midterm
---

# Midterm Project

**Develop a Website using Django Web Framework (in groups)**

---

## Learning Objectives

1. Design web pages
2. Build a website using Django web framework that follows MTV (Model-Template-View) architectural pattern
3. Use a CSS framework or toolkit to implement Responsive Web Design
4. Write unit tests and deployment (bonus)

### Notes

In addition to the [learning objectives](#learning-objectives) above,
you must also learn several scholarly aspects expected from potential bachelor graduates.
Among those that are relevant in this course are grit, independence, rigor, and metacognition.
In other words, you are expected to have the ability to set learning strategies that suit oneself including planning, monitoring, and evaluating their independent learning process.
This includes the ability to understand, communicate problems, discuss, and ask questions.

You also need to have a positive attitude when dealing with various circumstances that may affect you in positive or negative way.
Although it may be difficult to be always in positive attitude,
try to strive for it so any circumstances can be beneficial in regards to achieving the previously mentioned scholarly aspects.

A negative attitude will only make things worse and eliminate the benefits of this project as a learning experience that will be useful in the future.
The teaching team (lecturers and teaching assistants) will try their best to assist you in the learning process so you can keep pace with the course and learn as much as possible.

As an intermezzo, there is a video (attached below) that is quite popular and hopefully can entice you to keep working hard and practising for your success in the future:

<iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/42-hh-iMJJI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe><br /><br />

Good luck and have a great time working on the project! 😄

## General Rules of the Project

1. Each group comprises of 4-5 person. Group members assignment can be seen on SCELE.
2. Each group should create a single Git repository on one GitHub [organization](https://docs.github.com/en/organizations/collaborating-with-groups-in-organizations/about-organizations) that all group members use to collaborate. Share the link to your group's Git repository through a submission slot on SCELE.
3. Each group may propose their own ideas for the application they are building. The theme for the application is **books and literacy**. This theme was chosen for three reasons:
    - To raise our awareness of the importance of literacy.
    - This theme aligns with the theme of the 12th Indonesian Language Congress, which will be held from October 25 to 28, 2023, titled "Literacy in Diversity for the Progress of the Nation." The Indonesian Language Congress is the highest forum discussing language and literary issues in Indonesia. The Indonesian Language Congress is held every five years by the Language Development and Fostering Agency, Ministry of Education, Culture, Research, and Technology. <https://kbi.kemdikbud.go.id/>.
    - This theme is in line with the commemoration of Language and Literature Month, which is commemorated every October in Indonesia to remember the history of the birth of Bahasa Indonesia.
4. Each group implements a book catalog in the form of class models and stores data from the dataset into the Django database. The book catalog dataset must contain a minimum of 100 books. The source of the book catalog dataset can come from anywhere, for example, from Project Gutenberg, Google Books API, Kaggle, and LONTAR Thesis Data."
    Here are some examples of dataset URLs that can be used:
    - Project Gutenberg: <https://www.gutenberg.org/ebooks/offline_catalogs.html>, <https://www.gutenberg.org/cache/epub/feeds/pg_catalog.csv>, and <https://drive.google.com/file/d/17jiAwHx_68zUrolbTl75IoLRFK_JLYrx/view>
    - [Google Books API](https://developers.google.com/books/)
    - [Kaggle](https://www.kaggle.com/datasets?search=book)
    - [LONTAR Thesis Data](https://univindonesia-my.sharepoint.com/:x:/g/personal/iisafriyanti_office_ui_ac_id/EY1Lmwm40rJLhtbWtKerNOYBI3BxiLSlDbLuL3mFIsw8wA?e=DL43jL)
5. Each group member should work individually on a module. The modules will be decided by the group during project planning in the group and should be suitable for implementing the project idea.
6. The midterm project is submitted as a unified web application.

## Specific Rules for Each Group Member

1. Implement _models_ by writing their own models, reusing models provided by Django, or using models developed by other group members in separate module.
2. Implement _views_ to handle incoming requests and produce HTML/JSON-based responses.
3. Implement HTML templates with a systematic and efficient structure, such as `base.html`, `header.html`, and `footer.html`.
4. Implement _templates_ using CSS framework/toolkit that assist in implementing responsive design (such as [Bootstrap](https://getbootstrap.com/) or [Tailwind](https://tailwindcss.com/)).
5. Implement a page with a form that accepts user input that shall be processed by views. Examples of processing by views include inserting data into the model, querying data from the model, and updating data in the model.
6. Implement client-side scripting and asynchronous data loading (AJAX) using JavaScript.
7. Implement authentication and authorisation on a certain part of the functionality implemented in the module. For example, a menu is only visible when the user has logged in and authorised to view the hidden menu.
8. Implement filters on the displayed book catalog dataset. For example, displaying a list of books based on the author's name.

## Project Timeline

<table>
    <tr>
        <th>Milestones and Deliverables</th>
        <th>Due Dates and Remarks</th>
    </tr>
    <tr>
        <td>
            <b>Milestone I (40%)</b>
            <ul>
                <li>A Git repository on GitHub for the group project</li>
                <li>A README.md in the group project repository that describes:</li>
                    <ol>
                        <li>Group name and members</li>
                        <li>The story of the application and its benefits</li>
                        <li>List of modules (or, features) that will be implemented</li>
                        <li>The source of the book catalog dataset</li>
                    </ol>
                <li>Defined the possible user roles in the app (for implementing role-based access control)</li>
            </ul>
        </td>
        <td>
            <b>Due Date: Wednesday, 11 October 2023, at 23:55 (11:55 P.M.) WIB.</b>
            <b>Submit the link </b> to your group project's GitHub page to the submission slot in SCELE.
        </td>
    </tr>
    <tr>
        <td>
            <b>Milestone II (60%)</b>
            <p>(Implemented feature modules)</p>
            <ul>
                <li>Integrated modules from each group member</li>
                <li>Implemented URL routing</li>
                <li>Implemented data models</li>
                <li>Implemented the Django's views</li>
                <li>Integrated as a unified web application.</li>
                <li>Delivered the features according to the proposed ideas and design documents</li>
            </ul>
        </td>
        <td>
            <b>Due Date: Sunday, 29 October 2023, at 23.55 (11:55 P.M.) WIB</b>
            <p><b>Submission Criteria:</b> All modules are integrated and can be used by user when using the Django Project</p>
        </td>
    </tr>
    <tr>
        <td>
            <b>Bonus (5%)</b>
            <ul>
                <li>Write unit tests that verify the implementation correctness, and ensure the overall code coverage of the project reached at least 80%</li>
                <li>Implement continuous integration (CI) and continuous deployment (CD) using GitHub Actions.</li>
                <li>Display the status badges of the GitHub Actions in README.md. For example: status badge that reports whether the app is successfully deployed by GitHub Actions, status badge that reports code coverage</li>
            </ul>
        </td>
        <td></td>
    </tr>
</table>
