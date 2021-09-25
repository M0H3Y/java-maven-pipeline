pipeline {
    agent any 

    stages {
        stage('Build') {
            steps {
                script {
                    echo "Building The App.."
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    echo "Building The Docker Image..."
                }
            }
        }

        stage('Deploy') {
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')

            }
            steps {
                script {
                    echo "Deploying The App.."
                    sh 'kubectl create deployment nginx-deployment --image=nginx'
                }
            }
        }


    }
}