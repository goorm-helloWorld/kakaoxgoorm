---

# JSON 파싱 및 생성 예시 코드

## 1. **DTO 클래스 (Data Transfer Object)**

### Car.java

```java
package dto;

public class Car {
    private String name;
    private String number;

    // Getter와 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // 객체 정보를 출력하기 위한 toString 메서드
    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\\'' +
                ", number='" + number + '\\'' +
                '}';
    }
}

```

### User.java

```java
package dto;

import java.util.List;

public class User {
    private String name;
    private int age;
    private List<Car> car;

    // Getter와 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    // 객체 정보를 출력하기 위한 toString 메서드
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}

```

---

## 2. **JSON 생성 방법 (직렬화)**

JSON(JavaScript Object Notation)은 데이터를 저장하거나 전송할 때 많이 사용하는 형식입니다. `ObjectMapper`는 Jackson 라이브러리를 사용하여 Java 객체를 JSON으로 직렬화(serialize)하거나 역직렬화(deserialize)할 때 사용됩니다.

### JSON 생성 코드

```java
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

User user = new User();
user.setAge(25);
user.setName("조영무");

Car car1 = new Car();
car1.setName("KIA");
car1.setNumber("00무0000");

Car car2 = new Car();
car2.setName("BMW");
car2.setNumber("99구9999");

List<Car> carList = Arrays.asList(car1, car2);
user.setCar(carList);

String json = objectMapper.writeValueAsString(user);
System.out.println("생성한 json = " + json);

```

### 생성된 JSON

```json
{
    "name": "조영무",
    "age": 25,
    "car": [
        {"name": "KIA", "number": "00무0000"},
        {"name": "BMW", "number": "99구9999"}
    ]
}

```

### 개념 설명

- **ObjectMapper**: Jackson 라이브러리의 핵심 클래스이며, JSON <-> Java 객체 간 변환을 도와줍니다.
- **writeValueAsString()**: Java 객체를 JSON 문자열로 변환합니다.
- **DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES**: JSON에 존재하는 필드 중 Java 객체에 매핑되지 않는 필드가 있어도 오류가 발생하지 않도록 설정합니다.

---

## 3. **JSON을 특정 객체로 파싱 (역직렬화)**

JSON 데이터를 Java 객체로 변환하는 과정을 **역직렬화(Deserialization)**라고 합니다.

### 파싱 코드

```java
User parsing = objectMapper.readValue(json, User.class);
System.out.println("특정 객체로 파싱 진행 = " + parsing);

```

### 파싱된 객체

```
User{name='조영무', age=25, car=[Car{name='KIA', number='00무0000'}, Car{name='BMW', number='99구9999'}]}

```

### 개념 설명

- **readValue()**: JSON 문자열을 Java 객체로 변환하는 메서드입니다.
- **역직렬화(Deserialization)**: JSON 형식의 데이터를 Java 객체로 변환하는 과정입니다.

---

## 4. **루트 JSON 파싱**

JSON 데이터를 **트리 구조**로 탐색하면서 특정 값을 추출할 수 있습니다.

### 루트 JSON 파싱 코드

```java
JsonNode jsonNode = objectMapper.readTree(json);

// 루트 json 조회
String name = jsonNode.get("name").asText();
int age = jsonNode.get("age").asInt();

System.out.println(name);
System.out.println(age);

```

### 출력

```
조영무
25

```

### 개념 설명

- **JsonNode**: Jackson 라이브러리에서 제공하는 JSON 트리 구조를 탐색할 수 있는 클래스입니다.
- **get()**: 특정 키 값을 기준으로 데이터를 가져올 수 있는 메서드입니다.
- **asText(), asInt()**: 각 노드 값을 특정 형식으로 변환해 반환합니다.

---

## 5. **Depth JSON 파싱**

- **배열(JSON Array)**을 파싱하여 깊이 있는 구조의 데이터를 처리하는 방법입니다.

### Depth JSON 파싱 코드

```java
JsonNode cars = jsonNode.get("car");
ArrayNode arrayNode = (ArrayNode) cars;

List<Car> car = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
System.out.println(car);
```

> **핵심 차이: `JsonNode` vs `ArrayNode`**
>
> - `JsonNode`는 단순히 JSON 구조를 나타내는 일반적인 노드입니다. 배열, 객체, 문자열, 숫자 등의 다양한 데이터 타입을 표현할 수 있습니다. 하지만, `JsonNode`만으로는 Jackson이 내부적으로 배열을 다룰 수 없으므로 명확히 `ArrayNode`로 캐스팅해서 처리해야 합니다.
> - `ArrayNode`는 JSON 배열을 처리할 수 있는 Jackson의 특별한 형태입니다. 이 객체는 배열로부터 각 요소를 순회하거나 배열에 추가하는 등의 작업을 쉽게 수행할 수 있습니다.

### 출력

```
[Car{name='KIA', number='00무0000'}, Car{name='BMW', number='99구9999'}]

```

### 개념 설명

- **ArrayNode**: JSON 배열을 처리하는 Jackson 클래스입니다.
- **convertValue()**: `JsonNode`를 Java 객체로 변환할 때 사용됩니다.
- **TypeReference**: 제네릭 타입을 처리할 때 Jackson에서 사용되는 클래스입니다.

---

## 6. **JSON 노드 수정**

JSON 데이터를 동적으로 수정할 수 있습니다.

### 노드 수정 코드

```java
ObjectNode objectNode = (ObjectNode) jsonNode;
objectNode.put("name", "조영무님");

System.out.println(objectNode.toPrettyString());

```

### 수정된 JSON

```json
{
  "name": "조영무님",
  "age": 25,
  "car": [
    {"name": "KIA", "number": "00무0000"},
    {"name": "BMW", "number": "99구9999"}
  ]
}

```

### 개념 설명

- **ObjectNode**: JSON 객체를 수정할 수 있는 Jackson 클래스입니다.
- **put()**: JSON 객체의 특정 필드를 수정하거나 추가할 때 사용됩니다.
- **toPrettyString()**: JSON 데이터를 사람이 읽기 쉽게 포맷하여 출력합니다.

---