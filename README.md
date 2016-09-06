# OGI tools "Linux syntax"

##Cloning OGI tools

	$git clone https://gitlab.insight-centre.org/egov/ogi-tools.git ogi
	//You may need to install git -> $sudo apt-get install git
	$sudo chmod 777 -R ogi
	
	$cd ogi/
	
	$git checkout test

##Cloning and Build Tarql
	
On the directory "ogi" do the following:

   A- clone Tarql:

    $git clone https://github.com/cygri/tarql tarql
    
   B- Build Tarql:

	$cd tarql
	 
	$mvn clean install -DskipTests
    //You may need to install mvn -> $sudo apt-get install maven
    //You may need to install java -> $sudo apt-get install openjdk-7-jdk

## Download and Run Fuseki Server

#Fuseki
	
	Use instructions at > https://jstirnaman.wordpress.com/2013/10/11/installing-fuseki-with-jena-and-tdb-on-os-x/
	(just change the tar.gz locations to):
	(1) https://archive.apache.org/dist/jena/binaries/apache-jena-2.11.0.tar.gz
	(2) https://archive.apache.org/dist/jena/binaries/jena-fuseki-1.0.0-distribution.tar.gz
	
	$mkdir {user.dir}/jena-fuseki-1.0.0/LinkedcubeSpace
	$screen ./fuseki-server --port=8080 --update --loc=LinkedcubeSpace /ds 
	$curl http://localhost:8080
	 

## Download OGI Build Libraries (will be removed after maven is in action )
2- Download libraries

	$cd ogi
	
	$wget -i src/main/resources/lib/libs.txt  -P src/main/resources/lib/
	 or
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnZ0dxTzVqelladEE' -O src/main/resources/libs.zip
	$cd src/main/resources/
	$unzip libs.zip
	
## OGI Desktop UI 

3- Run OGI UI:

	$cd {base.dir}/ogi
	
	$javac -cp src/main/resources/lib/*:src/main/resources/:. src/main/java/*.java	
	
	$java -cp src/ main.java.OgiFront

## OGI Command Line UI 

3- Run OGI CMD:
	
	$cd {base.dir}/ogi
		
	$javac -cp src/main/resources/lib/*:src/main/resources/:. src/main/java/*.java
	
	$java -cp  src/main/resources/lib/*:src/main/resources/:src/ main.java.OgiCommandLine -help
	
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
	
	$cd {base.dir}/ogi
		
	$javac -cp src/main/resources/lib/*:src/main/resources/:. src/main/java/*.java
	
	$screen java -cp  src/main/resources/lib/*:src/main/resources/:src/ main.java.OgiWebService
	
	Available gates:
	> curl http://localhost:4567/
	
	> curl http://localhost:4567/cubeQueryingAPI/cubeQueryingArgs?sparql=queryToExecuteOverLinkedCubeSpace&fuseki=portNumberofFusekiserver

	> curl http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=inputFileNameAndLocation&schema=marineInstituteDatasetId&serialization=turtle&cube=outputFileAndLocation&fuseki=portNumberofFusekiserver
	
## Download example csv file
	
	$cd {base.dir}/ogi/src/main/resources/
	
	$mkdir data output
	
	$cd {base.dir}/ogi/src/main/resources/data
	
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnNkZwY3k2ZE5NNFE' -O IWBNetwork.csv
		$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnVjluN1FQa09aams
	$cd {base.dir}/ogi/
	
### Build Linked Cube
	
	A- Using Command Line UI
		$javac -cp src/main/resources/lib/*:src/main/resources/:src/main/resources/lib/*:. src/main/java/*.java
		
		$java -cp  src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:src/main/resources/lib/*:src/ main.java.OgiCommandLine -csv:src/main/resources/data/IWBNetwork.csv -schema:IWBNetwork -format:turtle -cube:src/main/resources/output/IWBNetwork.ttl
	
	B- Using Web Service API
	
		$javac -cp src/main/resources/lib/*:src/main/resources/:src/main/resources/lib/*:. src/main/java/*.java
		
		$java -cp  src/main/resources/lib/*:src/main/resources/:src/ main.java.OgiWebService
	
	From web browser:
	
	> http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=src/main/resources/data/IWBNetwork.csv&schema=IWBNetwork&serialization=turtle&cube=src/main/resources/output/IWBNetwork.ttl&fuseki=8080
	 
### Load Linked Cube to fuseki server
	$cd {fuseki.dir}
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnVjluN1FQa09aams'-O IWBNetwork.ttl
	$./s-put http://localhost:8080/ds/data default IWBNetwork.ttl


####
in case java 8 not installed 
http://www.tecmint.com/install-java-jdk-jre-in-linux/

