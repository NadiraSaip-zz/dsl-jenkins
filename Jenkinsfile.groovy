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
                sudo yum install telnet -y
                sudo yum install wget -y
                '''
            }
        }
        stage("Install Terraform"){
            steps{
                ws("tmp/"){
                    sh "pwd"
                    def exists fileExists "terraform_0.12.7_linux_amd64.zip"
                    if (exists){
                        sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                    } else {
                        sh "wget https://releases.hashicorp.com/terraform/0.12.7/terraform_0.12.7_linux_amd64.zip"
                    }
                    sh "unzip terraform_0.12.7_linux_amd64.zip"
                    sh "sudo mv terraform /bin"
                    sh "terraform version"

                }
            }
        }
    }

}




