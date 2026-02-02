# 🛒 Spring Boot JPA 쇼핑몰 미니 프로젝트

Spring Boot와 JPA를 기반으로 구축한 사용자 맞춤형 이커머스 쇼핑몰 프로젝트입니다.  
회원 관리, 상품 정보 제공, 장바구니 및 재고 차감 로직을 포함한 주문 시스템을 핵심 기능으로 구현하였습니다.

## 1. 프로젝트 소개
- **한 줄 요약**: Spring Data JPA를 활용하여 데이터 관계를 설계하고, 주문 및 재고 관리 로직을 구현한 개인 프로젝트입니다.
- **개발 기간**: 2025.12 ~ 2026.02
- **개발 인원**: 1인 개인 프로젝트

## 2. 사용 기술 (Tech Stack)
### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.6
- **ORM**: Spring Data JPA
- **Database**: Oracle Database (ojdbc11)
- **Security**: Spring Security (비밀번호 암호화)

### Frontend
- **View**: JSP, JSTL
- **Script**: jQuery, JavaScript
- **Design**: CSS3

## 3. 주요 기능
### 👤 회원 (Member)
- **비밀번호 암호화**: `BCryptPasswordEncoder`를 사용하여 보안성을 강화한 회원가입 및 로그인 기능을 구현했습니다.
- **접근 제어**: `LoginCheckInterceptor`를 통해 로그인하지 않은 사용자가 주문이나 마이페이지에 접근하는 것을 효율적으로 차단합니다.

### 🛍️ 상품 및 주문 (Product & Order)
- **상품 조회**: 카테고리별 목록 보기 및 키워드 기반의 상품 검색 기능을 제공합니다.
- **주문 프로세스**: 주문 생성, 상세 정보 저장, 장바구니 비우기 과정을 하나의 트랜잭션으로 처리하여 데이터 무결성을 보장합니다.
- **실시간 재고 관리**: 주문 시 상품 재고를 확인하고 부족할 경우 예외를 발생시켜 결제를 방지합니다.

### 👑 관리자 (Admin)
- **대시보드**: 총 주문 수, 배송 대기 건수, 총 매출액 등 주요 통계 데이터를 제공합니다.
- **주문 관리**: 전체 주문 내역을 모니터링하고 배송 상태를 직접 변경할 수 있습니다.

## 4. 핵심 로직 및 트러블 슈팅
### 💡 트랜잭션 관리를 통한 데이터 정합성 유지
- **문제**: 여러 상품 주문 시 일부 상품만 재고가 부족할 경우, 앞선 상품들만 결제되는 데이터 불일치 위험이 있었습니다.
- **해결**: `OrderService`의 주문 메소드에 `@Transactional`을 적용하였습니다. 재고 부족 시 `RuntimeException`을 던져 전체 프로세스가 롤백되도록 설계하여 데이터 정합성을 유지했습니다.

## 5. 시작 가이드
### 환경 설정 (application.properties)
프로젝트 실행을 위해 환경 변수 설정이 필요합니다.
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

## 6. 향후 계획
- **배포**: AWS EC2와 RDS를 연동하여 실제 운영 환경과 유사한 배포 경험을 쌓을 계획입니다.
- **Security 고도화**: 인터셉터 방식을 Spring Security 필터 체인 기반 인가 처리로 전환 예정입니다.
- **API화**: REST API 구조로 변경하여 프론트엔드 분리를 시도할 예정입니다.
