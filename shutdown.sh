docker-compose down
docker rmi -f jenio_payment-api jenio_database
docker volume rm $(docker volume ls -qf dangling=true)
