# 스프링 부트 도서 관리 시스템

## 1. 스프링 부트 설정

## 2. 도서 엔티티(Book) 정의
- **id (Long)** : 기본 키, 자동 증가
- **title (String)** : 책 제목
- **author (String)** : 저자
- **publishedYear (int)** : 출판 연도
- **genre (String)** : 장르

## 3. BookRepository 인터페이스 생성

## 4. API 기능 구현 (BookController)
- **전체 도서 조회**: GET /books
- **도서 조회 by ID**: GET /books/{id}
- **새로운 도서 추가**: POST /books (JSON 요청 바디에 도서 정보 포함)
- **도서 정보 수정**: PUT /books/{id} (수정할 도서 정보 포함)
- **도서 삭제**: DELETE /books/{id}

## 5. Postman 사용하여 API 테스트 수행

### 각 기능에 대한 Postman 테스트 결과 스크린샷
- 전체 도서 조회 (GET /books): 응답으로 반환된 도서 목록의 스크린샷
- 도서 조회 by ID (GET /books/{id}): 특정 도서의 정보가 정상적으로 조회된 스크린샷
- 새로운 도서 추가 (POST /books): 도서가 성공적으로 추가된 후의 응답 스크린샷
- 도서 수정 (PUT /books/{id}): 도서 정보가 정상적으로 수정된 응답 스크린샷
- 도서 삭제 (DELETE /books/{id}): 삭제 요청이 성공적으로 처리된 응답 스크린샷
