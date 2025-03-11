# Spring Project with Docker and Docker Compose

이 프로젝트는 Spring 애플리케이션을 Docker를 사용하여 컨테이너화하고, `docker-compose`로 여러 서비스를 함께 실행할 수 있도록 구성되었습니다.

## 필수 요구사항

- Docker: [https://www.docker.com/get-started](https://www.docker.com/get-started)에서 Docker 설치 방법을 확인하고 설치하세요.

## 실행 방법

#### Spring 프로젝트 빌드

프로젝트 디렉토리에서 다음 명령어로 jar 파일을 생성합니다.

```bash
./gradlew clean build
````

도커 컴포즈를 실행시켜 dockerfile 기반으로 스프링과 postgresql을 컨테이너로 실행시킵니다.
```bash
docker-compose up -d
```

localhost:8080를 통해 웹소켓 및 api 를 실행합니다.

- /api/v1/members/info/{id}
- /api/v1/members/update
- /api/v1/members/create
- /api/v1/members/delete/{id}

- chat 
  - 사용자는 메시지를 전송시 자신 및 접속된 모든 유저들에게 전송합니다.
  - @사용자이름 메시지를 전송하면 해당 사용자 이름의 PK 목록을 생성일자 기준으로 상위 5개를 가져옵니다.
