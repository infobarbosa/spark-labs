package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace}

object BolsaFamiliaDF extends App {

  def main() {
    val spark = SparkSession
                  .builder()
                  .appName("BolsaFamiliaDF")
                  .getOrCreate()

    val rawPayments = spark.read
                        .format("csv")
                        .option("header", "true")
                        .option("inferSchema", "true")
                        .option("delimiter", ";")
                        .option("encoding", "ISO-8859-1")
                        .load("hdfs:///bolsafamilia/pagamentos.csv")
                        .toDF("referencia", "competencia", "uf", "cd_mun", "ds_mun", "cpf", "favorecido", "valor_parcela")

    rawPayments.printSchema()

    //show demo
    rawPayments.show(5)


    val paymentsWithVals = rawPayments.withColumn("vl_parcela", regexp_replace(col("valor_parcela"), ",", ".").cast("float"))
    paymentsWithVals.printSchema()
    paymentsWithVals.show(5)

  }

  main()
}