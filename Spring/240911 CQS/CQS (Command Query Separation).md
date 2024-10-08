# CQS (Command Query Separation)

CQS는 "명령"과 "질의"를 분리하는 원칙이에요. 이를 제안한 사람은 **Bertrand Meyer**라는 사람이에요. 이 원칙을 따르면 코드가 더 깨끗해지고, 예상치 못한 문제(사이드 이펙트)를 줄일 수 있어요.

### 핵심 개념

1. **Command (명령)**: 상태를 변경하는 작업을 해요. 예를 들면, 데이터를 저장하거나 수정하는 것처럼요. 하지만 이때는 **결과를 반환하지 않아요**.
    - 예시: "책 정보를 업데이트했어요!"라고만 하고, 다른 정보는 돌려주지 않는 거예요.

2. **Query (질의)**: 시스템의 상태를 바꾸지 않고, **그냥 데이터를 읽어서 반환해요**. 부작용이 없고, 데이터를 요청하는 것만 해요.
    - 예시: "책 정보를 알려줘!"라고 하면, 책의 제목이나 저자를 반환해요.

---

### **사이드 이펙트(부작용)란?**

CQS에서 부작용은 시스템의 내부 상태가 변경되는 걸 말해요. 만약 상태가 변경되지 않았다면, 부작용이 없는 거예요. 예를 들어, 책 정보를 조회만 한다면 부작용이 없지만, 책 정보를 수정하면 부작용이 있을 수 있어요.

---

### **CQS의 목적**

CQS의 목적은 명령(Command)과 질의(Query)를 확실히 구분해서, 예상치 못한 부작용을 줄이는 거예요. 그러면 시스템을 더 쉽게 이해하고 유지보수할 수 있죠.

---

### **JPA에서의 예시**

#### **1. Insert (Command): 데이터베이스에 새로운 정보를 추가하는 작업**

```java
@Transactional
public Long save(Item item) {
    em.persist(item); // 아이템 저장
    return item.getId(); // 저장된 아이템의 ID 반환
}
```
- 설명: 데이터를 저장하고, 그 데이터의 ID를 반환해요. 하지만 ID를 반환하는 건 CQS 원칙을 깨는 거예요. 원래 Command는 **아무것도 반환하지 않아야** 하거든요.

#### **2. Update (Command): 기존 데이터를 수정하는 작업**

```java
@Transactional
public void update(Item item) {
    em.persist(item); // 아이템 수정
}
```
- 설명: 데이터를 수정하고, 아무것도 반환하지 않아요. 이건 CQS 원칙을 잘 따르고 있는 예시예요.

#### **3. Select (Query): 데이터를 조회하는 작업**

```java
@Transactional
public Item findOne(Long id) {
    return em.find(Item.class, id); // 아이템 조회 후 반환
}
```
- 설명: 데이터를 조회하고 그 데이터를 반환해요. 이건 **부작용이 없고**, 조회만 하는 것이기 때문에 완벽한 Query예요.

---

### **Command와 Query의 차이**

- **Command (명령)**: 시스템의 상태(데이터)를 변경해요. 하지만 결과(예: 데이터를) 반환하지 않는 게 원칙이에요.
- **Query (질의)**: 시스템의 상태를 변경하지 않고, 요청한 데이터를 반환해요.

---

### **Void 응답 vs ID 응답**

- **Void 응답**: 아무것도 반환하지 않는 Command가 이상적이지만, **상황에 따라 실용적인 정보를 반환하는 것도 괜찮아요**. 예를 들어, 저장된 데이터의 ID를 반환하는 것이 꼭 나쁜 건 아니에요.

---

### **권장 방법**

1. **Insert (Command)**: 데이터를 저장한 후, ID만 반환하는 것이 좋습니다.
2. **Update (Command)**: 아무것도 반환하지 않고, 단순히 데이터를 수정하는 것이 바람직합니다.
3. **Query (질의)**: 데이터를 조회하고, 상태를 변경하지 않는 것이 원칙입니다.

---

### **결론**

CQS는 코드를 더 명확하게 만들기 위한 원칙이에요. Command는 데이터를 바꾸고, Query는 데이터를 조회해요. 하지만 상황에 따라 실용적으로 ID나 결과를 반환하는 것도 괜찮아요. 중요한 것은 **어떤 메서드가 데이터를 변경하고, 어떤 메서드가 조회하는지**를 명확히 구분하는 거예요.