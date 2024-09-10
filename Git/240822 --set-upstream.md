# Git의 `--set-upstream` 옵션

## **1. 명령어 설명**

- **`git push --set-upstream origin main`**:
    - 이 명령어는 **현재 로컬 브랜치**를 **원격 저장소의 `main` 브랜치**에 푸시(push)하는 동시에, **로컬 브랜치와 원격 브랜치 사이의 추적 관계(upstream)**를 설정합니다.
    - 이후 **`git push`** 명령어만 사용해도 자동으로 **`origin/main` 브랜치**로 푸시됩니다. 즉, 매번 원격 브랜치를 명시하지 않아도 됩니다.

## **2. 명령어 구성**

- **`git push`**: 로컬 저장소의 변경 사항을 원격 저장소로 푸시하는 명령어입니다.
- **`--set-upstream`**: 로컬 브랜치와 원격 브랜치 간의 추적 관계를 설정하여, 이후 간단한 `git push`만으로 자동으로 원격 브랜치에 푸시됩니다.
- **`origin`**: 원격 저장소의 기본 이름입니다.
- **`main`**: 원격 브랜치 이름입니다.

## **3. 사용 예시**

- **새로운 로컬 브랜치를 생성하고, 이를 원격 저장소에 푸시하는 경우**:

    1. 로컬에서 새로운 브랜치 생성:

       ```bash
       git checkout -b feature/new-feature
       ```

    2. 생성된 브랜치를 원격 저장소에 푸시하면서 업스트림 설정:

       ```bash
       git push --set-upstream origin feature/new-feature
       ```

    3. 이후 간단히 `git push` 명령어만 사용하면 `feature/new-feature` 브랜치가 원격 저장소로 자동으로 푸시됩니다.

       ```bash
       git push
       ```

## **4. 테스트**

- **테스트 시나리오**:
    1. 새로운 로컬 브랜치 생성.
    2. 이 로컬 브랜치를 원격 저장소의 새로운 브랜치에 푸시하면서 업스트림 설정.

  ```bash
  git checkout -b feature/new-feature  # 새로운 로컬 브랜치 생성
  git push --set-upstream origin feature/new-feature  # 원격 저장소에 푸시하면서 업스트림 설정
