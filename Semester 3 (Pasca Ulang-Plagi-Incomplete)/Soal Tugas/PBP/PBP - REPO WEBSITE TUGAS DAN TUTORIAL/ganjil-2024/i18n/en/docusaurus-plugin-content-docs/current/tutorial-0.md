---
sidebar_label: Tutorial 0
sidebar_position: 2
Path: docs/tutorial-0
---

# Tutorial 0: Configuring and Installing Git and Django

Platform-Based Programming (CSGE602022) — Organized by the Faculty of Computer Science Universitas Indonesia, Odd Semester 2023/2024

---

## Learning Objectives

After completing this tutorial, students are expected to be able to:

- Understand basic Git commands necessary for working on an application project.
- Use basic Git commands necessary for working on an application project.
- Create Git repositories, both local and online on GitHub.
- Create a remote between local and online repositories.
- Understand Git branching and able to perform merge request/pull request.
- Create a Django application locally.
- Deploy a Django application on Adaptable.io.

## Tutorial: Creating a GitHub Account

### Introduction to Git and GitHub

This overview will help you to understand Git and GitHub.

#### Git: A Strong Version Controller System

- **Git** is a version control system that helps track changes made in a project's source code.
- With Git, you can monitor all revision history in a project.

#### GitHub: Git-Based Collaborating Platform

- **GitHub** is a web-based platform that allows you to store, manage, and collaborate on projects that utilize Git.
- GitHub provides a safe container to host your projects and interact with other team members.

#### Why It Matters?

- Git and GitHub are crucial in modern software development and team collaboration.
- Both Git and Github allow team to track code changes, save versions, and work together on a project efficiently.

With a basic understanding of Git and GitHub, you are ready to step further in a structured and collaborative software development world.

### Tutorial: Creating a GitHub Account

With a GitHub account, you can start collaborating on projects that utilize Git.

1. Go to the GitHub Website

    - Open your browser and go to [GitHub].

2. Create an Account

    - On GitHub homepage, find the **`Sign up`** button in the upper left corner.
    - Click the button to start the registration process.

3. Fill Out the Registration Form

   - Fill out the registration form with the necessary information, such as desired username, valid email, and a secure password.
   - Make sure to save this information securely to access your account in the future.

4. Verify Account Through Email

   - After completing the form, GitHub will send a verification email to the provided email.
   - Open the email and follow the instructions to verify your account.

5. GitHub Account Ready to Use

    - After verification is finished, your GitHub account is ready to be used for collaborating on projects and tracking changes using Git.

**Notes:**

- GitHub account is a gateway to collaborate on projects and store your project in this platform.
- Make sure the registration information you provide is accurate and safe.

#### Congratulations, You Have Created A GitHub Account!

Now you have a GitHub account capable of storing projects, collaborating with others, and more.

## Tutorial: IDE Installation

IDE (Integrated Development Environment) is a software used by developers to write, edit, and manage code. Here are the steps to install an IDE.

### Step 1: Choosing a Text Editor or an IDE

Choose a text editor or an IDE that matches your preferences. Some popular choices are:

