FROM huangqijun/jdk17centos8:1.0.0
WORKDIR /usr/local/app
ARG JAR_FILE
ADD target/${JAR_FILE} bootdocker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/app/bootdocker.jar"]