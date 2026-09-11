#!/user/bin/env groovy

def call(String imageName) {
    echo "Building the docker image...."
    withCredentials([usernamePassword(credentialsId: 'dockerhub', 
    usernameVariable: 'USER', 
    passwordVariable: 'PASS')]) {
        sh 'echo ${PASS} | docker login -u ${USER} --password-stdin'
        sh "docker build -t $imageName ."
        sh "docker push $imageName"
    }
}