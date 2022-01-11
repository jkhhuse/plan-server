# 环境
FROM  adoptopenjdk/openjdk11

# 创建目录存放 jar 包与配置文件
RUN mkdir -p /home/server

# 拷贝jar
ADD target/plan-server-1.0.jar /home/server/plan-server.jar

# 设置暴露的端口号
EXPOSE 8080

# 设置工作目录
WORKDIR /home/server

# 执行命令
ENTRYPOINT ["java","-jar","/home/server/plan-server.jar"]