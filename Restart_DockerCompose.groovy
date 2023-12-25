pipeline {
    agent {
        label 'ansible'
    }

    stages {
        stage('Restart_DockerCompose') {

            // environment {
            //         DISCRORD_WEBHOOK_LINK = credentials('Discord_webhook')
            // }

            steps {

                ansiColor('xterm') {
                    ansiblePlaybook(
                        // playbook: '/home/fill/ansible/playbooks/Wireguard_Restart.yml',
                        playbook: '/home/fill/ansible/playbooks/Ping.yml',
                        inventory: '/home/fill/ansible/inventory/wg_servers.ini',
                        // credentialsId: 'Jenkins_PrivateKey',
                        colorized: true)
                }

                // discordSend description: "Restart Wireguard", 
		        // link: env.BUILD_URL, 
		        // result: currentBuild.currentResult, 
		        // title: JOB_NAME, 
                // webhookURL: DISCRORD_WEBHOOK_LINK
            }
        }
    }
}