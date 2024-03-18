pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'docker-login-credentials' // Corrected Docker credentials ID
    }

    stages {
        stage('SCM Checkout') {
            steps {
                git 'https://github.com/RamisettyNaveen/BankingApp-Project.git'
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('Build Docker Image') {
            steps {
                // Authenticate with Docker registry
                withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                    sh "docker login -u rnavindevops -p Naveen@1993"
                    // Build Docker image
                    sh "docker build -t rnavindevops/bankingproject:1.0 ."
                }
            }
        }

        stage('Push the Docker Image') {
            steps {
                script {
                    // Push Docker image
                    sh "docker push rnavindevops/bankingproject:1.0"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    kubeconfig(credentialsId: 'k8s', serverUrl: 'https://172.31.37.92:6443') {
                        sh 'kubectl apply -f kubernetesdeploy.yml'
                    }
                }
            }
        }
    }
}

