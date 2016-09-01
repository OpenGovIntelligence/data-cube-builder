package main.java;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RDFtoJSON {

    private static ArrayList<String> prefixes;
    private static HashMap<String, HashMap<String, String>> map =
            new HashMap<String, HashMap<String, String>>();

    public static void main(String[] args) throws IOException {

        String filename = "/home/mohade/workspace/OGI1/src/main/resources/output/newcube.ttl" ;

        // "prefixes" to delete
//        prefixes = new ArrayList<String>();
//        prefixes.add("http://quixey.com/app/2600430904/t/");
//        prefixes.add("http://quixey.com/app/2600430904/p/");
//        prefixes.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#");

        FileInputStream input = null;
        try {
            input = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Model model = ModelFactory.createDefaultModel() ;
        model.read(input, "", "TURTLE") ;
//        System.out.println("Finished reading the input file"+);
        model.write(System.out);
//        StmtIterator it = model.listStatements();
//        Statement current = null;
//        while (it.hasNext()) {
//            current = it.nextStatement();
//            String url = current.getSubject().toString();
//            String predicate = removePrefix(current.getPredicate().toString());
//            String object = removePrefix(current.getObject().toString());
//
//            if (map.containsKey(url)) {
//                HashMap<String, String> relations = map.get(url);
//                relations.put(predicate, object);
//                map.put(url, relations);
//            } else {
//                HashMap<String, String> relation = new HashMap<String, String>();
//                relation.put(predicate, object);
//                map.put(url, relation);
//            }
//        }

//        generateJSON();
    }

    private static String removePrefix(String dirty) {
        for (String s : prefixes)
            if (dirty.startsWith(s))
                return dirty.replace(s, "");
        return dirty;
    }

//    private static void generateJSON() throws JSONException, IOException {
//
//        JSONArray json = new JSONArray();
//        for ( HashMap<String, String> h : map.values()) {
//            JSONObject entity = new JSONObject();
//            for ( String predicate : h.keySet()) {
//                entity.accumulate(predicate, h.get(predicate));
//            }
//            json.put(entity);
//        }
//
//        FileWriter output;
//        output = new FileWriter("output.json");
//        output.write(json.toString(2));
//        output.flush();
//    }

}