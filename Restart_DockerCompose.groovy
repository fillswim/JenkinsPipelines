pipeline {
    agent {
        label 'ansible'
    }

    stages {
        stage('Restart_DockerCompose') {
            steps {
                sh 'ansible --version'
                ansiColor('xterm') {
                    ansiblePlaybook(
                        playbook: '/home/fill/ansible/playbooks/Restart_DockerContainer.yml',
                        inventory: '/home/fill/ansible/inventory/wg_servers.ini',
                        colorized: true)
                }
            }
        }
    }
}