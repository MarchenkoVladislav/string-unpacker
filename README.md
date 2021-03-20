# string-unpacker
[![Language](http://img.shields.io/badge/language-java-brightgreen.svg)](https://www.java.com/)
[![License](http://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/samtools/PolinaBevad/bio_relatives)

Test task on Java. 

The project allows you to unpack strings. The input is a string of the form number[string], the output is a string containing repeated substrings.

## Table of Contents
-   [Used technologies](#used-technologies)
-   [Usage](#usage)
    -   [Restrictions](#restrictionsi)
    -   [How to run](#fhow-to-run)
-   [Tests](#tests)
-   [Maintainer](#maintainer)
-   [License](#license)

## Used technologies
-   Java 11
-   Lombok
-   JUnit 5
-   Maven

## Usage
### Restrictions
-   Input string can contains only Latin letters, numbers (only for count of substrings repeats) and square brackets (only for indication of repeatable substrings).
-   Brackets should be valid.

If the input string is invalid, then throw an exception with the appropriate message and the string will not be unpacked.
### How to run
You can run this project with Maven. There are two ways:
-   with command (when you are at the root of the project): `mvn clean compile exec:java`. If you choose this way, then you will need to enter the string you want to unpack after the message `Please, enter a string, that need to be unpacked:`.
-   with command (when you are at the root of the project): `mvn clean compile exec:java -Dexec.args=""`, where in args you should write the string you want to unpack, for example, `mvn clean compile exec:java -Dexec.args="3[xxx]4[2[s]f]"`.

After execution you can see unpacked string:
![image](https://user-images.githubusercontent.com/44652081/111881793-425dbe80-89c3-11eb-8c12-d9408fcb5b2a.png)

Or an exception message, if input string is invalid:
![image](https://user-images.githubusercontent.com/44652081/111881835-79cc6b00-89c3-11eb-9185-9a715a202d12.png)

## Tests
You can run tests with Maven. You should use command `mvn clean test` (when you are at the root of the project).
## Maintainer
[Vladislav Marchenko](https://github.com/MarchenkoVladislav)

## License
This project is licenced under the terms of the [MIT](LICENSE) license.
