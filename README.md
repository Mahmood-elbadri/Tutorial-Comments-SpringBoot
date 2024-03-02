# Spring Boot REST API for Tutorials and Comments

This Spring Boot application provides a RESTful API for managing tutorials and their associated comments. It allows clients to perform CRUD (Create, Read, Update, Delete) operations on tutorials and comments. The application uses Spring Data JPA for database interactions and H2 as the in-memory database for simplicity and ease of use.

## Features

- **Tutorials**
  - Create, update, delete, and fetch tutorials.
  - Fetch all tutorials or only those that are published.
- **Comments**
  - Add, update, and delete comments for a specific tutorial.
  - Fetch all comments associated with a specific tutorial.

## Setup and Installation

1. **Clone the Repository**

   ```sh
   git clone (https://github.com/Mahmood-elbadri/Tutorial-Comments-SpringBoot.git)
   ```

2. **Open and Run Project**

   Import the project into your favorite IDE as a Maven project and run it as a Spring Boot Application.

3. **Access H2 Database**

   - Navigate to `http://localhost:8080/h2-console` in your web browser.
   - Use the JDBC URL, username, and password as configured in your `application.properties`.

4. **API Endpoints**

   The application exposes several RESTful endpoints:

   - **Tutorials**
     - `GET /api/tutorials` - Fetch all tutorials.
     - `GET /api/tutorials/{id}` - Fetch a tutorial by ID.
     - `POST /api/tutorials` - Create a new tutorial.
     - `PUT /api/tutorials/{id}` - Update an existing tutorial.
     - `DELETE /api/tutorials/{id}` - Delete a tutorial by ID.
     - `GET /api/tutorials/published` - Fetch all published tutorials.

   - **Comments**
     - `GET /api/tutorials/{tutorialId}/comments` - Fetch all comments for a tutorial.
     - `GET /api/comments/{id}` - Fetch a comment by ID.
     - `POST /api/tutorials/{tutorialId}/comments` - Add a comment to a tutorial.
     - `PUT /api/comments/{id}` - Update a comment.
     - `DELETE /api/tutorials/{tutorialId}/comments` - Delete all comments for a tutorial.
