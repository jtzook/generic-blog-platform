# Generic Blog

This is a web application that allows users to create, read, update and delete (CRUD) posts on a blog platform.

## Setup

### Building and Running Postgres Container

```bash
    docker-compose build
    docker-compose up -d
```

### Running the API

```bash
    cd gbapi
    mvn spring-boot:run
```

## Features

- Users can sign up and log in with their email and password
- Users can create new posts with a title, a content and a timestamp
- Users can view all posts or filter them by author or date
- Users can edit or delete their own posts
- Users can comment on any post
- Users can like or dislike any post or comment

## Dependencies

This project uses the following technologies:

- Java 17 for the backend (Bellsoft Liberica JDK)
- Spring Boot for the web framework
- Postgres for the database
- Hibernate for the object-relational mapping
- React for the frontend
- JUnit 5 for the backend testing framework
- Jest for the frontend testing framework

## Installation

To run this project locally, you need to have Java 17, Postgres, Maven and Node.js installed on your machine.

1. Clone this repository to your local machine
2. Create a database named `blog` in Postgres and run the `schema.sql` file in the `src/main/resources` folder to create the tables
3. Update the `application.properties` file in the `src/main/resources` folder with your Postgres username and password
4. Open a terminal in the project root folder and run `mvn spring-boot:run` to start the backend server
5. Open another terminal in the `src/main/frontend` folder and run `npm install` to install all dependencies
6. Run `npm start` to start the frontend server
7. Open a browser and go to `http://localhost:3000` to access the web application

## Usage

To use this application, you need to sign up with your email and password first. Then you can log in with your credentials and start creating posts.

To create a new post, click on the `New Post` button on the top right corner of the home page. You will be redirected to a form where you can enter the title and content of your post. Click on the `Submit` button to save your post.

To view all posts, click on the `All Posts` button on the top left corner of the home page. You will see a list of all posts ordered by date. You can also filter them by author or date using the dropdown menus on the top.

To edit or delete your own post, click on the `Edit` or `Delete` button below your post. You will be redirected to an edit form or a confirmation dialog respectively.

To comment on any post, click on the `Comment` button below any post. You will see a form where you can enter your comment. Click on the `Submit` button to save your comment.

To like or dislike any post or comment, click on the `Like` or `Dislike` button below any post or comment. You will see an updated count of likes and dislikes.

### Testing

This project has unit tests and integration tests to ensure its quality and functionality.

To run all tests, open two terminals in both backend (`src/test/java`)and frontend (`src/main/frontend`) folders respectively  then run `mvn test` in one terminal  then run  'npm test' in another terminal .

To run only backend tests, open one terminal in backend (`src/test/java`) folder then run `mvn test
```
