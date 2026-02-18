# 자바 17 환경을 기반으로 시작
FROM eclipse-temurin:21-jdk-jammy

# 작업 폴더 설정
WORKDIR /app

# 프로젝트 빌드 파일 복사 (Maven Wrapper)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# 소스 코드 및 웹 리소스 복사
COPY src src

# gradlew 실행 권한 부여 및 빌드
# Maven Wrapper 실행 권한 부여
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# 빌드된 jar 파일을 app.jar로 이름 변경하여 복사
RUN cp target/*.war app.war

# DB 파일 저장을 위한 data 폴더 생성
RUN mkdir -p data

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.war"]