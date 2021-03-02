# job-definitions
Repository containing Jenkins job definitions in the form of Job DSL scripts. The definition block shows that whilst this script defines the behaviour and parameters used by the job such as the branch, the actual pipeline logic is fetched from the `dishout-frontend` repository with the repo being checked out using ssh and the path to the pipeline logic being the `Jenkinsfile` script.

Below is an example of one of these scripts:

```
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
```
- `pipelineJob('frontendApi')`: Name of the pipeline job.
- `parameters`: top level block describing the paramaters used by the job. In this case, branch. Parameters can be different types: booleanParam, choiceParam, etc.
- `gitParam('branch')`: Defining the parameter allowing selection of Git branch.
- `definition`: Defining the workflow.
- `cpsScm`: Loads pipeline script from source control. In this case, pipeline script comes from Github: Dishout-project/dishout-frontend.
- `branch('$branch')`: The branch to checkout is defined here.
- `scriptPath('Jenkinsfile')`: Sets the relative location of the pipeline script within the source code repository.
