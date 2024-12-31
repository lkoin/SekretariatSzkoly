# School Management System

A Java-based school management system that handles students, teachers, and graduates data with secure login functionality and administrative capabilities.

## 🎯 Features

| Feature              | Description                                |
|----------------------|--------------------------------------------|
| 🔐 Authentication    | Secure login system with role-based access |
| 👥 User Management   | Handle students, teachers, and graduates   |
| 📚 Class Management  | Organize and manage school classes         |
| 🔍 Advanced Search   | Find users by various criteria             |
| 📊 Data Organization | Sort and manage data efficiently           |

## 🏗 Project Structure

### Main Components

- **Person**: Base class for all individuals in the system
- **Worker**: Represents teachers and staff
- **Student**: Represents active students
- **Graduate**: Represents graduated students
- **SchoolClass**: Represents school classes
- **ViewController**: Handles user interface and interactions

## 📋 Functionality Details

### Authentication System (`login` method)
```java
private void login(Scanner scanner)
```
- Handles user authentication
- Validates username and password
- Directs users to appropriate menus based on their role
- Provides access control for administrative functions

### Administrative Functions

#### Display All Persons (`displayAllPersons` method)
```java
private void displayAllPersons(Scanner scanner)
```
Options:
1. Display all workers
2. Display all students
3. Display students in specific class
4. Display teachers in specific class
5. Display all graduates

#### Add New Person (`addNewPerson` method)
```java
private void addNewPerson(Scanner scanner)
```
Features:
- Adds new teachers or students
- Validates input data
- Handles class assignments for students
- Sets management access for teachers

#### Assign Student to Class (`assignStudentToClass` method)
```java
public void assignStudentToClass(Scanner scanner)
```
- Assigns students to specific classes
- Validates class existence
- Handles class capacity checks
- Updates class rosters

#### Sort Lists (`sortLists` method)
```java
private void sortLists(Scanner scanner)
```
Sorting options:
- Alphabetically (A-Z)
- Reverse alphabetically (Z-A)

#### Search Person (`searchPerson` method)
```java
private void searchPerson(Scanner scanner)
```
- Searches by login or surname
- Displays matching results

### Helper Functions

#### Find Student by PESEL (`findStudentByPesel` method)
```java
private Student findStudentByPesel(String pesel)
```
- Locates student records using PESEL number
- Returns null if not found

#### Find Class by Name (`findClassByName` method)
```java
private SchoolClass findClassByName(String className)
```
- Locates class by name
- Case-insensitive search
- Returns null if not found

## 🔒 Data Validation

The system includes validation for:
- PESEL numbers
- Date formats
- Phone numbers
- Required fields
- Class capacity limits

## 📱 User Interface

### Main Menu Options
1. Login
2. Exit

### Administrative Menu Options
1. Display all persons
2. Add new person
3. Assign student to class
4. Sort lists
5. Search person
6. Logout

### User Menu
- Displays personal information
- Shows class assignments (for students)
- Shows teaching assignments (for teachers)

## 🛠 Exception Handling

The system handles various exceptions:
- `InvalidDataException`: For invalid input data
- `AddingToSchoolClassException`: For class assignment issues
- `InputMismatchException`: For invalid user input

## 🚀 Getting Started

1. Clone the repository
2. Ensure you have Java JDK 17 or higher installed
3. Compile the project using your preferred IDE or command line
4. Run the `Main` class to start the application

## 📄 Requirements

- Java JDK 17+
