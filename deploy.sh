echo "docker compose up"
docker-compose up -d

echo "seed database"
sleep 5
docker exec -it jenio_database mongorestore .