package main.java;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
@Parameters(separators = ":")

public class test {
	
	
	static JCommander j;
	@Parameter(names = "--help", help = true)
	private boolean help;

	    @Parameter(names={"--length", "-x"}, description = "Connection password", required = false)
	    int length;
	    @Parameter(names={"--pattern", "-p"})
	    int pattern;
	 
	    public static void main(String ... args) {
	    	test t = new test();
	       j= new JCommander(t, args);
	        j.setProgramName("OGI EU");
	    	

	        t.run();
	    }
	 
	    public void run() {
	    	if (help == true)
	    	j.usage();
	        
	    	System.out.printf("%d %d", length, pattern);
	    }
	}


