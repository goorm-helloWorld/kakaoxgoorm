# MyBatis와 H2 데이터베이스를 활용한 도서 검색 및 전체 도서 목록 조회 REST API

## 1. 전체 도서 목록 조회
- **GET /books**

## 2. 도서 검색
- **GET /books/search**

## 3. H2 데이터베이스와 MyBatis 설정 진행

## 4. H2 데이터베이스에 Book 테이블 생성
- Book 테이블에 더미 데이터 저장
    - 예시 (꼭 같을 필요 없음):
      ```sql
      CREATE TABLE Book (
          id varchar primary key,
          title varchar,
          author varchar,
          publisher varchar,
          published_date DATE
      );
      ```

## 5. MyBatis Mapper 구현

## 6. 도서 DTO (BookDto) 구현

## 7. 서비스 계층 (BookService.java) 구현

## 8. REST 컨트롤러 (BookController.java) 구현

## 9. API 테스트

### 각 항목 스크린샷
- H2 콘솔에서 BOOK 테이블 생성 후 더미 데이터 추가 후 결과 스크린샷
- BookMapper.xml 파일 스크린샷
- BookDto.java 파일 스크린샷
- BookService.java 파일 스크린샷
- BookController.java 파일 스크린샷
