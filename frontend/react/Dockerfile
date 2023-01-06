FROM node:19-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install --silent
COPY . ./
RUN echo "VITE_API=http://localhost:8088" > .env
EXPOSE 5173
CMD ["npm", "run", "dev"]
