node {
    stage('SCM') {
        git 'https://github.com/xxh13/ManagerSystem.git'
    }
    stage('QA') {
        sh '~/workspace/sonar-scanner-2.8/bin/sonar-scanner'
    }
    stage('build') {
        sh "mvn -B clean package"
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d bd247d3e84aa"
        sh "docker cp target/ManageSystem.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
