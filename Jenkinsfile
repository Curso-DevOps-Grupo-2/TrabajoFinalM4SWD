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


        stage('Test with Newman') {
            steps {
                sh "mvn spring-boot:run"
                sh "newman run DiezPorCiento.postman_collection.json"
            }
        }
    }
}
