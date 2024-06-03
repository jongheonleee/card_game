## OOP 과제 분석

참고 > https://github.com/FastCampusKDTBackend/KDT_DBE1_Java-OOP_Assignment

#### 앱 1차 정의 
- 해당 게임은 2~4명 참가
  - 사용자로부터 몇 명 플레이어 수(2~4)와 닉네임을 요청받음 

- 게임 라운드(100번 진행)
  - 딜러가 플레이어에게 5개의 랜덤 카드 분배
  - 딜러가 각 플레이어의 점수 계산하고 출력
  - 승자 판단, 승자 및 패자에게 점수 부여

- 승리수가 많은 플레이어부터 내림차순 출력 



#### 게임 룰 정의

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

  


#### 1차 객체 정의

- 카드 : 카드 정보 저장
  - 숫자 : 2~10까지 
  - 무늬 : ♠(스페이드), ◆(다이아), ♥(하트), ♣(클로버)

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

- 게임 매니저 : 전체 애플리케이션 흐름 관장 
  - 게임 100번 자동으로 진행
  - 최종 결과 출력(승리의 수가 많은 플레이어부터 정렬) 


#### 1차 개발 진행 흐름 

[] Card(카드) : 카드 정보 저장
- 숫자 2~10까지, 무늬 ♠(스페이드), ◆(다이아), ♥(하트), ♣(클로버) 저장 
- 그 외의 값은 예외 발생 


[] Deck(덱) : 카드 관리 및 묶음 
- 52개의 Card로 구성 
- 스스로 생성하고 섞기


[] Player(플레이어) : 게임 참여자
- 고유 닉네임(1~20자 이내), 게임 머니(10,000원), 승점, 패점 저장 
- 그 외의 값은 예외 발생

[] Dealer(딜러) : 게임 진행자
- 각 플레이어에게 카드 배분
- 각 플레이어의 점수 계산
- 승점/패점 부여


[] GameManager : 전체 게임 애플리케이션 관장
- 게임 100번 자동 진행
- 사용자에게 입력값 요구
- 결과 출력 



