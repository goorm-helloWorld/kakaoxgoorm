-- 1. 고객 테이블에서 고객명, 생년월일, 성별 출력
SELECT clientName, clientBirth, clientGender
FROM mydb.client;

-- 2. 고객 테이블에서 주소만 검색하여 출력 (중복되는 주소는 한번만 출력)
SELECT DISTINCT clientAddress
FROM mydb.client;

-- 3. 고객 테이블에서 취미가 '축구'이거나 '등산'인 고객의 고객명, 취미 출력
SELECT clientName, clientHobby
FROM mydb.client
WHERE clientHobby = '축구' OR clientHobby = '등산';

-- 4. 도서 테이블에서 저자의 두 번째 위치에 '길'이 들어 있는 저자명 출력 (중복되는 저자명은 한번만 출력)
SELECT DISTINCT bookAuthor
FROM mydb.book
WHERE SUBSTRING(bookAuthor, 2, 1) = '길';

-- 5. 도서 테이블에서 발행일이 2020년인 도서의 도서명, 저자, 발행일 출력
SELECT bookName, bookAuthor, bookDate
FROM mydb.book
WHERE YEAR(bookDate) = 2020;

-- 6. 도서판매 테이블에서 고객번호 1, 2를 제외한 모든 판매 데이터 출력
SELECT *
FROM mydb.booksale
WHERE clientNo NOT IN ('C1', 'C2');

-- 7. 고객 테이블에서 취미가 NULL이 아니면서 주소가 '서울'인 고객의 고객명, 주소, 취미 출력
SELECT clientName, clientAddress, clientHobby
FROM mydb.client
WHERE clientHobby IS NOT NULL AND clientAddress LIKE '서울%';

-- 8. 도서 테이블에서 가격이 25,000원 이상이면서 저자 이름에 '길동'이 들어가는 도서의 도서명, 저자, 가격, 재고 출력
SELECT bookName, bookAuthor, bookPrice, bookStock
FROM mydb.book
WHERE bookPrice >= 25000 AND bookAuthor LIKE '%길동%';

-- 9. 도서 테이블에서 가격이 20,000 ~ 25,000원인 모든 도서 출력
SELECT *
FROM mydb.book
WHERE bookPrice BETWEEN 20000 AND 25000;

-- 10. 도서 테이블에서 저자명에 '길동'이 들어 있지 않는 도서의 도서명, 저자 출력
SELECT bookName, bookAuthor
FROM mydb.book
WHERE bookAuthor NOT LIKE '%길동%';

-- 11. 도서 테이블에서 가격 순으로 내림차순 정렬하여, 도서명, 저자, 가격 출력(가격이 같으면 저자 순으로 오름차순 정렬)
SELECT bookName, bookAuthor, bookPrice
FROM mydb.book
ORDER BY bookPrice DESC, bookAuthor ASC;

-- 12. 도서 테이블에서 저자에 ‘길동’이 들어가는 도서의 총 재고 수량 계산하여 출력
SELECT SUM(bookStock) AS totalStock
FROM mydb.book
WHERE bookAuthor LIKE '%길동%';

-- 13. 도서 테이블에서 ‘서울 출판사’ 도서 중 최고가와 최저가 출력
SELECT MAX(bookPrice) AS maxPrice, MIN(bookPrice) AS minPrice
FROM mydb.book
WHERE pubNo IN (SELECT pubNo FROM mydb.publisher WHERE pubName = '서울 출판사');

-- 14. 도서 테이블에서 출판사별로 총 재고 수량과 평균 재고 수량 계산하여 출력 (‘총 재고 수량’으로 내림차순 정렬)
SELECT pubNo, SUM(bookStock) AS totalStock, AVG(bookStock) AS avgStock
FROM mydb.book
GROUP BY pubNo
ORDER BY totalStock DESC;

-- 15. 도서판매 테이블에서 고객별로 ‘총 주문 수량’과 ‘총 주문 건수’ 출력 (단, 주문 건수가 2 이상인 고객만 해당)
SELECT clientNo, SUM(bsQty) AS totalQuantity, COUNT(bsNo) AS totalOrders
FROM mydb.booksale
GROUP BY clientNo
HAVING COUNT(bsNo) >= 2;
