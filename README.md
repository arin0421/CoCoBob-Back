# 밥 먹을 친구 찾을 땐? CO-CO-BOB (2021-2 캡스톤 디자인)

## 목적
* 밥 먹을 사람을 구하는 글을 올리는 서비스를 직접 구현함으로써 Backend System의 도메인을 이해해 보자!!
* Github Issues 기능을 사용해 보자!
* branch 관리 전략 학습

## 기획
* 서비스 이용자에게 약속 관련 정보를 포함한 글을 작성하여 약속을 성사시키는 중개 플랫폼을 구현하고자 함

[피그마 주소](https://www.figma.com/file/BLNpeNVT5i5r5lJCIFuDuH/%EC%BD%94%EC%BD%94%EB%B0%A5---%EA%B0%99%EC%9D%B4-%EB%B0%A5-%EB%A8%B9%EC%9D%84-%EC%B9%9C%EA%B5%AC%EB%A5%BC-%EC%B0%BE%EC%95%84%EB%B3%B4%EC%84%B8%EC%9A%94?node-id=0%3A1)

## 프로젝트 참여 인원
* [front-end](https://github.com/tjdgh925/CoCoBob-Front) : 1
* back-end : 2

## 프로젝트 주요 기능
* 회원가입 및 로그인
* 이메일(아이디로 사용) 중복 체크, 비밀번호 암호화 등 회원 가입 및 로그인 관련된 기타 기능 추가
* google smtp를 활용한 임시 비밀번호 발급 기능 구현 -> 비밀 번호 변경
* 원하는 약속 검색 기능 개발

## 사용 기술
* Spring Boot, Gradle, MySQL, JPA

## USECASE
* 제안자
  * 제안자는 밥 약속 제안을 위해 제안을 등록, 수정, 삭제할 수 있다.
* 참여자
  * 참여자는 참여를 위해 회원가입, 회원 수정을 할 수 있다.
  * 관심 키워드 별로 검색이 가능하다.

## 데모 영상
코코밥 회원 관리
https://youtu.be/um8dDbJshNo

코코밥 게시글 관리
https://youtu.be/b5-2TBntDng
