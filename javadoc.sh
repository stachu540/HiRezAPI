#!/usr/bin/env bash

./gradlew javadoc

for PROJECT in "smite" "paladins" "realm" "core" "."; do
    DIRECTORY=${PROJECT}/build/docs/javadoc
    if [[ PROJECT == "." ]]; then
        PROJECT="all"
    fi

    JAVADOC_DESTINATION=javadoc/${TRAVIS_TAG}/${PROJECT}

    cp -ar ${DIRECTORY} ${JAVADOC_DESTINATION}
done

cd ./javadoc

cp ./latest/index.html ./${TRAVIS_TAG}/index.html
ln -sfn ./${TRAVIS_TAG} ./latest
