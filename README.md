# MATCH-BACK

![MATCH-BACK](https://img.shields.io/badge/Spring_Boot-3.5.4-6DB33F?logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-JSON_Web_Token-000000?logo=jsonwebtokens&logoColor=white)

**MATCH-BACK**은 배팅 플랫폼을 위한 고성능 백엔드 서버입니다. 배팅 생성, 유저 참여 관리 및 자동 상태 전환을 위한 견고한 아키텍처를 제공합니다.

## 🚀 주요 기능

- **OAuth2 소셜 로그인**: Google 소셜 로그인을 통한 간편한 인증 시스템.
- **JWT 기반 인증**: JSON Web Token을 이용한 보안 무상태(stateless) 인증.
- **배팅 관리**: 다양한 배팅 시나리오 생성, 목록 조회 및 참여 기능.
- **자동 종료 로직**: 배팅 만료 시 백그라운드 스케줄러를 통해 자동으로 상태를 "종료"로 전환.
- **유저 잔액 관리**: 배팅 참여를 위한 통합 포인트 기반 시스템.
- **마이페이지**: 개인별 배팅 히스토리 및 내역 조회.

## 🛠 기술 스택

- **Framework**: Spring Boot 3.5.4
- **Language**: Java 21
- **Database**: PostgreSQL (Supabase 관리형 DB)
- **Security**: Spring Security, OAuth2 Client, JJWT
- **Persistence**: Spring Data JPA
- **Build Tool**: Gradle

## 📁 프로젝트 구조

```text
src/main/java/post/post
├── domain/                      # 도메인 별 비즈니스 로직
│   ├── betting/                 # 배팅 관리 (Entity, Service, Controller)
│   ├── participation/           # 배팅 참여 및 내역 관리
│   └── user/                    # 유저 프로필 및 잔액 관리
├── global/                      # 글로벌 설정 및 유틸리티
│   ├── exception/               # 전역 예외 처리 및 에러 코드 정의
│   └── security/                # Security 설정, JWT, OAuth2 로직
└── PostApplication.java         # 애플리케이션 메인 엔트리 포인트
```

## ⚙️ 환경 설정

프로젝트를 실행하려면 `application.yml` 또는 시스템 속성에 다음과 같은 환경 변수를 설정해야 합니다:

- `SUPABASE_DB_URL`: PostgreSQL 접속 URL
- `SUPABASE_DB_USERNAME`: 데이터베이스 계명
- `SUPABASE_DB_PASSWORD`: 데이터베이스 비밀번호
- `GOOGLE_CLIENT_ID`: Google OAuth2 클라이언트 ID
- `GOOGLE_CLIENT_SECRET`: Google OAuth2 클라이언트 보안 비밀번호
- `JWT_SECRET_KEY`: JWT 서명을 위한 비밀 키
- `FRONT_URL`: 프론트엔드 애플리케이션 URL (기본값: `http://localhost:5173`)

## 🛣 API 명세서

상세한 API 명세는 [API_SPECIFICATION.md](./API_SPECIFICATION.md) 파일을 참조해 주세요.

## 🏃 시작하기

1. 저장소 복제:
   ```bash
   git clone https://github.com/justhumannn/MATCH-BACK.git
   ```
2. 위의 환경 변수를 설정합니다.
3. 프로젝트 빌드:
   ```bash
   ./gradlew build
   ```
4. 애플리케이션 실행:
   ```bash
   ./gradlew bootRun
   ```
