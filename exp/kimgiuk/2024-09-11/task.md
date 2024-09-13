# 도서 정보 REST API 기능 추가 및 JPA 변경

이전 미션에서 작성한 MyBatis를 사용하여 도서 정보 검색 REST API에 기능을 추가합니다.

## 1. 기능 목록
1. 전체 도서 목록 조회: **GET /books**
2. 도서 검색: **GET /books/{id}**
3. 도서 정보 등록: **POST /books**
4. 도서 정보 업데이트: **PUT /books/{id}**
5. 도서 정보 삭제: **DELETE /books/{id}**

모든 기능을 확인 후, MyBatis로 구현된 기존 API를 JPA로 변경합니다.

## 2. MyBatis를 사용한 도서 등록, 수정, 삭제 기능 구현
- 도서 등록: **POST /books**
- 도서 수정: **PUT /books/{id}**
- 도서 삭제: **DELETE /books/{id}**

## 3. 등록, 수정, 삭제 기능 확인
- 모든 기능이 잘 동작하는지 확인합니다.

## 4. MyBatis로 구현한 API를 JPA로 변경
- Book Entity 클래스 생성 (MyBatis Mapper 대체)
- BookRepository 인터페이스 구현: JPARepository Interface를 사용하여 CRUD 작업 수행
- BookService 변경

## 5. JPA로 변경한 API 확인
- JPA로 변경한 API가 올바르게 잘 동작하는지 확인합니다.

## 6. 각 항목 스크린샷
1. BookMapper.xml 및 BookMapper 인터페이스 파일 스크린샷
2. BookService.java (MyBatis 및 JPA 버전) 스크린샷
3. API 테스트 결과 스크린샷
