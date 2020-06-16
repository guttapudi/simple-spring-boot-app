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

    stage('Deploy to remote tomcat server') {
      steps {
        sh 'scp ${WORKSPACE}/target/*.war tomcat@192.168.1.123:/opt/tomcat/webapps'
      }
    }

  }
}