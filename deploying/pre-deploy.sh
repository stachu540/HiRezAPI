#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_b20503c2b955_key -iv $encrypted_b20503c2b955_iv -in hirezapi.asc.enc -out hirezapi.asc -d
gpg --fast-import hirezapi.asc