## MongoDB 특징

- 도큐먼트 지향
    - MongoDB는 도큐먼트 지향적인 데이터베이스입니다.
    - JSON 형태로 데이터를 관리하는 구조입니다.
- 스키마less

  MongoDB는 스키마나 테이블과 같은 정형적인 구조에 데이터를 저장하는 것이 아니라 비정형 데이터를 저장할때 주로 사용합니다. 여기서 테이블과 같은 역할을 하는 컬렉션이 존재하는데 컬렉션에도 저장 규칙이 따로 존재하지 않습니다.

- 비관계형 DB

  MongoDB에는 관계의 개념이 없습니다. 따라서, 조인 연산을 지원하지 않습니다.

- 트랜잭션 지원 X

  관계형 DB와 다르게 트랜잭션을 지원하지 않습니다.

  따라서 Commit이나 Rollback의 개념이 존재하지 않습니다.


# RDB 대신 MongoDB를 선택하는 경우

### 1. 유연성

MongoDB를 선택할 이유는 특징 중 도큐먼트 지향과 스키마가 없다는 점을 생각해야됩니다.

크롤링을 해야 하는 웹사이트가 여러개일 경우 사이트마다 데이터 표시 구조가 다르고 수집할 수 있는 데이터도 차이가 있습니다.

만약, 관계형 DB를 사용할 경우에는 이렇게 사이트마다 테이블을 따로 만들어야 할 수도 있습니다.

하지만, MongoDB를 사용하면 테이블이 없는 Schema-less 구조이기 때문에 하나의 컬렉션 혹은 하나의 데이터베이스에서 처리가 가능합니다.

그리고 도큐먼트 지향 DB이기 때문에 데이터 저장이 관계형 DB보다 수월합니다.

예를 들어, 시간이 지나면서 필드가 변화할 수 있는 채팅 애플리케이션의 구조적 변화를 쉽게 반영할 수 있습니다.

추가적인 사용자 속성이나 메시지 형식의 변화가 있을 때 유연하게 대응할 수 있을 겁니다.

### 2. 1:N 관계

몽고 디비를 사용하는 대표적인 예시인 채팅 프로그램으로 예시를 들겠습니다.

### **2-1. 관계**

- 한 채팅 방(Room)에는 많은 메세지가 존재 합니다. 채팅 방과 메세지 간의 관계는 1:N관계입니다. RDB에서 이 관계를 구현하려면 두 개의 테이블(예: Room, Message)을 생성하고, `JOIN` 연산을 통해 관련 데이터를 조회해야 합니다. 하지만 트래픽이 많을 때 이러한 `JOIN` 연산은 성능에 부정적인 영향을 줄 수 있습니다.

### 2-2. **MongoDB에서는 단일 Document로 해결**

- MongoDB는 도큐먼트 지향적이고 스키마가 없으므로, 한 컬렉션에서 채팅 방과 그 안의 사용자 데이터를 모두 포함할 수 있습니다. 채팅 방에 참여한 사용자 목록을 하나의 필드로 관리함으로써 데이터를 계층적으로 구조화할 수 있습니다.

```kotlin

@Document(collection = "chat_room")
public class ChatRoom {

   @Id
   private String id;

   @Field("room_name")
   private String roomName;

   @Field("is_active")
   private Boolean isActive;

   @Field("participants")
   private List<String> participants;

   @Field("messages")
   private List<Message> messages;

}

public class Message {

   @Field("sender")
   private String sender;

   @Field("message")
   private String message;

   @Field("timestamp")
   private Date timestamp;
}

```

### 2-3. **Document 저장의 이점**

- MongoDB에서는 `ChatRoom`과 같은 도큐먼트에 메시지와 사용자 정보를 함께 저장할 수 있기 때문에, 관련 데이터를 조회할 때 `JOIN`이 필요하지 않으며, 이를 통해 성능 이슈를 줄일 수 있습니다. 예를 들어, 한 방에 저장된 메시지나 참여한 사용자를 바로 컬렉션에서 가져올 수 있습니다.

> Document를 활용하여 Join을 어떻게 처리되는데?
>
> - schema-less한 특징을 이용해 document안에 document를 넣는 방식으로 처리 됩니다.
>
> ```json
> {
>    "_id": "CHATROOM_ID",
>    "room_name": "General Chat",
>    "is_active": true,
>    "participants": [
>      "USER_1",
>      "USER_2",
>      "USER_3"
>    ],
>    "messages": [
>      {
>        "sender": "USER_1",
>        "message": "Hello everyone!",
>        "timestamp": "2024-09-24T10:00:00Z"
>      },
>      {
>        "sender": "USER_2",
>        "message": "Hi! How are you?",
>        "timestamp": "2024-09-24T10:01:00Z"
>      },
>      {
>        "sender": "USER_3",
>        "message": "Good morning!",
>        "timestamp": "2024-09-24T10:02:00Z"
>      }
>    ]
> }
> 
> ```
>

### 3. ACID보다 성능이 더 중요할 때

