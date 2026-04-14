# API 명세서

이 문서는 MATCH-BACK 프로젝트의 API 엔드포인트에 대한 상세 정보를 제공합니다.

## 🔑 인증 (Authentication)

본 애플리케이션은 Google OAuth2를 사용하여 인증을 처리합니다. 성공적으로 로그인하면 JWT 토큰이 생성되며, 쿼리 파라미터나 헤더를 통해 전달됩니다.

- **로그인 URL**: `GET /oauth2/authorization/google`
- **JWT 헤더**: `Authorization: Bearer <token>`

---

## 🏆 배팅 API (Betting APIs)

### 1. 모든 배팅 목록 조회
통계 정보를 포함한 사용 가능한 모든 배팅 카드 목록을 가져옵니다.

- **URL**: `/betting`
- **Method**: `GET`
- **인증 필요 여부**: 아니오
- **응답 (Response)**: `ListBettingResponseDto`
  ```json
  {
    "listBettingResponseDto": [
      {
        "id": 1,
        "title": "월드컵 결승전",
        "blue": "아르헨티나",
        "red": "프랑스",
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

### 2. 배팅 상세 조회
특정 배팅 카드에 대한 상세 정보를 가져옵니다.

- **URL**: `/betting/{bettingId}`
- **Method**: `GET`
- **인증 필요 여부**: 아니오
- **응답 (Response)**: `BettingResponseDto` (위와 동일한 구조)

### 3. 배팅 참여
특정 팀에 대해 배팅을 제출합니다.

- **URL**: `/betting`
- **Method**: `POST`
- **인증 필요 여부**: 예
- **요청 본문 (Request Body)**: `UserBettingRequestDto`
  ```json
  {
    "bettingId": 1,
    "bettingTeam": "blue",
    "bettingCost": 1000
  }
  ```
- **응답 (Response)**: `200 OK`

### 4. 배팅 생성 (관리자용)
새로운 배팅 항목을 생성합니다.

- **URL**: `/betting/save`
- **Method**: `POST`
- **인증 필요 여부**: 예 (관리자 권한 필요)
- **요청 본문 (Request Body)**: `BettingAddRequestDto`
  ```json
  {
    "title": "경기 제목",
    "blue": "팀 A",
    "red": "팀 B",
    "time": "2026-12-31T23:59:59"
  }
  ```
- **응답 (Response)**: `Long (생성된 bettingId)`

### 5. 배팅 결과 처리 (관리자용)
배팅 결과를 입력하고 (blue 또는 red 승리), 패배한 쪽의 포인트를 승리한 유저들에게 비율에 따라 분배(지급)합니다.

- **URL**: `/betting/{bettingId}/resolve`
- **Method**: `POST`
- **인증 필요 여부**: 예 (관리자 권한 필요)
- **요청 본문 (Request Body)**: `BettingResolveRequestDto`
  ```json
  {
    "winningTeam": "blue"
  }
  ```
- **응답 (Response)**: `200 OK`

### 6. 배팅 삭제 (관리자용)
배팅 항목을 제거합니다.

- **URL**: `/betting/del/{bettingId}`
- **Method**: `DELETE`
- **인증 필요 여부**: 예
- **응답 (Response)**: `200 OK`

---

## 👤 유저 API (User APIs)

### 1. 마이페이지 배팅 내역 조회
인증된 유저의 배팅 참여 이력을 가져옵니다.

- **URL**: `/mypage`
- **Method**: `GET`
- **인증 필요 여부**: 예
- **응답 (Response)**: `ListUserMyBettingResponseDto`
  ```json
  {
    "listUserMyBettingResponseDto": [
      {
        "title": "월드컵 결승전",
        "bettingTeam": "blue",
        "bettingCost": 1000
      }
    ]
  }
  ```

### 2. 홈 정보 조회
간단한 진단용 엔드포인트입니다.

- **URL**: `/home`
- **Method**: `GET`
- **파라미터**: `token` (선택 사항)
- **응답 (Response)**: JWT 정보를 포함한 문자열.

---

## ⚠️ 에러 코드 (Error Codes)

| 에러 코드 | HTTP 상태 | 설명 |
| :--- | :--- | :--- |
| `COMMON_500` | 500 | 서버 내부 에러 |
| `USER_404` | 404 | 유저를 찾을 수 없음 |
| `USER_400` | 400 | 잔액 부족 |
| `BETTING_404` | 404 | 배팅을 찾을 수 없음 |
| `BETTING_400` | 400 | 이미 종료된 배팅 |
| `BETTING_400` | 400 | 이미 결과가 처리된 배팅 (`BETTING_ALREADY_RESOLVED`) |
| `AUTH_401` | 401 | 인증되지 않음 / 유효하지 않은 토큰 |
| `AUTH_403` | 403 | 권한 없음 |
