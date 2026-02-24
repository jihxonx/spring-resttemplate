# Spring RestTemplate Mono-repo

두 개의 Spring Boot 프로젝트로 구성된 RestTemplate 실습 프로젝트입니다.

```
spring-resttemplate-mono/
├── spring-resttemplate-server/   # 서버 (포트: 7070)
└── spring-resttemplate-client/   # 클라이언트 (포트: 8080)
```

## 프로젝트 구조

### 🖥️ spring-resttemplate-server
- **포트**: 7070
- **역할**: REST API를 제공하는 서버
- 주요 구성: `ItemController`, `ItemService`, `Item`, `ItemResponseDto`, `UserRequestDto`

### 📡 spring-resttemplate-client
- **포트**: 8080 (기본값)
- **역할**: RestTemplate을 사용해 서버에 HTTP 요청을 보내는 클라이언트
- 주요 구성: `RestTemplateController`, `RestTemplateService`, Naver API 연동 (`NaverApiController`, `NaverApiService`)

## 실행 방법

두 프로젝트를 **각각 별도의 터미널**에서 실행합니다.

### 1. 서버 먼저 실행
```bash
cd spring-resttemplate-server
./gradlew bootRun
# http://localhost:7070 에서 실행됨
```

### 2. 클라이언트 실행
```bash
cd spring-resttemplate-client
./gradlew bootRun
# http://localhost:8080 에서 실행됨
```

## 기술 스택
- Java 17+
- Spring Boot
- Spring Web (RestTemplate)
- Gradle
