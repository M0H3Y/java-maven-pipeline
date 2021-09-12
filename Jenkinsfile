def gv
pipeline {
    agent any 

    parameters {
        choice(name: 'VERSION', choices: ['1.1','1.2','1.3'])
        booleanParam(name: 'executeTests', defaultValue: true)
    }
    stages {

        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }
        stage('build') {
            steps {

                script {
                    gv.build()
                }               
            }
        }

        stage('test') {
            when {
                expression {
                    params.executeTests 
                }
            }
            steps {
             script {
                 gv.test()
             }
            }
        }

        stage('Deploy') {

            steps {

                script {
                    env.ENV = input message: "Select The Environment To Deploy To", ok: "Done", parameters: [choice(name: 'ENV', choices: ['dev','staging','production'])]
                    gv.deploy()
                    echo "Deploying To ${ENV}"
                }
            }
        }
    }
}