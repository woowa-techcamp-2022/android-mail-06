# android-mail-06
## 공통 요구사항
- 적합한 컴포넌트 선택
- 필요 시 커스텀뷰 제작
- Vector Asset 활용
- 다크모드 고려 Style & Theme 정의
## LoginActivity
- 닉네임 규칙 검증
- 이메일 규칙 검증
- 닉네임과 이메일 규칙 어긋날 시 오류 메시지
- 규칙에 부합하면 Next 버튼 활성화
- 기기 회전 시 닉네임/이메일 입력값 유지
- Next 버튼 활성화 시 사용자 정보 데이터 다음 화면으로 전달
- 다음 Activity 에서 백버튼 눌러도 되돌아가지 않도록, 화면 전환 시 스택 제거
## MainActivity
- Mail 과 Setting 화면 구현
- 기본 화면은 Mail 화면
- 각 화면을 보여주기 위한 BottomNavigationView 구현
- Navigation Drawer 구현 및 Primary, Social, Promotions 세 아이템
- 가로 600dp 이상일 경우 NavigationRailView 로 전환
- 기기 회전되어도 선택된 탭은 유지
## MailFragment
- 기본 화면은 Primary 타입 메일
- Primary 화면에서 Social, Promotions 화면으로 여러번 이동하여도 백버튼 클릭 시 Primary 화면으로 전환
- 메일에는 발신인, 제목, 본문, 발신일 정보 포함
- 발신인 이름 시작 문자가 영문인 경우, 아이콘에 첫번째 문자 표기
- 발신인 이름 시작 문자가 한글인 경우, 아이콘을 프로필 기본 아이콘으로 설정
- 메일 컴포넌트의 커스텀뷰 구현
- Mail 화면에서 백버튼 누를 시 종료
## SettingFragment
- 정보 입력 화면에서 전달받은 닉네임과 이메일 화면에 표시
- 백버튼 누를 시 Mail 화면으로 전환
