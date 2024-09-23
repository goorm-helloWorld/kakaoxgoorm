### Thymeleaf에서 Spring Security와 연동한 `authorize` 사용 정리

Thymeleaf 템플릿 엔진은 Spring Security와 쉽게 연동할 수 있습니다. `sec:authorize`를 사용하면 특정 사용자 권한이나 인증 상태에 따라 HTML 요소를 표시하거나 숨길 수 있습니다. 아래에서 `sec:authorize`를 활용하여 사용자 권한별로 템플릿 요소를 제어하는 방법을 정리하겠습니다.

### 기본 설정

1. **`xmlns:sec` 추가**: `sec:authorize`를 사용하기 위해 Spring Security 확장을 선언해야 합니다.
   ```html
   <html xmlns:th="http://www.thymeleaf.org"
         xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
   ```
   이 선언은 `sec:authorize`와 같은 Spring Security 태그를 사용할 수 있게 합니다.

2. **권한에 따른 콘텐츠 표시**:
    - **인증된 사용자**: `sec:authorize="isAuthenticated()"`를 사용하면 로그인된 사용자에게만 콘텐츠를 표시할 수 있습니다.
    - **익명 사용자**: `sec:authorize="isAnonymous()"`를 사용하면 로그인되지 않은 익명 사용자에게만 콘텐츠를 표시합니다.
    - **특정 권한 사용자**: `sec:authorize="hasRole('ROLE_ADMIN')"` 또는 `sec:authorize="hasAuthority('ROLE_USER')"`를 사용해 특정 권한을 가진 사용자에게만 콘텐츠를 표시할 수 있습니다.

### 적용된 `list.html` 예시

#### 원본
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <style>
        .gradient-custom {
            background: linear-gradient(to right, rgba(174, 239, 222, 1), rgba(147, 226, 209, 1)); /* 민트색 그라데이션 */
        }
        .card {
            background-color: #ffffff; /* 흰색 카드 배경 */
            border-radius: 1rem;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
        }
        .list-group-item {
            background-color: #f8f9fa; /* 리스트 아이템 배경 */
            border: 1px solid #dee2e6;
        }
    </style>
</head>
<body class="gradient-custom">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card p-4">
                <h1 class="text-center">게시글 목록</h1>
                <div class="text-right mb-3">
                    <span th:text="${nickname}" class="mr-3"></span> <!-- 로그인된 사용자 닉네임 표시 -->
                    <a th:href="@{/logout}" class="btn btn-outline-danger">로그아웃</a>
                </div>

                <ul class="list-group">
                    <li class="list-group-item" th:each="post : ${posts}">
                        <a th:href="@{/posts/{id}(id=${post.id})}" th:text="${post.title}">게시글 제목</a>
                        <span class="badge badge-secondary float-right" th:text="${post.author}">작성자</span>
                    </li>
                </ul>
                <div class="text-right mt-3">
                    <a th:href="@{/posts/create}" class="btn btn-primary">게시글 작성</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

```

`list.html` 페이지에서 Spring Security를 활용해 사용자 권한에 따라 요소를 표시하도록 수정한 예시입니다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <style>
        .gradient-custom {
            background: linear-gradient(to right, rgba(174, 239, 222, 1), rgba(147, 226, 209, 1)); /* 민트색 그라데이션 */
        }
        .card {
            background-color: #ffffff;
            border-radius: 1rem;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .list-group-item {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
        }
    </style>
</head>
<body class="gradient-custom">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card p-4">
                <h1 class="text-center">게시글 목록</h1>

                <!-- 인증된 사용자만 닉네임과 로그아웃 버튼 표시 -->
                <div class="text-right mb-3">
                    <span sec:authorize="isAuthenticated()" th:text="${nickname}" class="mr-3"></span>
                    <a sec:authorize="isAuthenticated()" th:href="@{/logout}" class="btn btn-outline-danger">로그아웃</a>
                </div>

                <!-- 게시글 목록 출력 -->
                <ul class="list-group">
                    <li class="list-group-item" th:each="post : ${posts}">
                        <a th:href="@{/posts/{id}(id=${post.id})}" th:text="${post.title}">게시글 제목</a>
                        <span class="badge badge-secondary float-right" th:text="${post.author}">작성자</span>
                    </li>
                </ul>

                <!-- 인증된 사용자만 게시글 작성 버튼 표시 -->
                <div class="text-right mt-3" sec:authorize="isAuthenticated()">
                    <a th:href="@{/posts/create}" class="btn btn-primary">게시글 작성</a>
                </div>

                <!-- 관리자만 관리 페이지 버튼 표시 -->
                <div class="text-right mt-3" sec:authorize="hasRole('ROLE_ADMIN')">
                    <a th:href="@{/admin}" class="btn btn-warning">관리자 페이지</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

![](https://i.ibb.co/qjYR5Fz/2024-09-23-18-15-17.png)

보이는 것에는 차이가 없으나, 인증 방식의 차이가 존재한다.

### 주요 사용 예시

1. **닉네임 및 로그아웃 버튼**:
    - 인증된 사용자만 자신의 닉네임과 로그아웃 버튼을 볼 수 있습니다.
   ```html
   <span sec:authorize="isAuthenticated()" th:text="${nickname}" class="mr-3"></span>
   <a sec:authorize="isAuthenticated()" th:href="@{/logout}" class="btn btn-outline-danger">로그아웃</a>
   ```

2. **게시글 작성 버튼**:
    - 로그인한 사용자만 게시글 작성 버튼을 볼 수 있습니다.
   ```html
   <div class="text-right mt-3" sec:authorize="isAuthenticated()">
       <a th:href="@{/posts/create}" class="btn btn-primary">게시글 작성</a>
   </div>
   ```

3. **관리자 페이지 버튼**:
    - 관리자 권한을 가진 사용자만 "관리자 페이지" 버튼을 볼 수 있습니다.
   ```html
   <div class="text-right mt-3" sec:authorize="hasRole('ROLE_ADMIN')">
       <a th:href="@{/admin}" class="btn btn-warning">관리자 페이지</a>
   </div>
   ```

### 주요 `authorize` 표현식 정리

- **`isAuthenticated()`**: 로그인한 사용자만 해당 콘텐츠를 볼 수 있습니다.
- **`isAnonymous()`**: 로그인하지 않은 익명 사용자만 콘텐츠를 볼 수 있습니다.
- **`hasRole('ROLE_ADMIN')`**: "ROLE_ADMIN" 권한을 가진 사용자만 해당 콘텐츠를 볼 수 있습니다.
- **`hasAuthority('ROLE_USER')`**: 특정 권한을 가진 사용자만 콘텐츠를 볼 수 있습니다.

### 결론
`sec:authorize`는 Spring Security와 Thymeleaf를 통합하여, HTML에서 쉽게 인증 상태와 권한에 따른 접근 제어를 적용할 수 있는 강력한 도구입니다. 이를 통해 템플릿에서 어떤 요소를 표시할지 유연하게 제어할 수 있습니다.