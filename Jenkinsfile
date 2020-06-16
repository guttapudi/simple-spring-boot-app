pipeline {
  agent any
  stages {
    stage('Printing env vars') {
      steps {
        sh 'printenv | sort'
      }
    }

    stage('Build') {
      steps {
        git(url: 'https://github.com/guttapudi/simple-spring-boot-app.git', branch: 'master', poll: true)
      }
    }

  }
}