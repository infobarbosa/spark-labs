package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession

object DataFrameParquetWriting extends App{
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


    //parquet writing demo
    rawPayments
      .filter("ds_mun = 'ARACRUZ'")
      .write.parquet("/Users/infobarbosa/labs/bolsa-familia/out/aracruz.parquet")

  }

  main()

}
