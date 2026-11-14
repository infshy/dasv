# Dựng môi trường chạy thử trên VPS

Hướng dẫn cho Ubuntu 22.04 LTS. Ứng dụng: Spring Boot 4 (Java 21) + MySQL 8.

## 1. Cài đặt phần mềm
```bash
sudo apt update && sudo apt install -y openjdk-21-jre-headless mysql-server nginx git
```

## 2. Cấu hình MySQL
```bash
sudo mysql -e "CREATE DATABASE campus_food_express CHARACTER SET utf8mb4;
CREATE USER 'campus'@'localhost' IDENTIFIED BY '<MAT_KHAU_MANH>';
GRANT ALL ON campus_food_express.* TO 'campus'@'localhost';"
sudo mysql campus_food_express < campus_food_express.sql   # dữ liệu mẫu
```

## 3. Build & chạy
```bash
git clone https://github.com/infshy/dasv.git && cd dasv
./mvnw -DskipTests package
export SPRING_DATASOURCE_USERNAME=campus
export SPRING_DATASOURCE_PASSWORD='<MAT_KHAU_MANH>'
java -jar target/express-0.0.1-SNAPSHOT.jar
```
Spring Boot tự đọc biến môi trường `SPRING_DATASOURCE_*` thay cho giá trị trong `application.properties`.

## 4. Chạy nền bằng systemd
`/etc/systemd/system/campusfood.service`:
```ini
[Unit]
Description=Campus Food Express
After=mysql.service

[Service]
User=ubuntu
Environment=SPRING_DATASOURCE_USERNAME=campus
Environment=SPRING_DATASOURCE_PASSWORD=<MAT_KHAU_MANH>
ExecStart=/usr/bin/java -jar /home/ubuntu/dasv/target/express-0.0.1-SNAPSHOT.jar
Restart=always

[Install]
WantedBy=multi-user.target
```
```bash
sudo systemctl daemon-reload && sudo systemctl enable --now campusfood
```

## 5. Nginx reverse proxy
```nginx
server {
  listen 80;
  server_name _;
  location / {
    proxy_pass http://127.0.0.1:8080;
    proxy_set_header Host $host;
    proxy_set_header X-Forwarded-For $remote_addr;
    proxy_set_header X-Forwarded-Proto $scheme;
  }
}
```

## 6. Kiểm tra
`curl http://<IP_VPS>/api/products/categories` phải trả JSON `"success": true`.

> Ghi chú: `application.properties` hiện chứa mật khẩu MySQL mặc định cho máy dev; trên VPS luôn ghi đè bằng biến môi trường.
