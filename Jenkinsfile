pipeline {
  agent any
  stages {
    stage('Printing env vars') {
      steps {
        sh 'printenv | sort'
      }
    }

    stage('Checkout from SCM') {
      steps {
        git(url: 'https://github.com/guttapudi/simple-spring-boot-app.git', branch: 'master', poll: true)
      }
    }

    stage('Build') {
      steps {
        withMaven(maven: 'jenkins-maven') {
          sh '''mvn --version
mvn clean package'''
        }

      }
    }

    stage('Get Approval') {
      steps {
        input 'Can we deploy this ?'
      }
    }

    stage('Deploy to remote tomcat') {
      steps {
        sh 'scp ${WORKSPACE}/target/*.war tomcat@192.168.1.123:/opt/tomcat/webapps'
        mail(subject: 'Deployment Succeeded', body: 'Deployment Succeeded', to: 'guttapudi.karthik@gmail.com')
      }
    }

    stage('Archive the artifacts') {
      steps {
        archiveArtifacts(artifacts: 'target/*.war', fingerprint: true, onlyIfSuccessful: true)
      }
    }

    stage('Test Stage') {
      steps {
        junit(testResults: '**/surefire-reports/**/*.xml', allowEmptyResults: true)
      }
    }

  }
}