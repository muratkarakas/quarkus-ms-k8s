  
FROM golang:alpine

ENV GO111MODULE=on
ENV PORT=8090
WORKDIR /app/server
COPY go.mod .
COPY go.sum .

RUN go mod download
COPY . .
RUN ls -lrt
RUN ls -lrt models

RUN go build 

FROM alpine

WORKDIR /root/


COPY --from=0 /app/server/go-post-service .
 
CMD ["./go-post-service"]
