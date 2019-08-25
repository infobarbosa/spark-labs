# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  #configuracao da instancia do node1
  config.vm.define "node1" do |hadoop|
    hadoop.vm.box = "ubuntu/xenial64"

    hadoop.vm.network "private_network", ip: "192.168.56.41"
    hadoop.vm.network "public_network", bridge: "en0: Wi-Fi (AirPort)", ip: "192.168.15.41"
    hadoop.vm.hostname = "node1.infobarbosa.github.com"
    hadoop.vm.provider "virtualbox" do |v|
      v.memory = 2048
      v.cpus = 1
      v.name = "node1-lab-hadoop.vagrant"
    end

    hadoop.vm.provision "file", source: "config-files/core-site.xml", destination: "core-site.xml"
    hadoop.vm.provision "file", source: "config-files/hdfs-site.xml", destination: "hdfs-site.xml"
    hadoop.vm.provision "file", source: "config-files/yarn-site.xml", destination: "yarn-site.xml"
    hadoop.vm.provision "file", source: "config-files/mapred-site.xml", destination: "mapred-site.xml"
    hadoop.vm.provision "file", source: "config-files/hosts", destination: "hosts"
    hadoop.vm.provision "file", source: "config-files/id_rsa", destination: "id_rsa"
    hadoop.vm.provision "file", source: "config-files/id_rsa.pub", destination: "id_rsa.pub"
    hadoop.vm.provision "file", source: "packages/hadoop-3.2.0.tar.gz", destination: "hadoop-3.2.0.tar.gz"
    hadoop.vm.provision "file", source: "packages/spark-2.3.2-bin-hadoop2.7.tgz", destination: "spark-2.3.2-bin-hadoop2.7.tgz"

    #root script
    hadoop.vm.provision "shell", path: "script.sh"
  end

  ###########################
  #configuracao da instancia do node2
  config.vm.define "node2" do |hadoop|
    hadoop.vm.box = "ubuntu/xenial64"

    hadoop.vm.network "private_network", ip: "192.168.56.42"
    hadoop.vm.network "public_network", bridge: "en0: Wi-Fi (AirPort)", ip: "192.168.15.42"
    hadoop.vm.hostname = "node2.infobarbosa.github.com"
    hadoop.vm.provider "virtualbox" do |v|
      v.memory = 2048
      v.cpus = 1
      v.name = "node2-lab-hadoop.vagrant"
    end

    hadoop.vm.provision "file", source: "config-files/core-site.xml", destination: "core-site.xml"
    hadoop.vm.provision "file", source: "config-files/hdfs-site.xml", destination: "hdfs-site.xml"
    hadoop.vm.provision "file", source: "config-files/yarn-site.xml", destination: "yarn-site.xml"
    hadoop.vm.provision "file", source: "config-files/mapred-site.xml", destination: "mapred-site.xml"
    hadoop.vm.provision "file", source: "config-files/hosts", destination: "hosts"
    hadoop.vm.provision "file", source: "config-files/id_rsa", destination: "id_rsa"
    hadoop.vm.provision "file", source: "config-files/id_rsa.pub", destination: "id_rsa.pub"
    hadoop.vm.provision "file", source: "packages/hadoop-3.2.0.tar.gz", destination: "hadoop-3.2.0.tar.gz"
    hadoop.vm.provision "file", source: "packages/spark-2.3.2-bin-hadoop2.7.tgz", destination: "spark-2.3.2-bin-hadoop2.7.tgz"

    #root script
    hadoop.vm.provision "shell", path: "script.sh"
  end

  #####################
  #configuracao da instancia do node3
  config.vm.define "node3" do |hadoop|
    hadoop.vm.box = "ubuntu/xenial64"

    hadoop.vm.network "private_network", ip: "192.168.56.43"
    hadoop.vm.network "public_network", bridge: "en0: Wi-Fi (AirPort)", ip: "192.168.15.43"
    hadoop.vm.hostname = "node3.infobarbosa.github.com"
    hadoop.vm.provider "virtualbox" do |v|
      v.memory = 2048
      v.cpus = 1
      v.name = "node3-lab-hadoop.vagrant"
    end

    hadoop.vm.provision "file", source: "config-files/core-site.xml", destination: "core-site.xml"
    hadoop.vm.provision "file", source: "config-files/hdfs-site.xml", destination: "hdfs-site.xml"
    hadoop.vm.provision "file", source: "config-files/yarn-site.xml", destination: "yarn-site.xml"
    hadoop.vm.provision "file", source: "config-files/mapred-site.xml", destination: "mapred-site.xml"
    hadoop.vm.provision "file", source: "config-files/hosts", destination: "hosts"
    hadoop.vm.provision "file", source: "config-files/id_rsa", destination: "id_rsa"
    hadoop.vm.provision "file", source: "config-files/id_rsa.pub", destination: "id_rsa.pub"
    hadoop.vm.provision "file", source: "packages/hadoop-3.2.0.tar.gz", destination: "hadoop-3.2.0.tar.gz"
    hadoop.vm.provision "file", source: "packages/spark-2.3.2-bin-hadoop2.7.tgz", destination: "spark-2.3.2-bin-hadoop2.7.tgz"

    #root script
    hadoop.vm.provision "shell", path: "script.sh"
  end

  #####################
  #configuracao da instancia do node master
  config.vm.define "master" do |hadoop|
    hadoop.vm.box = "ubuntu/xenial64"

    hadoop.vm.network "private_network", ip: "192.168.56.40"
    hadoop.vm.network "public_network", bridge: "en0: Wi-Fi (AirPort)", ip: "192.168.15.40"
    hadoop.vm.hostname = "master.infobarbosa.github.com"
    hadoop.vm.provider "virtualbox" do |v|
      v.memory = 2048
      v.cpus = 1
      v.name = "master-lab-hadoop.vagrant"
    end

    hadoop.vm.provision "file", source: "config-files/hadoop.service", destination: "hadoop.service"
    hadoop.vm.provision "file", source: "config-files/yarn.service", destination: "yarn.service"
    hadoop.vm.provision "file", source: "config-files/core-site.xml", destination: "core-site.xml"
    hadoop.vm.provision "file", source: "config-files/hdfs-site.xml", destination: "hdfs-site.xml"
    hadoop.vm.provision "file", source: "config-files/yarn-site.xml", destination: "yarn-site.xml"
    hadoop.vm.provision "file", source: "config-files/mapred-site.xml", destination: "mapred-site.xml"
    hadoop.vm.provision "file", source: "config-files/hosts", destination: "hosts"
    hadoop.vm.provision "file", source: "config-files/id_rsa", destination: "id_rsa"
    hadoop.vm.provision "file", source: "config-files/id_rsa.pub", destination: "id_rsa.pub"
    hadoop.vm.provision "file", source: "packages/hadoop-3.2.0.tar.gz", destination: "hadoop-3.2.0.tar.gz"
    hadoop.vm.provision "file", source: "packages/spark-2.3.2-bin-hadoop2.7.tgz", destination: "spark-2.3.2-bin-hadoop2.7.tgz"

    #root script (padrao para todos os hosts)
    hadoop.vm.provision "shell", path: "script.sh"

    #root script (apenas no master)
    $script1 = <<-SCRIPT
      #format hdfs
      su hadoop -c "/opt/hadoop/bin/hdfs namenode -format"

      #hdfs service
      mv hadoop.service /etc/systemd/system/
      mv yarn.service /etc/systemd/system/
      chown hadoop:hadoop /etc/systemd/system/hadoop.service
      chown hadoop:hadoop /etc/systemd/system/yarn.service
      systemctl enable hadoop.service
      systemctl enable yarn.service
      systemctl daemon-reload
      systemctl start hadoop
      systemctl start yarn
    SCRIPT

    hadoop.vm.provision "shell", inline: $script1
  end

end
