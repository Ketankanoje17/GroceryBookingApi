pipeline {
    agent any

    environment {
        DOCKER_REGISTRY_CREDENTIALS = credentials('docker-hub-credentials-id') // Add your Docker Hub credentials ID here
        DOCKER_IMAGE_NAME = 'your-docker-hub-username/your-docker-image-name'
    }

    stages {
        stage('Build and Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_REGISTRY_CREDENTIALS) {
                        def customImage = docker.build(DOCKER_IMAGE_NAME, '-f Dockerfile .')
                        customImage.push()
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Deployment successful!'
            }
        }

        failure {
            script {
                echo 'Deployment failed!'
            }
        }
    }
}