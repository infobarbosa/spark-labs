package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace}

object DataFrameLimit extends App{
  def main() {
    val spark = SparkSession
      .builder()
      .appName("BolsaFamiliaDF")
      .master("local[2]")
      .getOrCreate()

    val rawPayments = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .option("delimiter", ";")
      .option("encoding", "ISO-8859-1")
      .csv("/Users/infobarbosa/labs/bolsa-familia/in/201811_BolsaFamilia_Pagamentos.csv")
      .toDF("referencia", "competencia", "uf", "cd_mun", "ds_mun", "cpf", "favorecido", "valor_parcela")

    rawPayments.printSchema()

    //select
    val competencia_valores =
      rawPayments
        .withColumn("vl_parcela", regexp_replace(col("valor_parcela"), ",", ".")
          .cast("float"))
      .select("competencia", "vl_parcela")
          .limit(10)

    competencia_valores.show(30)

  }

  main()

}
