Lunch voting system
===================

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4d12082ec7ec4735903c1f5011a39164)](https://www.codacy.com/gh/artemlagun/diploma/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=artemlagun/diploma&amp;utm_campaign=Badge_Grade)

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