package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DataFrameMean extends App{
  def main() {
    val spark = SparkSession
      .builder()
      .appName("BolsaFamiliaDF")
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
        .agg( mean("vl_parcela").alias("valor_medio") )
        .select("competencia", "valor_medio")
          .sort(desc("valor_medio"))

    competencia_valores.cache()
    competencia_valores.printSchema()

    for(row <- competencia_valores.take(20)){
      val competencia = row.getInt(0)
      val valor_medio = row.getDouble(1)
      println(f"Competencia: $competencia, valor medio: $valor_medio%1.2f")
    }

  }

  main()

}
