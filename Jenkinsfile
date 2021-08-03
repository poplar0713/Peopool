pipeline {
	agent none
	options { skipDefaultCheckout(true) }
    
	stages {
		
		stage('Docker build') {
			agent any
			options { skipDefaultCheckout(false) }
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
				
				sh 'docker run -d --name peopoolfe -p 3000:3000 peopoolfe:latest'
			}
		}
	}
}
