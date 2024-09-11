
![](https://i.ibb.co/vYf3nKz/CQS.png)

---

## **CQS (Command Query Separation)**

**제안자**: Bertrand Meyer

### **개념**

CQS는 명령(Command)과 쿼리(Query)를 분리하여 대규모 시스템에서 불필요한 사이드 이펙트를 줄이기 위한 원칙입니다. 특히 모듈 간 상호작용을 완전히 제어할 수 있을 때 유용합니다.

### **핵심 포인트**:

- **Command (명령)**: 시스템의 상태를 변경하지만 **결과를 반환하지 않음**. 상황에 따라 *modifier* 또는 *mutator*라고도 불립니다.
- **Query (질의)**: 시스템의 상태를 변경하지 않고 **정보를 반환**함. **부작용이 없음**.

### **사이드 이펙트 정의**

“사이트이펙트” 의 정의는 맥락에 따라 다를 수 있지만, CQS에서 부작용이란 내부에 변경이 발생하는지 여부를 의미합니다. 내부 변경이 없으면 사이드이펙트가 없는 것으로 간주합니다.

---

### **CQS의 목적**

CQS의 목적은 명령과 쿼리를 명확히 구분하여 불필요한 부작용을 줄이는 것입니다.

---

### **JPA에서의 예시**

### **1. Insert (Command)**: 데이터베이스의 상태를 변경하지만 ID를 반환함. 이는 CQS 원칙을 깨는 경우로 볼 수 있습니다.

```java
private EntityManager em;

@Transactional
public Long save(Item item) {
    em.persist(item);
    Item foundItem = em.find(Item.class, id);
    return foundItem.getId();
}

```

### **2. Update (Command)**: 아무 결과도 반환하지 않으며, 데이터베이스의 상태만 변경함.

```java
private EntityManager em;

@Transactional
public void update(Item item) {
    em.persist(item);
}

```

### **3. Select (Query)**: 내부 상태를 변경하지 않고 데이터를 반환함.

```java
@Transactional
public Item findOne(Long id) {
    Item foundItem = em.find(Item.class, id);
    return foundItem;
}

```

---

### **Update 메서드와 CQS**

업데이트 로직을 작성할 때 기존 데이터를 조회한 후 변경을 시도하는 경우가 있습니다. 이것이 CQS를 위반하는 것일까요?

- **답변**: 아닙니다. Command의 기준은:
    1. 결과를 반환하지 않음.
    2. 시스템(엔티티)의 상태를 변경함.

CQS는 *조회*(Query)와 *수정*(Modifier)을 명확히 구분합니다. 수정 메서드는 상태 변경에 중점을 두고, 데이터를 반환하지 않기 때문에 Query가 포함되어 있어도 Command로 간주됩니다.

---

### **Void 응답 vs ID 응답**

`void` 응답에 너무 집착할 필요는 없습니다. ID와 같은 데이터를 반환하는 것이 실용적인 경우라면, CQS를 엄격히 위반하는 것으로 볼 필요는 없습니다.

---

### **김영한 강사의 의견**

CQS의 장점은 웹 애플리케이션에만 국한된 것이 아니라, 전반적인 개발에 적용할 수 있습니다. 메서드를 호출했을 때, 내부에서 변경(사이드 이펙트)이 발생하는지 아니면 전혀 발생하지 않는지를 명확하게 분리하는 것이 중요합니다. 이렇게 하면 데이터 변경 관련 이슈가 발생했을 때, 변경이 일어나는 메서드만 확인하면 되므로 유지보수가 쉬워집니다.

**권장되는 방법**:

- **Insert**: ID만 반환.
- **Update**: 아무것도 반환하지 않음.
- **Query**: 내부 상태를 변경하지 않도록 설계.

---

### **POST 응답에서의 고려사항**

- **최소 반환값**: ID는 반환해야 합니다.
- **부가적인 정보**: 생성 시각, 버전 정보, 또는 데이터가 정확하게 입력되었는지 확인하는 정보도 반환할 수 있습니다.
- **피드백**: POST 응답으로 데이터를 반환하는 것은 명령이 잘 처리되었는지에 대한 피드백일 뿐이며, CQS 위반으로 보지 않습니다.

> 마틴 파울러의 의견: CQS 원칙을 따르되, 상황에 따라 유용하다면 이를 깨는 것이 더 좋을 수 있다.
>

---

### **최종 결론**

`void` 응답에 집착할 필요 없이, 상황에 따라 ID와 같은 필수 데이터를 반환하는 것은 큰 문제가 되지 않습니다.

---

**참고자료**:

- Bertrand Meyer, *Object-Oriented Software Construction*
- [martinfowler.com/bliki/CommandQuerySeparation](https://martinfowler.com/bliki/CommandQuerySeparation.html)
- https://www.inflearn.com/community/questions/709777/cqs
- https://hardlearner.tistory.com/383
- [https://velog.io/@xpmxf4/영한갓님-JPA-실전-강의-들었는-데-CQS-CQRS-안다-모른다](https://velog.io/@xpmxf4/%EC%98%81%ED%95%9C%EA%B0%93%EB%8B%98-JPA-%EC%8B%A4%EC%A0%84-%EA%B0%95%EC%9D%98-%EB%93%A4%EC%97%88%EB%8A%94-%EB%8D%B0-CQS-CQRS-%EC%95%88%EB%8B%A4-%EB%AA%A8%EB%A5%B8%EB%8B%A4)

---