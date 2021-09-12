def buildJar() {
    echo "Building The App...."
    sh 'mvn package'
}
def buildDockerImage() {
    echo "Building The Docker Image...."
    withCredentials([usernamePassword(credentialsId: 'dockerhub_creds',usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        sh '''

        docker build -t mohey/demo-app:2.0 .
        echo $PASS | docker login -u $USER --password-stdin
        docker push mohey/demo-app:2.0

        '''

    }

}

def deploy() {
    echo "Deploying The App"
}

return this