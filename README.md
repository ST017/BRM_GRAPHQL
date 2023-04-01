# BRM_GRAPHQL


## System requirements
```
java jdk 
nodejs 
any IDE with maven support 
have good internet access
```


# Steps

1. Run the schemagen.bat
2. Run the application schgrql



To generate schema 
run the batch file (schemagen.bat)
give the location of the infranet properties
give the object name ex:- /account
give the json file name  ex: account
give the project location 


Before running the project make sure the  jars are added to the project  classpath
run the project 
make sure  your server is up running


 Go to the postman 
 request  POST method
 endpoint  localhost:8090/graphql
 In body click GraphQL
 pass the query 
 
 example:-
 ```
query{
  search(poid:"72427",object:"/account"){
    flist{
      RESULTS {
        POID
        CREATED_T
      STATUS
        NAMEINFO{
            FIRST_NAME
           LAST_NAME
        }
      }
    }
  }
}
```
 
## make sure that generated schema object and  query object are same 
