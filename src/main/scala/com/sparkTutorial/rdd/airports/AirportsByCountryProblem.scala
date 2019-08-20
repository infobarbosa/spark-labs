package com.sparkTutorial.rdd.airports

import com.sparkTutorial.commons.Utils
import org.apache.spark.{SparkConf, SparkContext}

object AirportsByCountryProblem {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("airports").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val airports = sc.textFile("in/airports.text")


    val airportsFlat = airports.map( line => {
      val splits = line.split(Utils.COMMA_DELIMITER)
      (splits(3), 1)
    })
    airportsFlat.foreach(println)
    val airportsSumary = airportsFlat.reduceByKey((a, b) => a + b)
    airportsSumary.foreach(el => println(el._1))

    val airportsSumaryOrdered = airportsSumary
      .map(line => line.swap)
      .sortByKey(true, 1)
      .map(line => line.swap)

    airportsSumaryOrdered .foreach(println)
  }
}
