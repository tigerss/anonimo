anonimo
=======

Restful API
-----------

### USERS

JSON EXAMPLE: {"email":"","id":1,"name":"","password":""}
<br/>
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

<b>DELETE USER HAVING ID 1</b>

DELETE http://localhost:8080/Anonimo/users/1

### MESSAGES

JSON EXAMPLE: {"date":12,"id":1,"latitude":"","longitude":"","text":"First message","userId":1}

"date" type long = milliseconds since 1970
<br/>
"userId" type long
<br/>
All other fields types are strings

GET 	/messages 		(displays all messages)

Response (array of objects): [{"date":12,"id":1,"latitude":"","longitude":"","text":"First message","userId":1},
{"date":12,"id":2,"latitude":"","longitude":"","text":"Second message","userId":1}]

POST 	/messages 		create
<br/> 	 
PUT 	/messages/1 	update 			id="1"
<br/>
DELETE 	/messages/1 	delete 			id="1"
<br/>
GET 	/messages/1 	show 			id="1"
 
### COMMENTS

JSON:     
{
   "date": 0,
   "id": 1,
   "messageId": 0,
   "text": "test",
   "userId": 0
}

<b>ACTIONS:</b>

POST 	/comments 		create
<br/> 	 
PUT 	/comments/1 	update 			id="1"
<br/>
DELETE 	/comments/1 	delete 			id="1"
<br/>
GET 	/comments/1 	show 			id="1"

### EVENTS

JSON:
    {
       "date": 0,
       "description": "First event",
       "id": 1,
       "latitude": "",
       "longitude": "",
       "userId": 1
   }
   
<b>ACTIONS:</b>

POST 	/events 	create
<br/> 	 
PUT 	/events/1 	update 			id="1"
<br/>
DELETE 	/events/1 	delete 			id="1"
<br/>
GET 	/events/1 	show 			id="1"

### USER_EVENT

JSON:
    {
       "eventId": 1,
       "id": 1,
       "text": "joined event",
       "userId": 2
   }
   
<b>ACTIONS:</b>

POST 	/user_events 	create
<br/> 	 
PUT 	/user_events/1 	update 			id="1"
<br/>
DELETE 	/user_events/1 	delete 			id="1"
<br/>
GET 	/user_events/1 	show 			id="1"
