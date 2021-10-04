def gv
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

        stage('Build Jar ') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    gv.buildDockerImage()
                }
            }
        }
        stage ('provision server') {
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')
                TF_VAR_env_prefix = 'test'
            }
            steps {
                script {
                    dir('terraform') {
                        gv.provisionServer()

                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sleep(time: 90, unit: "SECONDS")
                    echo "Waiting for EC2 to initialize....."
                    gv.deploy()
                }
            }
        
        }
    }

}