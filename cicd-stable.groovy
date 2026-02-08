node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/hlasm-language-serverport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/hlasm-language-serverport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'A language server protocol (LSP) implemenation for the High Level Assembler language.'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
