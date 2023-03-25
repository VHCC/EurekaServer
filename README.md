# Eureka Server : 服務註冊中心


### ngin setting
location /eureka-server {
proxy_set_header Host $host;
proxy_set_header Referer $http_referer;
proxy_set_header X-Real-IP $remote_addr;
proxy_set_header X-Forwarded-Port $server_port;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
proxy_set_header X-Forwarded-Proto $scheme;
proxy_pass http://127.0.0.1:8761/eureka-server;
}


## noted
Spring Cloud并不是停止维护，而是从2020年开始转向社区驱动的开发模式。这意味着它将不再由Pivotal公司负责维护，而是由社区成员、贡献者和企业共同负责维护。该项目仍然是活跃的，并且仍然有很多人在使用和开发它。


注册中心：Nacos、Eureka、Zookeeper、Consul
服务调用：LoadBalancer、Ribbon、OpenFeign（Feign）
服务熔断：Sentinel、Resilience4j、Hystrix
服务网关：Gateway、Zuul
服务配置：Nacos、Config
服务总线：Nacos、Bus

nohup java -jar eureka-server.jar  > /dev/null  2>&1 &

ps -ef|grep java