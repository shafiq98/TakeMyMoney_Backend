# TakeMyMoney Backend
Super secret payment system 

## Aim
To build a dependable contactless payment system that completes transfers securely, while requiring the lowest amount of user interaction possible

## How to use?
Pre-requisites
1. Ensure docker is installed (for deployment)
2. Ensure java 11 installed (for local development)
3. Ensure Postman is installed (for testing)

### Steps
1. sudo docker build -t [your_choice_of_name] .
2. sudo docker run -p 8080:8080 -d [your_choice_of_name]
3. sudo docker ps
   a. Ensure the docker container is running
4. Import collection & environment into Postman from the postman folder 
   1. Click on Import
   2. Drag and drop both files (collection & environment)
5. Postman Steps
   1. Run Initialize from the Local folder within the collection
   2. Run login (receiver) request
   3. Run address request
   4. Run login (sender) request
   5. Run create transaction (request)
   6. Run getAllUsers to ensure that the balance was updated

## What just happened?
You just simulated the following:
1. Create a few users
2. Broadcast a receivers address
3. Sent money to the receiver from a sender's account

# NOTE
1. Do not run initialize multiple times
2. No proper responses will be sent when an api call fails (bad request, etc)
   1. Will be introduced in future updates
3. You may not need to run the docker commands in sudo, depending on your device configuration
4. DO NOT run initialize on the ALIBABA system
