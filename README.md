# TDD - Task Manager - LinkedIn Course

This project was created to follow along during LinkedIn course called Test-Driven Development in Spring Boot with JUnit and Mockito located in [LinkedIn](https://www.linkedin.com/learning/test-driven-development-in-spring-boot-with-junit-and-mockito/test-driven-development-in-spring-boot?contextUrn=urn%3Ali%3AlearningCollection%3A7311373442178531328&u=26281682)

**Course Title**: Test-Driven Development in Spring Boot with JUnit and Mockito
**Description**: If you’ve ever run out of time to write tests for your code, you know what a potential disaster you’re flirting with. Test-driven development (TDD) lets you write the test before the implementation code, essentially using the test to guide your development process. This course shows you how to apply TDD principles with Spring Boot projects. No prior knowledge of TDD is assumed, so instructor Maaike van Putten starts off by introducing TDD and showing a plain example. Then, move on to setting up the environment that will be used so you can follow along. After that, find out how to develop the different layers of a REST API the TDD way: controller layer, service layer, and the repository/data layer. Finally, Maaike wraps up the course up with some TDD best practices.

# Chapter: 1. TDD and Spring Boot

## Video: Introduction to TDD in Spring Boot
### 0:00:27: 
- TDD is a technique when coding where you create the test the feature you are working on before coding the feature.

### 0:02:26            
- Advantages:
    - Write better code.
    - Simpler code and decouple implementations
    - Have sufficient test coverages
    - Improve feature focus

### 0:04:11            
- TDD uses the Green-Red-Refactor process where
    1. you create a failing test (usually based on the feature) Red
    2. you add the minimal code that make the test passes Green
    3. you update the code to make readable, more performant and removing duplicated code. (THIS STEP CANNOT CHANGE CODE BEHAVIOR)

# Chapter: 3. TDD for the Data Layer
## Video: Writing TDD test for the repository
### 0:05:24            
- DataJpaTest annotation is a specialized annotation created by SpringBoot to test JPA repositories. It will load everything that is jpa related components avoiding, web layer and security configurations

## Video: Integration testing with an in-memory database
### 0:03:23            
- Unit Test: Test just one component, it doesnt rely on other components (repositories,...)
- Integration Test: Test how two or more components work together

# Chapter: 4. TDD for Services with Mockito
## Video: Quick overview of working with Mockito
### 0:00:38            
- Mocking: Simulate the behavior of a specific component so whenever this component is called, it will behave the way you configured it.

# Chapter: 6. TDD Best Practices
## Video: Common pitfalls to avoid
### 0:04:12            
- Commom Pitfalls:
    - Refactor is an important step to keep maintanable code
    - Writing too many test at the begining can be dificult to get the feature done
    - Mocking too much can couple your test
    - Test needs to be readable, treat as production code
    - Not writing edge test can break your app

## Video: Best practices for writing maintainable tests
### 0:01:59            
- Best Practices:
    - Test should be small, easy to read and maintanable, only checking one scenario
    - Test should have descriptive names
    - Nice to have the arrange-act-assert to make the test readable so you can see
        - what is the scenario you are testing
        - how you are testing
        - what you are verifying with the test
    - avoid duplications
    - refactor you test to keep redeability

# Side Notes
- Over-mocking can make your tests too specific to the implementation details, meaning that small changes to the implementation could require extensive changes to the tests. This tight coupling leads to fragile tests that may break when you modify the underlying code, even though the behavior hasn't changed. This defeats the purpose of unit testing, which is to isolate the unit from the rest of the system.
- TDD works well with Agile because both methods promote incremental progress. By writing tests based on user stories and acceptance criteria, you ensure that testing is done throughout the development process, not just at the end of the sprint. This way, no tests are skipped and it is guaranteed that the code is thoroughly tested as it’s being developed.
