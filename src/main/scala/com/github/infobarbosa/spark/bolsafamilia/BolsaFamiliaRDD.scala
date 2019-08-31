package com.github.infobarbosa.spark.bolsafamilia

import org.apache.spark.{SparkConf, SparkContext}

object BolsaFamiliaRDD extends App{
  val conf = new SparkConf().setAppName("BolsaFamilia")
  val sc = new SparkContext(conf)

  val rawPayments = sc.textFile("in/201811_BolsaFamilia_Pagamentos.csv")
    .mapPartitionsWithIndex {
      (idx, iter) => if (idx == 0) iter.drop(1) else iter
    }

  val first10 = rawPayments.take(10)

  val cpfList = rawPayments.map( line => {
    val splits = line.replace("\"", "").split(";")
    (splits(5), splits(7))
  } )

  cpfList.take(10).foreach(println)
  //total de CPFs
  //total de CPFs distintos
  //total de CPFs distintos por estado
  //total de valores pagos
  //total de valores pagos por estado
  //maior pagamento efetuado
  //maior pagamento efetuado por estado
  //mínimo pagamento efetuado
  //mínimo pagamento efetuado por estado
  //quantos CPFs novos em relação ao mês anterior
  //quantos CPFs do mês anterior não voltaram a ter pagamento no mês corrente

  //println(rawPayments.count())
}
