#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_a15d794d685b_key -iv $encrypted_a15d794d685b_iv -in $TRAVIS_BUILD_DIR/deploying/deploy.asc.enc -out $TRAVIS_BUILD_DIR/deploying/deploy.asc -d
gpg --fast-import $TRAVIS_BUILD_DIR/deploying/deploy.asc