package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object DataFrameFilter extends App{
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

    //filtering demo
    val pagamentosAracruz = rawPayments.filter("ds_mun = 'ARACRUZ'")

    pagamentosAracruz.show(30)

    val pagamentosOnair = rawPayments.filter("ds_mun = 'ARACRUZ' AND cpf = 10732685297")

    pagamentosOnair.show(30)

    println("another way...")
    val pagamentosAdair = rawPayments.filter(col("ds_mun") === "ARACRUZ" && col("cpf") === 12822907295L)

    pagamentosAdair.show(30)

  }

  main()
}
