## Steps to run app (from the current directory) in local-docker environment:
###   1. Setup/Start a mysql container in docker
    docker pull mysql:5.6

    docker run --name pop-mysql-container -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=pop -e MYSQL_USER=newuser -e MYSQL_PASSWORD=password -d mysql:5.6

If you already have above container (pop-mysql-docker) then use => 
1. docker container stop {container id without braces}
2. docker container start {container id without braces}

### 2. Build the executable jar file

    mvn clean install

### 3. Build the docker image

    docker build . -t pop-starter:0.0.1

Note - If you already have a pop-starter image from old builds, then delete that image and related container as well.

### 4. Start the application container

    docker run -p 9000:9000 --name pop-starter-container --link pop-mysql-container -d pop-starter:0.0.1