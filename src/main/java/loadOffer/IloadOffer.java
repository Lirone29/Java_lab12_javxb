package loadOffer;

import generated.Offer;

import java.io.File;

public interface IloadOffer {
    Offer loadOfferFromXmlFile(File file) throws loadOfferException;
    void saveOfferToXml(Offer ioff, String fileName) throws loadOfferException;

}
