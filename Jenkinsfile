pipeline {
    agent none
    options { skipDefaultCheckout(true) }
stages {

    stage('Build and Test') {
        agent {
            docker {
                image 'maven:3-alpine'
                args '-v /root/.m2:/root/.m2'
            }
        }
        options { skipDefaultCheckout(false) }
        steps {
            sh 'mvn -B -DskipTests -f "/var/jenkins_home/workspace/Peopool/peopool" clean package'
        }
    }
    stage('Docker build') {
        agent any
        steps {
            
            sh 'docker build -t peopoolbe:latest "/var/jenkins_home/workspace/Peopool/peopool"'
        }
    }
    stage('Docker run') {
        agent any
        steps{
            sh 'docker ps -f name=peopoolbe -q \
                | xargs --no-run-if-empty docker container stop'
                
            sh 'docker container ls -a -f name=peopoolbe -q \
                | xargs -r docker container rm'

            sh 'docker images -f "dangling=true" -q \
                | xargs -r docker rmi'

            sh 'docker run -d --name peopoolbe -p 8443:8443 -e JAVA_TOOL_OPTIONS='-Dspring-boot.run.jvmArguments="-Dkms.url=ws://52.79.162.52:8888/kurento"' peopoolbe:latest '
        }
    }
}
}
