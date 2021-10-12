# Spring Auth Practice Lab

- This is a demo project that uses Spring Security to login/authenticate a user.
- The user can sign up with some basic information and have it stored in a database.
- The user's password is hashed before being stored.
- Non-logged in users can only access the homepage, log in page, and sign up page.

## Routes
- `/` - This is the homepage.
- `/login` - A basic login form.
- `/signup` - User can fill out a form with their username, first name, last name, date of birth, and a short biography.
- `/users/{id}` - This page will display information on the user with the database id of {id}.