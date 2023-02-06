# Exec into redis
docker exec -it management-hub-redis redis-cli

# Simple key operations
SET key1 value1
GET key1
DEL key1
EXISTS key1

# Hash operation
HSET hkey1 field1 value1
HSET hkey1 field2 value1
HLEN hkey1
HGETALL hkey1

# Get redis keys
KEYS *
SCAN 0
SCAN 0 COUNT 1

# Redis sorted set
ZADD zkey 1 one
ZADD zkey 2 two
ZADD zkey 3 three
ZADD zkey 4 four
ZADD zkey 5 five
ZRANGE zkey 0 2
ZRANGE zkey 0 2 REV
ZREM zkey "four"

# Queues
SUBSCRIBE channel
PUBLISH channel message
