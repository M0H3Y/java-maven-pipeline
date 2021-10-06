def buildJar() {
    echo "Building The App...."
    sh 'mvn clean package'
}
def getImageVersion() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"

}
def buildDockerImage() {
    echo "Building The Docker Image...."
    withCredentials([usernamePassword(credentialsId: 'dockerhub_creds',usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh """

        docker build -t mohey/demo-app:${IMAGE_NAME} .
        echo $PASS | docker login -u $USER --password-stdin
        docker push mohey/demo-app:${IMAGE_NAME}

        """
    }

}

def provisionServer() {
    sh """
      terraform init 
      terraform apply -auto-approve
      """ 

    EC2_IP = sh(
        script: "terraform output instance_public_ip",
        returnStdout: true 
    ).trim()
}
def deploy() {

    echo "Deploying The App To ec2..."
    def shellCmd = "bash ./server-deployment.sh ${IMAGE_NAME} ${DOCKER_CREDS_USR} ${DOCKER_CREDS_PSW}"
    sshagent(['server-ssh-key']) {
        sh "scp -o StrictHostKeyChecking=no server-deployment.sh ec2-user@${EC2_IP}:/home/ec2-user/"
        sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ec2-user@${EC2_IP}:/home/ec2-user/"
        sh "ssh -o StrictHostKeyChecking=no ec2-user@${EC2_IP} ${shellCmd}"
    }
}

return this