> RDB vs MongoDB
>
- 트래픽이 많은 서비스라고 가정하고 개발힌디면  여러 사용자가 동시 접근했을 때 발생하는 동시성 이슈를 해결하는것이 매우 중요합니다.
- RDBMS
    - ACID를 구현하기 위해 Table 단위로 Blocking 하는 RDB는 대용량 트래픽에서 성능 이슈가 발생할 수 있습니다.
- MonogoDB
    - 도큐먼트 단위로 Lock을 할 수 있는 MongoDB가 동시성 이슈를 해결하며 빠른 성능을 낼 수 있습니다.
    - 더불어 트랜잭션과 같은 기능이 필요할 경우 Kafka 등의 MQ를 사용하거나 RDB를 인메모리 유효성 데이터만 끌고와서 처리하는 식으로 한계를 극복할 수 있습니다.

## 4. 트래픽이 많은 서비스에 유리

- MongoDB는 Sharding, Clustering을 통해 Scale-Out을 구현할 수 있습니다.

## Elastic Search vs MongoDB

| 구분 | MongoDB | Elastic Search |
| --- | --- | --- |
| 검색 | Field기준으로 Find 해야함 | 역색인 구조로 검색이 빠름 (특히 Full-Text Scan) |
| 저장 | MongoDB는 범용 데이터베이스 용도로 개발되었기 때문에 Document 형식으로 Schema-less 하게 저장 가능 | ES에서 segment는 불변이기 때문에, 문서가 업데이트 될 때 마치 새 문서가 인덱싱되듯 새 segment에 쓰이게 되기 때문에 Write가 느림 |
| 장점 | 상대적으로 Write가 빠르다 | 상대적으로 Read가 빠르다, 검색기능이 다양하다 |
- 고도화된 검색기능보다 DB 데이터의 수정 및 삭제가 빠른것이 더 중요할 때 MongoDB가 더 적합하다

## Spring Data Mongodb 사용하기

### 예시 프로젝트

- 간단한 몽고 디비 사용법을 확인합니다.
- 간단한 채팅 프로그램을 제작합니다.

### 의존성

```groovy
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
```

```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-websocket'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

### application.yml

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://user:<password>@pushpin.lgan0wm.mongodb.net/<database이름>?retryWrites=true&w=majority
```

### Entity

```java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(collection = "chatting_data") // MongoDB에서 사용할 컬렉션 이름
public class ChattingEntity {

    @Id
    private String id;

    private String name;

    @Field(name = "room_no")
    private int roomNO;

    private String message;

    private long timestamp;

    public ChattingEntity(String name, int roomNO, String message, long timestamp) {
        this.name = name;
        this.roomNO = roomNO;
        this.message = message;
        this.timestamp = timestamp;
    }
}
```

### Repository

```java
@Repository
public interface ChattingRepository extends MongoRepository<ChattingEntity, Long> {
    ChattingResDto findByName(String name);
    
    List<ChattingEntity> findByRoomNOOrderByTimestampAsc(int roomNo);  // 방 번호로 채팅 기록을 시간 순으로 가져오기

}
```

### service

```java
@Service
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;

    // 기존 채팅 기록 불러오기
    public List<ChattingEntity> getChatHistory(int roomNo) {
        return chattingRepository.findByRoomNOOrderByTimestampAsc(roomNo);
    }

    // 새로운 채팅 메시지 저장
    public void saveChatMessage(String name, int roomNo, String message) {
        ChattingEntity chat = new ChattingEntity(null, name, roomNo, message, System.currentTimeMillis());
        chattingRepository.save(chat);
    }
}
```

### controller

```java
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ChattingController {

    private final ChattingService chattingService;

    @GetMapping("/chat/{roomNo}")
    public ResponseEntity<?> getChatHistory(@PathVariable int roomNo) {
        return ResponseEntity.ok(chattingService.getChatHistory(roomNo));
    }

    // 채팅 메시지를 저장하는 엔드포인트
    @PostMapping("/chat/message")
    public ResponseEntity<?> saveChatMessage(@RequestBody ChatMessageRequestDto chatMessageRequestDto) {
        chattingService.saveChatMessage(
                chatMessageRequestDto.getName(),
                chatMessageRequestDto.getRoomNO(),
                chatMessageRequestDto.getMessage()
        );
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(HttpStatus.OK, "성공"));
    }
}
```

### 최종 결과

[![temp-Image-VR7yqn.avif](https://i.postimg.cc/yWCVKcMr/temp-Image-VR7yqn.avif)](https://postimg.cc/gL49qXkv)

## 결론

MongoDB는 **스키마리스** 데이터베이스이므로, JSON 형식의 문서(document)를 사용하여 데이터를 저장합니다.

이 때문에 데이터 구조가 고정되지 않아 필요에 따라 유연하게 변경할 수 있습니다.

예시로 들었던 채팅 애플리케이션에서는 **실시간 메시지 전송**과 **빈번한 로그 저장**이 이루어집니다.

MongoDB는 쓰기 작업이 빠르고, 많은 양의 데이터를 실시간으로 처리할 수 있는 **높은 처리 성능**을 제공합니다.