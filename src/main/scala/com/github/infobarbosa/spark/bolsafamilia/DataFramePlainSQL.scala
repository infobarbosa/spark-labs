package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, regexp_replace}

object DataFramePlainSQL extends App{
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
      .createOrReplaceTempView("competencia_valores")


    val valores_gt1000 = spark.sql("SELECT competencia, SUM(vl_parcela) FROM competencia_valores GROUP BY competencia ORDER BY sum(vl_parcela) DESC")

    valores_gt1000.show()

  }

  main()

}
