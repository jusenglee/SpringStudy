# SpringStudy

파이썬 기반의 크롤링 코드를 데이터베이스에 삽입받고, 데이터베이스에서 필요한 정보를 찾아 홈페이지에 띄우는 Api를 자바 스프링 부트로 구현

이승주 - 데이터베이스 및 데이터 검색리스트 Api 구현
강민석 - 크롤링 및 게시판 구현

크롤링은 python으로 구현 후 데이터를 수집하여 타입별 List에 담아 필드에 매칭시켜 mariadb에 insert 시킴
이후 DB와 스프링부트를 연결시켜 JPA Repository를 통해 데이터 검색, 검색 엔진에는 JPQL로 동적 쿼리문을 따로 작성하였음.

/*사용자로부터 뷰에서 보내온 데이터를 바탕으로 쿼리문을 실행한다.
처리해야할 로직 단계는 다음과 같다
1. 검색어 서칭(필수)
2. 지역 서칭 
3. 경력 서칭
4. 학력 서칭
5. 연봉 서칭
각 단계별로 사용자가 검색 체크박스에서 추가 검색옵션을 입력 할 수도 있고 안할 수도 있으므로
동적 쿼리를 사용. 디폴트값은 모든 데이터를 가져온다*/

![image](https://user-images.githubusercontent.com/85321903/204531859-b8b9ea91-5c25-4fea-a289-bf7fe36ad1e1.png)

도메인 모델과 레포지토리, 서비스 레이어, DTO까지 단계별로 구현 완료했으나 뷰 레이어 구현자가 개인 사정으로 인해서 미참, 타임리프와 부트스트랩으로 완성도는 떨어지더라도 자체 개발

개발환경-
운영체제 : Windows 10 -통합개발환경(IDE) : IntelliJ
JDK 버전 : JDK 11
데이터 베이스 : MariaDB
빌드 툴 : Gradle
배포 : AWS

백엔드
SpringBoot, Spring Data JPA
프론트엔드
HTML, CSS, Javascript, Bootstrap, Thymeleaf
데이터베이스
MariaDB, MySQL Workbench

구현 기능 :
여러개의 직업 구인 홈페이지에서 데이터 정보 탐색
검색창 Serch 기능
카테고리 기능 구현중
