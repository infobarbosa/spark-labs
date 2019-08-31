package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace, desc}
import org.apache.spark.sql.types._

object DataFrameGreaterThan extends App{
  def main() {
    val spark = SparkSession
      .builder()
      .appName("BolsaFamiliaDF")
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

    println("computando competencia valores")

    //select
    val competencia_valores =
      rawPayments
        .withColumn("vl_parcela", regexp_replace(col("valor_parcela"), ",", ".")
          .cast(DoubleType))
          .select("competencia", "cpf", "vl_parcela")

    competencia_valores.show()

    println("competencia valores ok")
    val valores_gt1000 =
      competencia_valores
        .filter("vl_parcela > 1000.0")
        .sort(desc("vl_parcela"))

    valores_gt1000.printSchema()
    val gt1000_count = valores_gt1000.count()
    println(s"valores maiores que 1000: $gt1000_count")

    println("imprimindo...")
    for(row <- valores_gt1000.take(20)){
      val competencia = row.getInt(0)
      val cpf = row.getString(1)
      val vl_parcela = row.getDouble(2)

      println(f"Competencia: $competencia, CPF: $cpf, Valor: $vl_parcela%1.2f")
//      println(s"Competencia: $competencia, CPF: $cpf")
//      println(f"Valor: $vl_parcela%1.2f")
    }

  }

  main()

}
