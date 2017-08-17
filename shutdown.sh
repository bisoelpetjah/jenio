docker-compose down
docker rmi -f jenio_payment-api jenio_database jenio_pos-frontend
docker volume rm $(docker volume ls -qf dangling=true)
