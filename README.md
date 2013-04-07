anonimo
=======

Restful API
-----------

### USERS

JSON EXAMPLE: {"email":"","id":1,"name":"","password":""}
ALL FIELDS ARE OPTIONAL

<b>GET ALL USERS</b>

GET http://localhost:8080/Anonimo/users

Response: [{"email":"email@address.com","id":1,"name":"nume","password":"parola"},
			{"email":"","id":2,"name":"Silviu","password":"Silviu"}]

<b>GET USER HAVING ID 1</b>

GET http://localhost:8080/Anonimo/users/1

Response: {"email":"email@address.com","id":1,"name":"nume","password":"parola"}

<b>CREATE USER</b>

POST http://localhost:8080/Anonimo/users

BODY: {"email":"email@address.com","name":"nume","password":"parola"}

<b>UPDATE USER HAVING ID 1</b>

PUT http://localhost:8080/Anonimo/users/1

BODY: {"email":"another address","name":"another name","password":"another password"}

<b>DELETE USER HAVING ID 1 (NOT IMPLEMENTED)</b>

DELETE http://localhost:8080/Anonimo/users/1

### MESSAGES

JSON EXAMPLE: {"date":12,"id":1,"latitude":"","longitude":"","text":"First message","userId":1}

"date" type long = milliseconds since 1970
"userId" type long
All other fields types are strings

GET 	/messages 		index

Response (array of objects): [{"date":12,"id":1,"latitude":"","longitude":"","text":"First message","userId":1},
{"date":12,"id":2,"latitude":"","longitude":"","text":"Second message","userId":1}]

POST 	/messages 		create 	 
PUT 	/messages/1 	update 			id="1"
DELETE 	/messages/1 	delete 			id="1" (NOT IMPLEMENTED)
GET 	/messages/1 	show 			id="1"
 