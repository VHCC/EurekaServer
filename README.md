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