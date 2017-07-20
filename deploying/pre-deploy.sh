#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_b20503c2b955_key -iv $encrypted_b20503c2b955_iv -in $TRAVIS_BUILD_DIR/deploying/hirezapi.asc.enc -out $TRAVIS_BUILD_DIR/deploying/hirezapi.asc -d
gpg --fast-import $TRAVIS_BUILD_DIR/deploying/hirezapi.asc