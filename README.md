# Employee Management API

## Description

The Employee Management API is a RESTful service designed to manage employee, attendance, department, and job data. It provides a set of endpoints for creating, retrieving, updating, and deleting entities related to employees, attendance records, departments, and jobs.

## Table of Contents

- [GenericController Endpoints](#genericcontroller-endpoints)
- [Specific Controller Endpoints](#specific-controller-endpoints)
- [Installation](#installation)
- [Usage](#usage)

## GenericController Endpoints

### POST /create

- **Description:** Creates a new entity.
- **Request Body:** JSON object with at least `firstName`, `lastName`, and `salary` fields.
```
{
    "firstName": "John",
    "lastName": "Doe",
    "salary": 50000
}
```
- **Response:**
 - `201 Created` with a plain text message indicating the entity was created successfully.
 - `500 Internal Server Error` with a plain text message if there was an error creating the entity.

### GET /{id}

- **Description:** Retrieves an entity by its ID.
- **Path Parameter:** `id` - The ID of the entity to retrieve.
- **Response:**
 - `200 OK` with the entity data in JSON format if found.
 - `500 Internal Server Error` with a plain text message if there was an error finding the entity.

### GET /

- **Description:** Retrieves all entities.
- **Response:**
 - `200 OK` with an array of entity data in JSON format.
 - `500 Internal Server Error` with a plain text message if there was an error finding all entities.

### PUT /{id}

- **Description:** Updates an entity by its ID.
- **Path Parameter:** `id` - The ID of the entity to update.
- **Request Body:** JSON object with at least `firstName`, `lastName`, and `salary` fields to update.
 ```
{
    "firstName": "John",
    "lastName": "Doe",
    "salary": 60000
}
```
- **Response:**
 - `200 OK` with a plain text message indicating the entity was updated successfully.
 - `500 Internal Server Error` with a plain text message if there was an error updating the entity.

### DELETE /{id}

- **Description:** Deletes an entity by its ID.
- **Path Parameter:** `id` - The ID of the entity to delete.
- **Response:**
 - `200 OK` with a plain text message "Deleted Successfully" if the entity was successfully deleted.
 - `500 Internal Server Error` with a plain text message if there was an error deleting the entity.

### DELETE /

- **Description:** Deletes all entities.
- **Response:**
 - `200 OK` with a plain text message "All Deleted Successfully" if all entities were successfully deleted.
 - `500 Internal Server Error` with a plain text message if there was an error deleting all entities.

## Specific Controller Endpoints

For each specific controller (`EmployeeController`, `AttendanceController`, `DepartmentController`, `JobController`), the endpoints follow the same pattern as described above for the `GenericController`. The request and response formats are consistent across all controllers, with the exception of the `findDepartmentEmployees` and `findJobEmployees` endpoints in the `DepartmentController` and `JobController`, which return a set of `EmployeeDto` objects in JSON format.

### DepartmentController Endpoint

#### GET /departments/{id}/employees

- **Description:** Retrieves all employees associated with a specific department.
- **Path Parameter:** `id` - The ID of the department.
- **Response:**
 - `200 OK` with a JSON array of `EmployeeDto` objects representing the employees in the specified department.
 - `500 Internal Server Error` with a plain text message if there was an error retrieving the employees.

### JobController Endpoint

#### GET /jobs/{id}/employees

- **Description:** Retrieves all employees associated with a specific job.
- **Path Parameter:** `id` - The ID of the job.
- **Response:**
 - `200 OK` with a JSON array of `EmployeeDto` objects representing the employees in the specified job.
 - `500 Internal Server Error` with a plain text message if there was an error retrieving the employees.

## Installation

To install and run this API, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have Java and Maven installed.
3. Navigate to the project directory and run `mvn clean install` to build the project.
4. Execute the application using your preferred method (e.g., IDE, command line).

## Usage

To use the API, make HTTP requests to the endpoints as described in the documentation. Use tools like Postman or cURL for testing.
