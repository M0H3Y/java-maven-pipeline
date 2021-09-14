def buildJar() {
    echo "Building The App...."
    sh 'mvn clean package'
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

def deploy() {
    echo "Deploying The App To ec2..."
    def dockerCmd = "docker run -d -p 8080:8080 mohey/demo-app:${IMAGE_NAME}"
    sshagent(['ec2-server-creds']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@15.236.51.141 ${dockerCmd}"
    }
}


def incrementVersion() {
    echo "Incrementing App Version....."
    sh 'mvn build-helper:parse-version versions:set \
         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def versionUpdate() { 
    withCredentials([
      usernamePassword(credentialsId: 'github_creds', usernameVariable: 'Github_User', passwordVariable: 'Github_Pass')
    ]) {
       sh """
            git config user.email jenkins@test.com
            git config user.name  jenkins
            git remote set-url origin https://${Github_User}:${Github_Pass}@github.com/M0H3Y/java-maven.git
            git add pom.xml
            git commit -m "Jenkins Update Version In pom.xml"
            git push origin HEAD:jenkins-jobs

            echo 'Testing Multibranch Pipeline.....'

        """
    }
}
return this
