[![cors.png](https://i.postimg.cc/vZzNwqRc/cors.png)](https://postimg.cc/9DDpGBQh)


최근 React를 사용 해야될 일이 생겼다. Spring boot로 서버를 구축하고 API를 호출해야 되기 때문에 CORS에 대해서 생각이 났다. 백엔드 개발자의 입장에서의 교차출처오류를 해결하기 위해 Controller 혹은 CorsConfig 등록, Spring Security 환경의 filter에서의 해결 방법 등 여러 시나리오를 생각하며 CORS 정책에 대한 배경을 정리하고자 한다.

---

## CORS의 등장

브라우저는 SOP 정책을 따르고 있다. 간단하게 정리하자면 “같은 출처에서만 리소스를 공유할 수 있다”라는 규칙을 가지고 있다. 보안적으로 SOP는 중요한 부분이지만 다른 출처 간의 통신을 해야하는 경우가 존재한다. 예외 케이스를 준비하여 해당 예외 케이스는 출처가 다르더라도 허용 할 수 있게 만들었다. 그중에 하나가 CORS 정책에 맞는 리소스 요청이다.

## CORS

CORS는 Cross-Origin Resource Sharing의 약자로 “교차 출처 리소스 공유”(교차 출처 == 다른 출처)라고 한다. 다시 해당 단어들을 쉽게 풀어 본다면 “다른 출처끼리 리소스를 공유하는 정책” 으로 볼 수 있다.

가장 흔한 예시로 React(3000port)와 SpringBoot(8000port)가 따로 서버가 구축된 환경이라면 리소스를 공유하고자 할 때 서로 port가 다르기 때문에 다른 출처로 판단되어 CORS 위반 오류가 발생한다.

## Origin (출처)

예시로 들었던 React(3000port)와 SpringBoot(8000port)가 따로 서버가 구축된 환경이 왜 다른 출처로 분류가 되는 것인가 ? 출처를 구분할 때는 규칙이 존재 한다.

```text
http://www.whatcors.com:3000
```

위의 URL을 확인해 보자

Origin은 해당 URL이라고 볼수 있고 3가지로 분류에서 판단하게 된다.

1. protocol(Scheme) → http://
2. host → www.whatcors
3. port → :3000

3가지 모두 만족한다면 같은 출처라고 한다.

같은 출처는 same-origin이라고 하며 아래의 URL은 같은 출처로 인정되는 것이다.

```text
http://www.whatcors.com:3000/posts
http://www.whatcors.com:3000/posts?keyword=hello&page=2
```

만일 3가지중 하나라도 다른 경우에는 다른 출처 즉 cross-origin이다.

## CORS의 흐름

React(3000port)와 SpringBoot(8000port)가 따로 서버가 구축된 환경에서 리소스를 공유하고자 한다.

현재 두 서버는 다른 출처이다. 언제 CORS에 위반되었다고 판단되는 것 일까?

대부분 개발 공부를 진행하면서 겪게 되는 케이스는 이런 경우일 것이다.

프론트엔드 개발자가 백엔드 개발자에게 CORS 오류가 난다고 전달한다.

백엔드 개발자는 생각한다. PostMan에서는 잘되던데…

왜 PostMan에서는 되고 React에서는 CORS 오류가 나는 것일 까?

바로 출처를 비교하는 로직이 서버에 구현된것이 아닌 브라우저에 구현되어 있는 스펙이기 때문이다.

서버는 정삭적으로 응답을 하고, 브라우저가 CORS 정책이 위반이라고 판단되면 응답을 버리게 된다.

결론은 CORS는 브라우저의 구현 스펙에 포함되는 정책이기 때문에 CORS에 대한 판단 여부는 브라우저가 진행한다.

## CORS의 허용

CORS의 동작 시나리오는 3가지가 있다. 먼저 이해를 위한 CORS가 허용이 되는 간단한 시나리오를 들어보겠다.

React의 3000port를 허용하기 위해 SpringBoot에서 Controller에 CORS에 대한 어노테이션을 작성했을 것이다.

브라우저가 CORS를 판단하게 되는데 서버에서 왜 CORS를 허용해주기 위해 로직을 작성하는 것일까 ?

웹 클라이언트 어플리케이션이 다른 출처의 리소스를 요청할 때 브라우저는 요청 헤더에 Origin이라는 필드에 요청을 보내는 출처를 함꼐 담아보내게 된다.

```text
Origin: http://www.whatcors.com:3000
```

이후 SpringBoot에서는 응답 헤더의 Access-Control-Allow-Origin이라는 값에 지정한 출처를 넣어서 응답을 주게 되고 브라우저는 자신이 보냈던 Origin과 서버의 Access-Control-Allow-Origin을 비교한 후 판단하게 된다.

## CORS의 동작 시나리오

1. Simple Request

일반적인 요청에 대해서는 CORS 정책 검사를 하지 않는다.

**일반적인 요청 조건**

- 요청의 메소드는 **`GET`**, **`HEAD`**, **`POST`** 중 하나여야 한다.
- Request Header에는 다음 속성만 허용**`Accept`**, **`Accept-Language`**, **`Content-Language`**, **`Content-Type`**, **`DPR`**, **`Downlink`**, **`Save-Data`**, **`Viewport-Width`**, **`Width`**
- 만약 **`Content-Type`**를 사용하는 경우에는 **`application/x-www-form-urlencoded`**, **`multipart/form-data`**, **`text/plain`**만 허용된다.
- 요청에 사용된 XMLHttpRequestUpload 객체에는 이벤트 리스너가 등록되어 있지 않다. 이들은 [XMLHttpRequest.upload](https://developer.mozilla.org/ko/docs/Web/API/XMLHttpRequest/upload) 프로퍼티를 사용하여 접근한다.
- 요청에 [ReadableStream](https://developer.mozilla.org/ko/docs/Web/API/ReadableStream) 객체가 사용되지 않는다.

간단하게 요약하자면 앞으로 설계하게 될 시스템에서는 사용 할 수 없는 요청이라고 봐도 된다.

API 통신간의 application/json , Authorization header 등 을 사용해야되는 시스템이 거의 대부분이기 떄문이다.

1. Preflight Request

CORS 오류에 당황하여 부랴부랴 아래의 Config Been을 등록한적이 있을 것이다.

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .allowedHeaders("headers")
                .maxAge(3000);
    }
}
```

아마 대부분 개발을 시작하면서 처음 만나게되는 CORS 오류는 해당 시나리오일 것이다.

브라우저는 요청을 한번에 보내지 않고 **예비 요청과 본 요청으로 나누어서 서버로 전송한다.**

다시 말해 preflight Request와 simple Request는 전반적인 로직 자체는 같고, 예비 요청의 존재 유무만 다르다고 보면 된다.

따라서 앞의 Simple Request와 같은 요청이 아닌 경우 브라우저는 접근할 리소스를 가지고 있는 서버에 **preflight Request (예비 요청)을 보낸다.**

이 예비 요청에는 HTTP 메소드 중 **`OPTIONS`** 메소드가 사용된다. 예비 요청의 역할은 본 요청을 보내기 전에 **브라우저 스스로 이 요청을 보내는 것이 안전한지 확인하는 것**이다.

이후 OPTIONS 요청을 받은 서버는 **Response Header에 서버가 허용할 옵션을 설정하여 브라우저에게 전달**한다.

예를 들어 응답 헤더에 **`Access-Control-Allow-Origin`** 항목을 추가하여 허용할 도메인을 지정할 수 있는데, 설정하게 되면 개발자 도구에서 아래와 같이 확인할 수 있다.

브라우저는 서버가 보낸 Response 정보를 이용하여 허용되지 않은 요청인 경우 405 Method Not Allowed 에러를 발생시키고, 실제 페이지의 요청(본 요청)은 서버로 전송하지 않고, 반대로 **허용된 요청인 경우 본 요청을 보낸다.**

브라우저는 XMLHttpRequest가 Cross-Origin 요청인 것을 판단하여 요청에 "Origin:http://localhost:3000" 헤더를 추가한다. 또한 브라우저는 **해당 요청의 HTTP 메서드**를 인지하고 있으며, **Content-Type이 application/x-www-form-urlencoded, multipart/form-data, text/plain에 포함되지 않기** 때문에 Prefight Request 방식으로 보내야 한다는 것을 알고 있다.(simple request가 아닌 것으로 인지 중 -) 그래서 브라우저는 요청에 아래와 같이 헤더 정보를 추가하여 외부 서버로 Preflight Request(예비 요청)을 보낸다.

서버는 **이 preflight Request(예비 요청)에 대한 응답**으로 현재 자신이 어떤 것들을 허용하고 있는지에 대한 **정보를 response header에 담아서 브라우저에게 다시 보내주게 된다.**

```
- Access-Control-Allow-Origin: https://localhost:3000
- Access-Control-Allow-Methods: POST, GET, PUT, PATCH OPTIONS
- Access-Control-Allow-Headers: headers
- Access-Control-Max-Age: 3000
```

위 헤더들은 다음과 같은 뜻이다.

Access-Control-Allow-Origin : 허가된 Origin

Access-Control-Allow-Methods : 허가된 메소드

Access-Control-Allow-Headers : 허가된 헤더

Access-Control-Max-Age : 응답 캐시가 유효 시간

응답으로 받은 response header의 정보를 통해서 브라우저는 본 요청을 외부 서버로 보낼지 말지를 판단하게 된다. 위 예시에서 표시된 정보는 **'해당 API가 Cross-Origin에 대해서 POST, GET, OPTIONS와 해당 헤더를 어용한다** 의미이다. 이에 해당되는 것들은 안전하다고 판단하여 CORS 위반으로 간주하지 않고, 브라우저는 본 요청을 브라우저는 외부 서버로 보낸다.

1. Credential Request

아마 CORS 오류를 2번째로 만나게 되는 경우는 이 시나리오 일 것이다.

열심히 만들어둔 CorsConfig를 복사 붙혀넣기 하면서 개발하다가 다시 만난 CORS에 당황하게 된다.

```java
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                        configuration.setExposedHeaders(Collections.singletonList("access"));

                        return configuration;
                    }
                }));
}
```

귀찮아서 와일드카드로 지정했던 AllowedOrigins에 자세히 출처를 적고 credentials 를 true로 바꿔주는 등

Spring Security를 이용하여 JWT 기반 인증 모듈을 구현하게 되면서 작성했을 코드이다.

이런경우에는 해당 시나리오에 해당된다.

헤더에 인증과 관련된 정보(쿠키, 토큰 등)를 담아서 보내는 Credential Request (인증된 요청)을 사용하는 방법이다.

CORS의 기본적인 방식이라기 보다는 다른 출처 간 통신에서 좀 더 보안을 강화하고 싶을 때 사용하는 방법이다.

예를 들어, 자바스크립트의 fetch API를 사용하거나 Axios, Ajax 등을 사용할 때 서버로 쿠키를 함께 전송해야 하는 경우가 있는데, 요청에 쿠키가 담기게 되면 Credentialed Request 허용이 되어 있어야 한다.

즉, **인증과 관련된 정보를 담을 수 있게 해주는 옵션 'credentials'**를 줘야하는데, 이 때 서버 쪽에서 응답 헤더에 Access-Control-Allow-Credentials: true를 보내주지 않는다면 브라우저에서 응답을 받는 것을 거부하게 된다.

이 옵션에는 총 3가지의 값을 사용할 수 있다.

1. same-origin → 같은 출처 간 요청에만 인증 정보를 담을 수 있다.
2. include → 모든 요청에 인증 정보를 담을 수 있다.
3. omit → 모든 요청에 인증 정보를 담지 않는다.

```jsx
/* 예시 axios */
axios.get("https://example.com/items", {
  withCredentials: false, // default
})

