pipeline {
    agent {
        label 'ansible'
    }

    environment {

        // Playbook
        PLAYBOOK_NAME       = 'Template_Restart_DockerCompose_Service.yml'
        ANSIBLE_PATH        = '/home/fill/ansible'
        PLAYBOOK_PATH       = 'playbooks'

        // Inventory
        INVENTORY_PATH      = 'inventory'
        INVENTORY_NAME      = 'wgs_servers.ini'

        // Host
        HOST                = 'wgs_servers'
        PROJECT_FOLDER      = '/root/VPN/wg-easy-https'
        SERVICE_NAME        = 'wg-easy'

        CREDENTIALS_NAME    = 'Jenkins_PrivateKey'

    }

    stages {
        stage('Restart_DockerCompose') {
            steps {

                sh 'ansible --version'

                ansiColor('xterm') {
                    ansiblePlaybook(
                        playbook: '${ANSIBLE_PATH}/${PLAYBOOK_PATH}/${PLAYBOOK_NAME}',
                        inventory: '${ANSIBLE_PATH}/${INVENTORY_PATH}/${INVENTORY_NAME}',
                        extraVars: [
                            host: '${HOST}',
                            project_folder: '${PROJECT_FOLDER}',
                            service_name: '${SERVICE_NAME}'
                        ],
                        colorized: true)
                }
            }
        }
    }
}