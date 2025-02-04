# 일정 관리 앱
- Spring boot를 활용하여 일정 관리 앱을 구현
- Postman을 활용하여 요청 및 응답 확인
- 개발 기간 : 2025.01.27 ~ 2025.02.03
- 개발 환경
  - environment : IntelliJ IDEA, git, github
  - development : JAVA JDK 17, Spring Boot 3.4.2, JDBC, MySql 8.4.4, Swagger 2.7.0
  

# 구현 내용
<details>
    <summary>요구 및 구현사항 정리</summary>

## Lv 1. 일정 생성 및 조회 (필수)

| 기능 | 설명 | 조건 | 구현 여부|
|------|------|------|---|
| **일정 생성** | 일정 데이터를 저장해야 함 | `할일`, `작성자명`, `비밀번호`, `작성/수정일` 저장 |✔|
| | 작성/수정일은 날짜와 시간을 포함 | `YYYY-MM-DD HH:MM:SS` 형식 |✔|
| | 일정의 고유 ID를 자동 생성 | UUID 또는 Auto-Increment 방식 |✔|
| | 최초 생성 시 작성일과 수정일이 동일 | `작성일 = 수정일` |✔|
| **전체 일정 조회** | 등록된 일정 목록을 조회 | `수정일`, `작성자명` 기준 |✔|
| | 수정일 기준 내림차순 정렬 | 최신 일정이 상단에 위치 |✔|
| | 검색 조건을 만족하지 않아도 조회 가능 | 검색 조건 없이도 전체 조회 가능 |△|
| **선택 일정 조회** | 특정 일정을 단건 조회 | 일정 ID를 통해 조회 |✔|

## Lv 2. 일정 수정 및 삭제 (필수)

| 기능 | 설명 | 조건 | 구현 여부|
|------|------|------|---|
| **일정 수정** | 일정 내용 일부만 수정 가능 | `할일`, `작성자명`만 수정 가능 |✔|
| | 비밀번호 확인이 필요 | `비밀번호` 일치해야 수정 가능 |✔|
| | 작성일은 변경할 수 없음 | `작성일` 유지 |✔|
| | 수정 완료 시 수정일 갱신 | `수정일 = 현재 시각` |✔|
| **일정 삭제** | 특정 일정을 삭제 | 일정 ID를 통해 삭제 |✔|
| | 비밀번호 확인이 필요 | `비밀번호` 일치해야 삭제 가능 |✔|

## Lv 3. 연관 관계 설정 (도전)

| 기능 | 설명 | 조건 | 구현 여부|
|------|------|------|---|
| **작성자와 일정 연결** | 작성자를 식별할 수 있도록 변경 | 작성자 고유 ID 추가 |✔|
| | 작성자 정보를 별도 관리 | `이름`, `이메일`, `등록일`, `수정일` 포함 |✔|
| | 일정 테이블에 작성자 ID 추가 | FK(외래키) 설정 |✔|
| **전체 일정 조회 개선** | 일정 검색 시 작성자 ID 활용 | 일정 조회 시 FK 기준으로 검색 |✔|

## Lv 4. 페이지네이션 (도전)

| 기능 | 설명 | 조건 | 구현 여부|
|------|------|------|--|
| **일정 목록 페이징** | 많은 데이터를 효율적으로 조회 | `페이지 번호`, `페이지 크기` 요청 |✔|
| | 요청한 페이지 범위의 데이터만 조회 | 지정된 개수만 반환 |✔|
| | 조회 결과에 작성자명 포함 | `작성자명` 표시 |△|
| | 범위를 초과한 경우 빈 배열 반환 | 데이터가 없으면 `[]` 반환 |✔|

## Lv 5. 예외 발생 처리 (도전)

| 기능 | 설명 | 조건 | 구현 여부|
|------|------|------|---|
| **예외 처리** | 수정/삭제 시 비밀번호 검증 실패 | `비밀번호 불일치` 예외 발생 |✔|
| | 존재하지 않는 일정 조회 시 예외 | `잘못된 정보`, `이미 삭제됨` 예외 발생 |✔|
| | 공통 예외 처리를 구현 가능 | `@ExceptionHandler` 활용 가능 |✔|

## Lv 6. null 체크 및 유효성 검증 (도전)

| 기능 | 설명 | 조건 | 구현 여부|
|------|------|------|---|
| **유효성 검사** | 할일 입력값을 제한 | 최대 `200자`, 필수 입력 |✔|
| | 비밀번호 필수 입력 | `NULL` 불가 |✔|
| | 이메일 형식 검증 | `@Valid` 활용 가능 |✔|

</details>

## API 명세서

