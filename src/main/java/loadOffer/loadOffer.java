package loadOffer;

import generated.Offer;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class loadOffer implements IloadOffer {
    private static final String XML_EXTENSION = ".xml";
    private Unmarshaller jaxbUnmarshaller;
    private Marshaller jaxbMarshaller;

    protected loadOffer (Unmarshaller jaxbUnmarshaller, Marshaller jaxbMarshaller) {
        this.jaxbUnmarshaller = jaxbUnmarshaller;
        this.jaxbMarshaller = jaxbMarshaller;
    }

    public Offer loadOfferFromXmlFile(File file) throws loadOfferException {
        Offer offer = null;

        try {
            offer = (Offer) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new loadOfferException(e.getMessage());
        }

        return offer;
    }

    public void saveOfferToXml(Offer offer, String fileName) throws loadOfferException {

        String effectiveFileName = "./data/" + fileName;
        if (!effectiveFileName.contains(".xml")) {
            effectiveFileName += XML_EXTENSION;
        }
        File outputXmlFile = new File(effectiveFileName);
        try {
            FileOutputStream fos = new FileOutputStream(outputXmlFile, false); // "false" causes to override file
            jaxbMarshaller.marshal(offer, fos);

        } catch (FileNotFoundException e) {
            throw new loadOfferException(e.getMessage());
        } catch (JAXBException e) {
            throw new loadOfferException(e.getMessage());
        }
    }
}
