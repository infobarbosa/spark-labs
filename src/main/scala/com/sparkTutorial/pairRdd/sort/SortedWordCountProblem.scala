package com.sparkTutorial.pairRdd.sort

import org.apache.spark.{SparkConf, SparkContext}


object SortedWordCountProblem extends App{

    /* Create a Spark program to read the an article from in/word_count.text,
       output the number of occurrence of each word in descending order.

       Sample output:

       apple : 200
       shoes : 193
       bag : 176
       ...
     */

  val conf = new SparkConf().setAppName("SortedWordCount").setMaster("local[2]")
  val sc = new SparkContext(conf)

  val lines = sc.textFile("in/word_count.text")
  lines.foreach(println)

  val words = lines
    .flatMap( line => line.split("\\s+") )
    .filter( word => !word.isEmpty )
    .map( word => (word.replace(",", "").replace(".", "").toLowerCase(), 1))

  words.foreach(println)

  val wordCount = words.reduceByKey( (x, y) => x + y)
  wordCount.foreach(println)

  val sortedWordCount = wordCount
    .map( wc => (wc._2, wc._1))
    .sortByKey(ascending = false)
    .map(wc => (wc._2, wc._1))

  sortedWordCount.foreach(println)

  println("==============")
  for((word, count) <- sortedWordCount.collect()) println(word + " : " + count)



}

