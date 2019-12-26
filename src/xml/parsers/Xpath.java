/**
 * 
 */
package xml.parsers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author sakkenapelly
 *
 */
public class Xpath {

	DocumentBuilderFactory dbFactory = null;
	DocumentBuilder dbBuilder = null;
	Document doc = null;
	NodeList poHeaderNodesList = null;
	Xpath xpath = null;
	public static String pathNodes[] = null;
	Map<String, String> data = new HashMap<>();
	int i = 0;

	public Map<String, String> getData(String xpath, File file) {

		Map<String, String> hashMap = null;
		dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dbBuilder = dbFactory.newDocumentBuilder();
			doc = dbBuilder.parse(file);
			doc.getDocumentElement().normalize();
			Xpath.pathNodes = xpath.split("/");
			NodeList nodeList = doc.getElementsByTagName(Xpath.pathNodes[i]);
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				if (nodeList.item(temp).getNodeType() == Node.ELEMENT_NODE) {
					Node node = nodeList.item(temp);
					Xpath path = new Xpath();
					hashMap = path.valueOf(node);
				}
			}
			Xpath.pathNodes = null;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return hashMap;
	}

	public Map<String, String> valueOf(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.hasChildNodes()) {
				i++;
				NodeList nodeList = node.getChildNodes();
				for (int temp = 0; temp < nodeList.getLength(); temp++) {
					if (nodeList.item(temp).getNodeType() == Node.ELEMENT_NODE) {

						Node childNode = nodeList.item(temp);

						if (i < Xpath.pathNodes.length) {
							if (childNode.getNodeName().equals(Xpath.pathNodes[i])) {
								return valueOf(childNode);
							}
						}
					}
				}

			}
			NamedNodeMap map = node.getAttributes();
			Map<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < map.getLength(); i++) {
				hashMap.put(map.item(i).getNodeName(), map.item(i).getNodeValue());
			}
			hashMap.put(node.getNodeName(), node.getTextContent());
			return hashMap;
		}
		return null;
	}
}
