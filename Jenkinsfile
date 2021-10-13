pipeline {
    agent any 

    stages {
        stage('Copy Files To Ansible Server') {
             steps { 
                 script {
                   echo "Copying Files To Control Node ....."
                   sshagent(["ansible_ssh_key"]) {
                       sh "scp -o StrictHostKeyChecking=no ansible/* mohey@40.89.192.235:~/"
                       withCredentials([sshUserPrivateKey(credentialsId: "ec2_ssh_key", keyFileVariable: 'keyfile')]) {
                           sh 'scp $keyfile mohey@40.89.192.235:~/ec2_ssh_key.pem'

                       }
                   } 


             }
            }
        }
    }
}