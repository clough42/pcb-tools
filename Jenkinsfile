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
        archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
        junit 'build/reports/**/*.xml'
    }
  }
}