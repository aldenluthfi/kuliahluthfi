# Tutorial Pemrograman Lanjut Modul 5
## Alden Luthfi - 2206028932

## Refleksi

> What is the difference between the approach of performance testing with JMeter and profiling with IntelliJ Profiler in the context of optimizing application performance?

JMeter simulates workloads from multiple people, while Intellij Profiler is used to analyze bottlenecks and see what functions is taking the most time. SO

> How does the profiling process help you in identifying and understanding the weak points in your application?

It shows you how much time each function is using by CPU time and many other factors so we can pinpoint which function to optimize

> Do you think IntelliJ Profiler is effective in assisting you to analyze and identify bottlenecks in your application code?

Yes, it tells me which functions are consuming the most time

> What are the main challenges you face when conducting performance testing and profiling, and how do you overcome these challenges?

Looking for solutions to optimize the code is the most challenging part

> What are the main benefits you gain from using IntelliJ Profiler for profiling your application code?

Since it is builtin in IntelliJ, IntelliJ Profiler helps me so that I don't have to open any third-party apps to profile my program

> How do you handle situations where the results from profiling with IntelliJ Profiler are not entirely consistent with findings from performance testing using JMeter?

As far as I tested, there were no inconsistencies between JMeter and IntelliJ Profiler

> What strategies do you implement in optimizing application code after analyzing results from performance testing and profiling? How do you ensure the changes you make do not affect the application's functionality?

Java has a lot of builtin functions to iterate through a list, and sometimes it is far better than using a for-loop


## JMeter Report and Test Results
### JMeter from CMD
<img src="images/cmd 1.png" alt="all-student"/>
<img src="images/cmd 2.png" alt="all-student"/>
<img src="images/cmd 3.png" alt="all-student"/>
### **Endpoint** `/all-student`
Before Optimization:
<img src="images/getAllStudentWithCourses() 1.png" alt="all-student">
<img src="images/getAllStudentWithCourses() 3.png" alt="all-student"/>
After Optimization:
<img src="images/getAllStudentWithCourses() 2.png" alt="all-student">
<img src="images/getAllStudentWithCourses() 4.png" alt="all-student"/>

Execution Time `getAllStudentWithCourses()` from Intellij Profiler:

| Before   | After  | Diff Percentage |
|----------|--------|----------------|
| 5,006 ms | 837 ms | 83.28%         |

### **Endpoint** `/all-student-name`
Before Optimization:
<img src="images/joinStudentNames() 1.png" alt="all-student">
<img src="images/joinStudentNames() 3.png" alt="all-student">
After Optimization:
<img src="images/joinStudentNames() 2.png" alt="all-student">
<img src="images/joinStudentNames() 4.png" alt="all-student">

Execution Time `joinStudentNames()` from Intellij Profiler:

| Before | After  | Diff Percentage |
|--------|--------| -- |
| 435 ms | 200 ms | 54.02% |

### **Endpoint** `/highest-gpa`
Before Optimization:
<img src="images/findStudentWithHighestGpa() 1.png" alt="all-student">
<img src="images/findStudentWithHighestGpa() 3.png" alt="all-student">
After Optimization:
<img src="images/findStudentWithHighestGpa() 2.png" alt="all-student">
<img src="images/findStudentWithHighestGpa() 4.png" alt="all-student">

Execution Time `findStudentWithHighestGpa()` from Intellij Profiler:

| Before | After | Diff Percentage |
|--------|-------| -- |
| 129 ms | 60 ms | 53.49% |

The screenshots clearly shows an increase of performance after optimization.