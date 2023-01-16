: "${USERNAME:?USERNAME not set or empty}"
: "${REPO:?REPO not set or empty}"
: "${TAG:?TAG not set or empty}"

docker buildx create --use

docker buildx build \
    --platform=linux/amd64,linux/arm64 \
    -t "${USERNAME}/${REPO}:${TAG}" \
    -t "${USERNAME}/${REPO}:latest" \
    "${@:2}" \
    --push \
    "$1"

