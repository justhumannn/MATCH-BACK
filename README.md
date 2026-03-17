# MATCH-BACK

MATCH-BACK은 Java 기반의 백엔드 서비스입니다. 안정적인 서비스 제공과 손쉬운 배포를 위해 Gradle 빌드 도구와 Docker를 활용하여 구축되었습니다.

## 🛠 Tech Stack (기술 스택)

- **Language:** Java
- **Build Tool:** Gradle
- **Containerization:** Docker
- **Framework:** Spring Boot (추정)

## 📁 Project Structure (프로젝트 구조)

```text
MATCH-BACK/
├── src/                  # 소스 코드 및 리소스 디렉토리 (main/test)
├── gradle/wrapper/       # Gradle Wrapper 파일
├── build.gradle          # Gradle 빌드 스크립트 (의존성 및 플러그인 관리)
├── settings.gradle       # Gradle 설정 파일
├── gradlew               # Unix용 Gradle Wrapper 스크립트
├── gradlew.bat           # Windows용 Gradle Wrapper 스크립트
└── Dockerfile            # Docker 컨테이너 이미지 빌드 파일
```
## Getting Started (시작하기)
이 프로젝트를 로컬 환경에서 실행하고 테스트하기 위한 가이드입니다.

### Prerequisites (사전 요구 사항)
- Java 21+
- Docker (컨테이너 환경에서 실행할 경우)

### Installation & Run (설치 및 실행)
1. Repository Clone (저장소 클론)
```
git clone [https://github.com/justhumannn/MATCH-BACK.git](https://github.com/justhumannn/MATCH-BACK.git)
cd MATCH-BACK
```
2. 프로젝트 빌드
Gradle Wrapper를 사용하여 프로젝트를 빌드합니다.
```
# Mac / Linux
./gradlew build

# Windows
gradlew.bat build
```
3. 로컬에서 실행
```
# Mac / Linux
./gradlew bootRun

# Windows
gradlew.bat bootRun
```
### Docker로 실행하기
1. Docker 이미지 빌드
```
docker build -t match-back .
```
2. Docker 컨테이너 실행
```
docker run -d -p 8080:8080 --name match-back-app match-back
```
(포트 번호 8080은 프로젝트 설정에 맞게 변경하여 사용하세요.)