/* 예시 fetch API */
fetch("https://example.com:1234/users", {
  credentials: "include",
})

```

만약 `same-origin` 이나 `include`와 같은 옵션을 사용하여 리소스 요청에 인증 정보가 포함된다면, 이제 브라우저는 다른 출처의 리소스를 요청할 때 단순히 `Access-Control-Allow-Origin`만 확인하는 것이 아니라 좀 더 빡빡한 검사 조건을 추가하게 된다.

요청에 인증 정보가 담겨있는 상태에서 다른 출처의 리소스를 요청하게 되면 브라우저는 CORS 정책 위반 여부를 검사하는 룰에 다음 두 가지를 추가하게 된다.

1. **`Access-Control-Allow-Origin`**에는 와일드카드를 사용할 수 없다.
2. 응답 헤더에는 반드시 `Access-Control-Allow-Credentials: true`가 존재해야한다.

ref

---

[https://velog.io/@effirin/CORS란-무엇인가](https://velog.io/@effirin/CORS%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)

[https://velog.io/@jh100m1/CORS-에러가-뭔데-어떻게-해결하는건데](https://velog.io/@jh100m1/CORS-%EC%97%90%EB%9F%AC%EA%B0%80-%EB%AD%94%EB%8D%B0-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%B4%EA%B2%B0%ED%95%98%EB%8A%94%EA%B1%B4%EB%8D%B0)