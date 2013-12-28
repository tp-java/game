package resourceSystem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class SaxHandler extends DefaultHandler {
	private static String CLASSNAME = "class";
    private Map<String, String> objectFields;
	private String objectField = null;
    private String objectClassName = null;
	private Object object = null;

    public SaxHandler() {
        super();
        objectFields = new HashMap<String, String>();
    }

	public void startDocument() throws SAXException {
        objectFields.clear();
        objectClassName = null;
        objectField = null;
		//System.out.println("Start document");
	}

	public void endDocument() throws SAXException {
		//System.out.println("End document ");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName != CLASSNAME){
			objectField = qName;
            objectFields.put(objectField, null);
		}
		else{
			String className = attributes.getValue(0);
			//System.out.println("Class name: " + className);
			//object = ReflectionHelper.createInstance(className);
            objectClassName = className;
		}	
	}
 
	public void endElement(String uri, String localName, String qName) throws SAXException {
		objectField = null;
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
		if(objectField != null){
			String value = new String(ch, start, length);
			//System.out.println(objectField + " = " + value);
			//ReflectionHelper.setFieldValue(object, element, value);
            objectFields.put(objectField, value);
		}
	}
	
	public Object getObject(){
        object = ReflectionHelper.createInstance(objectClassName, objectFields);
		return object;
	}
}
