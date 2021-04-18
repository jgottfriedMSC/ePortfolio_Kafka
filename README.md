# Apache Kafka ePortfolio README

Clone this project into your repository to get started.

## Installation
Prerequisite: Docker is required for this project, which means you need to install either Docker for Windows or Docker on your Linux system beforehand.

1. Open a CLI or Shell and navigate to the folder where the "docker-compose.yml" file was placed.
2. Run the following command to deploy an Apache Kafka development instance: "docker-compose up -d".
3. Run the command "docker container ls" to get a list of running containers and to find out the name of the Kafka container.
4. Run the command "docker container exec -it <name_of_kafka_container> ./bin/bash" to get access to the Kafka container.
5. Run the command "cd opt/bitnami/kafka/bin".
6. Run the command "kafka-topics.sh --create --topic cities --bootstrap-server localhost:9092" to create a Kafka topic where all your records are stored.

Now you can start to process data with the Kafka Producer and Consumer.

