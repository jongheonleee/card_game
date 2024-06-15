## OOP 과제 분석

참고 > https://github.com/FastCampusKDTBackend/KDT_DBE1_Java-OOP_Assignment

### 📌 앱 1차 정의 
- 해당 게임은 2~4명 참가
  - 사용자로부터 몇 명 플레이어 수(2~4)와 닉네임을 요청받음 

- 게임 라운드(100번 진행)
  - 딜러가 플레이어에게 5개의 랜덤 카드 분배
  - 딜러가 각 플레이어의 점수 계산하고 출력
  - 승자 판단, 승자 및 패자에게 점수 부여

- 승리수가 많은 플레이어부터 내림차순 출력 



### 📌 게임 룰 정의

참고 > https://poker.hangame.com/gameguide/poker7/game_7poker2_2.html

- 높은 무늬 순서 
  -  ♠(스페이드) > ◆(다이아) > ♥(하트) > ♣(클로버)

- 높은 숫자 순서
  - A > K > Q > J > 10~2

- 족보 
  - 1. 로얄 스트레이트 플러쉬 : 5장 모두 같은 무늬 & 10-J-Q-K-A 연달아 있음 (13점)
  - 2. 백 스트레이트 플러쉬 : 5장 모두 같은 무늬 & A-2-3-4-5 연달아 있음 (12점)
  - 3. 스트레이트 플러쉬 : 5장 모두 같은 무늬 & 숫자가 연달아 있음 (11점)
  - 4. 포카드 : 같은 숫자의 카드 4장 있음 (10점)
  - 5. 풀하우스 : 같은 숫자 3개(트리플)와 같은 숫자 2개(원페어) (9점)
  - 6. 플러쉬 :  5장 모두 같은 무늬 (8점)
  - 7. 마운틴 : 모든 무늬가 같지는 않고 10, J, Q, K, A가 연달아 있음 (7점)
  - 8. 백스트레이트 : 모든 무늬가 같지는 않고 A, 2, 3, 4, 5가 연달아 있음 (6점)
  - 9. 스트레이트 : 모든 무늬가 같지는 않고 카드 5장의 숫자가 연달아 있음 (5점)
  - 10. 트리플 : 5장의 카드 중에서 3장의 숫자가 같음 (4점)
  - 11. 투 페어 : 같은 숫자 두 개(원페어)가 두 쌍이 있음 (3점)
  - 12. 원 페어 : 5장의 카드 중에서 2장의 숫자가 같음 (2점)
  - 13. 노 페어 : 5장의 카드로 위의 경우 어디에도 해당하지 않음 (1점)
  
  위의 족보를 기준으로 플레이어의 점수 계산하기 

  


### 📌 1차 객체 정의

- 카드 : 카드 정보 저장
  - 숫자 : 2~14까지 
  - 무늬 : ♠(스페이드), ◆(다이아), ♥(하트), ♣(클로버) -> 1, 2, 3, 4로 표현

- 덱 : 카드 관리 
  - 서로 다른 52장의 카드 저장 
  
- 플레이어 : 사용자 플레이어, 게임에서 발생한 기록 저장 
  - 고유 닉네임(1~20)
  - 게임 머니 10,000원
  - 스코어(승/패)

- 딜러 : 게임 진행자 
  - 각 플레이어에게 카드 배분(5장의 카드)
  - 매 게임마다 카드의 결과를 계산(일반적인 포커의 랭크를 참고)
    - 승자 100원과 1승 획득
    - 패자 0원과 1패 추가 

- 게임 컨트롤러 : 전체 애플리케이션 흐름 관장 
  - 게임 100번 자동으로 진행
  - 최종 결과 출력(승리의 수가 많은 플레이어부터 정렬) 

### 📌 클래스 다이어그램 
<img width="633" alt="스크린샷 2024-06-10 오전 12 13 06" src="https://github.com/jongheonleee/KDT_DBE1_Java-OOP_Assignment/assets/87258372/7c8df986-4fd1-4685-b9c7-805faae07d97">


### 📌 1차 개발 진행 흐름 [✅]

