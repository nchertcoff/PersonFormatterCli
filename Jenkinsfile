pipeline {
    agent any

    parameters {
        choice(
            name: 'VERSION_TYPE',
            choices: ['patch', 'minor', 'major'],
            description: 'Tipo de incremento'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Increment Version') {
            steps {

                sshagent(credentials: ['github-cred']) {

                    sh '''
                    git checkout main
                    git pull --rebase origin main
                    VERSION=$(grep "^version=" gradle.properties | cut -d= -f2)

                    MAJOR=$(echo "$VERSION" | cut -d. -f1)
                    MINOR=$(echo "$VERSION" | cut -d. -f2)
                    PATCH=$(echo "$VERSION" | cut -d. -f3)

                    PATCH=$((PATCH + 1))

                    NEW_VERSION="$MAJOR.$MINOR.$PATCH"

                    echo "$NEW_VERSION"

                    sed -i "s/^version=.*/version=$NEW_VERSION/" gradle.properties

                    # git config user.email "jenkins@company.com"
                    # git config user.name "Jenkins"

                    git add gradle.properties
                    git commit -m "Bump version to $NEW_VERSION"
                    git remote set-url origin git@github.com:nchertcoff/PersonFormatterCli.git
                    git push origin HEAD:main

                    '''
                }
            }
        }
    }
}