package com.example

class Docker implements Serializable {

    def docker

    Docker(docker) {
        this.docker = dcoker
    }

    def buildDockerImage(String imageName) {
        echo "Building the docker image...."
        docker.withCredentials([docker.usernamePassword(credentialsId: 'dockerhub', 
        usernameVariable: 'USER', 
        passwordVariable: 'PASS')]) {
            docker.sh "echo '${docker.PASS}' | docker login -u '${docker.USER}' --password-stdin"
            docker.sh "docker build -t $imageName ."
            docker.sh "docker push $imageName"
        }
    }

}