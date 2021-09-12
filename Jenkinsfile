pipeline {
    agent any 
    environment {
        SERVER_CREDS = credentials('server-creds')
    }
    stages {
        stage('build') {
            steps {
               echo "Building App..."
               
            }
        }

        stage('test') {
            steps {
              echo "Testing App...."
            }
        }

        stage('Deploy') {
            steps {
                 echo "Deploying App...."
                sh "echo ${SERVER_CREDS} > /tmp/out.txt" 
            }
        }
    }
}