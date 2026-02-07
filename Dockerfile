FROM openjdk:27-ea-oraclelinux9

WORKDIR /app

COPY  build/libs/app-fat.jar  .

COPY start.sh .

EXPOSE 5656

ENTRYPOINT ["sh","start.sh"]
