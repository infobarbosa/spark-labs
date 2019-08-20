package com.sparkTutorial.rdd.sumOfNumbers

import org.apache.spark.{SparkConf, SparkContext}

object SumOfNumbersProblem {

  def main(args: Array[String]) {

    /* Create a Spark program to read the first 100 prime numbers from in/prime_nums.text,
       print the sum of those numbers to console.

       Each row of the input file contains 10 prime numbers separated by spaces.
     */

    val conf = new SparkConf().setAppName("SumOfNumbers").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val rawPrimes = sc.textFile("in/prime_nums.text")
    rawPrimes.foreach(println)

    val stringPrimes = rawPrimes.flatMap(line => line.replace("\t", "\b").split("\b"))
    val primes = stringPrimes.map( prime => prime.trim.toInt )
    val first100Primes = primes.take(100)

    val sumOf100Primes = first100Primes.reduce( (x, y) => x + y)
    println(sumOf100Primes)
  }
}
