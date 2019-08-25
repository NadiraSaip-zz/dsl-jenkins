pipeline{
    agent any
    stages{
        stage("Run Command"){
            steps{
                sh '''
                set +xe
                echo Hello
                ech Hello
                sudo yum install httpd -y
                ping -c 4 google.com
                yum install telnet -y
                yum install wget -y
                '''
            }
        }
        stages("Install Terraform"){
            steps{
                ws("tmp/"){
                    sh "pwd"
                    sh "wget https://releases.hashicorp.com/terraform/0.12.7/terraform_0.12.7_linux_amd64.zip"

                }
            }
        }
    }

}

