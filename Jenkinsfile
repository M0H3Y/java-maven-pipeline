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
                           sh "scp $keyfile mohey@40.89.192.235:~/ec2_ssh_key.pem"

                       }
                   } 

                } 
            }
        }

        stage('Execute Ansible Playbook') {
            steps {
                script {
                    echo "Executing Ansible Playbook To Configure EC2 Instances"
                    def remote = [:]
                    remote.name = "ansible-server"
                    remote.host = "40.89.192.235"
                    remote.allowAnyHosts = true
                    withCredentials([sshUserPrivateKey(credentialsId: "ansible_ssh_key", keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
                           remote.user = user
                           remote.identityFile = keyfile
                           sshCommand remote: remote, command: "ls -l" 

                   }
                }
            }
        }
    }

}