node('master'){
    stage("checkout"){
       cleanWs()
       checkout scm
    }

    stage("Job seeding"){
        jobDsl(targets: 'resources/jobDSL/*.groovy')
    }
}