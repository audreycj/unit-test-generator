# CLI-Based Unit Test Code Generator Using OpenAI API

This Command-Line Interface (CLI) application is designed to automate the process of generating unit test code for a given function or module. It utilizes the OpenAI API to generate code based on user-specified inputs and expected outputs. The application is built using the Spring Boot framework and the Picocli library for parsing command-line arguments. It is compiled using the GraalVM native-image compiler, resulting in a native binary that can be executed quickly and with low memory usage.

The user can specify the function or module to be tested, define test cases (including input parameters and expected output values), choose a testing framework (such as pytest or unittest), and generate code for the specified testing framework. The generated code includes test cases for each user-specified input and expected output, as well as any necessary setup or teardown code. The code is written in the chosen testing framework's syntax and can be easily copy-pasteable into a larger testing suite.

The application provides clear and concise feedback to the user about the generated test code, including any errors or warnings. It also handles common edge cases or unexpected input values gracefully, providing helpful messages to the user if necessary.

Overall, the goal of this application is to simplify and automate the process of generating unit test code, allowing developers to focus on writing quality code and ensuring that it functions as expected.
## Authors

- [@audreycj](https://github.com/audreycj)

