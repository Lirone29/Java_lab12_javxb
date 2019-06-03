package transformHTML;

import generated.Offer;

public interface ItransformHTML {
    String transformOfferToHtml(Offer offer) throws HTMLTransformException;
}
