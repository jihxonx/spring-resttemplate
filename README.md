# 📚 Spring RestTemplate 학습 프로젝트

Spring Boot에서 **RestTemplate을 활용한 서버 간 HTTP 통신**을 학습한 예제 프로젝트입니다.  
Server 프로젝트와 Client 프로젝트로 분리하여 실제 서비스 간 통신 구조를 구현했습니다.

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Web (RestTemplate)
- Lombok
- JSON (org.json)
- Gradle

---

## 📂 프로젝트 구조

```
spring-resttemplate/
├── spring-resttemplate-server/          # REST API를 제공하는 서버 (port: 7070)
│   ├── controller/
│   │   └── ItemController.java          # GET/POST 요청 처리 엔드포인트
│   ├── service/
│   │   └── ItemService.java             # 아이템 조회 비즈니스 로직 (인메모리 데이터)
│   ├── dto/
│   │   ├── ItemResponseDto.java         # 아이템 목록 응답 DTO
│   │   └── UserRequestDto.java          # 사용자 정보 요청 DTO (username, password)
│   └── entity/
│       └── Item.java                    # 아이템 엔티티 (title, price)
│
└── spring-resttemplate-client/          # RestTemplate으로 서버에 요청하는 클라이언트 (port: 8080)
    ├── controller/
    │   ├── RestTemplateController.java  # 클라이언트 API 엔드포인트
    │   └── naver/NaverApiController.java
    ├── service/
    │   ├── RestTemplateService.java     # getForEntity / postForEntity / exchange 사용 예시
    │   └── naver/NaverApiService.java   # 네이버 쇼핑 검색 Open API 연동
    ├── dto/
    │   ├── ItemDto.java
    │   └── naver/ItemDto.java           # 네이버 API 응답 파싱용 DTO
    └── entity/
        └── User.java                    # 요청 시 Body에 담을 사용자 정보
```

---

## 📌 학습 내용

### 1️⃣ RestTemplate 기본 사용법

- `RestTemplateBuilder`로 `RestTemplate` 빈 생성
- `UriComponentsBuilder`로 URI 생성 (QueryParam, PathVariable 포함)
- `getForEntity()` — GET 요청으로 단일 객체 또는 문자열 응답 받기
- `postForEntity()` — POST 요청으로 Body에 객체 담아 전송
- `exchange()` — 커스텀 Header 포함 요청 및 응답 처리

### 2️⃣ GET 요청

- **단일 객체 조회** (`getCallObject`) — `QueryParam`으로 검색어 전달, `ItemDto`로 바로 역직렬화
- **목록 조회** (`getCallList`) — 응답을 `String`으로 받은 후 `JSONArray`로 파싱하여 `List<ItemDto>` 변환

### 3️⃣ POST 요청

- **Path Variable + RequestBody** (`postCall`) — URL에 `{query}` 포함, Body에 `User` 객체 전송
- **커스텀 Header + RequestBody** (`exchangeCall`) — `RequestEntity`로 `X-Authorization` 헤더와 Body를 함께 구성하여 `exchange()` 호출

### 4️⃣ 외부 API 연동 (네이버 쇼핑 검색)

- `NaverApiService` — 네이버 Open API에 `X-Naver-Client-Id` / `X-Naver-Client-Secret` 헤더를 담아 `exchange()` 요청
- JSON 응답의 `items` 배열을 `JSONObject` → `ItemDto`로 파싱

---

## 🔗 API 엔드포인트

### 서버 (`localhost:7070`)

| Method | URL | 설명 |
|---|---|---|
| GET | `/api/server/get-call-obj?query=` | 쿼리로 아이템 단건 조회 |
| GET | `/api/server/get-call-list` | 전체 아이템 목록 조회 |
| POST | `/api/server/post-call/{query}` | PathVariable + Body로 아이템 조회 |
| POST | `/api/server/exchange-call` | 커스텀 헤더 + Body로 목록 조회 |

### 클라이언트 (`localhost:8080`)

| Method | URL | 설명 |
|---|---|---|
| GET | `/api/client/get-call-obj?query=` | 서버에 GET 단건 요청 |
| GET | `/api/client/get-call-list` | 서버에 GET 목록 요청 |
| GET | `/api/client/post-call?query=` | 서버에 POST 요청 |
| GET | `/api/client/exchange-call` | 서버에 헤더 포함 요청 (Authorization 헤더 필요) |

---

## ▶️ 실행 방법

두 프로젝트를 **각각 별도의 터미널**에서 실행합니다.

```bash
# 1. 서버 먼저 실행
cd spring-resttemplate-server
./gradlew bootRun

# 2. 클라이언트 실행
cd spring-resttemplate-client
./gradlew bootRun
```

---

## 🏆 핵심 정리

| 개념 | 설명 |
|---|---|
| `RestTemplate` | Spring에서 제공하는 동기 방식 HTTP 클라이언트 |
| `RestTemplateBuilder` | `RestTemplate`을 커스터마이징하여 빈으로 생성하는 빌더 |
| `UriComponentsBuilder` | URI를 타입 안전하게 조립하는 빌더 (QueryParam, PathVariable 지원) |
| `getForEntity()` | GET 요청 후 `ResponseEntity<T>`로 응답 수신 |
| `postForEntity()` | POST 요청 후 `ResponseEntity<T>`로 응답 수신 |
| `exchange()` | Method, Header, Body를 자유롭게 설정할 수 있는 범용 요청 메서드 |
| `RequestEntity<T>` | HTTP 요청 정보(URL, Method, Header, Body)를 하나로 묶은 객체 |
| `ResponseEntity<T>` | HTTP 응답 정보(Status, Header, Body)를 하나로 묶은 객체 |
| `JSONObject` / `JSONArray` | JSON 문자열을 파싱하여 데이터를 추출하는 유틸리티 |

---

## 📖 학습 현황 (Roadmap)

### ✅ 완료
- `RestTemplate` 빈 생성 (`RestTemplateBuilder`)
- `UriComponentsBuilder`로 URI 구성
- **`getForEntity()`** — GET 단건 / 목록 조회
- **`postForEntity()`** — POST 요청 + RequestBody 전송
- **`exchange()`** — 커스텀 Header 포함 요청
- **`RequestEntity` / `ResponseEntity`** 활용
- JSON 응답 수동 파싱 (`JSONObject`, `JSONArray`)
- **외부 API 연동** — 네이버 쇼핑 검색 Open API
