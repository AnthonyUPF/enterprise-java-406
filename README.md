# enterprise-java-404-AnthonyUPF

[Lab 404.postman_collection.json](Lab%20404.postman_collection.json)


    1-Did you use the same type of route to update patient information and to update an employee department?

            Not really, to update the patient information use a route with a request of type PUT and to update 
            the information of the department of the employee use a route with a request of type PATCH

    2-Why did you choose the strategy that you chose?

           I selected those options because they seemed intuitive to me according to the actions I planned to take, since 
            in the first case I update all the patient parameters and in the second only one parameter of the employee         

    3-What are the pros and cons of the strategies you chose for creating these routes?

            As a pro it would be that the requests become intuitive, as a con it would be that it is easy to make ambiguity errors

    4-What are the tradeoffs between PUT and PATCH?

            PUT and PATCH are HTTP methods used in REST APIs to update an existing resource. The main tradeoff between these two methods 
            is the level of specificity in the update.

            PUT: Is used to update a resource in its entirety, meaning that the entire resource representation must be sent to the server 
            in the request body. The server will replace the existing resource with the one provided in the request. Guarantees that the resource 
            state will be the same as the representation sent in the request.

            PATCH: Is used to make partial updates to a resource, meaning that only the fields that need to be updated are sent to the server in the 
            request body. The server will apply the updates to the existing resource and leave the other fields unchanged. Does not guarantee that the 
            resource state will be the same as the representation sent in the request.

            In summary, use PUT when you need to replace the entire resource and use PATCH when you need to make partial updates to the resource.






