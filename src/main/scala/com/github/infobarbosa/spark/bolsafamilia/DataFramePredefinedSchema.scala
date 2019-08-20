package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace}
import org.apache.spark.sql.types._

object DataFramePredefinedSchema extends App{
  def main() {
    val spark = SparkSession
      .builder()
      .appName("BolsaFamiliaDF")
      .master("local[2]")
      .getOrCreate()

    val manualSchema = StructType(Array(
      StructField("MÊS REFERÊNCIA", IntegerType, true),
      StructField("MÊS COMPETÊNCIA", IntegerType, true),
      StructField("UF", StringType, true),
      StructField("CÓDIGO MUNICÍPIO SIAFI", IntegerType, true),
      StructField("NOME MUNICÍPIO", StringType, false),
      StructField("NIS FAVORECIDO", StringType, false),
      StructField("NOME FAVORECIDO", StringType, false),
      StructField("VALOR PARCELA", StringType, false)
    ))

    val rawPayments = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .option("delimiter", ";")
      .option("encoding", "ISO-8859-1")
      .format("csv")
      .schema(manualSchema)
      .csv("/Users/infobarbosa/labs/bolsa-familia/in/201811_BolsaFamilia_Pagamentos.csv")
      .toDF("referencia", "competencia", "uf", "cd_mun", "ds_mun", "cpf", "favorecido", "valor_parcela")

    rawPayments.printSchema()
    rawPayments.cache()
    rawPayments.show(5)

  }

  main()

}
