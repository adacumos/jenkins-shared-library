#!/user/bin/env groovy

def call() {
    echo "Building the docker image...."
    withCredentials([usernamePassword(credentialsId: 'dockerhub', 
    usernameVariable: 'USER', 
    passwordVariable: 'PASS')]) {
        sh 'echo ${PASS} | docker login -u ${USER} --password-stdin'
        sh 'docker build -t ${DOCKER_IMAGE} .'
        sh 'docker push ${DOCKER_IMAGE}'
    }
}