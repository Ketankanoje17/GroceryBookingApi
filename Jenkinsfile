pipeline {
    agent any

    stages {
        // stage('Build and Push Docker Image') {
        //     steps {
        //         script {
        //            sh 'docker build -t MyAppImage .'
        //             }
        //         }
        //     }
        // }

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
