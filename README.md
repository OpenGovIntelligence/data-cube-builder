# OGI tools "Linux syntax"

## Cloning OGI tools

	$git clone https://gitlab.insight-centre.org/egov/ogi-tools.git ogi-cubebuilder

	//You may need to install git -> $sudo apt-get install git
	
	$sudo chmod 777 -R ogi
	
	$cd ogi-cubebuilder/
	
	$git checkout test
	
	

## Download and Run Fuseki Server
	
	Use instructions at: 
	> https://jstirnaman.wordpress.com/2013/10/11/installing-fuseki-with-jena-and-tdb-on-os-x/
	
	(just change the tar.gz locations to):
	(1) https://archive.apache.org/dist/jena/binaries/apache-jena-2.11.0.tar.gz
	(2) https://archive.apache.org/dist/jena/binaries/jena-fuseki-1.0.0-distribution.tar.gz
	
	$mkdir {user.dir}/jena-fuseki-1.0.0/LinkedcubeSpace
	
	$sudo screen -S cubeserver ./fuseki-server --port=8080 --update --loc=LinkedcubeSpace /ds 
	
	$curl http://localhost:8080
	
	$cd ogi-cubebuilder/src/main/resources/
	
	$nano config.properties (add fuseki port of your choice) // to be deleted when json cube API works
	
## Download tarql service as library

	$cd {base.dir}/ogi-cubebuilder/src/main/resources/lib/

	$wget --no-check-certificate 'https://github.com/opencube-toolkit/tarql-component/raw/master/lib/extensions/tarql-1.0a.jar' -O tarql-1.0a.jar

	$wget --no-check-certificate 'https://github.com/opencube-toolkit/tarql-component/raw/master/lib/extensions/tarql-1.0a-javadoc.jar' -O tarql-1.0a-javadoc.jar
	
	$cd {base.dir}/ogi-cubebuilder/ 

	$mvn install:install-file -Dfile=src/main/resources/lib/tarql-1.0a.jar -DgroupId=org.deri.tarql -DartifactId=tarql -Dversion=1.0a -Dpackaging=jar
	
	$mvn install:install-file -Dfile=src/main/resources/lib/tarql-1.0a-javadoc.jar -DgroupId=org.deri.tarql -DartifactId=tarqldoc -Dversion=1.0a -Dpackaging=jar
	
	
	$mvn clean install
	
## Clone, Configure, build and run jsonCubeAPI service

	$cd {base.dir}/

	$clone .............................. ogi-api
	
	$cd ogi-api/resources/
	
	$nano config.prop (Set fuseki/SPARQL server URL, ex: http://localhost:8080/ds/query) 

	$cd ogi-api/

	$mvn package
	
	add to tomcat server, or run using jetty server
	

	 
## OGI Desktop UI 

1- Run OGI UI:
	
	$cd {base.dir}/ogi
	
	$mvn exec:java -Dexec.args="--run:desktop"

## OGI Command Line UI 

2- Run OGI CMD:
	
	$cd {base.dir}/ogi-cubebuilder
	
	$mvn exec:java -Dexec.args="--run:cmd -csv:/../../??.csv -schema:?? -format:?? -qb:/../../??.ttl -qbN:??"
	
	- Usage: OGI EU [options]
	  Options:
	    --csvFilePath, -csv
	       CSV input file Location and name
	    --dimOrMeasures, -l
	       Customized Dim and Measures are Not Available at this stage
	    --help, -help, -h
	       Help
	       Default: false
	    --marineDatasetName, -schema
	       Data Set Schema Currently Supporting (IWaveBNetwork30Min, IMI_EATL_WAVE,
	       IrishNationalTideGaugeNetwork and IWBNetwork)
	    --qbFileName, -qbN
	       Cube output file Location and name
	    --qbPath, -qb
	       Cube output file Location and name
	    --run, -r
	       Which main class to Run!
	    --serialization, -format
	       Output Cube RDF serlization format (turtle or ntriples)
	       Default: turtle


## OGI Web Service API

3- Run OGI Web Service API:
	
	$cd {base.dir}/ogi-cubebuilder
	
	$mvn exec:java -Dexec.args="--run:webservice"
	
## Available gates:

	> curl http://localhost:4567/

### cube builder:
	> curl http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=inputFileNameAndLocation&schema=marineInstituteDatasetId&serialization=turtle&qbPath=outputFileLocation&qbName=outputFileName

### listing available cubes
	> curl http://localhost:4567/cubeQueryingAPI/listLqbs?limit=numberOfRetrievedRecords

### retrieve cube metadata 
	> curl http://localhost:4567/cubeQueryingAPI/LqbMeta?dsuri=marineInstituteDatasetURI
	
### retrieve data of certain cube	
	>curl http://localhost:4567/cubeQueryingAPI/listdataofLqb?dsuri=marineInstituteDatasetURI&limit=numberOfRetrievedRecords

### sparql endpoint	
	>curl http://localhost:4567/cubeQueryingAPI/cubeQueryingArgs?query=sparqlQueryToExecuteAgainstLinkedCubeSpace
	
# Examples:


## Download example csv file
	
	$cd {base.dir}/ogi-cubebuilder/src/main/resources/
	
	$mkdir data output
	
	$cd {base.dir}/ogi-cubebuilder/src/main/resources/data
	
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnNkZwY3k2ZE5NNFE' -O IWBNetwork.csv

	$cd {base.dir}/ogi-cubebuilder/
	
### Build Linked Cube
	
	A- Using Command Line UI
		
		$mvn exec:java -Dexec.args="-run:cmd -csv:src/main/resources/data/IWBNetwork.csv -schema:IWBNetwork -format:turtle -qb:src/main/resources/output/ -qbN:IWBNetwork.ttl"
	
	B- Using Web Service API
	
		$mvn exec:java -Dexec.args="-run:webservice"
	
	From web browser:
	
	> http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=src/main/resources/data/IWBNetwork.csv&schema=IWBNetwork&serialization=turtle&qbPath=src/main/resources/output/&qbName=IWBNetwork.ttl
	 
### Load Linked Cube to Running fuseki server from cmd
	
	$cd {fuseki.dir}
	
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnVjluN1FQa09aams'-O IWBNetwork.ttl
	
	$./s-put http://localhost:8080/ds/data default IWBNetwork.ttl


#### in case java 8 is not installed 
http://www.tecmint.com/install-java-jdk-jre-in-linux/

#### incase mvn 3.3.9 not installed
https://javedmandary.blogspot.ie/2016/09/install-maven-339-on-ubuntu.html

