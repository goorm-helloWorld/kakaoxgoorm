![](https://i.ibb.co/hFVs2Nf/hibernate.png)

---

## JPA 기본 키 생성 전략

### 1. `@GeneratedValue(strategy=GenerationType.AUTO)`

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    
    // Getters and Setters
}

```

- **설명**: 데이터베이스 벤더에 따라 자동으로 키 생성 전략이 결정됩니다.
- **벤더별 전략**:
    - MySQL: `AUTO_INCREMENT`
    - PostgreSQL: `SERIAL`
    - Oracle: `시퀀스`
    - SQL Server: `IDENTITY`

### 2. `@GeneratedValue(strategy=GenerationType.IDENTITY)`

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    // Getters and Setters
}

```

- **설명**: 데이터베이스에서 기본 키 생성을 담당합니다 (`AUTO_INCREMENT`, `SERIAL`, `IDENTITY`).
- **특징**:
    - 기본 키가 `NULL`일 때 자동 증가.
    - `entityManager.persist()` 호출 시 즉시 `INSERT` 쿼리를 실행하며, 그때 식별자를 조회합니다.

**장점:**

- **간단한 구현**: DB의 자동 증가 로직을 사용하여 간단하게 적용할 수 있습니다.
- **성능 우수**: UUID 방식보다 오버헤드가 적어 성능이 더 뛰어납니다.
- **효율적인 저장 공간**: PK가 `BigInt` 타입이므로, UUID보다 적은 저장 공간을 차지합니다.

**단점:**

- **예측 가능성**: PK 값이 순차적으로 증가하기 때문에, 기존 또는 미래의 레코드를 유추하기 쉬워집니다.
- **악의적 공격에 취약**: PK가 순차적으로 증가하면서 테이블의 행 수를 추정할 수 있어 보안에 취약할 수 있습니다.
- **글로벌 유일성 보장 불가**: 동일한 테이블이나 데이터베이스 내에서만 유일성을 보장합니다.

> Identity 전략과 최신화: 
JDBC3에 추가된 Statement.getGenratedKeys() 를 사용하면 데이터를 저장하면서 동시에 생성된 기본 키 값도 얻어 올 수있다. 하이버네이트는 이 메소드를 사용해서 데이터베이스와 한번 만 통신 한다.
>

### 3. `@GeneratedValue(strategy=GenerationType.SEQUENCE)`

```java
@Entity
@SequenceGenerator(name = "seq_gen", sequenceName = "my_sequence", allocationSize = 1)
public class SequenceKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Long id;

    private String details;
    
    // Getters and Setters
}

```

- **설명**: 데이터베이스 시퀀스를 사용하여 기본 키를 생성하는 전략입니다. (`Oracle`, `PostgreSQL` 등에서 사용 가능).
- **특징**:
    - `@SequenceGenerator` 어노테이션과 함께 사용하여 시퀀스를 지정할 수 있습니다.
    - 시퀀스 값은 트랜잭션 시작 시점에 결정되어 미리 가져올 수 없습니다.
    - 여러 클라이언트에서 같은 시퀀스 값을 사용하면 충돌 가능성이 있습니다.

### 4. `@GeneratedValue(strategy=GenerationType.TABLE)`

```java
@Entity
@TableGenerator(name = "tbl_gen", table = "key_table", pkColumnName = "gen_name", valueColumnName = "gen_value", allocationSize = 1)
public class TableKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tbl_gen")
    private Long id;

    private String value;
    
    // Getters and Setters
}

```

- **설명**: 키 생성 전용 테이블을 사용하여 기본 키를 생성하는 전략입니다.
- **특징**:
    - 이식성이 높지만, 데이터베이스의 성능에 영향을 미칠 수 있으며 대량 데이터 처리 시 성능 저하 가능성.
    - 트랜잭션 충돌 가능성:
        - 두 트랜잭션이 동시에 같은 키 값을 사용하려 할 때 충돌 발생 가능.

### 5. `@GeneratedValue(strategy=GenerationType.UUID)`

```java
@Entity
public class UUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // java.util.uuid
}

// repository
public interface UUIDRepository extends JpaRepository<UUIDEntity, UUID> {
}
---------------------------------
@Entity
public class UUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}

// repository
public interface UUIDRepository extends JpaRepository<UUIDEntity, String> {
}

```

- **설명**: UUID(Universally Unique Identifier)를 사용하여 기본 키를 생성합니다.
- **특징**:
    - 시퀀스나 테이블을 생성할 필요 없이 고유성을 보장합니다.
    - 인덱싱 성능이 떨어질 수 있으며, 순서를 보장하지 않기 때문에 순서에 의존하는 로직에 부적합합니다.
    - 필드 타입이 uuid인 경우 db 컬럼 타입은 uuid. String인 경우 varchar

  **장점:**

    - **보안성 향상**: UUID는 랜덤하며 순차적이지 않아 PK를 추정할 수 없습니다.
    - **글로벌 유일성 보장**: 테이블과 데이터베이스를 넘어 전역적으로 유일한 값을 보장합니다.

  **단점:**

    - **성능 문제**: UUID는 순차적이지 않아 대규모 테이블에서 데이터 삽입 시 DB 성능이 저하될 수 있습니다. 데이터베이스는 비순차적인 UUID 때문에 테이블 행을 재정렬해야 하는 경우가 많습니다.
    - **저장 공간 증가**: UUID는 `BigInt`보다 약 4배 많은 공간을 차지하여 저장소 효율이 떨어집니다.

---

### **Auto Increment Key와 UUID: 선택 기준**

**Auto Increment Key를 사용할 때**:

- 엔티티의 PK가 외부 클라이언트에 노출되지 않는 경우.
- **성능과 저장 효율성**이 중요한 경우.

**UUID를 사용할 때**:

- PK가 외부 클라이언트에 노출되는 경우 (예: `https://www.youtube.com/watch?v=clFP_6pW_Y0`).
- PK가 **유추할 수 없는 고유한 값**이어야 하는 경우 (예: 쿠폰 코드, 민감한 데이터).

---

### **결론**

적절한 PK 매핑 전략을 선택함으로써 보안성과 성능 사이의 균형을 맞출 수 있습니다. Auto Increment Key는 간단하고 성능이 우수하지만, UUID는 클라이언트에 노출되는 엔티티에서 더 나은 보안성을 제공합니다.

---

ref

https://0soo.tistory.com/178

[https://fourjae.tistory.com/entry/Spring-boot-JPA-기본-키-생성-전략AUTO-IDENTITY-SEQUENCE-TABLE-UUID](https://fourjae.tistory.com/entry/Spring-boot-JPA-%EA%B8%B0%EB%B3%B8-%ED%82%A4-%EC%83%9D%EC%84%B1-%EC%A0%84%EB%9E%B5AUTO-IDENTITY-SEQUENCE-TABLE-UUID)