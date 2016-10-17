# OGI tools "Linux syntax"

## Cloning OGI tools

	$git clone https://gitlab.insight-centre.org/egov/ogi-tools.git ogi

	//You may need to install git -> $sudo apt-get install git
	
	$sudo chmod 777 -R ogi
	
	$cd ogi/
	
	$git checkout test
	
	$mvn clean install

## Download and Run Fuseki Server
	
	Use instructions at: 
	> https://jstirnaman.wordpress.com/2013/10/11/installing-fuseki-with-jena-and-tdb-on-os-x/
	
	(just change the tar.gz locations to):
	(1) https://archive.apache.org/dist/jena/binaries/apache-jena-2.11.0.tar.gz
	(2) https://archive.apache.org/dist/jena/binaries/jena-fuseki-1.0.0-distribution.tar.gz
	
	$mkdir {user.dir}/jena-fuseki-1.0.0/LinkedcubeSpace
	
	$sudo screen -S cubeserver ./fuseki-server --port=8080 --update --loc=LinkedcubeSpace /ds 
	
	$curl http://localhost:8080
	
## Download tarql service as library

	$cd {base.dir}/ogi/src/main/resources/lib/

	$wget --no-check-certificate 'https://github.com/opencube-toolkit/tarql-component/raw/master/lib/extensions/tarql-1.0a.jar' -O tarql-1.0a.jar

	$wget --no-check-certificate 'https://github.com/opencube-toolkit/tarql-component/raw/master/lib/extensions/tarql-1.0a-javadoc.jar' -O tarql-1.0a-javadoc.jar
	 
## OGI Desktop UI 

1- Run OGI UI:

	$nano {base.dir}/ogi/pom.xml
	
	change: 
	<mainClass>eu.opengov.cubebuilder.webservice.OgiWebService</mainClass>		
	
	to: 
	<mainClass>eu.opengov.cubebuilder.desktop.OgiFront</mainClass>	
	
	$cd {base.dir}/ogi
	
	$mvn exec:java

## OGI Command Line UI 

2- Run OGI CMD:
	
	$nano {base.dir}/ogi/pom.xml
	
	change: 
	<mainClass>eu.opengov.cubebuilder.webservice.OgiWebService</mainClass>		
	
	to: 
	<mainClass>eu.opengov.cubebuilder.cmd.OgiCommandLine</mainClass>	
	
	$cd {base.dir}/ogi
	
	$mvn exec:java
	
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
	       Output Cube RDF serialization format (turtle or ntriples)
	       Default: turtle

## OGI Web Service API

3- Run OGI Web Service API:
	
	$cd {base.dir}/ogi
	
	$sudo screen -S cubebuilder mvn exec:java
	
## Available gates:

	> curl http://localhost:4567/

### cube builder:
	> curl http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=inputFileNameAndLocation&schema=marineInstituteDatasetId&serialization=turtle&qbPath=outputFileLocation&qbName=outputFileName&fuseki=portNumberofFusekiserver

### listing available cubes
	> curl http://localhost:4567/cubeQueryingAPI/listLqbs?limit=numberOfRetrievedRecords&fuseki=portNumberofFusekiserver

### retrieve cube metadata 
	> curl http://localhost:4567/cubeQueryingAPI/LqbMeta?dsuri=marineInstituteDatasetURI&fuseki=portNumberofFusekiserver
	
### retrieve data of certain cube	
	>curl http://localhost:4567/cubeQueryingAPI/listdataofLqb?dsuri=marineInstituteDatasetURI&fuseki=portNumberofFusekiserver&limit=numberOfRetrievedRecords

### sparql endpoint	
	>curl http://localhost:4567/cubeQueryingAPI/cubeQueryingArgs?query=sparqlQueryToExecuteAgainstLinkedCubeSpace&fuseki=portNumberofFusekiserver
	
# Examples:

## Download example csv file
	
	$cd {base.dir}/ogi/src/main/resources/
	
	$mkdir data output
	
	$cd {base.dir}/ogi/src/main/resources/data
	
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnNkZwY3k2ZE5NNFE' -O IWBNetwork.csv

	$cd {base.dir}/ogi/
	
### Build Linked Cube
	
	A- Using Command Line UI (review!)
		
		$mvn exec:java -Dexec.args="-csv:src/main/resources/data/IWBNetwork.csv -schema:IWBNetwork -format:turtle -cube:src/main/resources/output/IWBNetwork.ttl"
	
	B- Using Web Service API
	
		$mvn exec:java
	
	From web browser:
	
	> http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=src/main/resources/data/IWBNetwork.csv&schema=IWBNetwork&serialization=turtle&cube=src/main/resources/output/IWBNetwork.ttl&fuseki=8080
	 
### Load Linked Cube to fuseki server
	
	$cd {fuseki.dir}
	
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnVjluN1FQa09aams'-O IWBNetwork.ttl
	
	$./s-put http://localhost:8080/ds/data default IWBNetwork.ttl




#### in case java 8 is not installed 
http://www.tecmint.com/install-java-jdk-jre-in-linux/

