pipeline {
	agent none
	options { skipDefaultCheckout(true) }
    
	stages {
		stage('Build and Test') {
			agent any 
			steps{
				sh 'npm run build'
			}
		}
		stage('Docker build') {
			agent any
			steps {
				sh 'docker build -t peopoolfe:latest "/var/jenkins_home/workspace/peopoolFE/test00"'
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
