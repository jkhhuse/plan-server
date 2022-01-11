# 环境
FROM  adoptopenjdk/openjdk11

# 拷贝jar
ADD target/plan-server-1.0.jar /plan-server.jar

# 设置暴露的端口号
EXPOSE 8080

# 执行命令
ENTRYPOINT ["java","-jar","plan-server.jar"]