- [Vim](http://www.vim.org/download.php)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Sublime Text](https://www.sublimetext.com/)
- [PyCharm](https://www.jetbrains.com/pycharm/)


### Step 2: Installation Process

1. Go to the official website of your chosen IDE.
2. Follow the given steps to download the IDE installer.
3. Run the installer and follow the instructions to comple the installation process.

### Step 3: Using the IDE

1. After the installation process is complete, open the installed IDE.
2. Explore the interface and features provided by the IDE to assist you in project development.

**Notes:**

- Make sure you choose an IDE suitable for the project you are working on.
- Do not hesitate to explore the features provided by the IDE (ex: extensions and plugins) and utilize supporting resources such as documentation and tutorials to increase your productivity in software development.

## Tutorial: Installing and Configuring Git

### Step 1: Installing Git

If Git is not installed on your system yet, you can follow these steps to install it.

1. Go to the [Git](https://git-scm.com/downloads) official website.
2. Select the appropriate operating system (Windows, macOS, or Linux) and download the installer.
3. Run the downloaded installer and follow the steps to complete the installation process.

### Step 2: Git Initial Configuration

After Git is downloaded, these steps will help you setting up the initial configuration before using Git.

1. Open the terminal or command prompt on your system.
2. Change to the desired directory for storing Git projects using the command `cd <directory_name>`.
3. Initialize a new repository with the command `git init`. This command will create an empty Git repository inside your chosen directory.

### Step 3: Configuring Username and Email

Before you start contributing to a repository, you need to configure a username and email that will be associated with your commits.

Configure a username and email that will be associated with your contributions in this repository. Make sure to use the same username and email that you have on your [GitHub] account.

```bash
git config user.name "<NAME>"
git config user.email "<EMAIL>"
```

Example:

```bash
git config user.name "pakbepe"
git config user.email "pak.bepe@cs.ui.ac.id"
```

### Step 4: Global Configuration (Optional)

If you want to apply a configuration to all local repository, use the `--global` flag on the command `git config`. However, keep in mind that this will change all global configuration in your system.

For global username and email configuration, run these commands.

```bash
git config --global user.name "<NAME>"
git config --global user.email "<EMAIL>"
```

Example:

```bash
git config --global user.name "pakbepe"
git config --global user.email "pak.bepe@cs.ui.ac.id"
```

### Step 5: Verifying Configuration

To verify that your local configuration has been correctly set up in your local repository, you can use these commands.

```bash
git config --list --local
```

**Notes:**

- Make sure to change `<NAME>` and `<EMAIL>` with your personal information.
- Use the aforementioned steps as a guide to configure Git according to your preferences.

## Tutorial: Basic Usage of Git

A repository (repo) is a storage location for software projects, which includes all the revisions and changes that have been made to the project. To execute Git commands, you can perform them in repositories on GitHub, a collaborative platform for managing projects using Git.

### Step 1: Initiate Repository in GitHub

The first step in using Git is to initiate a new repository on GitHub to start tracking changes in your project.

1. Open [GitHub] on your browser.

2. Create new repository

   - On the GitHub homepage, create a new repository with the name `my-first-repo`.
   - Open the newly created repository. Make sure to set project visibility as `Public` and leave all the other options to `default`.

3. Specify local directory

   - Choose a local directory on your computer that has been initialized with Git. This is where you will store the local versions of the project.

4. Add `README.md`

   - Create a new file called `README.md` in your local project directory.
   - Fill in the `README.md` file with information such as your name, NPM and class. For example:

        ```md
        Name       : Kevin Ivan Eda Radhiyya

        StudentID  : 2201233210

        Class      : PBP B
        ```

5. Check Status and Perform Tracking

   - Open the command prompt or terminal, then run `git status`. This command will display files that have not been tracked yet.
   - Use the command `git add README.md` to mark the README.md as to be commited (tracked).

6. Commit Changes
   - Run `git status` again and make sure README.md has been tracked as to be commited
   - Proceed with `git commit -m "<YOUR COMMENT>"` to create a commit with a comment messages relevant to the changes you've made.
   
**Notes:**

- This step will get you ready to start tracking changes to your project using Git.
- A **good practice** in providing commit messages is to briefly explain what you have done to the project.
- Good commit messages help you and your team members to understand the purpose of the changes.
- **Avoid** messages that are too general or ambiguous, such as `Fixed bug` or `Update file`.

### Step 2: Connect Local Repository to the Remote Repository on GitHub

After initializing the local repository, the next step is to connect it to the remote repository on GitHub so you can start collaborating and saving changes on the online platform.

1. Create new main Branch

   - On terminal or Command Prompt, run `git branch -M main` to create new main branch with the name "main".
   - Make sure the letter "M" in the `-M` command is typed in **uppercase**.

2. Connect to the GitHub Repository

   - Use the command `git remote add origin <URL_REPO>` to connect the local repository with the repository on GitHub.
   - Replace ``<URL_REPO>`` with the HTTPS URL of the repository you have created on GitHub. For example:

       ```bash
       git remote add origin https://github.com/pakbepe/test.git
       ```

3. Make a First Push to GitHub

   - Lastly, perform the initial push to GitHub by running the command `git push -u origin main`.
   - This command will push all the changes made in the current branch (which is the main branch in this case) on the local repository to the main branch on the GitHub repository.

4. Double Check

   - Refresh your page, can you see the README.md file?

**Notes:**

- This step is important to maintain consistency between the local repository and the repository on GitHub.
- This process allows you to start collaborating and save project changes in a structured way on the GitHub.

### Step 3: Cloning a Repository

**Cloning a repository** is the process of duplicating all the content of a repository from the GitHub to your local computer. To clone a repository, perform these steps:

1. Open the [GitHub] page of the repository you've created earlier.

2. Copy the Clone URL

   - Click the **`Code`** button in the upper right corner of the GitHub repository page.
   - Choose the HTTPS option to copy the clone URL.

3. Clone the Repository to Your Local Computer

   - Open the Command Prompt or terminal in a **different** directory from where your local repository is.
   - Run the command `git clone <URL_CLONE>` (replace `<CLONE_URL>` with the URL you copied).
   - This command will duplicate the entire repository to your local computer.
   
Currently, you have three repositories:

1. The **original local repository** in your computer.
2. The **remote repository** on GitHub connected to the local repository.
3. The **new local repository from the cloning process**, which is also connected to the GitHub repository.

**Note:**

- This step allows you to work with repositories in various locations easily.

### Step 4: Push to a Repository

As mentioned earlier (Step 2), **push** is the process of sending the changes you've made in your local repository to the GitHub repository. The steps are as follows.

1. Open the **original local repository** that you created in the first step.

2. Modify the content of the README.md file by adding your hobby. For example:

    ```md
    Name       : Kevin Ivan Eda Radhiyya

    StudentID  : 2201233210

    Class      : PBP B

    Hobby      : Sleeping
    ```

3. Push to the GitHub Repository

   - Run `git status` to see the state of current changes.
   - Run `git add README.md` to stage the changes for commit.
   - Commit the changes with `git commit -m "<YOUR COMMENT>"` to provide a short-but-consice description of the changes.
   - Lastly, run `git push -u origin <BRANCH_NAME>` to upload the changes to the selected branch on the GitHub repository (replace "Branch Name" with the target branch, such as main).

4. Double Check

   - Refresh the page, can you see the changes in the README.md file?

**Note**: To add all unstaged changes **from the entire project directory**, use `git add .`

### Step 5: Pull from a Repository

**Pulling** from a repository is the process of fetching the latest changes from the GitHub repository and merging them into your local repository.

1. Open the **cloned repository**.

2. Run Pull command

   - In the Command Prompt or terminal, run `git pull origin main` to fetch the latest changes from the GitHub repository and merge them into your local repository.

3. Double Check

   - Check the README.md file in the repository. Does it include your hobby?

**Notes:**

- This step ensures that your local repository is always updated and synchronized with the latest changes from the GitHub repository.
- Regularly doing pulls is important to avoid conflicts and ensure you are working with the latest version of the project.

### Step 6: Branching on a Repository

As previously mentioned, in this step you will learn about the use of branches in Git, which allow you to develop features or fix bugs in separate environments before merging them back into the main branch.

Before you start, make sure you have already executed the command `git branch -M main` to create a new main branch named `main`.

**What is a Branch in Git?**

- A branch in Git is a separate source code that allows independent development of features or changes.
- It allows teams to work on features or bug fixes without interfering the code in the main branch.

1. Creating and Switching to a New Branch

    - In the **original local repository directory** (not clone), use the command `git checkout -b <BRANCH_NAME>` in the terminal or Command Prompt to create and switch to the new branch. For example: `git checkout -b major_branch`
    - Add the major attribute to the README.md file. For example:

        ```md
        Name       : Kevin Ivan Eda Radhiyya

        StudentID  : 2201233210

        Class      : PBP B

        Hobby      : Sleeping

        Major      : Computer Information Systems Science International Class
        ```

2. Save Changes and Push to GitHub

     - After adding the major attribute, save the file.
     - Perform `add`, `commit`, and `push` to GitHub using the commands you've learned before.
     - When pushing, don't forget to replace `<BRANCH_NAME>` with the name of the newly created branch.

3. Merging a Branch Using a Pull Request

    - Open the GitHub repository page.
    - Automatically, a pop-up with the **`Compare & pull request`** button will appear. If not, press the **`Pull Request`** button and then select **`Pull Request`**.
    - GitHub will then compare the changes in both branches you want to merge.
    - If there are no conflicts, press the **`Merge pull request`** button, which will merge the changes from the branch you want to merge into the main branch **`main`**.
    - By following the steps above, all changes from both branch will be integrated into the main branch, creating a cohesion between the changes.

**Notes:**

- If you want to switch between existing branches, run `git checkout <NAME_BRANCH>` in terminal. (The `-b` flag is used to create a new branch and switch to it in one step)
- **Conflict** occurs when changes made on one branch collide with changes made on another branch. For example, if two developers change the same parts of the same file at the same time, Git cannot automatically decide which changes to apply.
- If there are conflicts or conflicting changes, the platform will prompt you to determine which changes should be taken.
- **It's important** to understand the concept of branching in Git because it allows **organized and separate development** before all the changes are combined back into the main code.

## Tutorial: Installing Django and Initializing a Django Project

**Django** is a popular framework for web application development using the Python programming language. In this tutorial, you will learn how to install Django and initialize a demo project as a starter.

### Step 1: Creating a Directory and Activating a Virtual Environment

1. Create a new directory named `shopping_list` and navigate into it.
2. Inside the directory, open Command Prompt (Windows) or terminal shell (UNIX).
3. Create a virtual environment by running the following command.

    ```bash
    python -m venv env
    ```
    
4. This **virtual environment** is used to isolate the package and dependencies of the application, preventing conflicts with other versions on your computer. You can activate the virtual environment with the following command.

    - Windows:

        ```bash
        env\Scripts\activate.bat
        ```

    - UNIX (Mac/Linux):

        ```bash
        source env/bin/activate
        ```
        
5. The virtual environment will be activated, indicated by `(env)` in the terminal prompt.

### Step 2: Setting Up Dependencies and Creating a Django Project

**Dependencies** are components or modules required by the software to function, including libraries, frameworks, or packages. They allow developers to leverage existing code, accelerating development, but also require careful management to ensure proper version compatibility. Using a virtual environment helps isolate dependencies between different projects.

1. In the same directory, create a file named `requirements.txt` and add some dependencies.

    ```text
    django
    gunicorn
    whitenoise
    psycopg2-binary
    requests
    urllib3
    ```
    
2. Install the dependencies with the following command. Remember to activate the virtual environment before running the command.

    ```bash
    pip install -r requirements.txt
    ```
    
3. Create a Django project named `shopping_list` using the following command.

    ```bash
    django-admin startproject shopping_list .
    ```
    
    > Make sure to include the `.` character at the end of the command

### Step 3: Configuring the Project and Running the Server

1. Add `*` to `ALLOWED_HOSTS` in `settings.py` for deployment purposes:
    
    ```python
    ...
    ALLOWED_HOSTS = ["*"]
    ...
    ```
    
    In a deployment context, `ALLOWED_HOSTS` acts as a list of hosts allowed to access the web application. By setting the value to `["*"]`, you allow access from **any** host, which will make the application accessible widely. However, remember to use this setting wisely and only in specific situations, such as testing or early development stages.

2. Ensure that `manage.py` file is in the active directory in your shell. Run the Django server with the command:

    - Windows:

        ```bash
        python manage.py runserver
        ```

   - UNIX:

        ```bash
       ./manage.py runserver
        ```
        
3. Open <http://localhost:8000> in your web browser to see rocket animation indicating that your Django application has been successfully created.
  
### Step 4: Stopping the Server and Deactivating the Virtual Environment

1. To stop the server, press `Ctrl+C` (Windows/Linux) or `Control+C` (Mac) in the shell.
2. Deactivate the virtual environment with the following command:

    ```bash
    deactivate
    ```
    
    Congratulations! You've successfully created a Django application from scratch.

## Tutorial: Uploading the Project to GitHub Repository

1. Create a new GitHub repository named `shopping-list` with public visibility.

2. Initialize the `shopping_list` directory as a Git repository.

    > *Hint: Recall the previous tutorial*
    
3. Add a `.gitignore` file

    - Create a `.gitignore` file and fill it with the following content.
    
        ```yaml
        # Django
        *.log
        *.pot
        *.pyc
        __pycache__
        db.sqlite3
        media

        # Backup files
        *.bak 

        # If you are using PyCharm
        # User-specific stuff
        .idea/**/workspace.xml
        .idea/**/tasks.xml
        .idea/**/usage.statistics.xml
        .idea/**/dictionaries
        .idea/**/shelf

        # AWS User-specific
        .idea/**/aws.xml

        # Generated files
        .idea/**/contentModel.xml

        # Sensitive or high-churn files
        .idea/**/dataSources/
        .idea/**/dataSources.ids
        .idea/**/dataSources.local.xml
        .idea/**/sqlDataSources.xml
        .idea/**/dynamic.xml
        .idea/**/uiDesigner.xml
        .idea/**/dbnavigator.xml

        # Gradle
        .idea/**/gradle.xml
        .idea/**/libraries

        # File-based project format
        *.iws

        # IntelliJ
        out/

        # JIRA plugin
        atlassian-ide-plugin.xml

        # Python
        *.py[cod] 
        *$py.class 

        # Distribution / packaging 
        .Python build/ 
        develop-eggs/ 
        dist/ 
        downloads/ 
        eggs/ 
        .eggs/ 
        lib/ 
        lib64/ 
        parts/ 
        sdist/ 
        var/ 
        wheels/ 
        *.egg-info/ 
        .installed.cfg 
        *.egg 
        *.manifest 
        *.spec 

        # Installer logs 
        pip-log.txt 
        pip-delete-this-directory.txt 

        # Unit test / coverage reports 
        htmlcov/ 
        .tox/ 
        .coverage 
        .coverage.* 
        .cache 
        .pytest_cache/ 
        nosetests.xml 
        coverage.xml 
        *.cover 
        .hypothesis/ 

        # Jupyter Notebook 
        .ipynb_checkpoints 

        # pyenv 
        .python-version 

        # celery 
        celerybeat-schedule.* 

        # SageMath parsed files 
        *.sage.py 

        # Environments 
        .env 
        .venv 
        env/ 
        venv/ 
        ENV/ 
        env.bak/ 
        venv.bak/ 

        # mkdocs documentation 
        /site 

        # mypy 
        .mypy_cache/ 

        # Sublime Text
        *.tmlanguage.cache 
        *.tmPreferences.cache 
        *.stTheme.cache 
        *.sublime-workspace 
        *.sublime-project 

        # sftp configuration file 
        sftp-config.json 

        # Package control specific files Package 
        Control.last-run 
        Control.ca-list 
        Control.ca-bundle 
        Control.system-ca-bundle 
        GitHub.sublime-settings 

        # Visual Studio Code
        .vscode/* 
        !.vscode/settings.json 
        !.vscode/tasks.json 
        !.vscode/launch.json 
        !.vscode/extensions.json 
        .history
        ```
        
    - The `.gitignore` file is a configuration file used in a Git repository to specify files and directories that should be ignored by Git.
    - The files listed in `.gitignore` will **not** be included in Git version control.
    - This file is important because there are files that don't need to be tracked by Git, such as files generated by compilation processes, temporary files, or personal configuration files.

4. Perform `add`, `commit`, and `push` from the local repository directory.

**Notes**

- The **`shopping_list`** repository you've just created will be the foundation for the upcoming tutorials. This repository will be continuously used and developed throughout the tutorials.
- By the end of the semester, you'll see that this tutorial repository has evolved into a complete application created by you. So, feel free to include it in your portfolio!
- Therefore, make sure to manage this repository well and follow the subsequent tutorials to further develop your programming skills.


## Tutorial: Creating an Account and Deploying to Adaptable.io

1. Create an account on [Adaptable.io] using the same GitHub account you used to create the `shopping_list` project.
2. Once logged in, press the `New App` button. Choose `Connect an Existing Repository`.
3. Connect [Adaptable.io] with GitHub and select `All Repositories` during the installation process.
4. Choose the `shopping_list` project repository as the basis for the application to be deployed. Select the branch you want to use as the deployment branch.
5. Choose `Python App Template` as the deployment template.
6. Select `PostgreSQL` as the database type to be used.
7. Adjust the Python version according to your application's specifications. To check, activate the virtual environment and run the command `python --version`.
8. In the `Start Command` section, enter the command `python manage.py migrate && gunicorn shopping_list.wsgi`.
9. Enter the application name, which will also be the website's domain name.
10. Check the `HTTP Listener on PORT` option and click `Deploy App` to initiate the application deployment process.

## Closing

Congratulations! You've completed tutorials on using Git, GitHub, setting up an IDE, developing a project with Django, and deploying to Adaptable.io. 

In the future, if you're working on labs, don't be overwhelmed by the amount of material. Take it easy; it's not a sprint. Just go at your own pace, and you'll make it. You don't need to memorize all the code right away, but it's important to understand it, right? So, **don't just copy-paste without understanding**; you might get confused later on. If you get stuck, don't hesitate to ask your friends or teaching assistants. Teaching assistants are always ready to help. So, keep your spirits up and enjoy the process. Good luck!

## Additional References

- [More About Hosting](https://determinedguy.github.io/cecoret/hosting-alternatives/)
- [About pull request merges](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/incorporating-changes-from-a-pull-request/about-pull-request-merges)
- [Resolving a merge conflict on GitHub](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/addressing-merge-conflicts/resolving-a-merge-conflict-on-github)

## Contributors

- Kevin Alexander
- Ivan Rabbani Cezeliano
- Fariz Eda
- Dafi Nafidz Radhiyya
- Aidah Novallia Putri (EN Translator)
- Bonaventura Galang (EN Translator)
- Ferry (EN Translator)

## Credits

This tutorial was developed based on [PBP Odd 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) and [PBP Even 2023](https://github.com/pbp-fasilkom-ui/genap-2023) written by the 2023 Platform-Based Programming Teaching Team. All tutorials and instructions included in this repository are designed so that students who are taking Platform-Based Programming courses can complete the tutorials during lab sessions.

[GitHub]: https://github.com/
[Adaptable.io]: https://adaptable.io/
