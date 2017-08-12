FROM mhart/alpine-node:6.10.1
WORKDIR /source
ADD . .

RUN npm install

CMD ["npm", "start", "--prod"]