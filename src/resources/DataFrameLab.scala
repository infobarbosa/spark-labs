import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header", "true").option("encoding", "ISO-8859-1").option("Locale", "Brazil").option("delimiter",";").option("inferSchema", "true").csv("/Users/infobarbosa/labs/bolsa-familia/in/201811_BolsaFamilia_Pagamentos.csv")

df.printSchema()

def commaToDot(v: String): Float = v.replace(",", ".").toFloat

import spark.implicits._
import org.apache.spark.sql.types.FloatType

println("casting...")

val df2 = df.withColumn("VALOR", regexp_replace(df("VALOR PARCELA"), ",", ".").cast(FloatType))
df2.printSchema
df2.filter($"NOME MUNICÍPIO" === "ARACRUZ").show(5)

println("maiores valores...")
df2.groupBy("UF").max("VALOR").show()

println("soma tudo")
df2.select(sum("VALOR")).show

println("soma e ordena...")
df2.groupBy("UF").max("VALOR").orderBy("VALOR").show()


//df.select($"MÊS REFERÊNCIA", $"NOME FAVORECIDO", $"VALOR").filter($"NOME FAVORECIDO" === "FERNANDA CORDEIRO").show(5)
