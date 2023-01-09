docker run --rm \
  -v "${TMS_HOME}/db/users:/flyway/sql/users" \
  -v "${TMS_HOME}/db/github:/flyway/sql/github" \
  --network "management-hub_default" \
  flyway/flyway \
  -url=jdbc:postgresql://management-hub-postgres:5432/postgres \
  -user=postgres \
  -password=postgres \
  migrate
