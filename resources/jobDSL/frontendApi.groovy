pipelineJob('frontendApi'){
    parameters{
        gitParam('branch'){
            type('BRANCH')
            sortMode('ASCENDING_SMART')
            defaultValue('origin/master')
        }
    }
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github("Dishout-project/dishout-frontend", "ssh")
                        credentials("github-key")
                    }
                    branch('$branch')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}