# 퀴즈 시스템 구현 계획

## Task 1: QuizQuestion 클래스 생성
- **QuizQuestion 클래스**
    - **멤버필드**: 질문 내용, 정답, 선택지 등
    - **생성자**: 매개변수를 통한 초기화
    - **Getter/Setter**: 각 멤버필드에 대한 접근 메서드

## Task 2: QuizSession 클래스 생성
- **QuizSession 클래스**
    - **@SessionScope**: 사용자의 퀴즈 진행 상태(현재 문제, 점수)를 세션 동안 유지
    - **생성자**: 출제할 퀴즈 정보를 미리 삽입하고 관리

## Task 3: QuizController 생성
- **QuizController**
    - **HTTP 요청 처리**
        - 퀴즈 시작: `/quiz`
        - 퀴즈 제출: `/quiz`
        - 퀴즈 결과: `/quiz-result`
        - 퀴즈 재시작: `/restart`

## Task 4: 뷰 페이지 작성
- 필요한 뷰 페이지는 적절히 구성하여 사용자가 퀴즈를 진행할 수 있도록 합니다.