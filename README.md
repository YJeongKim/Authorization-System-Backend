# Authorization-System 🔐️

<br>

## 🎯 Requirements Spec

- 사용자 DB 설계

- 가입, 로그인 페이지

- 인증 서버 (API)

- RDBMS DB 사용 (MySQL, PostgreSQL)

- Password Encryption

- 유저 관리 페이지 (Admin/BackOffice)

- E-Mail 인증

- 비밀번호 찾기

- 캐시

<br>

## 🎮 Skill

### Backend

`Java` `SpringBoot` `Gradle` `H2Database(test)` `MySQL(local)` `Redis` `SpringDataJPA` `SpringCloud`

### Frontend

`React` `JavaScript` `SCSS`

<br>

## Architecture

![msa-server](https://user-images.githubusercontent.com/33328991/150264659-39b188a5-ef4a-46d8-baa6-114d135e91a2.png)

<br>

### DataBase Modeling

(추가 예정)

<br>

## 🧩 API Spec

> Request URL : http://localhost:9000/

<br>

## Auth-Service

### `POST` /api/auth/token

```
✔️ 로그인을 수행하고 Json Web Token을 발급한다.
  내부적으로 `POST` /api/users/signIn 호출
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
          "password" : "string/비밀번호"
      }
      ```

- Response

    - Status Line

        ```bash
        200 OK
        400 Bad Request         // 상황에 따라 message 출력
        ```

    - Body

      ```json
      {
          "id"   : "string/user Id",
          "nickname"  : "string/닉네임"
      }
      ```

<br>

## User-Service

### `POST` /api/users

```
✔️ User 정보를 조회한다.
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
          "name"  : "string/이름",
          "nickname"  : "string/닉네임"
      }
      ```

<br>

(나머지 API는 추후 작성)