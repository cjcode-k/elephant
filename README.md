# elephant

## docker build hadoop cluster

### create network
`docker network create --driver=bridge --subnet=172.90.0.0/16 --ip-range=172.90.1.0/24 --gateway=172.90.1.254 br0`

### build script
`#!/bin/bash
 
 docker run -d --name cluster-master \
  --net br0 --ip 172.90.1.11 \
  --hostname cluster-master \
  --add-host cluster-master:172.90.1.11 \
  --add-host cluster-slave1:172.90.1.12 \
  --add-host cluster-slave2:172.90.1.13 \
  -p 5002:22 \
  -p 9000:9000 \
  -p 9870:9870 \
  -p 8088:8088 \
  -p 19888:19888 \
  -v /Users/jefferychan/data/hdfs/data/master:/home/hadoop \
  jefferydchan/hadoop-cluster:v1.2 /usr/sbin/sshd -D
 
  docker run -d --name cluster-slave1 \
  --net br0 --ip 172.90.1.12 \
  --hostname cluster-slave1 \
  --add-host cluster-master:172.90.1.11 \
  --add-host cluster-slave1:172.90.1.12 \
  --add-host cluster-slave2:172.90.1.13 \
  -p 5003:5003 \
  -v /Users/jefferychan/data/hdfs/data/slave1:/home/hadoop \
  jefferydchan/hadoop-cluster:v1.2 /usr/sbin/sshd -D
 
 docker run -d --name cluster-slave2 \
  --net br0 --ip 172.90.1.13 \
  --hostname cluster-slave2 \
  --add-host cluster-master:172.90.1.11 \
  --add-host cluster-slave1:172.90.1.12 \
  --add-host cluster-slave2:172.90.1.13 \
  -p 5004:5004 \
  -v /Users/jefferychan/data/hdfs/data/slave2:/home/hadoop \
  jefferydchan/hadoop-cluster:v1.2 /usr/sbin/sshd -D
 
 docker exec -it cluster-master bash -c "jps"
`