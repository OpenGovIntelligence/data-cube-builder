# OGI tools "Linux syntax"

##Cloning 

	$git clone https://gitlab.insight-centre.org/egov/ogi-tools.git ogi
	//You may need to install git -> $sudo apt-get install git
	$sudo chmod 777 -R ogi
	
	$cd ogi/
	
	$git checkout test

## Building
	
1- On the directory "ogi" do the following:

   A- clone Tarql:

    $git clone https://github.com/cygri/tarql tarql
    
   B- Build Tarql:

	$cd tarql
	 
	$mvn clean install -DskipTests
    //You may need to install mvn -> $sudo apt-get install maven
    //You may need to install java -> $sudo apt-get install openjdk-7-jdk
    
2- Download libraries

	$cd ogi/src/main/resources/lib/
	
	$wget http://search.maven.org/remotecontent?filepath=com/beust/jcommander/1.48/jcommander-1.48.jar -O jcommander-1.48.jar
	

3- Run OGI UI:

	$cd {base.dir}/ogi
	
	$javac -cp src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:. src/main/java/*.java	
	
	$java -cp src/ main.java.OgiFront

3- Run OGI CMD:
	
	$cd {base.dir}/ogi
		
	$javac -cp src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:. src/main/java/*.java
	
	$java -cp  src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:src/ main.java.OgiCommandLine -help
	
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
	
	$cd {base.dir}/ogi/src/main/resources/
	
	$mkdir data output
	
	$cd {base.dir}/ogi/src/main/resources/data
	
	$wget --no-check-certificate 'https://drive.google.com/uc?export=download&id=0B-DxlQVxO6pnNkZwY3k2ZE5NNFE' -O IWBNetwork.csv
		
	$cd {base.dir}/ogi/
	
	$javac -cp src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:. src/main/java/*.java
	
	$java -cp  src/main/resources/lib/jcommander-1.48.jar:src/main/resources/:src/ main.java.OgiCommandLine -csv:/src/main/resources/data/IWBNetwork.csv -schema:IWBNetwork -format:turtle -cube:/src/main/resources/output/newcube.ttl
	
	
#Fuseki
	https://jstirnaman.wordpress.com/2013/10/11/installing-fuseki-with-jena-and-tdb-on-os-x/
	just change the tar.gz locations to:
	https://archive.apache.org/dist/jena/binaries/apache-jena-2.11.0.tar.gz
	https://archive.apache.org/dist/jena/binaries/jena-fuseki-1.0.0-distribution.tar.gz
	
	$mkdir {user.dir}/jena-fuseki-1.0.0/Db
	 ./fuseki-server --port=8080 --update --loc=Db /ds 
	 
	 

