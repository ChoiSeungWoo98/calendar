# ChoiSeungWoo98 Calendar

## Table of Contents

[ 📝 Overview](#📝-overview)  
[ 📁 Project Structure](#📁-project-structure)  
[ 🚀 Getting Started](#🚀-getting-started)  
[ 💡 Motivation](#💡-motivation)  
[ 🎬 Demo](#🎬-demo)  
[ 🌐 Deployment](#🌐-deployment)  
[ 🤝 Contributing](#🤝-contributing)  
[ ❓ Troubleshooting & FAQ](#❓-troubleshooting-&-faq)  
[ 📈 Performance](#📈-performance)  

## 📝 Overview
이 프로젝트는 개인의 일정을 관리하고, 공휴일 및 음력 정보를 제공하는 캘린더 애플리케이션입니다. 사용자는 일기를 작성하고, 목표를 설정하며, 이벤트를 관리할 수 있습니다.

### Main Purpose
- 사용자가 자신의 일정을 효율적으로 관리할 수 있도록 돕기 위해 개발되었습니다.
- 공휴일 및 음력 정보를 제공하여 사용자가 중요한 날짜를 놓치지 않도록 합니다.
- 개인의 목표를 설정하고 관리할 수 있는 기능을 제공합니다.

### Key Features
- 일기 작성 및 관리
- 목표 설정 및 관리
- 공휴일 및 음력 정보 제공
- 사용자 친화적인 인터페이스

### Core Technology Stack
- Frontend: JavaScript, HTML, CSS
- Backend: Spring Boot
- Database: MySQL
- Others: Tailwind CSS

## 📁 Project Structure
[ChoiSeungWoo98-calendar-main]
├── 📁 src
│   ├── 📁 main
│   │   ├── 📁 java
│   │   │   ├── 📁 com
│   │   │   │   ├── 📁 choi
│   │   │   │   │   ├── 📁 calender
│   │   │   │   │   │   ├── application
│   │   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── domain
│   │   │   │   │   │   ├── mapper
│   │   │   │   │   │   └── util
│   │   │   │   │   └── ...
│   │   └── 📁 resources
│   │       ├── 📁 static
│   │       │   └── 📁 js
│   │       └── 📁 templates
│   └── ...
└── ...

## 🚀 Getting Started

### Prerequisites

- Supported Operating Systems
  * Windows, macOS, Linux
- Required Software
  * Runtime environment: Java
  * Version requirements: Java 17
  * Package managers: Gradle
- System Dependencies
  * MySQL Community Server

### Installation

- Dockerfile을 사용할 수 있습니다.
- Dockerfile에 모든 설치 방법을 포함하세요.

```bash
# Clone the repository
git clone https://github.com/ChoiSeungWoo98/calendar/.git
cd calender

# Install required packages
# Frontend Setup
# Backend Setup
./gradlew clean build --stacktrace --info

# Database Setup
# MySQL 설치 및 초기화
yum update -y && yum upgrade -y
yum install -y https://dev.mysql.com/get/mysql84-community-release-el9-1.noarch.rpm
yum install -y mysql-community-server
mysqld --initialize-insecure --datadir=/var/lib/mysql
mysqld --daemonize --user=root --datadir=/var/lib/mysql
mysql -u root < src/main/resources/scripts/ci/createInitData.sql
mysql -u choi -p <password> calender < src/main/resources/scripts/ci/createInitTable.sql

# AI/Services Setup

# Configure environments
# 환경 설정 명령어
```

### Usage

```bash
# How to run
# 실행 명령어
```

## 💡 Motivation
이 프로젝트는 개인의 일정을 보다 효율적으로 관리하고, 중요한 날짜를 놓치지 않도록 돕기 위해 영감을 받았습니다. 또한, 사용자가 자신의 목표를 설정하고 달성할 수 있도록 지원하는 것이 목표입니다.

## 🎬 Demo
![Demo Video or Screenshot](path/to/demo.mp4)

## 🌐 Deployment
- AWS를 사용하여 배포할 수 있습니다.
- 배포 단계는 다음과 같습니다:
  1. AWS EC2 인스턴스 생성
  2. JDK 및 MySQL 설치
  3. 애플리케이션 빌드 및 실행

## 🤝 Contributing
- 기여를 원하시는 분은 먼저 이슈를 생성해 주세요.
- 코드 스타일은 Java의 일반적인 스타일 가이드를 따릅니다.
- Pull request를 통해 기여해 주세요.

## ❓ Troubleshooting & FAQ
- **Q: 애플리케이션이 시작되지 않아요.**
  - A: 데이터베이스 설정을 확인하세요.
- **Q: 공휴일 정보가 업데이트되지 않아요.**
  - A: API 키가 유효한지 확인하세요.

## 📈 Performance
- 성능 최적화는 주기적으로 진행됩니다.
- 데이터베이스 쿼리 최적화 및 캐싱 기법을 사용하여 성능을 개선합니다.
