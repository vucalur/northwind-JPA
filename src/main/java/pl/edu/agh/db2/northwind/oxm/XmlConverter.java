package pl.edu.agh.db2.northwind.oxm;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class XmlConverter {

	@Resource(name = "marshaller")
	private Marshaller marshaller;

	@Resource(name = "marshaller")
	private Unmarshaller unmarshaller;

	public void writeToXml(Object object, String filePath) {
		try (FileOutputStream os = new FileOutputStream(filePath)) {
			marshaller.marshal(object, new StreamResult(os));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object loadFromXml(String xmlFile) {
		try (FileInputStream is = new FileInputStream(xmlFile)) {
			return unmarshaller.unmarshal(new StreamSource(is));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}