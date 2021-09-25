pipeline {
    agent any 
    tools {
        maven 'jenkins-maven'
    }

    stages {
    
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }

        stage('Increment Version') {
            steps {
                script {
                    gv.incrementVersion()
                }
            }
        }
        stage('Build Jar ') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    gv.buildDockerImage()
                }
            }
        }

        stage('Deploy') {
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')
                APP_NAME = 'java-maven-app'
            }
            steps {
                script {
                    gv.deploy()
                }
            }
        
        }

        stage('Commit Version Update') {
            steps {
                script {
                    gv.versionUpdate()
                }
            }
        }        
    }

}