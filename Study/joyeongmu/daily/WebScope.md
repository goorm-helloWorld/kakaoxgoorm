

# Spring Bean Scope

Spring Bean 스코프는 스프링 Bean이 앱이 구동되는 동안 존재할 수 있는 범위를 뜻합니다. 스프링 컨테이너는 빈 객체를 생성, 관리, 제거하며, 빈의 스코프를 설정하여 생명주기를 제어할 수 있습니다. 기본적으로 스프링 빈은 **싱글톤**으로 생성되지만, 필요에 따라 다른 스코프를 지정할 수 있습니다.

## 1. Spring Bean Scopes

스프링에서 제공하는 다섯 가지 스코프:

| Scope | Description |
| --- | --- |
| singleton | (기본값) 스프링 IoC 컨테이너당 하나의 인스턴스만 사용. 애플리케이션이 구동되는 동안 하나만 존재합니다. |
| prototype | 매번 새로운 빈을 정의해서 사용합니다. |
| request | HTTP 라이프사이클마다 하나의 빈을 사용. 웹 환경에서만 사용 가능합니다. |
| session | HTTP 세션마다 하나의 빈을 사용. 웹 환경에서만 사용 가능합니다. |
| application | ServletContext 라이프사이클 동안 하나의 빈만 사용. 웹 환경에서만 사용 가능합니다. |
| websocket | WebSocket 라이프사이클 동안 하나의 빈을 사용. 웹 환경에서만 사용 가능합니다. |

> 웹 스코프: request, session, application 스코프는 웹 환경에서만 동작합니다.
> 

### 웹 스코프의 특징

- **request**: HTTP 요청마다 새로운 빈 인스턴스 생성 및 관리.
- **session**: HTTP 세션과 동일한 생명주기를 가짐.
- **application**: 서블릿 컨텍스트(ServletContext)와 동일한 생명주기를 가짐.
- **websocket**: 웹 소켓의 생명주기와 동일하게 빈이 생성 및 소멸.

## 2. 스코프 설정 방법

### 어노테이션 사용

```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    ...
}

```

- `@Scope` 어노테이션을 사용하여 스코프 설정.
- **proxyMode**: 빈이 실제 사용될 때 프록시 빈에서 실제 빈을 가져오도록 함.

### 프록시 빈

스프링 컨테이너는 프록시 객체를 빈으로 등록하며, 실제 애플리케이션 구동 후 요청이 있을 때 진짜 빈을 생성하여 사용합니다. 특히 **request scope** 타입 빈은 **지연 생성**되며, 프록시 객체는 클래스 기반의 프록시(CGLIB)를 사용합니다.

## 3. @RequestScope, @SessionScope vs @Scope

### @RequestScope와 @Scope 비교

1. `@Component`와 `@Scope` 사용
    
    ```java
    @Component
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public class MyLogger {
        ...
    }
    
    ```
    
    - 각 HTTP 요청마다 새로운 인스턴스가 생성됩니다.
    - `proxyMode = ScopedProxyMode.TARGET_CLASS`는 클래스 기반 프록시 사용을 의미.
2. `@RequestScope` 사용
    
    ```java
    @RequestScope
    public class MyLogger {
        ...
    }
    
    ```
    
    - `@RequestScope`는 `@Scope(value = "request")`의 단축 표현으로, 클래스 기반 프록시가 기본적으로 설정됩니다.

### 차이점

- **기능적 차이 없음**: 두 방식은 동일한 기능을 제공합니다.
- **표현 차이**: `@RequestScope`는 간단한 표현, `@Scope`는 더 명시적인 설정이 가능합니다.
- **프록시 모드**: `@RequestScope`는 기본적으로 클래스 기반 프록시를 사용하며, `@Scope`는 `proxyMode`를 명시할 수 있습니다.

복잡한 설정이 필요 없는 경우 `@RequestScope`를 사용하는 것이 간편하며, 복잡한 설정이 필요한 경우(예: 인터페이스 기반 프록시 사용) `@Scope`를 사용하는 것이 좋습니다.

```java
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class MyLogger implements LoggerInterface {
    ...
}

```

## 4. Scope Bean 소멸 시점

- **RequestScope**: HTTP 요청이 끝나고 응답을 완료한 후 소멸.
- **SessionScope**: HTTP 세션이 종료되거나 명시적으로 세션을 초기화할 때 소멸.

---