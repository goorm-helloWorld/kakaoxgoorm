### **JPA에서 @PrePersist와 @PreUpdate 어노테이션**

**1. @PrePersist 어노테이션**
- **역할**: 엔티티가 **데이터베이스에 저장되기 전에** 호출되는 메서드를 정의하는 어노테이션.
- **사용 시점**: 새로운 엔티티가 처음 영속화(persist)되기 직전에 호출됩니다. 즉, 데이터베이스에 **Insert**하기 전에 실행됩니다.
- **예시**: 생성 시점(`createdAt`)과 수정 시점(`updatedAt`)을 현재 시간으로 설정하는 데 주로 사용됩니다.

```java
@PrePersist
public void prePersist() {
    long currentTimeMillis = System.currentTimeMillis();
    this.createdAt = currentTimeMillis;
    this.updatedAt = currentTimeMillis;
}
```

**2. @PreUpdate 어노테이션**
- **역할**: 엔티티가 **업데이트되기 전에** 호출되는 메서드를 정의하는 어노테이션.
- **사용 시점**: 기존 엔티티가 수정되어 **Update**되기 직전에 호출됩니다.
- **예시**: 수정 시점(`updatedAt`)을 최신 시간으로 갱신하는 데 사용됩니다.

```java
@PreUpdate
public void preUpdate() {
    this.updatedAt = System.currentTimeMillis();
}
```

---

### **BaseEntity 클래스**
`BaseEntity` 클래스는 엔티티의 공통적인 필드와 동작을 정의하고, 이를 상속받는 엔티티에서 재사용할 수 있도록 설계되었습니다.

- **`createdAt`**: 엔티티가 처음 생성된 시간.
- **`updatedAt`**: 엔티티가 마지막으로 수정된 시간.
- **`@MappedSuperclass`**: 이 클래스를 상속하는 엔티티들이 이 클래스의 필드와 메서드를 상속받도록 해줍니다.

**BaseEntity 클래스 코드**

```java
@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
    protected Long createdAt;
    protected Long updatedAt;
    
    // 엔티티가 DB에 Insert되기 전에 호출됨
    @PrePersist
    public void prePersist() {
        long currentTimeMillis = System.currentTimeMillis();
        this.createdAt = currentTimeMillis;
        this.updatedAt = currentTimeMillis;
    }
    
    // 엔티티가 DB에 Update되기 전에 호출됨
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}
```

---

### **BoardEntity 클래스**
`BoardEntity`는 `BaseEntity`를 상속받아, 게시글(게시판)의 정보를 관리하는 엔티티입니다.

- **`id`**: 게시글의 고유 식별자.
- **`title`**: 게시글의 제목.
- **`content`**: 게시글의 내용.
- 이 엔티티는 `BaseEntity`에서 상속받은 `createdAt`과 `updatedAt` 필드도 포함하게 됩니다.

**BoardEntity 클래스 코드**

```java
@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
}
```

---

### **정리**
- **@PrePersist**: 엔티티가 처음 데이터베이스에 **저장될 때(Insert)** 호출되는 메서드를 정의. `createdAt`과 `updatedAt` 필드를 초기화하는 데 주로 사용.
- **@PreUpdate**: 엔티티가 **수정될 때(Update)** 호출되는 메서드를 정의. `updatedAt` 필드를 최신 시간으로 갱신하는 데 사용.
- **BaseEntity**: 여러 엔티티에서 공통적으로 사용하는 필드와 메서드를 정의하여 코드의 중복을 줄이고 유지보수를 쉽게 함.