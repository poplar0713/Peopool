FROM node:lts-alpine as build-stage
WORKDIR /test00
COPY package*.json ./

RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as production-stage
RUN rm /etc/nginx/default.conf
COPY ./nginx/homepage.conf /etc/nginx/homepage.conf

COPY --from=build-stage ./test00/dist /usr/share/nginx/html/homepage
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
