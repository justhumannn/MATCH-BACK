# API Specification

This document details the API endpoints for the MATCH-BACK project.

## 🔑 Authentication

The application uses Google OAuth2 for authentication. After a successful login, a JWT token is generated and passed via a query parameter or header.

- **Login URL**: `GET /oauth2/authorization/google`
- **JWT Header**: `Authorization: Bearer <token>`

---

## 🏆 Betting APIs

### 1. Get All Bettings
Retrieve a list of all available betting cards with statistics.

- **URL**: `/betting`
- **Method**: `GET`
- **Auth Required**: No
- **Response**: `ListBettingResponseDto`
  ```json
  {
    "listBettingResponseDto": [
      {
        "id": 1,
        "title": "World Cup Final",
        "blue": "Argentina",
        "red": "France",
        "result": "미정",
        "status": "진행 중",
        "time": "2026-04-12T23:00:00",
        "blueBettingCount": 150,
        "redBettingCount": 140,
        "blueBettingCost": 150000,
        "redBettingCost": 140000
      }
    ]
  }
  ```

### 2. Get Betting Detail
Retrieve detailed information for a specific betting card.

- **URL**: `/betting/{bettingId}`
- **Method**: `GET`
- **Auth Required**: No
- **Response**: `BettingResponseDto` (Same structure as above)

### 3. Participate in Betting
Submit a bet for a specific team.

- **URL**: `/betting`
- **Method**: `POST`
- **Auth Required**: Yes
- **Request Body**: `UserBettingRequestDto`
  ```json
  {
    "bettingId": 1,
    "bettingTeam": "blue",
    "bettingCost": 1000
  }
  ```
- **Response**: `200 OK`

### 4. Create Betting (Admin)
Create a new betting entry.

- **URL**: `/betting/save`
- **Method**: `POST`
- **Auth Required**: Yes (Required Role/Privilege)
- **Request Body**: `BettingAddRequestDto`
  ```json
  {
    "title": "Match Title",
    "blue": "Team A",
    "red": "Team B",
    "time": "2026-12-31T23:59:59"
  }
  ```
- **Response**: `Long (bettingId)`

### 5. Delete Betting (Admin)
Remove a betting entry.

- **URL**: `/betting/del/{bettingId}`
- **Method**: `DELETE`
- **Auth Required**: Yes
- **Response**: `200 OK`

---

## 👤 User APIs

### 1. My Page History
Retrieve the betting history of the authenticated user.

- **URL**: `/mypage`
- **Method**: `GET`
- **Auth Required**: Yes
- **Response**: `ListUserMyBettingResponseDto`
  ```json
  {
    "listUserMyBettingResponseDto": [
      {
        "title": "World Cup Final",
        "bettingTeam": "blue",
        "bettingCost": 1000
      }
    ]
  }
  ```

### 2. Home Info
Simple diagnostic endpoint.

- **URL**: `/home`
- **Method**: `GET`
- **Parameters**: `token` (optional)
- **Response**: String containing JWT info.

---

## ⚠️ Error Codes

| Error Code | HTTP Status | Description |
| :--- | :--- | :--- |
| `COMMON_500` | 500 | Internal Server Error |
| `USER_404` | 404 | User not found |
| `USER_400` | 400 | Insufficient balance |
| `BETTING_404` | 404 | Betting not found |
| `BETTING_400` | 400 | Betting already ended |
| `AUTH_401` | 401 | Unauthorized / Invalid token |
| `AUTH_403` | 403 | Forbidden |
