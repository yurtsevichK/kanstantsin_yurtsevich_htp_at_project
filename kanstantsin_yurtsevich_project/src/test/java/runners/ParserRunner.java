package runners;

import org.xml.sax.SAXException;
import utility.JsonParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ParserRunner {
    static JsonParser jsonParser = new JsonParser();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        {
            jsonParser.parseJSON();
            jsonParser.parseGSON();
            jsonParser.parseJacson();
            jsonParser.fromGSON();

        }
    }
}