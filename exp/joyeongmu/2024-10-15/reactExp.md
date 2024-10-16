### [EXP 미션] useState를 사용하여 두 개의 입력값이 동일한지 확인하는 InputCompare 컴포넌트를 구현
두개의 객체인 loginId, password 를 관리하였다.

실제 두개의 값이 유효한지 확인하고 싶기 때문에 이전에 제작했던 로그인 API 를 사용해보았다.

inputCompare 컴포넌트 대신 handleSubmit을 작성하여 axios 요청시 값을 try catch하여서 검증하였다.

```jsx
import axios from 'axios';
import React, { useState } from 'react';

const LoginPage = () => {
  // 로그인
  const [formData, setFormData] = useState({
    loginId: '',
    password: '',
  });

  const { loginId, password } = formData;

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        'http://localhost:8080/login',
        formData
      );
    } catch (error) {
      console.error('로그인 실패', error);
    }
  };

  // 소셜 로그인
  const onNaverLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/naver';
  };

  const onKakaoLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/kakao';
  };
  const onGoogleLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };

  return (
    <div>
      {/* 로그인 */}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="loginId">아이디:</label>
          <input
            type="text"
            id="loginId"
            name="loginId"
            value={loginId}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="password">비밀번호:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">로그인</button>
      </form>
      {/* 소셜 로그인 */}
      <button onClick={onNaverLogin} className="naver">
        Naver
      </button>
      <button onClick={onKakaoLogin} className="kakao">
        Kakao
      </button>
      <button onClick={onGoogleLogin} className="google">
        Google
      </button>
    </div>
  );
};

export default LoginPage;
```