docker run --rm `
  -v "${PWD}/db/users:/flyway/sql"`
  --network "management-hub_default" `
  flyway/flyway `
  -url=jdbc:postgresql://management-hub-postgres:5432/users `
  -user=postgres `
  -password=postgres `
  migrate

docker run --rm `
  -v "${PWD}/db/github:/flyway/sql"`
  --network "management-hub_default" `
  flyway/flyway `
  -url=jdbc:postgresql://management-hub-postgres:5432/github `
  -user=postgres `
  -password=postgres `
  migrate
