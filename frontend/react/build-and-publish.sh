BUILD_NUMBER=$(date '+%d.%m.%Y.%H.%M.%S')

IMAGE_NAME="amigoscode/amigoscode-react:${BUILD_NUMBER}"

docker build . -t ${IMAGE_NAME}

docker push ${IMAGE_NAME}