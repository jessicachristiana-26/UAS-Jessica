# UAS Jessica - Registrasi Mahasiswa

Project UAS berbasis Spring Boot untuk sistem registrasi mahasiswa.

## truktur Folder

UAS-mhs/
  demo/              # Source code Spring Boot
    registrasi_db.sql  # Database MySQL
      README.md

---

##  Tools yang Digunakan

- Java JDK 17+
- Spring Boot
- MySQL / phpMyAdmin
- Maven
- VS Code / IntelliJ

---

## Cara Menjalankan Project

### 1. Import Database

1. Buka phpMyAdmin
2. Buat database baru:

registrasi_db

3. Import file:

registrasi_db.sql

---

### 2. Jalankan Spring Boot

Masuk folder:

demo/

Lalu run:

mvn spring-boot:run

atau jalankan `DemoApplication.java`

---

### 3. Akses di Browser

http://localhost:8080/


http://localhost:8080/login
username = admin
password = admin 

---

##  Dibuat Oleh

Jessica Christiana
UAS Pemrograman Web / Java
