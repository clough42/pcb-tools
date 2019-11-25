pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B clean package'
      }
    }
    stage('Package Release') {
        steps {
            sh 'mkdir archive'
            sh 'cp bom-panelizer/target/bom-panelizer-1.0-SNAPSHOT-jar-with-dependencies.jar archive/bom-panelizer.jar'
            sh 'cp README.md archive/'
            sh 'cp LICENSE archive.'
            sh 'mkdir archive/samples'
            sh 'cp bom-panelizer/src/text/resources/sample-*.csv archive/'
            zip zipFile: 'pcb-tools.zip', archive: false, dir: 'archive'
            archiveArtifacts artifacts: 'pcb-tools.zip', fingerprint: true
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