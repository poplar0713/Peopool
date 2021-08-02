pipeline {
	agent none
	options { skipDefaultCheckout(true) }
    
	stages {
		stage('Build and Test') {
			// docker image에 명시된 image를 활용하여 steps 수행
			agent {
				docker {
					image 'maven:3-alpine'
					args '-v /root/.m2:/root/.m2'
				}
			}
			options { skipDefaultCheckout(false) }
			
		}
		stage('Docker build') {
			agent any
			steps {
				sh 'docker build -t peopoolfe:latest <front dockerfile path>'
			}
		}
		stage('Docker run') {
			agent any
			steps {
				sh 'docker ps -f name=peopoolfe -q \
					| xargs --no-run-if-empty docker container stop'
				
				sh 'docker container ls -a -f name=peopoolfe -q \
					| xargs -r docker container rm'
				
				sh 'docker images -f "dangling=true" -q \
					| xargs -r docker rmi'
				
				sh 'docker run -d --name peopoolfe -p 80:80 peopoolfe:latest'
			}
		}
	}
}
