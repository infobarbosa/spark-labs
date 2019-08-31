#!/bin/bash

#hosts
cat hosts >> /etc/hosts
sed -i "s/127.0.1.1/#127.0.1.1/" /etc/hosts

useradd -m hadoop
echo "hadoop:hadoop" | chpasswd

locale-gen en_US.UTF-8
add-apt-repository ppa:openjdk-r/ppa
apt-get -y update && apt-get install -y openjdk-8-jdk scala net-tools

echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre" >> /etc/profile
echo "export PATH=${JAVA_HOME}/bin:${PATH}" >> /etc/profile

#wget -qO- http://ftp.unicamp.br/pub/apache/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz | tar xvz -C /opt/
tar xzf hadoop-3.2.0.tar.gz
mv hadoop-3.2.0 /opt/hadoop
chown -R hadoop:hadoop /opt/hadoop

tar xzf spark-2.3.2-bin-hadoop2.7.tgz
mv spark-2.3.2-bin-hadoop2.7 /opt/spark
chown -R hadoop:hadoop /opt/spark

#variaveis de ambiente
echo "export HADOOP_HOME=/opt/hadoop" >> /etc/profile
echo "export HADOOP_CONF_DIR=/opt/hadoop/etc/hadoop" >> /etc/profile
echo "export HADOOP_MAPRED_HOME=/opt/hadoop" >> /etc/profile
echo "export LD_LIBRARY_PATH=/opt/hadoop/lib/native" >> /etc/profile
echo "export SPARK_HOME=/opt/spark" >> /etc/profile
echo "export PATH=$PATH:/opt/hadoop/bin:/opt/hadoop/sbin:/opt/spark/bin" >> /etc/profile

sed -i "/#[[:space:]]*export[[:space:]]*JAVA_HOME=/ s/#[[:space:]]*export[[:space:]]*JAVA_HOME=.*/export JAVA_HOME=/" /opt/hadoop/etc/hadoop/hadoop-env.sh
sed -i "/export[[:space:]]JAVA_HOME=/ s:=.*:=/usr/lib/jvm/java-8-openjdk-amd64/jre:" /opt/hadoop/etc/hadoop/hadoop-env.sh

su hadoop -c 'touch /opt/hadoop/etc/hadoop/workers' 
echo "node1" >> /opt/hadoop/etc/hadoop/workers
echo "node2" >> /opt/hadoop/etc/hadoop/workers

su hadoop -c 'mkdir -p /home/hadoop/.ssh'
chmod 700 /home/hadoop/.ssh
mv /home/vagrant/id_rsa /home/hadoop/.ssh/
mv /home/vagrant/id_rsa.pub /home/hadoop/.ssh/
chown -R hadoop:hadoop /home/hadoop/.ssh/*
su hadoop -c 'touch /home/hadoop/.ssh/authorized_keys'
su hadoop -c 'cat /home/hadoop/.ssh/id_rsa.pub > /home/hadoop/.ssh/authorized_keys'
chmod 600 /home/hadoop/.ssh/*

mv /home/vagrant/*.xml /opt/hadoop/etc/hadoop/
chown -R hadoop:hadoop /opt/hadoop/etc/hadoop/

#spark configs
cp /opt/spark/conf/spark-env.sh.template /opt/spark/conf/spark-env.sh
echo "export SPARK_MASTER_HOST='192.168.56.40'" >> /opt/spark/conf/spark-env.sh
echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre" >> /opt/spark/conf/spark-env.sh
su hadoop -c "touch /opt/spark/conf/slaves"
echo "master" >> /opt/spark/conf/slaves
echo "node1" >> /opt/spark/conf/slaves
echo "node2" >> /opt/spark/conf/slaves
