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
                '''
            }
        }
    }
}

