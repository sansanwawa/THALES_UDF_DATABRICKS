package com.thales.id.jakarta.cadp.lib.demo;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SparkTest {

    @Test
    public void testReadCSV(){
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark Demo")
                .config("spark.master", "local")
                .getOrCreate();

        // Create a Spark DataFrame consisting of stock price with date and low and high
        // price.
        StructType schema = new StructType(new StructField[] {
                new StructField("StockCode", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Date", DataTypes.DateType, false, Metadata.empty()),
                new StructField("highPrice", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("lowPrice", DataTypes.IntegerType, false, Metadata.empty()),
        });

        List<Row> dataList = new ArrayList<Row>();
        dataList.add(RowFactory.create("Apple", Date.valueOf("2021-07-13"), 20, 12));
        dataList.add(RowFactory.create("Twitter", Date.valueOf("2021-07-13"), 40, 35));
        dataList.add(RowFactory.create("MS", Date.valueOf("2021-07-13"), 60, 53));
        dataList.add(RowFactory.create("Google", Date.valueOf("2021-07-13"), 76, 65));

        Dataset<Row> stock= spark.createDataFrame(dataList, schema);


        spark.sql("USE default");
        spark.sql("DROP TABLE IF EXISTS stock_table");
        stock.write().saveAsTable("stock_table");

        // Query the table on the Databricks cluster, returning rows
        Dataset<Row> df_stock = spark.sql("SELECT * FROM stock_table");
        df_stock .show();
    }


    @Test
    public void testReadCSV1() {

        String file = "/Users/sandy/Documents/TMP/data.csv";
        SparkSession spark = SparkSession.builder().appName("Simple Application")
                .config("spark.master", "local").getOrCreate();
        Dataset<Row> logData = spark.read().option("header","true").csv(file).cache();
        logData.createOrReplaceTempView("csv_table");
        logData.sqlContext().sql("SELECT * FROM csv_table").map((MapFunction<Row, String>) row -> {
            //need to decrypt here...
            String streetName = row.getString(1);

            return streetName;
        }, Encoders.STRING()).show();

        spark.stop();

    }



}
