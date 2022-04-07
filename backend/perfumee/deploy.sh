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

nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &
