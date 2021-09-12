pipeline {
    agent any 

    stages {

        stage('test') {
            steps {
                script {
                    echo "Testing The App.."
                    echo "Executing The Pipeline For Branch $BRANCH_NAME"
                }
            }
        }
        stage('Build') {
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    echo "Building The App.."
                }
            }
        }

        stage('Deploy') {
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }

            steps {
                script {
                    echo "Deploying The App.."
                }
            }
        }


    }
}