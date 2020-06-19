pipeline {
    agent any
    parameters {
        choice(
                choices: ['CreateK8sCluster', 'DestroyK8sCluster',  'WSO2_VM', 'WSO2_K8s', 'Jenkins', 'Sonar'],
                description: 'Select want you want to do', name: 'PIB_Options'
        )
        string(name: 'branchName', defaultValue:'master' , description: 'Enter the name of client branch')

    }


    stages {
        stage('Create K8s Cluster') {
            when {
                expression {
                    params.PIB_Options == 'CreateK8sCluster'
                }
            }

            steps {
                build job: 'Kubernetes_Setup/master', parameters: [string(name: 'Kops_Option', value: 'CreateCluster')]
                sleep time:5,unit:"MINUTES"
                echo "Validating the cluster"
                build job: 'Kubernetes_Setup/master', parameters: [string(name: 'Kops_Option', value: 'ValidateCluster')]
                echo "Deploying Dashboard"
                build job : 'Kubernetes_Dashboard/master', parameters: [string(name: 'k8sDashboard_Option', value: 'CreateDashboard')]
                echo "Deploying Istio"
                build job : 'Istio_Setup/master', parameters: [string(name: 'Isitio_Option', value: 'PrepareIstio')]
                echo "Deploy Sample Service on Kubernetes"
                build job : 'Istio_Setup/master', parameters: [string(name: 'Isitio_Option', value: 'CreateSampleApplication')]
                sleep time:1,unit:"MINUTES"
                echo 'Checking Running Service'
                build job : 'Istio_Setup/master', parameters: [string(name: 'Isitio_Option', value: 'checkServiceStatus')]

            }
        }

        stage('Delete K8s Cluster') {
            when {
                expression {
                    params.PIB_Options == 'DestroyK8sCluster'
                }
            }

            steps {
                build job: 'Kubernetes_Setup/master', parameters: [string(name: 'Kops_Option', value: 'DestroyCluster')]
                  }
        }


        stage('WSO2 Deployment on PIB K8s') {
            when {
                expression {
                    params.PIB_Options == 'WSO2_K8s'
                }
            }

            steps {
                build job: 'PIB_WSO2_Setup', parameters: [string(name: 'Kops_Option', value: 'CreateCluster')]
            }
        }
    }
}
