# Assignment Scalable Web 

## GOAL

The goal of this assignment is to show your coding skills and what you value in software engineering. We value new ideas so next to the original requirement feel free to improve/add/extend.
We evaluate the assignment depending on your role (Developer/Tester) and your level of seniority

## The assignment

- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
	- <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
- The provided data needs to be diff-ed and the results shall be available on a third end point
	- <host>/v1/diff/<ID>
- The results shall provide the following info in JSON format
	- If equal return that
	- If not of equal size just return that
	- If of same size provide insight in where the diffs are, actual diffs are not needed (So mainly offsets + length in the data)
- Make assumptions in the implementation explicit, choices are good but need to be communicated

## Must haves
- Solution written in Java
- Internal logic shall be under unit test
- Functionality shall be under integration test
- Documentation in code
- Clear and to the point readme on usage

## Nice to haves
- Suggestions for improvement

Please	upload	the	assignment	on	your	personal	GitHub	account	once	finished,	and	send	the	link	to	the	
responsible	Tech Sourcer before	deadline.

## Running the project

First of all you will need to clone this repository using the following command

```
$ git clone https://github.com/pauloads/waes-technical-assignment.git
```
There are several defferent ways to run this service

### Using Maven Spring Boot Plugin

Inside project folder
```
$ mvn spring-boot:run
```

### Using Docker Image
Inside project folder
```
$ sh run-docker-container.sh
```
 
## Testing the project
 
 You can test this service using one of these two common ways:
 
### Using Postman  

You can import these following cURL scripts in Postman

- to set left side
```
curl -X POST \
  http://localhost:8080/v1/diff/1/left \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 244' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: JSESSIONID=32BD952B6BB9C3B7443E749D44DE6F67' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: 523e612e-0bfe-40e4-8ae1-b8b73cdbfa54,e9b973a1-e563-40c8-971d-42c74eed85a0' \
  -H 'User-Agent: PostmanRuntime/7.17.1' \
  -H 'cache-control: no-cache' \
  -d '{
    "base64EncodedJson": "eyJiYW5kIjp7Im5hbWUiOiJQaW5rIEZsb3lkIiwibWVtYmVycyI6W3sibmFtZSI6IkRhdmlkIEdpbG1hdXIifSx7Im5hbWUiOiJTaWQgQmFycmV0In0seyJuYW1lIjoiUm9nZXIgV2F0ZXJzIn0seyJuYW1lIjoiUmljaGFyZCBXcmlnaHQifSx7Im5hbWUiOiJOaWNrIE1hc29uIn1dfX0="
}'
``` 

- to set right side
```
curl -X POST \
  http://localhost:8080/v1/diff/1/right \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 244' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: JSESSIONID=32BD952B6BB9C3B7443E749D44DE6F67' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: a95a0968-043f-4444-9fa3-70fc27b30c25,d3bce676-b0bc-4380-a199-3f3007a60169' \
  -H 'User-Agent: PostmanRuntime/7.17.1' \
  -H 'cache-control: no-cache' \
  -d '{
	"base64EncodedJson": "eyJiYW5kIjp7Im5hbWUiOiJQaW5rIEZsb3lkIiwibWVtYmVycyI6W3sibmFtZSI6IkRhdmlkIEdpbG1vdXIifSx7Im5hbWUiOiJTeWQgQmFycmV0In0seyJuYW1lIjoiUm9nZXIgV2F0ZXJzIn0seyJuYW1lIjoiUmljaGFyZCBXcmlnaHQifSx7Im5hbWUiOiJOaWNrIE1hc29uIn1dfX0="
}'
```

- to show the differences
```
curl -X GET \
  http://localhost:8080/v1/diff/1 \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Cookie: JSESSIONID=32BD952B6BB9C3B7443E749D44DE6F67' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: e1a66665-732f-429b-8bd6-b15360e6671c,a1ecafea-0e24-41ab-81c3-3737d846eed9' \
  -H 'User-Agent: PostmanRuntime/7.17.1' \
  -H 'cache-control: no-cache'
```

### Using Swagger-UI

You can also test the service using Swagger-UI accessing the following URL:

- http://localhost:8080/swagger-ui.html


## Suggestions for improvement

- Use Spring profiles (dev, qa, production);
- Increase the maturity model (Richardson Maturity Model);
- Use a container orchestrator to scale the service;
- Create a Jenkins pipeline;
- Use a real database;
- Implement logs;
