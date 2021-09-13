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
        stage('Build Docker Image') {
            steps {
                script {
                    gv.buildDockerImage()
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    gv.deploy()
                }
            }
        }        
    }
}