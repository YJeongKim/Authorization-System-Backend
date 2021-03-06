# Authorization-System ๐๏ธ

<br>

## ๐ฏ Requirements Spec

- ์ฌ์ฉ์ DB ์ค๊ณ

- ๊ฐ์, ๋ก๊ทธ์ธ ํ์ด์ง

- ์ธ์ฆ ์๋ฒ (API)

- RDBMS DB ์ฌ์ฉ (MySQL, PostgreSQL)

- Password Encryption

- ์ ์  ๊ด๋ฆฌ ํ์ด์ง (Admin/BackOffice)

- E-Mail ์ธ์ฆ

- ๋น๋ฐ๋ฒํธ ์ฐพ๊ธฐ

- ์บ์

<br>

## ๐ฎ Skill

### Backend

`Java` `SpringBoot` `Gradle` `H2Database(test)` `MySQL(local)` `Redis` `SpringDataJPA` `SpringCloud`

### Frontend

`React` `JavaScript` `SCSS`

<br>

## Architecture

![msa-server](https://user-images.githubusercontent.com/33328991/150264659-39b188a5-ef4a-46d8-baa6-114d135e91a2.png)

<br>

### DataBase Modeling

(์ถ๊ฐ ์์ )

<br>

## ๐งฉ API Spec

> Request URL : http://localhost:9000/

<br>

## Auth-Service

### `POST` /api/auth/token

```
โ๏ธ ๋ก๊ทธ์ธ์ ์ํํ๊ณ  Json Web Token์ ๋ฐ๊ธํ๋ค.
  ๋ด๋ถ์ ์ผ๋ก `POST` /api/users/signIn ํธ์ถ
```

- Request
    - Start Line

        ```bash
        POST /api/auth/token
        ```

    - Body

      ```json
      {
          "email" : "string/Email",
          "password" : "string/๋น๋ฐ๋ฒํธ"
      }
      ```

- Response

    - Status Line

        ```bash
        200 OK
        400 Bad Request         // ์ํฉ์ ๋ฐ๋ผ message ์ถ๋ ฅ
        ```

    - Body

      ```json
      {
          "id"   : "string/user Id",
          "nickname"  : "string/๋๋ค์"
      }
      ```

<br>

## User-Service

### `POST` /api/users

```
โ๏ธ User ์ ๋ณด๋ฅผ ์กฐํํ๋ค.
```

- Request
    - Start Line

        ```bash
        POST /api/users
        ```

- Response

    - Status Line

        ```bash
        200 OK
        400 Bad Request
        ```

    - Body

      ```json
      {
          "email"   : "string/Email",
          "name"  : "string/์ด๋ฆ",
          "nickname"  : "string/๋๋ค์"
      }
      ```

<br>

(๋๋จธ์ง API๋ ์ถํ ์์ฑ)