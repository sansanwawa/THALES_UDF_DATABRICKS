# THALES UDF (User Defined Function) For DATABRICKS

## This is a sample java-library for creating UDF in Databricks (https://www.databricks.com)

First, you need to setup below parameters that communicate to Thales CipherTrust Manager.

- IP
- Port
- username
- password
- keyname
- etc



You can edit file `CryptoUtils.java` (it is on `lib/src/main/java/com/thales/id/jakarta/cadp/lib/demo/utils`'s folder) to adjust base on your requirement.
The UDF class is under package `com.thales.id.jakarta.cadp.lib.demo.udf.fpe.*` 

Currently, this library that i created is supported only for Alphanumeric and Numeric function, those function need to call it separately and here is the classes.


> # ALPHANUMERIC

- com.thales.id.jakarta.cadp.lib.demo.udf.fpe.alphanumeric.Tokenize
- com.thales.id.jakarta.cadp.lib.demo.udf.fpe.alphanumeric.DeTokenize


> # NUMERIC

- com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.Tokenize
- com.thales.id.jakarta.cadp.lib.demo.udf.fpe.numeric.DeTokenize


> # And here is how to use in Databricks.



