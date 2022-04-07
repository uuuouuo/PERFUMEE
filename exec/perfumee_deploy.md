# Perfumee

### Server

- nginx 설치

  ```shell
  # apt repository 에 설치하고자 하는 nginx 버전 추가 
  # ubuntu 버전 (18.04: bionic, 16.04: xenial)
  $ sudo touch /etc/apt/sources.list.d/nginx.list
 
  # 저장소 업데이트
  $ sudo apt-get update
  
  # nginx 설치
  $ sudo apt-get install nginx
  ```

- mysql 설치

  ```shell
  # 우분투 서버 업데이트
  $ sudo apt-get update
  
  # mysql-server 설치
  $ sudo apt-get install mysql-server
  
  # 방화벽 설정
  $ sudo ufw allow mysql
  
  # mysql 실행
  $ sudo systemctl start mysql
  ```

- jdk 11 설치

  ```shell
  # apt 업데이트
  $ sudo apt-get update && sudo apt-get upgrade
  
  # openjdk-11-jdk 설치
  $ sudo apt-get install openjdk-11-jdk
  
  # 설치 확인
  $ java -version
  ```

- python 3 설치

  ```shell
  # apt 업데이트
  $ sudo apt-get update && sudo apt-get upgrade
  
  # openjdk-11-jdk 설치
  $ sudo apt-get install python3.7
  
  # 설치 확인
  $ python3 -V
  ```  
- Django 설치

  ```shell
  # python 실행
  $ cd /venvs/recomm/bin
  $ . activate

  # django 설치
  $ pip install django
 
  # pandas
  $ pip install pandas
  ```
- git 설치

  ```shell
  # 패키지 리스트 업데이트
  $ sudo apt-get install git
  
  # 깃 설치
  $ sudo apt install git
  
  # 설치 후 버전 확인
  $ git --version
  ```

- node js 및 npm 설치

  ```shell

  # apt 업데이트
  $ sudo apt-get update && sudo apt-get upgrade

  # node js 설치
  $ sudo apt install nodejs
  
  # npm 설치
  $ sudo apt-get install npm
  
  ```

- 방화벽

  1) 설치
     ```shell
     $ apt-get install ufw
     ```

  2) 설정

     ```shell
     $ ufw allow ssh
     $ ufw allow 80/tcp
     $ ufw allow 443/tcp
     $ ufw allow 3478/tcp
     $ ufw allow 3478/udp
     $ ufw allow 40000:57000/tcp
     $ ufw allow 40000:57000/udp
     $ ufw allow 57001:65535/tcp
     $ ufw allow 57001:65535/udp
     $ ufw enable
     ```

### Build & Deploy

- Backend 빌드 및 배포

  - S06P11C101/backend/perfumee 로 이동

  - ```shell
    # 쉘 스크립트 권한 부여 및 실행
    $ chmod +x ./deploy.sh
    $ ./deploy.sh
    ```

  - deploy.sh

    ```shell
    #!/bin/bash
    
    // 변수 설정
    REPOSITORY=/home/ubuntu/S06P22C101/backend
    PROJECT_NAME=perfumee
    
    cd $REPOSITORY/$PROJECT_NAME/
    
    echo "> Git Pull"
    
    // gitlab의 master pull
    git pull origin master
    
    echo "> 프로젝트 Build 시작"
    
    // 빌드
    ./gradlew build
    
    echo "> S06P22C101 디렉토리로 이동"
    
    cd $REPOSITORY
    
    echo "> Build 파일 복사"
    
    cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/
    
    echo "> 현재 구동중인 애플리케이션 pid 확인"
    
    CURRENT_PID=$(pgrep -f perfumee-0.0.1-SNAPSHOT.jar)
    
    echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
    
    if [ -z "$CURRENT_PID" ]; then
    	echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
    else
    	echo "> kill -15 $CURRENT_PID"
    	kill -15 $CURRENT_PID
    	sleep 5
    fi
    
    echo "> 새 애플리케이션 배포"
    
    JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)
    
    echo "> JAR Name: $JAR_NAME"
    
    // 실행
    nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &
    ```

- Frontend 빌드 및 배포

  - app/S06P12C204/frontend 로 이동

    ```shell
    $ npm install
    $ npm run serve
    ```

- Django 서버 실행

  - projects/recomm/backend/recomm 로 이동

    ```shell
    $ python manage.py runserver 0:8000
    $ bg
    $ disown -h
    ```
