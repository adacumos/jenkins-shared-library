package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def dockerBuild(String imageName) {
        script.echo "Building docker image...."
        script.sh "docker build -t $imageName ."
    }

    def dockerLogin() {
        script.echo "Loging into Docker...."
        script.withCredentials([docker.usernamePassword(credentialsId: 'dockerhub', 
        usernameVariable: 'USER', 
        passwordVariable: 'PASS')]) {
            script.sh "echo \$PASS' | docker login -u \$USER' --password-stdin"
        }
    }

    def dockerPush(String imageName) {
        script.echo "Pushing build to Docker...."
        script.sh "docker push $imageName"
    }
 
}