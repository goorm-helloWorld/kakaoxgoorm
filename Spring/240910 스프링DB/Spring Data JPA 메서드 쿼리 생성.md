## Spring Data JPA - JpaRepository 적용

### 1. 공통 인터페이스: JpaRepository

Spring Data JPA는 `JpaRepository`라는 공통 인터페이스를 제공하여 기본적인 CRUD, 페이징 등의 기능을 제공합니다. 적용 방법은 간단하며, `JpaRepository<T, ID>`를 상속하는 인터페이스를 구현하면 됩니다.

- **T**: 엔티티 타입
- **ID**: 식별자 타입

```java
public interface EntityRepository extends JpaRepository<T, ID> {}

```

- `@Repository` 애너테이션은 생략 가능하며, Spring Data JPA가 컴포넌트 스캔 및 JPA 예외 처리를 스프링 예외로 자동 변환해줍니다.

---

### 2. JpaRepository 주요 메서드

| 메서드 | 설명 |
| --- | --- |
| `<S extends T> S save(S)` | 새로운 엔티티 저장 또는 기존 엔티티 병합 |
| `delete(T entity)` | 엔티티 삭제 |
| `Optional<T> findById(ID id)` | 식별자(ID)로 엔티티 조회 |
| `List<T> findAll()` | 모든 엔티티 조회 (정렬 및 페이징 옵션 가능) |

---

### 3. Query Creation - 메서드 이름으로 쿼리 생성

메서드 이름을 기반으로 쿼리를 생성할 수 있으며, JpaRepository를 상속한 인터페이스 내에 규칙에 맞게 메서드 선언만 하면 됩니다.

### 이름 규칙

- **조회**: `find...By`, `read...By`, `query...By`, `get...By`
- **COUNT**: `count...By` (반환 타입: long)
- **EXISTS**: `exists...By` (반환 타입: boolean)
- **삭제**: `delete...By`, `remove...By`
- **DISTINCT**: `findDistinct`, `findMemberDistinctBy`
- **LIMIT**: `findFirst`, `findTop`, `findTop3`

> 주의: 엔티티 필드명이 변경되면 인터페이스 메서드 이름도 함께 수정해야 합니다. 그렇지 않으면 애플리케이션 실행 시 오류가 발생할 수 있습니다.
>

---

### 4. 메서드 이름 쿼리 생성 예시

### 4-1. 기본 메서드 (상품 예시)

| 메서드 | 설명 | 예시 |
| --- | --- | --- |
| `findAll()` | 전체 데이터를 조회 | 모든 상품 목록을 조회합니다. |
| `findBy()` | 조건을 추가하여 전체 데이터를 조회 | 특정 조건으로 상품 목록을 조회합니다. (예: 카테고리, 가격) |
| `findTop5By()` | 조건에 맞는 상위 5건 데이터 조회 | 최신 등록된 상위 5개의 상품을 조회합니다. |
| `findDistinctBy()` | 중복을 제거하여 조회 | 중복된 상품 정보를 제거하고 조회합니다. |
| `findFirstBy()` | 조회된 데이터 중 첫 번째 데이터 조회 | 첫 번째로 등록된 상품을 조회합니다. |
| `count()` | 전체 행 수 조회 | 전체 상품 개수를 조회합니다. |
| `countBy()` | 조건에 맞는 전체 행 수 조회 | 특정 카테고리에 속하는 상품 개수를 조회합니다. |
| `save()` | 단일 데이터 저장 | 새로운 상품을 저장합니다. |
| `saveAll()` | 여러 건의 데이터 저장 | 여러 상품을 한 번에 저장합니다. |
| `delete()` | 단일 데이터 삭제 | 특정 상품을 삭제합니다. |
| `deleteAll()` | 여러 건의 데이터 삭제 | 전체 상품을 삭제합니다. |
| `deleteBy()` | 조건에 맞는 데이터 삭제 | 특정 조건을 충족하는 상품을 삭제합니다. (예: 재고가 0인 상품) |

---

### 4-2. 메서드 조건 규칙 (상품 예시)

