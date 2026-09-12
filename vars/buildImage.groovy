#!/usr/bin/env groovy

import com.example.Docker

def call(String imageName) {

    if (!imageName) throw new IllegalArgumentException("imageName required")

    def d = new Docker(this)

    try {
        d.dockerBuild(imageName)
        d.dockerLogin()
        d.dockerPush(imageName)
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        throw e
    }
}