[✅] Card(카드) : 카드 정보 저장
- 숫자 2~14까지, 무늬 ♠(스페이드), ◆(다이아), ♥(하트), ♣(클로버) 저장 
- 그 외의 값은 예외 발생 


[✅] Deck(덱) : 카드 관리 및 묶음 
- 52개의 Card로 구성 
- 스스로 생성하고 섞기


[✅] Player(플레이어) : 게임 참여자
- 고유 닉네임(1~20자 이내), 게임 머니(10,000원), 승점, 패점 저장 
- 그 외의 값은 예외 발생

[✅] Dealer(딜러) : 게임 진행자
- 각 플레이어에게 카드 배분
- 각 플레이어의 점수 계산
- 승점/패점 부여


[✅] GameController : 전체 게임 애플리케이션 관장
- 게임 100번 자동 진행
- 사용자에게 입력값 요구
- 결과 출력 

### 📌 2차 개발 진행 흐름 []

[✅] GameController에서 view 분리

[✅] PlayerFactory : Player를 생성하고 관리함

[✅] GameComponentFactory : 특정 게임에 필요한 객체(요소) 생성하고 관리함, Flyweight 패턴 
( DealerFactory : Dealer를 생성하고 관리함, DeckFactory : Deck을 생성하고 관리함, 또한 요청에 따라서 덱을 배분함
)

[✅] Players : 이 객체 생성해서 Dealer 역할 축소(계산 알고리즘에 집중)

[✅] Dealer의 계산 알고리즘 분리 : 전략 패턴 적용


[✅] Dealer가 너무 많은 것을 알고있음, 이 부분 분리해내기
- 1차 개발, Dealer가 Deck도 가지고 있고 Players도 가지고 있음
- 이 부분 iv로 가지고 있지말고 필요한 값만 매개변수로 넘겨서 진행


### 📌 3차 개발 진행 흐름 []

[✅] Card : iv 한개로 변경, 무늬, 숫자는 메서드로 산출할 수 있는 형태로 구분

[✅] Strategy : 해당 알고리즘 비효율적임, 일일이 특정 족보에 맞는지 판단하기 보다는 공통부분 찾아내서 하나의 메서드로 다루기

[] 컨틀롤러 빌더 패턴 적용

[] 클래스 다이어그램 그리기


### 📌 설계 고민

#### [06/06 수요일]
> 변경되는 것과 변경되지 않는 것(전제 조건 : 포커 게임에 국한해서 생각, 다른 게임으로 변경되는 것은 생각하지 말기)
01. 변경되는 것 : 점수 계산 알고리즘, 게임 진행 방식(플레이어 인원, 게임 횟수, 자동 진행), 기본 초기값, 계산 값
- 계산 알고리즘은 쉽게 갈아끼울 수 있어야함
- 게임 진행 방식에 필요한 값들은 외부에서 넣어주는게 좋음 


02. 변경되지 않는 것 : 앱을 구성하는 객체(카드, 덱, 플레이어, ...)
- 스프링 컨테이너 형식처럼 관리하는 것은 어떨까? 예를 들어서 덱, 게임 매니저, ... 은 싱글톤으로 공유하고 플레이어는 프로토타입으로 복제해줌


>  1차 계발(6/7일 이후) 이후에 적용해볼 것들 => Flyweight, Singleton, Strategy, Factory Method
01. Deck 팩토리를 만드는 건 어떨까? Flyweight 패턴 적용해서 관심사 분리(생성과 사용 분리)
02. Card와 Deck은 싱글톤으로 구현하는 건 어떨까? Card는 n개 이기때문에 Enum으로 묶는게 좋을 듯, Deck은 싱글톤 가능
03. Dealer의 플레이어 점수 계산을 전략 패턴으로 구현. 해당 점수 계산 알고리즘은 여러 방식으로 적용 가능, 또한 게임 룰도 추후에 바뀔 수 있음 
04. Dealer가 Player의 점수를 계산하는 방식을 sort('비교 대상', '비교 기준') 과 유사하게 구현하는 것은 어떨까? 매우 좋은듯 

#### [06/07 목요일]

> 딜러 객체에서 족보별로 계산하는 부분에 중복되는 코드 따로 빼서 메서드로 정의 


