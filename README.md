
### Steps to run the project  on the docker host machine- 


1. Spin up the database container first using following command 
    #### $ docker run  --name mysql --hostname db_host -v /opt/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mycl0ud -d mysql

    Above command will start the container with name - mysql from official MySQL docker image. 

    If you're running the project first time, please run following commands to set up database 

    1.Connect to mysql container 
      #### $ docker exec -it mysql bash
    2. Login to mysql console  
      #### $ #mysql -u root -p
      Use the password set in above docker run command
    3. Create the database -
      #### mysql> create database blog_db;   
      uncomment  the following line in src/main/resources/application.properties with the value "create"      spring.jpa.properties.hibernate.hbm2ddl.auto = create

2. Spin up the Application Server container  using following command 
   #### $ docker run -it  --hostname app_host -d --name my-maven-project -v "$(pwd)":/usr/src/mymaven  -v maven-repo:/root/.m2 --link      mysql:mysql -w /usr/src/mymaven maven:3.3-jdk-8  mvn spring-boot:run

   Above command will start the container with name - my-maven-project from official Maven docker image and run spring boot application. 
   This container will be linked to database mysql container 

3. Spin up the Nginx container using following command 

    #### docker run --name nginx_proxy --link my-maven-project:my-maven-project -d -p 8090:443 -v `pwd`:/etc/nginx/:ro nginx
    Above command will start nginx container from official Nginx docker image with config defined in nginx.conf and link to my-maven-project container. 

  Self signed certs can generated using following command   
  ##### openssl req -newkey rsa:2048 -nodes -keyout my-site.com.key -x509 -days 365 -out my-site.com.crt

4. Finally, Access the application using following link 

  https://localhost:8090/home

