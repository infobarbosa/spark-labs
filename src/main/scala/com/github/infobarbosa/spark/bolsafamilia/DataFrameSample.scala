package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession

object DataFrameSample extends App{
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

    val totalPagamentos = rawPayments.count()

    val seed = 5
    val withReplacement = false
    val fraction = 0.001

    val sample =
      rawPayments
        .sample(withReplacement, fraction, seed)

    val totalSample = sample.count()

    println(s"total pagamentos: $totalPagamentos, total sample: $totalSample")
    sample.show(30)

  }

  main()

}
