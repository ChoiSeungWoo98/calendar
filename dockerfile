# 단계 1: 애플리케이션 빌드
FROM gradle:7.5-jdk17 as builder

# 컨테이너 내 작업 디렉터리 설정
WORKDIR /app

# Gradle 빌드 파일 및 래퍼 스크립트 복사
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# 소스 코드 복사
COPY src ./src

# 테스트 없이 애플리케이션 빌드
RUN ./gradlew clean build -x test --no-daemon

# 단계 2: 애플리케이션 실행
FROM openjdk:17-jdk-slim

# 컨테이너 내 작업 디렉터리 설정
WORKDIR /app

# 빌더 단계에서 JAR 파일 복사
COPY --from=builder /app/build/libs/calender-0.0.1-SNAPSHOT.jar /app/calender.jar

# 애플리케이션이 실행되는 포트 노출
EXPOSE 8080

# 애플리케이션을 실행할 명령 설정
CMD ["java", "-jar", "/app/calender.jar"]