# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### Refleksi 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Clean code principles that I have implemented are: Meaningful Names, One Function One Task, DRY (Don't Repeat Yourself), Consistent Code and Error Handling while security practices that I have implemented are: I/O Validation and Dependency Management. Currently there is little to be improved in my code in terms format, though the logic of the code can be improved.

### Refleksi 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

There should be one unit test covering a specific area in the code. 100% code coverage doesn't guarantee your code is 100% bug free. tests are there to ensure that the end consumer of your project doesn't get silly error that should be avoidable. there might be edge cases where our code fails that tests don't cover.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

one test should only have one functionality in one area of the code, but creating a new class means repeating the same boilerplate that is in other test classes, this violates DRY. we can also use inheritance to reduce redundancy. Java is a language that is notorious for boilerplate. Therefore, there is little to solve when it comes to code duplication.

> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

There is surprisingly little issue in my code. There are some modifiers here and there that should've been removed. All in all they're mostly code smells and inconsistencies. I have been very careful when I write code. There is still some smelly code though, I haven't been able to fix them yet.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

I have implemented thorough tests and checks and also have automated deployment. All of those things are automatically executed everytime the main branch updates. Therefore I conclude that this is already an OK example of CI/CD.

>Apply the SOLID principles you have learned. You are allowed to modify the source code according to the principles you want to implement. Please answer the following questions:
> 1) Explain what principles you apply to your project!
> 2) Explain the advantages of applying SOLID principles to your project with examples.
> 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

1. Principles that is applied
    - Single Responsibility Principle, Separation of pages related to Homepage, Car models and Product models into their respective controllers
    - Interface Segregation, because CarService and ProductService are created separately because the service that implements it only requires one of them and not both.
    - Dependency Inversion is performed on the Car Controller file by replacing all references to CarServiceImpl so that it relies on the interface
    - Open Close Principle is done by creating an update() method on the models so that its subclasses need not to modify its behaviour, it just need to override the update() method to update its fields

2. Advantages of applying the SOLID principles
   - **Why Apply it?**
     - Single Responsibility Principle (SRP):A class should have only one reason to change, meaning it should have only one responsibility. This makes the class more maintainable, as changes to one aspect of the system won't affect unrelated aspects. 
     - Open/Closed Principle (OCP): Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. This encourages the use of interfaces and abstract classes, allowing you to extend the behavior of a system without modifying existing code. This promotes code stability. 
     - Liskov Substitution Principle (LSP): Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. This ensures that subtypes can be used interchangeably with their base types, promoting polymorphism and preventing unexpected behaviors. 
     - Interface Segregation Principle (ISP): A class should not be forced to implement interfaces it does not use. This principle promotes the creation of smaller, more specific interfaces, preventing classes from being burdened with methods they don't need. 
     - Dependency Inversion Principle (DIP): High-level modules should not depend on low-level modules. Both should depend on abstractions. This principle encourages the use of dependency injection and inversion of control to decouple components, making the system more flexible and easier to maintain.
   - **Applying the SOLID principles can lead to several benefits:**
     - Maintainability: Code is easier to understand, modify, and extend.
     - Flexibility: Code is more adaptable to changes without affecting the entire system.
     - Scalability: Designing with SOLID principles often leads to more modular and reusable components.
     - Readability and Understandability: Following these principles makes code more readable and self-explanatory.
     - Testability: Components designed with SOLID principles are often easier to test in isolation.
   - **Examples**
     - not segregating the controllers will overbloat the ProductController file, leading to a nightmarish project to maintain

3. Disadvantages of not applying the SOLID principles
   - **Rigidity and Fragility:**
     - Without adhering to the Single Responsibility Principle (SRP), classes may have multiple responsibilities, making them rigid and fragile. Changes in one area of the code may inadvertently affect unrelated areas, leading to unintended consequences.

   - **Difficulty in Extension:**
     - Violating the Open/Closed Principle (OCP) can result in code that is difficult to extend. Modifying existing code to accommodate new features or changes increases the risk of introducing bugs and negatively impacting the stability of the system.

   - **Subtype Misuse:**
     - Ignoring the Liskov Substitution Principle (LSP) might lead to subtype misuse. If subclasses do not properly adhere to the contract defined by their base class, unexpected behavior can occur when substituting objects, leading to errors and inconsistencies.

   - **Interface Proliferation:**
      - Neglecting the Interface Segregation Principle (ISP) can result in large, monolithic interfaces that force classes to implement methods they don't need. This can lead to unnecessary dependencies and difficulties in maintaining the code.

   - **Tight Coupling:**
      - Failure to follow the Dependency Inversion Principle (DIP) can result in tight coupling between high-level and low-level modules. This can make the code more difficult to modify and test, as changes in one module may have cascading effects on others.

   - **Decreased Readability and Maintainability:**
      - Overall, not applying the SOLID principles can lead to code that is less readable, harder to understand, and more challenging to maintain. This can result in a higher likelihood of introducing defects and a decrease in the overall quality of the software.

   - **Reduced Testability:**
      - Code that lacks adherence to SOLID principles may be more challenging to test in isolation. Tight dependencies and lack of abstraction can make it difficult to create meaningful unit tests, leading to a decrease in the overall testability of the software.

   - **Increased Technical Debt:**
      - Over time, the consequences of not applying SOLID principles can accumulate, leading to increased technical debt. Technical debt refers to the long-term cost of maintaining and adapting poorly designed code, and it can slow down the development process.

