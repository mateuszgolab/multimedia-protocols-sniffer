package ksm.sniffer.module.loader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Parses XML plugins info file.
 */
public class XmlPluginInfoParser implements DescriptorParser {
    
    private static final String PLUGIN_ELEMENT = "plugin";
    private static final String CLASS_NAME_ELEMENT = "class";
    
    private int iterator;
    private final NodeList nodeList;
    
    /**
     * Constructor using fileName.
     * @param fileName file to parse
     * @throws LoadException load exception
     */
    public XmlPluginInfoParser(final String fileName) throws LoadException {
        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final Document document = documentBuilder.parse(fileName);
            nodeList = document.getDocumentElement().getElementsByTagName(PLUGIN_ELEMENT);
        } catch (Exception e) {
            throw new LoadException(e);
        }
    }
    
    @Override
    public String getNextPluginClassInfo() {
        if (iterator < nodeList.getLength()) {
            return getClassNameValue((Element) nodeList.item(iterator++));
        } else {
            return null;
        }
    }
    
    private String getClassNameValue(final Element element) {
        String textVal = null;
        final NodeList nlTmp = element.getElementsByTagName(CLASS_NAME_ELEMENT);
        if (nlTmp != null && nlTmp.getLength() > 0) {
            final Element elTmp = (Element) nlTmp.item(0);
            textVal = elTmp.getFirstChild().getNodeValue();
        }
        return textVal;
    }
}
