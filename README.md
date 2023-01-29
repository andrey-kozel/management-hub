## Setup correct shell to environment if you are useng windows

Set in idea `gitbash` as a default shell

## Install the next tools

- Java 17
- Node 18

## Naming convention

Branches: feature/${number_of_github_issue}

## Environment

Setup environment variable TMS_HOME with the path to your project

## Setup project

Run `docker compose up -d` from the project directory to run database
Run `./scripts/run-migration.sh` to apply all the flyway migrations on the database

## Run frontend

`cd ui` - go to frontend directory
`npm start` - run frontend part

## Run backend

- Open ApiGatewayApplication.java
- Press debug button on it
- Open UserApplication.java
- Press debug button on it
