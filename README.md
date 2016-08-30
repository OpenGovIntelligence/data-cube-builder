# OGI tools

##Cloning 

$git clone https://gitlab.insight-centre.org/egov/ogi-tools.git

## Building

Grant Permissions for the {base.dir} 

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

3- Run OGI CMD "Linux syntax":
	
	$cd {base.dir}
	
	$javac -cp src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:. src/main/java/*.java
	
	$java -cp  src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:src/ main.java.OgiCommandLine [-option1:value1 -option2:value2]
	
Usage: OGI EU [options]  
  Options:
    --csvPath, -csv
       CSV input file Location and name
    --cubePath, -cube
       Cube output file Location and name
    --dimOrMeasures, -l
       Not Available at this stage
    --help, -help, -h
       Help
       Default: false
    --marineDatasetName, -schema
       Data Set Schema Currently Supporting (IWaveBNetwork30Min, IMI_EATL_WAVE,
       IrishNationalTideGaugeNetwork and IWBNetwork)
    --serialization, -format
       Output Cube RDF serlization format (turtle or ntriples)
       Default: turtle

	
### example
	
	$cd {base.dir}
	
	
	$javac -cp src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:. src/main/java/*.java
	
	
	$java -cp  src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:src/ main.java.OgiCommandLine -csv:/home/mohade/workspace/OGI1/src/main/resources/data/IWBNetwork.csv -schema:IWBNetwork -format:ntriples -cube:/home/mohade/workspace/OGI1/src/main/resources/output/newcube.nt

