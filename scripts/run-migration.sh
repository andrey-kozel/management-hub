docker run --rm \
  -v "/Users/Yauhen/Documents/Yauhen/management-hub/db/users:/flyway/sql" \
  --network "management-hub_default" \
  flyway/flyway \
  -url=jdbc:postgresql://management-hub-postgres:5432/postgres \
  -user=postgres \
  -password=postgres \
  migrate