| 조건 | 메서드 명명 규칙 예시 | 실제 생성된 쿼리 예시 |
| --- | --- | --- |
| **동일(=)** | `findByName` | `select ... from product where name = ?` (특정 이름의 상품 조회) |
| **대소(> , >= , < , <=)** | `> : findByPriceGreaterThan` | `select ... from product where price > ?` (특정 가격보다 비싼 상품 조회) |
|  | `>= : findByPriceGreaterThanEqual` | `select ... from product where price >= ?` (특정 가격 이상인 상품 조회) |
|  | `< : findByStockLessThan` | `select ... from product where stock < ?` (재고가 적은 상품 조회) |
|  | `<= : findByStockLessThanEqual` | `select ... from product where stock <= ?` (재고가 일정 이하인 상품 조회) |
| **범위(BETWEEN)** | `findByPriceBetween` | `select ... from product where price between ? and ?` (특정 가격 범위 내의 상품 조회) |
| **포함(LIKE, NOT LIKE)** | `findByNameContains` | `select ... from product where name like ? escape '\\\\'` (상품 이름에 특정 문자열이 포함된 상품 조회) |
|  | `findByNameNotContains` | `select ... from product where name not like ? escape '\\\\'` (특정 문자열이 포함되지 않은 상품 조회) |
| **시작, 끝 값 (startWith, endWith)** | `findByNameStartsWith` | `select ... from product where name like ? escape '\\\\'` (특정 문자열로 시작하는 상품 조회) |
|  | `findByNameEndsWith` | `select ... from product where name like ? escape '\\\\'` (특정 문자열로 끝나는 상품 조회) |
| **NULL 확인** | `findByDescriptionIsNull` | `select ... from product where description is null` (설명이 없는 상품 조회) |
|  | `findByDescriptionIsNotNull` | `select ... from product where description is not null` (설명이 있는 상품 조회) |
| **IN 조건** | `findByCategoryIn` | `select ... from product where category in (?)` (특정 카테고리에 속하는 상품 조회) |
|  | `findByCategoryNotIn` | `select ... from product where category not in (?)` (특정 카테고리에 속하지 않는 상품 조회) |

---

### 4-3. 정렬 규칙 (상품 예시)

| 정렬 종류 | 메서드 명명 규칙 예시 | 실제 생성된 쿼리 예시 |
| --- | --- | --- |
| **오름차순** | `findByOrderByPrice` | `select ... from product order by price asc` (가격 오름차순으로 상품 정렬) |
| **내림차순** | `findByOrderByPriceDesc` | `select ... from product order by price desc` (가격 내림차순으로 상품 정렬) |
| **컬럼 여러 개** | `findByOrderByPriceDescNameDesc` | `select ... from product order by price desc, name desc` (가격과 이름을 기준으로 내림차순 정렬) |

---

### 5. Pageable, Sort 파라미터 사용 (상품 예시)

특별한 파라미터로 `Pageable`, `Slice`, `Sort` 등을 사용하여 상품 데이터를 조회할 수 있습니다.

```java
// Pageable을 사용하여 특정 이름의 상품을 페이지 단위로 조회
Page<Product> findByName(String name, Pageable pageable);

// Slice를 사용하여 특정 이름의 상품을 슬라이스 단위로 조회
Slice<Product> findByName(String name, Pageable pageable);

// Sort를 사용하여 특정 이름의 상품을 정렬된 리스트로 조회
List<Product> findByName(String name, Sort sort);

// Pageable을 사용하여 특정 이름의 상품을 페이지 단위로 정렬 조회
List<Product> findByName(String name, Pageable pageable);

```

### 사용 예시

- `Pageable`을 사용하면 페이지 번호와 페이지 크기를 기반으로 상품을 조회할 수 있습니다. 예를 들어, 특정 카테고리의 상품을 10개씩 조회하거나, 원하는 페이지로 이동하여 조회할 수 있습니다.
- `Sort`를 사용하면 상품의 가격 또는 이름을 기준으로 오름차순 혹은 내림차순으로 정렬하여 상품을 조회할 수 있습니다.

### 예시 쿼리

```sql
-- 이름이 'Laptop'인 상품을 페이지 단위로 조회
select * from product where name = 'Laptop' limit ?, offset ?;

-- 이름이 'Laptop'인 상품을 가격 기준으로 정렬하여 조회
select * from product where name = 'Laptop' order by price asc;

-- 이름이 'Laptop'인 상품을 가격과 재고 기준으로 정렬하여 조회
select * from product where name = 'Laptop' order by price asc, stock desc;

```

---