# 자동차 경주 게임 구현 계획

## Task 1: Engine 인터페이스 정의
- **Engine 인터페이스**: `start()` 메서드만 가집니다.

## Task 2: V6Engine 클래스
- **V6Engine 클래스**: `Engine` 인터페이스를 구현하고,
    - 출력 메시지: “V6 Engine is starting…”

## Task 3: ElectricEngine 클래스
- **ElectricEngine 클래스**: `Engine` 인터페이스를 구현하고,
    - 출력 메시지: “Electric Engine is starting…”

## Task 4: Car 클래스
- **Car 클래스**: `@Autowired`를 사용하여 `Engine` 빈을 주입합니다.
    - 어떤 엔진이 주입될지는 `@Qualifier`를 통해 결정합니다.

## Task 5: RacingGameController
- **RacingGameController**: 자동차를 생성하고, 주입된 엔진으로 경주를 시작합니다.
    - HTTP 요청 경로: `/race`로 설정합니다.

## 추가미션 (확장 과제)

### Task 6: Car 클래스에서 엔진 동적 교체
- **Car 클래스**: 엔진을 동적으로 교체할 수 있도록 `@Qualifier`를 매개변수로 받습니다.
    - `ElectricEngine`과 `V6Engine`을 상황에 맞게 선택합니다.

### Task 7: 사용자 엔진 선택
- **사용자 요청 처리**: 사용자가 `/race?engine=V6` 또는 `/race?engine=Electric` URL을 통해 엔진을 선택하여 경주를 시작할 수 있도록 구현합니다.

## 출력 예시

1. **코드 스크린샷**
    - Engine 인터페이스, V6Engine, ElectricEngine, Car, RacingGameController 등 소스 스크린샷

2. **/race 엔드포인트 호출 시 결과 화면 스크린샷**

3. **Task 6과 Task 7 확장 적용 시**:
    - 사용자가 `V6` 또는 `Electric` 엔진 선택 호출 결과 스크린샷
        - `/race?engine=V6` 호출 시
        - `/race?engine=Electric` 호출 시
