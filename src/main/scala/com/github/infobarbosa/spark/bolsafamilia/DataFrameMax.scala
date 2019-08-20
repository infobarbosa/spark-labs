package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DataFrameMax extends App{
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
        .groupBy("competencia")
        .agg( max("vl_parcela").alias("maior_pagamento") )
        .select("competencia", "maior_pagamento")
          .sort(desc("maior_pagamento"))

    competencia_valores.cache()
    competencia_valores.printSchema()

    for(row <- competencia_valores.take(20)){
      val competencia = row.getInt(0)
      val maior_pagamento = row.getFloat(1)
      println(f"Competencia: $competencia, maior: $maior_pagamento%1.2f")
    }

  }

  main()

}
