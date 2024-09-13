# Spring Boot REST API 게시판 서비스 구현

기존의 Thymeleaf 기반 게시판 서비스는 그대로 두고, REST API를 추가하여 게시판 데이터를 클라이언트와 JSON 형식으로 주고받을 수 있도록 확장합니다.

## 1. PostRESTController 클래스 구현
- REST API 컨트롤러인 PostRestController를 추가합니다.
- CRUD 기능을 위한 API 엔드포인트를 구현:
    - `GET /api/posts`: 전체 게시글 목록 조회
    - `GET /api/posts/{id}`: 특정 게시글 상세 조회
    - `POST /api/posts`: 새로운 게시글 생성
    - `PUT /api/posts/{id}`: 게시글 수정
    - `DELETE /api/posts/{id}`: 게시글 삭제

## 2. 서비스 및 DTO 재사용
- 기존 PostService와 PostDto를 그대로 사용하여 비즈니스 로직을 재사용합니다.
- PostRestController에서는 해당 서비스를 통해 데이터를 처리하고, DTO를 사용하여 클라이언트와 데이터를 주고받습니다.

## 3. API 문서화 및 테스트
Swagger 설정을 추가하여 API 문서화를 자동화하고, Swagger UI를 통해 API 엔드포인트 및 기능을 확인할 수 있도록 합니다. API 동작을 테스트하고 확인합니다.

- PostRestController.java 파일
- Swagger UI 화면 스크린샷 (http://localhost:8080/swagger-ui/index.html)
