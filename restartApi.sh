docker rmi -f jenio_payment-api
docker volume rm $(docker volume ls -qf dangling=true)
sh deploy.sh 