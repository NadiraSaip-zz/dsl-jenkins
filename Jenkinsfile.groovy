pipeline{
    agent any
    stages{
        stage("Run Command"){
            steps{
                sh '''
                set +xe
                echo Hello
                ech Hello
                sudo yum install unzip httpd -y
                ping -c 4 google.com
                sudo yum install telnet -y
                sudo yum install wget -y
                '''
            }
        }
        stage("Download Terraform"){
            steps{
                ws("tmp/"){
                    script {
                        def exists = fileExists 'terraform_0.12.7_linux_amd64.zip'
                        if (exists) {
                            sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                            sh "sudo mv -f terraform /bin"
                        } else {
                            sh "wget https://releases.hashicorp.com/terraform/0.12.7/terraform_0.12.7_linux_amd64.zip"
                            sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                            sh "sudo mv -f  terraform /bin"
                        }
                    }
                }
            }       
        }
        stage("write to a file"){
            steps{
                ws("tmp/"){
                    writeFile text: "Test", file: "Testfileœ"

                }
            }
        }
        stage("Download Packer"){
            steps{
                ws("tmp/"){
                    script {
                        def exists = fileExists 'terraform_0.11.9_linux_amd64.zip'
                        if (exists) {
                            sh "unzip -o terraform_0.11.9_linux_amd64.zip"
                            sh "sudo mv packer /bin"
                        } else {
                            sh "wget https://releases.hashicorp.com/terraform/0.11.9/terraform_0.11.9_linux_amd64.zip"
                            sh "unzip -o terraform_0.11.9_linux_amd64.zip"
                            sh "sudo mv packer /bin"
                            sh "packer version"
                        }
                    }
                }
            }       
        }
        stage("Pull Repo"){
            steps{
                git("https://github.com/NadiraSaip/packer_terraform.git")
            }
        }
        stage("Build Image"){
            steps{
                // sh "packer image build updates/ami.json"
                echo "Hello"

            }
        }
    }
        post{
           success {
               echo "Done"
           }
           failure {
               mail to: "testsaip1@gmail.com", subject: “job”, body: "job completed"
           }
        }
           
    }




