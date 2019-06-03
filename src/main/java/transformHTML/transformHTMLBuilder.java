package transformHTML;

import generated.Offer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class transformHTMLBuilder {

    private static final String DEFAULT_STYLESHEET_FILENAME = "offer_transform.xsl";
    public ItransformHTML newInstance(File selectedStylesheetFile) throws HTMLTransformException {
        ItransformHTML ihtmlTransformer = null;

        StreamSource stylesheetStreamSource = new StreamSource(selectedStylesheetFile);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {

            Transformer transformer = transformerFactory.newTransformer(stylesheetStreamSource);
            JAXBContext jaxbContext = JAXBContext.newInstance(Offer.class);
            ihtmlTransformer = new transformHTML(transformer, jaxbContext);

        } catch (TransformerConfigurationException e) {
            throw new HTMLTransformException(e.getMessage());
        } catch (JAXBException e) {
            throw new HTMLTransformException(e.getMessage());
        }

        return ihtmlTransformer;
    }

    public ItransformHTML defaultTransformer() throws HTMLTransformException {
        ClassLoader classLoader = getClass().getClassLoader();
        File defaultStylesheetFile = new File(classLoader.getResource(DEFAULT_STYLESHEET_FILENAME).getFile());
        return newInstance(defaultStylesheetFile);
    }
}