|     기능     |  메소드   |       URL       |         요청          |     정상 응답       |                   에러 응답                    |
|:----------:|:------:|:---------------:|:-------------------:|:--------:|:------------------------------------------:|
|   일정 생성    |  POST  |   /schedules    |    request body     |      201 : Created   |   400 : Bad Request, <br/>404 Not Found    |
| 일정 조회(전체)  |  GET   |   /schedules    |    request param    |      200 : OK      |                     -                      |
| 일정 조회(단일)  |  GET   | /schedules/{id} |    request param      |     200 : OK      |               404 Not Found                |
|   일정 수정    | PATCH  | /schedules/{id} | request param, body |      200 : OK      | 400 : Bad Request,<br/> 401 : Unauthorized |
|   일정 삭제    | DELETE | /schedules/{id} |    request param    |  204 : No Content  |     401 : Unauthorized, 404 Not Found      |
|   작성자 생성   |  POST  |    /writers     |    request body     |   201 : Created   |             400 : Bad Request              |
| 작성자 조회(전체) |  GET   |    /writers     |          -          |               200 : OK      |                     -                      |
| 작성자 조회(단일) |  GET   |  /writers/{id}  |    request param    |               200 : OK      |         404 Not Found                                    |
|   작성자 삭제   | DELETE |      /writers/{id} |    request param    |  204 : No Content  |     404 Not Found                                        |


## ERD 다이어그램
![ERD](https://github.com/user-attachments/assets/5c417a0b-2197-42be-b924-2d956f35ff40)


## 프로젝트 구조
<details>
    
```
schedule
├── build.gradle🛠️
├── schedule.sql🛢️
└─src📁
    ├── main/java/com/example📁
    │     ├── exception📁
    │     │    ├── ErrorCode☕
    │     │    ├── GlobalExceptionHandler☕
    │     │    ├── InternalServerException☕
    │     │    ├── InvalidPasswordException☕
    │     │    ├── ScheduleNotFoundException☕
    │     │    └── UserNotFoundException☕
    │     ├── schedule📁
    │     │    ├── controller📁
    │     │    │    └── ScheduleController☕
    │     │    ├── dto📁
    │     │    │    ├── PageResponseDto☕
    │     │    │    ├── ScheduleCreateRequestDto☕
    │     │    │    ├── ScheduleRequestDto☕
    │     │    │    └── ScheduleResponseDto☕
    │     │    ├── entity📁
    │     │    │    └── Schedule
    │     │    ├── repository📁
    │     │    │    ├── JdbcTemplateScheduleRepository☕
    │     │    │    └── ScheduleRepository☕
    │     │    └── service📁
    │     │         ├── ScheduleService☕
    │     │         └── ScheduleServiceImpl☕
    │     ├── writer📁
    │     │    ├── controller📁
    │     │    │    └── WriterController☕
    │     │    ├── dto📁
    │     │    │    ├── WriterRequestDto☕
    │     │    │    ├── WriterResponseDto☕
    │     │    │    └── WriterWithSchedulesResponseDto☕
    │     │    ├── entity📁
    │     │    │    └── Writer☕
    │     │    ├── repository📁
    │     │    │    ├── JdbcTemplateWriterRepositoryImpl☕
    │     │    │    └── WriterRepository☕
    │     │    └── service📁
    │     │         ├── WriterService☕
    │     │         └── WriterServiceImpl☕
    │     └── ScheduleApplication☕
    │     
    └── resources📁
         └── application.properties⚙️
```
</details>

## 환경 변수
```
DATA_BASE_URL=${your DB_URL}
DATA_BASE_USER_NAME=${your DB_ID}
DATA_BASE_USER_PASSWORD=${your DB_PASSWORD}
```
## 구현 결과
<details>
    <summary>일정 생성</summary>
      <details>
          <summary>성공</summary>
        ![일정생성 성공](https://github.com/user-attachments/assets/f2c068d3-87c5-49f8-9c5f-6ad0a91d6896)
      </details>
      <details>
          <summary>실패(400)</summary>
      </details>
      <details>
          <summary>실패(404)</summary>
      </details>
</details>
<details>
    <summary>일정 조회(전체)</summary>
</details>
<details>
    <summary>일정 조회(단일)</summary>
<details>
          <summary>성공</summary>
      </details>
      <details>
          <summary>실패(404)</summary>
      </details>
</details>
<details>
    <summary>일정 수정</summary>
      <details>
          <summary>성공</summary>
      </details>
      <details>
          <summary>실패(400)</summary>
      </details>
      <details>
          <summary>실패(401)</summary>
      </details>
</details>
<details>
    <summary>일정 삭제</summary>
    <details>
          <summary>성공</summary>
      </details>
      <details>
          <summary>실패(401)</summary>
      </details>
      <details>
          <summary>실패(404)</summary>
      </details>
</details>
<details>
    <summary>작성자 생성</summary>
       <details>
          <summary>성공</summary>
      </details>
      <details>
          <summary>실패(400)</summary>
      </details>
</details>
<details>
    <summary>작성자 조회(전체)</summary>
</details>
<details>
    <summary>작성자 조회(단일)</summary>
    <details>
          <summary>성공</summary>
      </details>
      <details>
          <summary>실패(404)</summary>
      </details>
</details>
<details>
    <summary>작성자 삭제</summary>
    <details>
          <summary>성공</summary>
      </details>
      <details>
          <summary>실패(404)</summary>
      </details>
</details>
