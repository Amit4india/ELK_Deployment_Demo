pipeline {
    agent any
    parameters {
        choice(
                choices: ['CreateK8s_ELKStack', 'DestroyK8s_ELKStack'],
                description: 'Select want you want to do', name: 'PIB_Options'
        )
        string(name: 'branchName', defaultValue: 'master', description: 'Enter the name of client branch')

    }


    stages {
        stage('CreateK8s_ELKStack') {
            when {
                expression {
                    params.PIB_Options == 'CreateK8s_ELKStack'
                }
            }

            steps {
                build job: 'Airbus_Kubernetes_Setup/master', parameters: [string(name: 'Kops_Option', value: 'CreateCluster')]
                sleep time: 5, unit: "MINUTES"
                echo "Validating the cluster"
                build job: 'Airbus_Kubernetes_Setup/master', parameters: [string(name: 'Kops_Option', value: 'ValidateCluster')]
                echo "Deploying Dashboard"
                build job: 'Airbus_Kubernetes_Dashboard/master', parameters: [string(name: 'k8sDashboard_Option', value: 'CreateDashboard')]
                echo "Deploying Ingress_Controller"
                build job: 'Airbus_Kubernetes_Ingress/master', parameters: [string(name: 'k8sIngress_Option', value: 'CreateIngressController')]
                echo "Deploying ELK_STACK"
                build job: 'Airbus_Kubernetes_ELK/master', parameters: [string(name: 'elk_Option', value: 'DeployELK')]

            }
        }

        stage('DestroyK8s_ELKStack') {
            when {
                expression {
                    params.PIB_Options == 'DestroyK8s_ELKStack'
                }
            }

            steps {
                build job: 'Aribus_Kubernetes_Setup/master', parameters: [string(name: 'Kops_Option', value: 'DestroyCluster')]
            }
        }


    }
}