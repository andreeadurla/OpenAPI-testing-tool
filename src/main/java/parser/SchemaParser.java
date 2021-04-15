package parser;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Parse a JSON file to get certain informations.
 */
public class SchemaParser {

    private static final Logger logger = Logger.getLogger(SchemaParser.class.getName());

    /**
     * Parse a file to get the name of performed operations.
     * @param resourceFile the path of file to be parsed
     * @return list of found operations
     */
    public static List<String> getOperations(String resourceFile) {

        List<String> operations = new ArrayList<>();

        try {
            URL url = new URL(resourceFile);

            InputStream inputStream = url.openStream();

            JSONTokener tokener = new JSONTokener(inputStream);

            JSONObject schemaData = new JSONObject(tokener);

            JSONObject pathsData = schemaData.getJSONObject("paths");

            Set<String> keys = pathsData.keySet();

            for (String key : keys) {
                JSONObject operationData = pathsData.getJSONObject(key);

                Set<String> methods = operationData.keySet();
                for (String method : methods) {
                    operations.add(method.toUpperCase() + " " + key);
                }
            }
        }
        catch(Exception e) {
            logger.log(Level.SEVERE, e.getMessage());

        }
        finally {

            return operations;

        }
    }

}
