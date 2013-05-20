anonimo
=======

Restful API
-----------

### USERS

<b>JSON EXAMPLE:</b> 
```json
{
	"email":"",
	"id":1,
	"name":"",
	"password":""
}
```
<br/>
ALL FIELDS ARE OPTIONAL

<b>GET ALL USERS</b>

GET http://localhost:8080/Anonimo/users

Response: 
```json
[
	{
		"email":"email@address.com",
		"id":1,
		"name":"nume",
		"password":"parola"
	},
	{
		"email":"",
		"id":2,
		"name":"Silviu",
		"password":"Silviu"
	}
]
```
<b>GET USER HAVING ID 1</b>

GET http://localhost:8080/Anonimo/users/1

Response: 
```json
{
	"email":"email@address.com",
	"id":1,
	"name":"nume",
	"password":"parola"
}
```
<b>CREATE USER</b>

POST http://localhost:8080/Anonimo/users

BODY: 
```json
{
	"email":"email@address.com",
	"name":"nume",
	"password":"parola"
}
```
<b>UPDATE USER HAVING ID 1</b>

PUT http://localhost:8080/Anonimo/users/1 
OR
POST /users/1/updateInfo

BODY: 
```json
{
	"email":"another address",
	"name":"another name",
	"password":"another password"
}
```
<b>DELETE USER HAVING ID 1</b>

DELETE http://localhost:8080/Anonimo/users/1

<b>CUSTOM ACTIONS</b>

GET /users/y/messages returns messages of user "y"
Response:
```
[
   {
       "date": 1366230231,
       "id": 4,
       "latitude": "44.435519",
       "longitude": "26.102527",
       "text": "Si ce mai face fetilii? Ie bini di tat?",
       "userId": 7
   }
]
```

### MESSAGES

<b>JSON EXAMPLE:</b> 
```json
{
	"date":12,
	"id":1,
	"latitude":"",
	"longitude":"",
	"text":"First message",
	"userId":1
}
```
"date" type long = milliseconds since 1970
<br/>
"userId" type long
<br/>
All other fields types are strings

GET 	/messages 		(displays all messages)

Response (array of objects): 
```json
[
	{
		"date":12,
		"id":1,
		"latitude":"",
		"longitude":"",
		"text":"First message",
		"userId":1
	},
	{
		"date":12,
		"id":2,
		"latitude":"",
		"longitude":"",
		"text":"Second message",
		"userId":1
	}
]
```
POST 	/messages 		create
<br/> 	 
PUT 	/messages/1 	update 			id="1"
<br/>
DELETE 	/messages/1 	delete 			id="1"
<br/>
GET 	/messages/1 	show 			id="1"

<b>CUSTOM ACTIONS</b>

GET /messages/7/comments returns comments of message #7
Response:
```
[
   {
	   "date": 1366419985,
	   "id": 2,
	   "messageId": 7,
	   "text": "Da, foarte smecher!",
	   "userId": 31
   }
]
```
 
### COMMENTS

<b>JSON:</b>
```json
{
   "date": 0,
   "id": 1,
   "messageId": 0,
   "text": "test",
   "userId": 0
}
```
<b>ACTIONS:</b>

POST 	/comments 		create
<br/> 	 
PUT 	/comments/1 	update 			id="1"
<br/>
DELETE 	/comments/1 	delete 			id="1"
<br/>
GET 	/comments/1 	show 			id="1"

### EVENTS

<b>JSON:</b>
```json
{
   "date": 0,
   "description": "First event",
   "id": 1,
   "latitude": "",
   "longitude": "",
   "userId": 1
}
```
<b>ACTIONS:</b>

POST 	/events 	create
<br/> 	 
PUT 	/events/1 	update 			id="1"
<br/>
DELETE 	/events/1 	delete 			id="1"
<br/>
GET 	/events/1 	show 			id="1"

### USER_EVENT

<b>JSON:</b>
```json
{
   "eventId": 1,
   "id": 1,
   "text": "joined event",
   "userId": 2
}
```
   
<b>ACTIONS:</b>

POST 	/user_events 	create	 
PUT 	/user_events/1 	update 			id="1"
<br/>
DELETE 	/user_events/1 	delete 			id="1"
<br/>
GET 	/user_events/1 	show 			id="1"

### VOTES

<b>JSON:</b>
```json
{
   "id": 1,
   "messageId": 1,
   "userId": 7,
   "value": "up"
}
```

<b>CUSTOM ACTIONS:</b>   

GET /messages/{id}/votes (get votes for a message)

Ex: 
GET /messages/1/votes
Result:
```
[
   {
	   "downVotes": 2,
	   "upVotes": 1
   }
]
```

GET /events/{id}/participants (get participants for event)

Ex: 
GET /events/1/participants
Result:
```
[
   {
       "eventId": 1,
       "participants": 2
   }
]
```

<b>UPDATE USER HAVING ID 1</b>

POST /users/1/updateInfo

BODY: 
```json
{
	"email":"another address",
	"name":"another name",
	"password":"another password"
}
```

<b>DEFAULT ACTIONS:</b>

POST 	/votes 		create	 
PUT 	/votes/1 	update 			id="1"
<br/>
DELETE 	/votes/1 	delete 			id="1"
<br/>
GET 	/votes/1 	show 			id="1"


