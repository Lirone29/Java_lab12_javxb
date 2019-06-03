package loadOffer;

import generated.Offer;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class loadOfferBuilder {
    private static final String XSD_SCHEMA_FILENAME = "offer_schema.xsd";

    public IloadOffer newInstance() throws loadOfferException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(XSD_SCHEMA_FILENAME).getFile());
        IloadOffer iloadOfferLoader = null;

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Offer.class);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema invoiceSchema = schemaFactory.newSchema(new StreamSource(file));

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(invoiceSchema);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            iloadOfferLoader = new loadOffer(jaxbUnmarshaller, jaxbMarshaller);

        } catch (JAXBException e) {
            throw new loadOfferException(e.getMessage());
        } catch (SAXException e) {
            throw new loadOfferException(e.getMessage());
        }

        return iloadOfferLoader;
    }
}
