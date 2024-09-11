![](https://i.ibb.co/fH6LSJp/image.png)

## **Setter 메소드를 사용하면 안 되는 이유**

1. **값 변경의 의도를 파악하기 어렵다**

   Setter 메소드를 사용하면 객체의 값을 변경한 의도를 명확하게 알기 어렵습니다.

2. **객체의 일관성을 유지하기 어렵다**

   Setter는 public으로 선언되어, 외부에서 언제든지 객체의 상태를 변경할 수 있어 객체의 일관성을 유지하기 어렵습니다.


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

위 코드는 `Book` 객체의 정보를 변경하는 예시입니다. 하지만 `setter` 메소드들이 나열되어 있을 뿐, 변경의 의도가 명확하지 않아 코드의 가독성이 떨어집니다.

---

### **2. 객체의 일관성을 유지하기 어렵다**

`Setter` 메소드는 외부에서 객체의 상태를 언제든지 변경할 수 있기 때문에, 객체의 일관성을 해칠 위험이 있습니다. 특히, 도서 정보 관리에서 모든 곳에서 객체의 상태를 변경할 수 있다면 데이터 무결성이 유지되지 않을 수 있습니다.

---

## **Setter 대신 사용할 수 있는 방법**

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
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }
}

```

생성자를 오버로딩하여 객체를 생성하는 방법입니다. 하지만 멤버 변수가 많아지면 생성자 코드가 복잡해져 가독성이 떨어질 수 있습니다.

> [주의] JPA 사용 시 기본 생성자가 필요합니다.
>

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

`Builder` 패턴을 사용하면 필요한 데이터만으로 유연하게 객체를 생성할 수 있습니다. 이는 다양한 생성자들 대신 하나의 통합된 형태로 관리할 수 있어 유지보수 및 가독성이 향상됩니다. 또한, 인자의 순서를 상관하지 않기 때문에 실수를 줄일 수 있습니다.

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

`정적 팩토리 메소드`를 사용하면 메소드에 이름을 부여하여, 반환할 객체의 의도를 더욱 명확히 할 수 있습니다.

---

### 활용

정적 팩토리 메소드 + builder

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

private로 선언된 내부 builder 를 이용하여 유연하게 정적 팩토리 메소드를 생성하는 방법

entity class 내 코드의 가독성이 높다는 장점이 있다.