services:
  db:
    container_name: postgres
    image: postgres:14.1
    environment:
      POSTGRES_USER: amigoscode
      POSTGRES_PASSWORD: password
      PGDATA: /data/postgres
    volumes:
      - db:/data/postgres
    ports:
      - "5332:5432"
    networks:
      - db
    restart: unless-stopped
  amigoscode-api:
    container_name: amigoscode-api
    image: amigoscode/amigoscode-api
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/customer
    ports:
      - "8088:8080"
    networks:
      - db
    depends_on:
      - db
    restart: unless-stopped
  amigoscode-react:
    container_name: amigoscode-react
    image: amigoscode/amigoscode-react
    build:
      context: frontend/react
      args:
        api_base_url: http://localhost:8088
    ports:
      - "3000:5173"
    depends_on:
      - amigoscode-api
    restart: unless-stopped

networks:
  db:
    driver: bridge

volumes:
  db:
