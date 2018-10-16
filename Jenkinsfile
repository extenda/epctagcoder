library identifier: 'pipeline-library', changelog: false

configuration {
  email = false
}
// clean verify site-deploy
def label = 'ecs-small'

buildProject {
  node(label) {
    stage ('commit') {
      checkout scm
      test {
        if (branch('master')) {
          mvn 'clean install -DnoSnapshots=true'
        } else {
          mvn '-U clean install'
        }
      }
    }

    if (branch('master')) {
      stage('nexus') {
        mvn 'deploy -DjenkinsDeploy=true'
      }
    }

    if (branch('master')) {
      milestone 1
      stage('devdoc') {
        mvn 'site-deploy'
      }
    }

    sonarStage() {
      mvnSonarQube()
    }

    notification 'success'
  }

  if (branch('master')) {
    releaseProject { releaseVersion, developmentVersion ->
      node(label) {
        checkout scm
        mvnRelease args: '-DskipTests -DjenkinsRelease=true', tag: "v$releaseVersion"
      }
    }
  }
}