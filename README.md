# Drone Transport System

A web service that contains controller for performing action on drones such as registering, loading medications. e.t.c

## SYSTEM REQUIREMENT
 - Java 8 and greater
 - Tomcat 8.5
 - Maven

<br>

## TO BUILD THIS PROJECT
- Clone the project  your local repository 
- Open directory in your terminal and run command ``` mvn clean compile ```

## TO RUN TEST
- Clone the project  your local repository 
- Open directory in your terminal and run command ``` mvn test ```

##  TO RUN THIS PROJECT 
-  Run command  ``` mvn package ```
- A war name ```DroneTransportSystem.war ```is generated which can be found in the target folder of the project
-  Deploy war in tomcat server.

## ASSUMPTIONS
- Drone battery level is set 100 , weight set to 20gr,  state set to IDLE at registration
- Drone state changes as action are been performed on the drone.




**Base URL**: <br>{{localhost}}/DroneTransportSystem/v1<br> 

<br>

#### Content List

- [Basic Information](#basic-information)
- [Drone Registration](#drone-registeration)
- [Load Drone Medication](#load-drone-medication)
- [Get Drone Items](#get-drone-items)
- [Available Drones](#available-drones)
- [Drone Battery Level](#drone-battery-level)
- [Other Functional API](#other-functional-api) 
- [Periodic Battery Logs](#periodic-battery-logs)

<br>

#### Basic Information

Request and Response bodies are in JSON format

**ERROR Response**

Status Code: **500** 

>Response Body: 

``` json
{
   "response": {
        "message": "An error occured",
        "status": false
    }
}
```

**SUCCESS Response**

Status Code: **200** 

>Response Body: As specified for each endpoint below

``` json
{
    "data":{
        "model":"Lightweight"
    },
   "response": {
        "message": "Drone fetched successfully",
        "status": true
    }
}
```

<br>

#### Drone Registration

**registering a drone**

>**POST** */drones* 

Request Body fields:
- **model**: Drone model. Allowed values (Lightweight, Middleweight, Cruiserweight, Heavyweight)
- **serial**: Drone serial number. Maximum of 100 characters.

>Request Body Sample: 

``` json
{	
    "model":"Cruiserweight",
    "serial":"855485FIR98DELIIMDKD9988"
}
```


Response Body fields:
- **data**: A JSON object containing the drone data;

>- **droneId**: Drone unique identifier
>- **weight**: The weight of the drone
>- **serial**: The serial number of the drone
>- **model**: The model of the drone
>- **battery**: The battery level of the drone
>- **state**: The state of the drone
>- **links**: Array of links to access other details related to the drone

- **count**: The total count of all the movies
- **self**: The URL to fetch all movies

>Response Body Sample: 

``` json
{
     "data": {
        "droneId": 1,
        "weight": 20,
        "serial": "855485FIR98DELIIMDKD9988",
        "model": "Cruiserweight",
        "battery": 100,
        "state": "IDLE",
        "links": [
            {
                "url": "{baseURL}/v1/drones/1",
                "self": "self"
            }
        ]
    },
     "response": {
        "message": "Drone save successfully",
        "status": true
    }
}
```

<br>

#### Load Drone Medication

**Load drone with medication**

**POST** */drones/{droneID}/medications* 

Request Body fields:
- **name**: Drone model. Allowed values ( allowed only letters, numbers, ???-???, ???_???)
- **weight**: Drone weight (String)
- **code**: Drone serial number. Maximum of 100 characters.
- **image**: Image Base64 String

>Request Body Sample: 

``` json
{	"name":"94949daldfeEA_-",
    "weight":"80",
    "code":"DKDKAD999_",
    "image":"data:image/png;base64,...."
}
```


Response Body fields:
- **data**: A JSON object containing the medication data;

>- **medId**: Medication unique identifier
>- **weight**: The weight of the medication
>- **name**: The name of the medication
>- **code**: The code of the medication
>- **droneId**: The droneId of the medication
>- **image**: Medication image base64 string
>- **links**: Array of links to access other details related to the medication
>Response Body Sample: 

``` json
{ 
    "data": {
        "medId": 1,
        "name": "94949daldfeEA_-",
        "weight": "80",
        "code": "DKDKAD999_",
        "droneId": 0,
        "image": "data:image/png;base64,...",
        "links": [
                {
                    "url": "http://localhost:9099/DroneTransportSystem/v1/drones/1/medications/1",
                    "self": "self"
                }
            ]
    },
    "response": {
        "message": "Medication saved successfully",
        "status": true
    }
}

```

#### Get Drone Items

**Get all drone medications**

>**GET** */drones/{droneID}/medications* 

Response Body fields:
- **data**: An array containing list of drone medications;

>- **medId**: Medication unique identifier
>- **weight**: The weight of the medication
>- **name**: The name of the medication
>- **code**: The code of the medication
>- **droneId**: The droneId of the medication
>- **image**: Medication image base64 string
>- **links**: Array of links to access other details related to the medication

>Response Body Sample: 
``` json

{
    "data": [
        {
            "medId": 1,
            "name": "94949daldfeEA_-",
            "weight": "8E+1",
            "code": "DKDKAD999_",
            "droneId": 1,
            "image": "data:image/png;base64,..",
            "links": [
                {
                    "url": "http://localhost:9099/DroneTransportSystem/v1/drones/1/medications/1",
                    "self": "self"
                }
            ]
        },
        {
            "medId": 1,
            "name": "94949daldfeEA_-",
            "weight": "8E+1",
            "code": "DKDKAD999_",
            "droneId": 1,
            "image": "data:image/png;base64,..",
            "links": [
                {
                    "url": "http://localhost:9099/DroneTransportSystem/v1/drones/1/medications/1",
                    "self": "self"
                }
            ]
        },

    ],
    "count": 2,
    "response": {
        "message": "Medications fetched successfully",
        "status": true
    }
}

```
#### Available Drones

**Get all available drones**

>**GET** */drones/free*

Response Body fields:
- **data**: An array containing list of available drones;

> Response Sample body
``` json
{
    "data": [
        {
            "droneId": 1,
            "weight": 100,
            "serial": "855485FIR98DELIIMDKD9988",
            "model": "Cruiserweight",
            "battery": 100,
            "state": "LOADING",
            "links": [
                {
                    "url": "http://localhost:9099/DroneTransportSystem/v1/drones/free/1",
                    "self": "self"
                }
            ]
        }
    ],
    "count": 1,
    "response": {
        "message": "Drones fetched successfully",
        "status": true
    }
}

```
#### Drone Battery Level

**Get drone battery level**

>**GET** */drones/{droneID}/battery* 

> Response Body fields:
- **battery**: Drone battery level;


``` json
{
    "battery": 100,
    "response": {
        "message": "Drone battery fetched",
        "status": true
    }
}

```


#### Other Functional API

##### GET ALL DRONES

**Get all drones**

>**GET** */drones* 

Response Body fields:
- **data**: An array containing list of available drones;

 Response Sample body
``` json
{
    "data": [
         {
            "droneId": 1,
            "weight": 100,
            "serial": "855485FIR98DELIIMDKD9988",
            "model": "Cruiserweight",
            "battery": 100,
            "state": "IDLE",
            "links": [
                {
                    "url": "http://localhost:9099/DroneTransportSystem/v1/drones/free/1",
                    "self": "self"
                }
            ]
        },
        {
            "droneId": 2,
            "weight": 100,
            "serial": "855485FIR98DELIIMDKD9988",
            "model": "Cruiserweight",
            "battery": 100,
            "state": "LOADING",
            "links": [
                {
                    "url": "http://localhost:9099/DroneTransportSystem/v1/drones/free/1",
                    "self": "self"
                }
            ]
        }
    ],
    "count": 2,
    "response": {
        "message": "Drones fetched successfully",
        "status": true
    }
}

```


##### GET DRONE BY ID

**Get drone by ID**

>**GET** */drones/{droneID}* 

Response Body fields:
- **data**: A JSON object of drone data;

 Response Sample body
``` json
{
    "data": {
        "droneId": 1,
        "weight": 120,
        "serial": "45D23SDF98OP",
        "model": "Cruiserweight",
        "battery": 100,
        "state": "LOADING",
        "links": [
            {
                "url": "http://localhost:8080/DroneTransportSystem/v1/drones/1",
                "self": "self"
            },
            {
                "url": "http://localhost:8080/DroneTransportSystem/v1/drones/1/medications",
                "self": "medications"
            }
        ]
    },
    "response": {
        "message": "Drone details successfull",
        "status": true
    }


}

```

##### GET Specifi Drone  Medication by ID

**Get medication by ID**

>**GET** */drones/{droneID}/medications/{medID}* 

Response Body fields:
- **data**: A JSON object of medications data;

 Response Sample body
``` json
{
 "data": {
        "medId": 1,
        "name": "Sad949daldfeEA_-",
        "weight": "100",
        "code": "ASGSFD999_",
        "droneId": 1,
        "image": "data:image/png;base64,....",
        "links": [
            {
                "url": "http://localhost:8080/DroneTransportSystem/v1/drones/1/medications/1",
                "self": "self"
            }
        ]
    },
    "response": {
        "message": "Medications details fetched successfully",
        "status": true
    }



}
```

#### Periodic Battery Logs
 - A  battery log  text file is created in the tomcat logs folder that records battery level with drone ID.