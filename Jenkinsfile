pipeline {
  agent any
  stages {
    stage('Run this only on master branch') {
      when {
        branch 'master'
      }
      steps {
        sh 'echo "Running this step only when on master branch"'
      }
    }

    stage('Printing env vars') {
      parallel {
        stage('Printing env vars') {
          steps {
            withCredentials(bindings: [usernamePassword(credentialsId: 'sample-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
              sh 'echo $PASSWORD'
              echo USERNAME
              echo "username is $USERNAME"
            }

            sh 'echo $USERNAME'
            sh 'printenv | sort'
          }
        }

        stage('Another printing vars') {
          steps {
            echo 'Another printing vars'
          }
        }

      }
    }

    stage('Checkout from SCM') {
      steps {
        git(url: 'https://github.com/guttapudi/simple-spring-boot-app.git', branch: 'master', poll: true)
      }
    }

    stage('Build') {
      agent any
      steps {
        withMaven(maven: 'jenkins-maven') {
          sh '''mvn --version
mvn clean package'''
        }

      }
    }

    stage('Get Approval') {
      post {
        success {
          sh 'echo Got the approval'
        }

      }
      steps {
        mail(subject: "Job '${JOB_NAME}' (${BUILD_NUMBER}), commit '${GIT_COMMIT}' on branch '${GIT_BRANCH}' is waiting for input", body: 'Waiting for you approval', to: 'guttapudi.karthik@gmail.com')
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