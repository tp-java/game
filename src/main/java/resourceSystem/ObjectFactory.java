package resourceSystem;

/**
 * Created with IntelliJ IDEA.
 * User: ruslan
 * Date: 12/27/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectFactory {
    private static ObjectFactory instance;

    private ReadXMLFileSAX mXmlParser;

    private ObjectFactory() {
        mXmlParser = new ReadXMLFileSAX();
    }
	//TODO: Загрузка ресурсов при старте сервера, виртуальная файловая система
    public static ObjectFactory getInstance() {
        if (instance == null)
            instance = new ObjectFactory();
        return instance;
    }
    public Object getObjectByPath(String path) {

        return mXmlParser.readXML(path);
    }
}