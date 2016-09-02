package main.java;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

//import 
	public class test {
		
		
		 /**
		   * The select-mode for a SparQl query. 
		   */ 
		  public static final String MODE_SELECT = "SELECT"; 
		  /**
		   * This is the standard query, no named graph is specified within the incoming query. 
		   */ 
		  private static final String NONE_GRAPH_SPECIFIED = ""; 
		  /**
		   * The fuseki endpoint to do a SparQl query. Will be concatenated to the dataset URL. 
		   */ 
		  public static String ENDPOINT_QUERY = "/query"; 
		  /**
		   * The fuseki endpoint to do an update on a dataset. Will be concatenated to the dataset URL. 
		   */ 
		  public static String ENDPOINT_UPDATE = "/update"; 
		  /**
		   * The fuseki endpoint to do a manipulate the dataset. Will be concatenated to the dataset URL. 
		   */ 
		  public static String ENDPOINT_DATA = "/data"; 
		  /**
		   * The name of the default model (if not specifying a name for the graph). 
		   */ 
		  public static String DEFAULT_NAMED_MODEL = "default"; 
//		  private static FusekiClient instance; 
		
		
		
		public static void main(String args[]){
			
			
			String queryCommand=
					  "SELECT ?x ?y ?z \n" +
					  "WHERE{?x ?y ?z} \n"
					  + "LIMIT 10000";
			ResultSet results= queryServerWithDefaultGraph("http://localhost:3030/ds/query", queryCommand, "SELECT", "") ;
		
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				// assumes that you have an "?x" in your query
				RDFNode x = soln.get("x");
				System.out.println(x);
				RDFNode y = soln.get("y");
				System.out.println(y);
				RDFNode z = soln.get("z");
				System.out.println(z);
			}
			
		}
		
		
		
		/**
		 * Perform the select query.
		 * @param pathToQueryEndpoint
		 * @param queryCommand
		 * @param resultFormat
		 * @param defaultGraph
		 * @return a {@link ResultSet} containing the specified response.
		 */
		private static ResultSet queryServerWithDefaultGraph(final String pathToQueryEndpoint, final String queryCommand, final String resultFormat, final String defaultGraph) {
		  Query q = QueryFactory.create(queryCommand);
		  QueryExecution queryEx = QueryExecutionFactory.sparqlService(pathToQueryEndpoint, q, defaultGraph);
		  if(resultFormat.equals(MODE_SELECT)) {
		    ResultSet results = queryEx.execSelect(); // SELECT returns a ResultSet
		    return results;    
		  } 
		  return null;    
		}

	
	
	
	
	}


