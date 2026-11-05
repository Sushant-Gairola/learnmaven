pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Build and Test') {
            steps {
                bat 'mvn clean test -Dtest=DemoMaven -Dgroups=regression'
            }
        }
    }
    
    post {
        always {
            step([$class: 'Publisher',
                  reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'])
        }
    }
}