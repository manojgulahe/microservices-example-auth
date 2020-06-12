# microservices-example-auth
Authentication is main component for microservices architeture. Many different types of architecture is available on internet.
I have implimented it with OAuth and Consul as service discovery.

## Prerequisites
Install Consul on your system. Download consul from https://www.consul.io/. 
Run consul using instruction from website https://learn.hashicorp.com/consul/getting-started/install
Also need to install mongodb as database.

### Create database in mongodb
Create  oauth-db in mongodb
Create collection as authClientDetails
Insert following document

```
{
    "clientId": "browser",
    "clientSecret": "$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK",
    "scopes": "ui",
    "grantTypes": "refresh_token,password,client_credentials"
}

{
    "clientId": "account-service",
    "clientSecret": "$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK",
    "scopes": "server",
    "grantTypes": "refresh_token,client_credentials,password"
}
```
Create collection as user
Insert following document
```
{
    "activated": true,
    "authorities": ["ROLE_USER"],
    "password": "$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK",
    "username": "randomuser"
}
```
## Description of component
ZuulServer -- gateway for all services. You have access services from gateway only. In real environment you will face CROS issue because all services are running on different ports. Gateway resolve that issue
AuthService -- It is a OAuth server which will authenticate and authorize user using spring security.
AccountService -- It is a Resource server. All other service will be resource server.

### Execution Steps
* Start Consul server
* Run Zuul service
* Run Auth service
* Lastly Run Account service

#### Zuul Service
This only work as gateway. Application.yml file contains routes which is very important part it will forward to service disovery. And service discovery will take route your request to runing services. You can run mutiple authservice and account service. Service discovery(consul) will take responsibility to route request to correct service. 

#### Auth service
This is authentication and authorization server. It will create a token and store it into database. All properties stored in bootstrap.yml because it will load before application start. Health check up api is require for consul. It used by consul for heart beat check else consul conside service is down.

#### Account Service
This is resource service. As per OAuth flow if you want to access resource servers you need OAuth token. Authorization flow is used in this example.

### API Flow
```
Post API 
  http://localhost:8081/uaa/oauth/token
  Form submission
  grant_type : password
  username : randomuser
  password : 1234
To generate token.

Respose will be like 
{
  "access_token": "fb5e13a7-e41e-4dc4-8ec9-6ed6db69c30c",
  "token_type": "bearer",
  "refresh_token": "2587af85-72da-426a-a4e1-babb1b71f2ac",
  "expires_in": 42910,
  "scope": "server"
}
```
```
Post API To create User
  http://localhost:8080/accounts/
  Header 
    Authorization: Bearer fb5e13a7-e41e-4dc4-8ec9-6ed6db69c30c
  Body
    {
      "username":"NewUser",
      "password":"1234"
    }
Respose will be like
    {
  "id": "5eb65fd5ecef38584d967aaf",
  "username": "NewUser"
  }
```
This api internally calls auht service for user creation. As we have used oauth sso resouce server with FeignClient will
relay token to calling service. Used EnableOAuth2Sso annotaion in resource server.

It also have one more API
```
Get Current user
  http://localhost:8080/uaa/user/current
  Header 
    Authorization: Bearer fb5e13a7-e41e-4dc4-8ec9-6ed6db69c30c
```
