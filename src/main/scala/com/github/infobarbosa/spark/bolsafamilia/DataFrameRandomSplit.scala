package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession

object DataFrameRandomSplit extends App{
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

    val totalPagamentos = rawPayments.count()

    val seed = 5
    val splits =
      rawPayments
        .randomSplit(Array(0.25, 0.75), seed)

    val split1 = splits(0).count()
    val split2 = splits(1).count()

    println(s"total pagamentos: $totalPagamentos, split1: $split1, split2: $split2, soma: ${split1 + split2}")

  }

  main()

}
