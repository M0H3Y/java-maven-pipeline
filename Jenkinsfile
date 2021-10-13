pipeline {
    agent any 
    environment {
        ANSIBLE_SERVER = "40.89.192.235"
    }
    stages {
        stage('Copy Files To Ansible Server') {
             steps { 
                 script {
                   echo "Copying Files To Control Node ....."
                   sshagent(["ansible_ssh_key"]) {
                       sh "scp -o StrictHostKeyChecking=no ansible/* mohey@${ANSIBLE_SERVER}:~/"
                       withCredentials([sshUserPrivateKey(credentialsId: "ec2_ssh_key", keyFileVariable: 'keyfile')]) {
                           sh 'scp $keyfile mohey@$ANSIBLE_SERVER:~/ec2_ssh_key.pem'

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
                    remote.host = "${ANSIBLE_SERVER}"
                    remote.allowAnyHosts = true
                    withCredentials([sshUserPrivateKey(credentialsId: "ansible_ssh_key", keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
                           remote.user = user
                           remote.identityFile = keyfile
                           sshScript remote: remote, script: "prepare-ansible-server.sh"
                           sshCommand remote: remote, command: "ansible-playbook deploy-docker.yaml" 

                   }
                }
            }
        }
    }

}