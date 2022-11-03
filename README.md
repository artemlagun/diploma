
<h2>Lunch voting system<h2>

<h3>Technical requirement:<h3/>

Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users<br>
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)<br>
- Menu changes each day (admins do the updates)<br>
- Users can vote for a restaurant they want to have lunch at today<br>
- Only one vote counted per user<br>
- If user votes again the same day:
  - If it is before 11:00 we assume that he changed his mind.<br>
  - If it is after 11:00 then it is too late, vote can't be changed<br>

Each restaurant provides a new menu each day.