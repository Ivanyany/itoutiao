# Start with a base image containing Java runtime
FROM openjdk:8-jre-alpine

# author
MAINTAINER Ivan "ivan@163.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8001 available to the world outside this container
EXPOSE 8001

# The application's jar file 打包时传参设置名字 eg.--build-arg=JAR_FILE=itoutiao-0.0.1.jar
ARG JAR_FILE

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/app.jar"]


# 打包镜像的执行(将jar包和Dockerfile上传至服务器同一目录中)
# docker build --build-arg=JAR_FILE=itoutiao-0.0.1.jar -t itoutiao:0.0.1 .

# 运行容器
# docker run -d --name itoutiao -p 8001:8001 -e TZ=Asia/Shanghai -v /home/filePath/:/home/filePath/ itoutiao:0.0.1

