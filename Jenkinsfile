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
            sh 'rm -rf archive'
            sh 'rm -f pcb-tools.zip'
            sh 'mkdir archive'
            sh 'cp bom-panelizer/target/bom-panelizer-1.0-SNAPSHOT-jar-with-dependencies.jar archive/bom-panelizer.jar'
            sh 'cp README.md archive/'
            sh 'cp LICENSE archive/'
            sh 'mkdir archive/samples'
            sh 'cp bom-panelizer/src/test/resources/sample-*.csv archive/samples/'
            sh 'cp etc/scripts/bom-panelizer.bat archive/'
            script {
                zip zipFile: 'pcb-tools.zip', archive: true, dir: 'archive'
            }
        }
    }
  }
  post {
    always {
        junit '**/surefire-reports/*.xml'
    }
  }
}