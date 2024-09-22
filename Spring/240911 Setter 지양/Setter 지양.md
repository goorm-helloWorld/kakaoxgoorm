# **Setter 메소드를 사용하면 안 되는 이유**

1. **값 변경의 의도를 파악하기 어렵다**  
   Setter 메소드는 객체의 값이 변경된 의도를 명확히 알 수 없어, 코드 가독성이 떨어질 수 있습니다.

2. **객체의 일관성을 유지하기 어렵다**  
   Setter 메소드는 외부에서 객체의 상태를 쉽게 변경할 수 있어, 객체의 일관성과 무결성을 해칠 가능성이 큽니다.

---

### **1. 값 변경의 의도를 파악하기 어렵다**

```java
public Book updateBook(long id) {
    final Book book = findById(id);
    book.setTitle("New Title");
    book.setAuthor("New Author");
    book.setPublishedYear(2023);
    book.setGenre("Science Fiction");
    return book;
}
```

위 예시에서 여러 `setter` 메소드가 사용되고 있지만, **변경의 의도**가 명확하지 않기 때문에 **가독성이 낮아집니다.**

---

### **2. 객체의 일관성을 유지하기 어렵다**

`Setter` 메소드는 외부에서 객체의 상태를 언제든지 변경할 수 있어, 객체의 **무결성**이 깨질 수 있습니다. 이를 방지하려면 상태 변경을 제어하는 방법이 필요합니다.

---

### **Setter 대신 사용할 수 있는 대안**

- **생성자 오버로딩**
- **Builder 패턴**
- **정적 팩토리 메소드**

---

### **1. 생성자 오버로딩**

```java
public class Book {
    private String title;
    private String author;
    private int publishedYear;
    private String genre;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, int publishedYear) {
        this(title, author);
        this.publishedYear = publishedYear;
    }
}
```

**설명**: 생성자를 오버로딩하여 객체를 초기화할 수 있습니다. **단점**으로는 멤버 변수가 많아질수록 생성자가 복잡해져 가독성이 떨어질 수 있습니다.

**주의**: JPA를 사용할 경우, **기본 생성자**가 필요합니다.

---

### **2. Builder 패턴**

```java
public class Book {
    private String title;
    private String author;
    private int publishedYear;
    private String genre;

    @Builder
    public Book(String title, String author, int publishedYear, String genre) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
    }
}
```

**설명**: `Builder 패턴`은 객체를 유연하게 생성할 수 있습니다. 여러 개의 생성자 대신, 필요한 필드만 설정할 수 있어 **가독성과 유지보수성**이 뛰어납니다.

**장점**:
- 인자의 순서와 상관없이 사용할 수 있어 실수를 줄입니다.

---

### **3. 정적 팩토리 메소드**

```java
public class Book {
    private String title;
    private String author;
    private int publishedYear;
    private String genre;

    public static Book createBook(String title, String author, int publishedYear, String genre) {
        Book book = new Book();
        book.title = title;
        book.author = author;
        book.publishedYear = publishedYear;
        book.genre = genre;
        return book;
    }
}
```

**설명**: 정적 팩토리 메소드를 사용하면 **의도를 명확히** 할 수 있습니다. 메소드 이름에 **의도를 담아** 반환할 객체가 어떤 역할을 하는지 쉽게 이해할 수 있습니다.

---

### **활용: 정적 팩토리 메소드 + Builder**

```java
@Builder
private Book(String title, String author, int publishedYear, String genre) {
    this.title = title;
    this.author = author;
    this.publishedYear = publishedYear;
    this.genre = genre;
}

/* 생성 */
public static Book createBook(String title, String author, int publishedYear, String genre) {
    return Book.builder()
            .title(title)
            .author(author)
            .publishedYear(publishedYear)
            .genre(genre)
            .build();
}
```

**설명**: `private`로 선언된 내부 `builder`를 사용해 **유연하게 객체를 생성**하는 방식입니다. 이를 통해 코드 가독성이 높아지고 객체 생성 시 실수를 줄일 수 있습니다.

---

### **결론**

Setter 메소드를 사용하는 대신, **생성자 오버로딩**, **Builder 패턴**, **정적 팩토리 메소드**를 사용하면 객체의 상태를 명확하게 제어하고 코드 가독성을 높일 수 있습니다. 이를 통해 객체의 일관성을 유지하며, 의도를 명확하게 표현하는 코드를 작성할 수 있습니다.