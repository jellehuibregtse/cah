FROM node:10-alpine as build-step

RUN mkdir /app
WORKDIR /app
COPY package.json /app
RUN yarn install
COPY . /app

# create the optimized build
RUN yarn build

# command to run
RUN yarn global add serve
EXPOSE 3000
CMD ["serve", "-s", "build"]
