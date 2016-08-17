# OGI tools

## Building

1- On the directory "{base.dir}/tarql-run/tarql/" do the following:

   A- Download Tarql:

    $cd {base.dir}/tarql-run/tarql/
    $git clone https://github.com/cygri/tarql
    
   B- Build Tarql:

    $mvn clean install -DskipTests
    
2- Run OGI UI:

	$cd {base.dir}
	$javac src/main/java/*.java
	$java -cp src/ main.java.OgiFront


