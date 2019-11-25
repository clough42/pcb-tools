pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B clean package'
      }
    }
  }
  post {
    always {
        archiveArtifacts artifacts: '**/*.jar', fingerprint: true
        junit '**/surefire-reports/*.xml'
    }
  }
}