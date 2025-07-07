package utils;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

    @SuppressWarnings("unchecked")
	public static Iterator<Object[]> getTestData(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray testDataArray = (JSONArray) parser.parse(new FileReader(filePath));
            return testDataArray.stream()
                    .map(obj -> new Object[] { (JSONObject) obj })
                    .iterator();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
