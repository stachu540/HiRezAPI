#!/usr/bin/env bash
REPO_VERSION=$(mvn -q -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)
if [[ ! -z $TRAVIS_TAG  ] && [ $REPO_VERSION != '*-SNAPSHOT' ] && [ "$TRAVIS_BRANCH" != 'dev' ]] || [[ $REPO_VERSION == '*-SNAPSHOT' ] && [ "$TRAVIS_BRANCH" == 'dev' ]]; then
    mvn deploy -P sign,build-extras --settings ./settings.xml
fi