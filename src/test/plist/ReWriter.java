package test.plist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ReWriter {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DocumentException, IOException {
		SAXReader r = new SAXReader();
		Document doc = r.read(new File("role.plist"));
		List<Element> list = doc.getRootElement().selectNodes("//dict//dict//dict");
		for (Element e : list) {
			List<Element> children = e.content();
			String size = children.get(1).getText().trim();
			size = size.substring(size.lastIndexOf("{"), size.length() - 1);
			
			//offset
//			String offset = children.get(3).getText().trim();
//			offset = "{0," + offset.split(",")[1];
//			children.get(3).setText(offset);
			children.get(3).setText("{0,0}");
			
//			String rect = children.get(7).getText().trim();
//			rect = rect.substring(0, rect.lastIndexOf("{")) + size + "}"; 
//			children.get(7).setText(rect);
//			
//			children.get(9).setText(size);
			
//			Element e6 = children.get(6);
//			Element e7 = children.get(7);
//			e.remove(e6);
//			e.remove(e7);
		}
		XMLWriter writer = new XMLWriter(new FileWriter("n.plist"), OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}

}
