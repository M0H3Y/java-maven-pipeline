pipeline {
    agent any 

    parameters {
        choice(name: 'VERSION', choices: ['1.1','1.2','1.3'])
        booleanParam(name: 'executeTests', defaultValue: true)
    }
    stages {
        stage('build') {
            steps {
               echo "Building App..."
               
            }
        }

        stage('test') {
            when {
                expression {
                    params.executeTests 
                }
            }
            steps {
              echo "Testing App...."
            }
        }

        stage('Deploy') {
            steps {
                 echo "Deploying App...."
                 echo "Version ${params.VERSION}"
            }
        }
    }
}