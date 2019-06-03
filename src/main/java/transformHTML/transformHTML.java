package transformHTML;

import generated.Offer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class transformHTML implements ItransformHTML {

    private Transformer transformer;
    private JAXBContext jaxbContext;

    protected transformHTML(Transformer transformer, JAXBContext jaxbContext) {
        this.transformer = transformer;
        this.jaxbContext = jaxbContext;
    }

    public String transformOfferToHtml(Offer offer) throws HTMLTransformException {
        String result = null;
        try {

            JAXBSource jaxbSource = new JAXBSource(jaxbContext, offer);

            StringWriter writer = new StringWriter();
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(jaxbSource, streamResult);
            result = writer.toString();
        } catch (JAXBException e) {
            throw new HTMLTransformException(e.getMessage());
        } catch (TransformerException e) {
            throw new HTMLTransformException(e.getMessage());
        }

        return result;
    }
}
