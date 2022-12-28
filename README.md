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

### [Postman REST API documentation](https://documenter.getpostman.com/view/22816952/2s8Z6x4ZUC)
### curl samples:
For windows use `Git Bash`
<br>
<br>
>#### FoodRestController

#### getAll Foods
`curl -s http://localhost:8080/api/admin/foods --user admin@gmail.com:admin`

#### get Foods 100007 from Restaurant 100004
`curl -s http://localhost:8080/api/admin/foods/100004/100007 --user admin@gmail.com:admin`

#### getAllByDate Foods from Restaurant 100006
`curl -s http://localhost:8080/api/admin/foods/100006/by-date?voteDate=2022-11-07 --user admin@gmail.com:admin`

#### getAllByRestaurant Foods from Restaurant 100004
`curl -s http://localhost:8080/api/admin/foods/100004 --user admin@gmail.com:admin`

#### create Foods in Restaurant 100006
`curl -s -X POST -d '{"voteDate": "2022-12-07", "description": "Created food", "price": 9.99}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/admin/foods/100006 --user admin@gmail.com:admin`

#### update Foods in Restaurant 100004
`curl -s -X PUT -d '{"id": 100007, "voteDate": "2022-12-07", "description": "Updated food", "price": 7.77}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/foods/100004 --user admin@gmail.com:admin`

#### delete Foods
`curl -s -X DELETE http://localhost:8080/api/admin/foods/100004/100008 --user admin@gmail.com:admin`
<br>
<br>
>#### VoteAdminRestController

#### getAll Votes
`curl -s http://localhost:8080/api/admin/votes --user admin@gmail.com:admin`

#### get Votes 100022
`curl -s http://localhost:8080/api/admin/votes/100022 --user admin@gmail.com:admin`

#### getByUserAndDate Votes for User 100000
`curl -s "http://localhost:8080/api/admin/votes/by-user-date?voteDate=2022-11-07&userId=100000" --user admin@gmail.com:admin`

#### getAllByDate Votes
`curl -s http://localhost:8080/api/admin/votes/by-date?voteDate=2022-11-07 --user admin@gmail.com:admin`

#### getByRestaurantAndDate Votes from Restaurant 100004
`curl -s http://localhost:8080/api/admin/votes/100004/by-date?voteDate=2022-11-07 --user admin@gmail.com:admin`

#### create Votes
`curl -s -X POST -d '{"restaurantId": "100004"}' -H 'Content-Type: application/json;charset=UTF-8' "http://localhost:8080/api/admin/votes?userId=100003&restaurantId=100004" --user admin@gmail.com:admin`

#### update Votes 100023
`curl -s -X PUT -d '{"restaurantId": "100004"}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/votes/100023?restaurantId=100004 --user admin@gmail.com:admin`

#### delete Votes
`curl -s -X DELETE http://localhost:8080/api/admin/votes/100025 --user admin@gmail.com:admin`
<br>
<br>
>#### VoteProfileRestController

#### get Votes of User 100000
`curl -s http://localhost:8080/api/profile/votes --user evahester@gmail.com:password`

#### create Votes by User 100002
`curl -s -X POST -d '{"restaurantId": "100004"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/api/profile/votes?restaurantId=100004 --user alicerussell@gmail.com:password`

#### update Votes 100022 by user 100000
`curl -s -X PUT -d '{"restaurantId": "100005"}' -H 'Content-Type: application/json' http://localhost:8080/api/profile/votes/100022?restaurantId=100005 --user evahester@gmail.com:password`
<br>
<br>
>#### RestaurantAdminRestController

#### getAll Restaurants
`curl -s http://localhost:8080/api/admin/restaurants --user admin@gmail.com:admin`

#### get Restaurants 100004
`curl -s http://localhost:8080/api/admin/restaurants/100004 --user admin@gmail.com:admin`

#### getAllWithMenu Restaurants
`curl -s http://localhost:8080/api/admin/restaurants/with-menu --user admin@gmail.com:admin`

#### getMenuOfDay from Restaurant 100004
`curl -s http://localhost:8080/api/admin/restaurants/100004/menu --user admin@gmail.com:admin`

#### create Restaurants
`curl -s -X POST -d '{"name": "Created restaurant"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/api/admin/restaurants --user admin@gmail.com:admin`

#### update Restaurants
`curl -s -X PUT -d '{"id": "100006", "name": "Udated restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/restaurants --user admin@gmail.com:admin`

#### delete Restaurants
`curl -s -X DELETE http://localhost:8080/api/admin/restaurants/100004 --user admin@gmail.com:admin`
<br>
<br>
>#### RestaurantRestController

#### getAllWithMenu Restaurants
`curl -s http://localhost:8080/api/restaurants/with-menu --user evahester@gmail.com:password`

#### getMenuOfDay Restaurants 100005
`curl -s http://localhost:8080/api/restaurants/100005/menu --user evahester@gmail.com:password`
<br>
<br>
>#### AdminRestController

#### getAll Users
`curl -s http://localhost:8080/api/admin/users --user admin@gmail.com:admin`

#### get Users 100000
`curl -s http://localhost:8080/api/admin/users/100000 --user admin@gmail.com:admin`

#### getByEmail Users
`curl -s http://localhost:8080/api/admin/users/by-email?email=evahester@gmail.com --user admin@gmail.com:admin`

#### create Users
`curl -s -X POST -d '{"name": "Created user", "email": "createduser@gmail.com", "password": "password", "roles": ["USER"]}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/api/admin/users --user admin@gmail.com:admin`

#### update Users 100001
`curl -s -X PUT -d '{"name": "Updated user", "email": "updateduser@gmail.com", "password": "password"}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/users/100001 --user admin@gmail.com:admin`

#### delete Users
`curl -s -X DELETE http://localhost:8080/api/admin/users/100002 --user admin@gmail.com:admin`

#### validate with Error
` curl -s -X POST -d '{}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/users --user admin@gmail.com:admin`
<br>
<br>
>#### ProfileRestController

#### get Users 100000
`curl -s http://localhost:8080/api/profile --user evahester@gmail.com:password`

#### register User
`curl -s -X POST -d '{"name": "New User", "email": "test@gmail.com", "password": "test-password"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/api/profile`

#### get Profile
`curl -s http://localhost:8080/api/profile --user test@gmail.com:test-password`

#### update Users 100001
`curl -s -X PUT -d '{"name": "Updated user", "email": "updated_user@gmail.com", "password": "password"}' -H 'Content-Type: application/json' http://localhost:8080/api/profile --user bryanterry@gmail.com:password`

#### delete Users 100000
`curl -s -X DELETE http://localhost:8080/api/profile --user evahester@gmail.com:password`

