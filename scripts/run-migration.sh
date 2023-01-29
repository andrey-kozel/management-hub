docker run --rm \
  -v "${TMS_HOME}/db/users:/flyway/sql/users" \
  --network "management-hub_default" \
  flyway/flyway \
  -url=jdbc:postgresql://management-hub-postgres:5432/users \
  -user=postgres \
  -password=postgres \
  migrate

docker run --rm \
  -v "${TMS_HOME}/db/github:/flyway/sql/github" \
  --network "management-hub_default" \
  flyway/flyway \
  -url=jdbc:postgresql://management-hub-postgres:5432/github \
  -user=postgres \
  -password=postgres \
  migrate

