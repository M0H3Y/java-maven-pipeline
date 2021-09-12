def gv
pipeline {
    agent any 
    tools {
        maven 'jenkins-maven'
    }
    stages {
        stage('Build Jar ') {
            steps {
                script {
                    echo "Building The App...."
                    sh 'mvn package'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building The Docker Image...."
                    withCredentials([
                        usernamePassword(credentials: 'dockerhub_creds',usernameVariable: 'USER', passwordVariable: 'PASS')
                    ]) {
                        sh 'docker build -t mohey/demo-app:2.0 .'
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh 'docker push mohey/demo-app:2.0'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo "Deploying The App..."
                }
            }
        }        
    }
}