pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                sh "mvn clean"
            }
        }

        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Package') {
            steps {
                sh "mvn package"
            }
        }

        stage('Run service') {
            steps {
                sh "java -jar target/devops-0.0.1-SNAPSHOT.jar &"
                sh "sleep 20"
            }
        }


        stage('Test with Newman') {
            steps {
                sh "newman run DiezPorCiento.postman_collection.json"
            }
        }
    }
}
