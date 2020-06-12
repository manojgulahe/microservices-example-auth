# microservices-example-auth
Authentication is main component for microservices architeture. Many different types of architecture is available on internet.
I have implimented it with OAuth and Consul as service discovery.

## Prerequisites
Install Consul on your system. Download consul from https://www.consul.io/. 
Run consul using instruction from website https://learn.hashicorp.com/consul/getting-started/install
Also need to install mongodb as database.

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
This is resource service. As per OAuth flow if you want to access resource servers you need OAuth token. Authorization flow is used in source code.
