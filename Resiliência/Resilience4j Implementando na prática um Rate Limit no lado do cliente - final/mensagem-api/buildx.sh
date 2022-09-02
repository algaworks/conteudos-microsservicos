#docker buildx create --use
mvn clean package -Dmaven.test.skip=true
docker buildx build . --no-cache --platform linux/amd64,linux/arm64 --tag algaworks/algamensagens-api:latest --push