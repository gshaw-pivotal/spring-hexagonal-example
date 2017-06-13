package gs.hexagonaldemo.springhexagonaldemo.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;
import java.net.URL;

public class JsonValidator {

    public static final String JSON_V4_SCHEMA_IDENTIFIER = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_SCHEMA_IDENTIFIER_ELEMENT = "$schema";

    public static boolean validateJson(URL schemaURL, String response) throws IOException, ProcessingException {
        return generateReport(schemaURL, response);
    }

    private static boolean generateReport(URL schemaURL, String response) throws IOException, ProcessingException {
        ProcessingReport report = isJsonValid(schemaURL, response);
        if (report.isSuccess()) {
            return true;
        } else {
            for (ProcessingMessage processingMessage : report) {
                System.out.println(processingMessage);
            }
            return false;
        }
    }

    private static ProcessingReport isJsonValid(URL schemaURL, String response) throws IOException, ProcessingException {
        final JsonSchema schemaNode = getSchemaNode(schemaURL);
        final JsonNode jsonNode = getJsonNode(response);
        return schemaNode.validate(jsonNode);
    }

    private static JsonNode getJsonNode(String jsonText) throws IOException {
        return JsonLoader.fromString(jsonText);
    }

    private static JsonNode getJsonNode(URL url) throws IOException {
        return JsonLoader.fromURL(url);
    }

    private static JsonSchema getSchemaNode(URL schemaFile) throws IOException, ProcessingException {
        final JsonNode schemaNode = getJsonNode(schemaFile);
        return getSchemaNode(schemaNode);
    }

    private static JsonSchema getSchemaNode(JsonNode jsonNode) throws ProcessingException {
        final JsonNode schemaIdentifier = jsonNode.get(JSON_SCHEMA_IDENTIFIER_ELEMENT);
        if (null == schemaIdentifier){
            ((ObjectNode) jsonNode).put(JSON_SCHEMA_IDENTIFIER_ELEMENT, JSON_V4_SCHEMA_IDENTIFIER);
        }

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        return factory.getJsonSchema(jsonNode);
    }
}
