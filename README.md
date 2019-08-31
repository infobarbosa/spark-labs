
###Clone do repositório
```
git clone https://github.com/infobarbosa/spark-labs.git
```

###Criação das chaves
```
mkdir -p config-files
cd config-files
ssh-keygen -q -t rsa -b 4096 -C "hadoop@infobarbosa.github.com" -P "" -f "./config-files/id_rsa"
```

###Download do Hadoop para a pasta _packages_ :
```
mkdir -p packages
cd packages
wget http://ftp.unicamp.br/pub/apache/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz 
```

###Download do Spark para a pasta _packages_ :
```
mkdir -p packages
cd packages
wget https://archive.apache.org/dist/spark/spark-2.3.2/spark-2.3.2-bin-hadoop2.7.tgz
```

###Formato do arquivo pagamentos.csv
```

  "M�S REFER�NCIA";"M�S COMPET�NCIA";"UF";"C�DIGO MUNIC�PIO SIAFI";"NOME MUNIC�PIO";"NIS FAVORECIDO";"NOME FAVORECIDO";"VALOR PARCELA"
```

###spark-submit
```
hdfs dfs -mkdir /bolsafamilia
hdfs dfs -put pagamentos.csv /bolsafamilia
hdfs dfs -ls /bolsafamilia

export HADOOP_USER_NAME=hadoop

spark-submit \
  --class com.github.infobarbosa.spark.bolsafamilia.BolsaFamiliaDF \
  --master yarn \
  --deploy-mode cluster \
  --executor-memory 1G \
  --num-executors 10 \
  target/scala-2.11/sparklabs_2.11-0.1.jar

spark-submit \
  --class com.github.infobarbosa.spark.bolsafamilia.BolsaFamiliaDF \
  --master yarn \
  --deploy-mode client \
  --executor-memory 1G \
  --num-executors 10 \
  target/scala-2.11/sparklabs_2.11-0.1.jar

```

###O que queremos?
Calcular
  //total de CPFs
  //total de CPFs distintos
  //total de CPFs distintos por estado
  //total de valores pagos
  //total de valores pagos por estado
  //maior pagamento efetuado
  //maior pagamento efetuado por estado
  //mínimo pagamento efetuado
  //mínimo pagamento efetuado por estado
  //quantos CPFs novos em relação ao mês anterior
  //quantos CPFs do mês anterior não voltaram a ter pagamento no mês corrente

###Referências
https://spark.apache.org/docs/latest/rdd-programming-guide.html#transformations
https://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.rdd.PairRDDFunctions
