1. useReducer 구현
```jsx
import React, { useReducer, useEffect } from 'react';

const initialState = {
  name: '',
  email: '',
};


function reducer(state, action) {
  switch (action.type) {
    case 'CHANGE_INPUT':
      return {
        ...state,
        [action.name]: action.value,
      };
    case 'RESET':
      return initialState;
    default:
      return state;
  }
}

const FormHandler = () => {
  const [state, dispatch] = useReducer(reducer, initialState);


  useEffect(() => {
    console.log('현재 입력 상태:', state);
  }, [state]);


  useEffect(() => {
    console.log('데이터 로딩 중...');
    const timer = setTimeout(() => {
      console.log('데이터 로딩 완료');
    }, 2000);


    return () => clearTimeout(timer);
  }, []);


  const handleChange = (e) => {
    const { name, value } = e.target;
    dispatch({ type: 'CHANGE_INPUT', name, value });
  };


  const handleReset = () => {
    dispatch({ type: 'RESET' });
    alert('초기화되었습니다');
  };

  return (
    <div>
      <input
        name="name"
        placeholder="이름"
        value={state.name}
        onChange={handleChange}
      />
      <input
        name="email"
        placeholder="이메일"
        value={state.email}
        onChange={handleChange}
      />
      <button onClick={handleReset}>초기화</button>
    </div>
  );
};

export default FormHandler;

```

2. useState에서 useReducer로 변경

이전에 만들었던 spring boot와 axios 통신을 하는 로그인 시스템을 useReducer로 변경해보았다.

이전 코드는 아래와 같다.
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

useReducer를 사용하면 아래와 같다.
```jsx

import axios from 'axios';
import React, { useReducer } from 'react';

// 초기 상태
const initialState = {
  loginId: '',
  password: '',
};

// reducer 함수 정의
function reducer(state, action) {
  switch (action.type) {
    case 'CHANGE_INPUT':
      return {
        ...state,
        [action.name]: action.value,
      };
    default:
      return state;
  }
}

const LoginPage = () => {
  const [state, dispatch] = useReducer(reducer, initialState);
  const { loginId, password } = state;

  const handleChange = (e) => {
    dispatch({
      type: 'CHANGE_INPUT',
      name: e.target.name,
      value: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login', state);
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