# Library Book Issue System

This project implements the University of Central Punjab assignment for a Library Management GUI with exception handling.

## Features
- Swing-based GUI with required fields:
  - Student Name
  - Roll Number
  - Book Title
  - Book Category
  - Issue Date
  - Return Date
  - Remarks (optional)
- Dropdown selection for book category
- Radio button selection for book type (New Edition / Old Edition)
- Buttons: Issue Book, Reset, Exit
- Exception handling with `try-catch`, `throw`, and `finally`

## Implemented Exceptions
- `EmptyFieldException` for empty required fields
- `InvalidRollNumberException` for invalid roll number format
- `NullSelectionException` for no book category or book type selection
- `InvalidDateException` for return date earlier than issue date
- `NumberFormatException` while converting roll number to numeric

## Files
- `src/SmartLibraryPortal.java` - main application source file

## Compile and Run
1. Open a terminal in the project root.
2. Run `javac src\SmartLibraryPortal.java`
3. Run `java -cp src SmartLibraryPortal`
