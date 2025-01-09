FROM ubuntu:latest
LABEL authors="tipom"

ENTRYPOINT ["top", "-b"]