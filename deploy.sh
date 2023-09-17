#!/bin/bash
REPOSITORY=/home/ubuntu/deploy # 배포된 프로젝트 경로.

cd $REPOSITORY # 이 경로로 이동해서 밑에 명령어들을 차례로 실행.

nohub java -jar -Dspring.profiles.active=prod app.jar &