# 미션

## 1. 회원가입 실행화면 스크린샷
- **경로**: /signup
- **내용**: 이메일, 비밀번호, 닉네임을 입력한 회원가입 화면.

## 2. 로그인 후 게시글 전체 보기 화면 스크린샷
- **경로**: /posts
- **내용**: 닉네임이 로그아웃 버튼 옆에 표시된 상태의 게시글 목록 화면.

## 3. 게시글 작성/수정 화면 스크린샷
- **경로**: /posts/create, /posts/{id}/edit
- **내용**: 작성자 필드가 자동으로 로그인된 사용자의 닉네임으로 채워져 있으며, readonly 속성이 적용된 화면.

## 4. 게시글 상세 보기 화면 스크린샷
- **경로**: /posts/{id}
- **내용**: 본인이 작성한 게시글에만 수정 및 삭제 버튼이 표시된 상태의 화면.

---

## 기능 요구사항

### 1. 회원 가입 시 닉네임 입력
- **경로**: /signup
- **내용**: 회원가입 시 이메일, 비밀번호와 함께 닉네임을 추가로 입력받습니다. 해당 닉네임을 데이터베이스에 저장합니다.

### 2. 로그인 시 닉네임 표시
- **경로**: /posts
- **내용**: 로그인 후 게시글 목록 페이지(list.html) 상단에 로그인된 사용자의 닉네임이 로그아웃 버튼 옆에 표시됩니다.

  ```java
  // 현재 인증된 사용자의 정보를 가져오기
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
  
  // 사용자 이메일을 모델에 추가
  model.addAttribute("email", userDetails.getUsername());
  
  // 사용자 이메일 출력
  System.out.println("로그인된 사용자 이메일: " + userDetails.getUsername());
  ```

### 3. 게시글 작성 및 수정 시 닉네임 자동 입력
- **경로**: /posts/create, /posts/{id}/edit
- **내용**: 게시글 작성 및 수정 시 작성자 필드는 로그인된 사용자의 닉네임으로 자동 설정되며, 해당 필드는 readonly 속성이 적용됩니다. 사용자는 작성자 필드를 수정할 수 없습니다.

### 4. 본인 게시글만 수정 및 삭제 버튼 표시
- **경로**: /posts/{id}
- **내용**: 게시글 상세 페이지에서 로그인한 사용자가 작성한 게시글인 경우에만 수정 및 삭제 버튼이 표시되도록 수정합니다.

---

## 추가 작업
- **프로젝트**: MyWebService
- **내용**: 회원가입과 로그인 이후, 사용자와 게시글 작성자 정보를 개선하기 위한 추가 작업을 진행합니다. (list.html, detail.html, create.html, edit.html 페이지도 부트스트랩 간단히 적용해서 깃헙 푸쉬해뒀습니다.)
