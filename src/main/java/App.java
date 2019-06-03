import generated.*;
import loadOffer.IloadOffer;
import loadOffer.loadOfferBuilder;
import loadOffer.loadOfferException;
import transformHTML.ItransformHTML;
import transformHTML.transformHTML;
import transformHTML.transformHTMLBuilder;
import transformHTML.HTMLTransformException;

import javax.swing.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class App {
    private static final String APP_NAME = "JAXB example";
    private static ItransformHTML itransformer;
    private static Offer loadedOffer;
    private static IloadOffer iofferLoader;
    private static transformHTMLBuilder htmlTransformerBuilder;

    public static void main( String[] args )
    {

        try {

            htmlTransformerBuilder = new transformHTMLBuilder();
            itransformer =  htmlTransformerBuilder.defaultTransformer();

            final loadOfferBuilder offerLoaderBuilder = new loadOfferBuilder();
            iofferLoader = offerLoaderBuilder.newInstance();

            loadedOffer = createSampleOffer();

            createAndShowGUI();

        } catch (loadOfferException e) {
            e.printStackTrace();
        } catch (HTMLTransformException e) {
            e.printStackTrace();
        }
    }

    private static Offer createSampleOffer() {
        ObjectFactory objectFactory = new ObjectFactory();
       OfferAddress offerAddress = objectFactory.createOfferAddress("Avenue des Champs-Élysées", "Paris", "France", "Four Seasons Hotel George V", "694355358", "654");

        TravelAgency travelAgency =  objectFactory.createTravelAgency("T_Agency", "21", "Mila", "Warszawa", "tAgency@gmail.com", "666 666 666");
        OfferDescription offerDescription = objectFactory.createOfferDescription(offerAddress, "France-Lifestyle", "All in clusive", "Plane","..." , 1200.23, 2, "2020-01-07", "2020-01-27");

        Offer offer  = objectFactory.createOffer(offerDescription, offerAddress, travelAgency,1);

        return offer;
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame mainFrame = new JFrame(APP_NAME);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

        final JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setPreferredSize(new Dimension(600, 1000));
        try {
            editorPane.setText(itransformer.transformOfferToHtml(loadedOffer));
        } catch (HTMLTransformException e2) {
            e2.printStackTrace();
        }

        /*
         * Control panel setup
         */
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        JPanel controlButtonsPanel = new JPanel();
        controlButtonsPanel.setLayout(new BoxLayout(controlButtonsPanel, BoxLayout.LINE_AXIS));

        JButton stylesheetChooserButton = new JButton("Choose Offer style");
        stylesheetChooserButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("./transformations"));
                if(fileChooser.showOpenDialog(mainFrame) == 0) {
                    try {
                        itransformer = htmlTransformerBuilder.newInstance(fileChooser.getSelectedFile());
                        editorPane.setText(itransformer.transformOfferToHtml(loadedOffer));
                        JOptionPane.showMessageDialog(mainPanel, "Stylesheet loaded successfully");
                    } catch (HTMLTransformException e1) {
                        JOptionPane.showMessageDialog(mainPanel, "Loading stylesheet transformaion from file has failed", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        JButton loadOfferButton = new JButton("Load Offer");
        loadOfferButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("./data"));
                if (fileChooser.showOpenDialog(mainFrame) == 0) {

                    try {
                        loadedOffer = iofferLoader.loadOfferFromXmlFile(fileChooser.getSelectedFile());
                        editorPane.setText(itransformer.transformOfferToHtml(loadedOffer));
                        JOptionPane.showMessageDialog(mainPanel, "Offer loaded successfully");
                    } catch (loadOfferException e) {
                        JOptionPane.showMessageDialog(mainPanel, "Loading Offer from file failed", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    } catch (HTMLTransformException e) {
                        JOptionPane.showMessageDialog(mainPanel, "Loading Offer from file failed", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }

                }
            }
        });

        JButton saveOfferButton = new JButton("Save Offer");
        saveOfferButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if (loadedOffer != null) {
                    String fileName = ""+loadedOffer.getOfferDescription().getOfferName() +"_"+ loadedOffer.getOfferId() ;
                    fileName += ".xml";

                    try {
                        iofferLoader.saveOfferToXml(loadedOffer, fileName);
                        JOptionPane.showMessageDialog(mainPanel, "Offer saved: /data/" + fileName);
                    } catch (loadOfferException e) {
                        JOptionPane.showMessageDialog(mainPanel, "Saving Offer to file failed", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }

            }
        });

        /*
         * Offer edit panel
         */

        JPanel OfferEditPanel = new JPanel();
        OfferEditPanel.setLayout(new BoxLayout(OfferEditPanel, BoxLayout.PAGE_AXIS));

        JLabel offerTitleLabel = new JLabel("Offer title");
        final JTextField offerTitleTextField = new JTextField();
        offerTitleTextField.setText(loadedOffer.getOfferId()+"/"+loadedOffer.getOfferDescription().getOfferName());

        JLabel OfferIDLabel = new JLabel("Offer id");
        final JTextField OfferIDTextField = new JTextField();
        OfferIDTextField.setText(""+loadedOffer.getOfferId());

        /*
         * OfferAddress data
         */

        JLabel offerAddressStreetLabel = new JLabel("offerAddress Street");
        final JTextField offerAddressStreetTextField = new JTextField();
        offerAddressStreetTextField.setText(loadedOffer.getOfferDescription().getOfferAddress().getStreet());

        JLabel offerAddressHouseNumberLabel = new JLabel("offerAddress houseNumber");
        final JTextField offerAddressHouseNumberTextField = new JTextField();
        offerAddressHouseNumberTextField.setText(loadedOffer.getOfferDescription().getOfferAddress().getHouseNumber());

        JLabel offerAddressCityLabel = new JLabel("offerAddress City");
        final JTextField offerAddressCityTextField = new JTextField();
        offerAddressCityTextField.setText(loadedOffer.getOfferDescription().getOfferAddress().getCity());

        JLabel offerAddressCountryLabel = new JLabel("offerAddress Country");
        final JTextField offerAddressCountryTextField = new JTextField();
        offerAddressCountryTextField.setText(loadedOffer.getOfferDescription().getOfferAddress().getCountry());

        JLabel offerAddressHotelNameLabel = new JLabel("offerAddress HotelName");
        final JTextField offerAddressHotelNameTextField = new JTextField();
        offerAddressHotelNameTextField.setText(loadedOffer.getOfferDescription().getOfferAddress().getHotelName());

        JLabel offerAddressHotelContactLabel = new JLabel("offerAddressHotelContact");
        final JTextField offerAddressHotelContactTextField = new JTextField();
        offerAddressHotelContactTextField.setText(loadedOffer.getOfferDescription().getOfferAddress().getHotelContact());

        /*
         * OfferDescription data
         */

        final JLabel offerDescNameLabel = new JLabel("offerDescName");
        final JTextField offerDescNameTextField = new JTextField();
        offerDescNameTextField.setText(loadedOffer.getOfferDescription().getOfferName());

        JLabel offerBeginDateLabel = new JLabel("offerBeginDate [yyyy-mm-dd]");
        final JTextField offerBeginDateTextField = new JTextField();
        offerBeginDateTextField.setText(loadedOffer.getOfferDescription().getBeginDate().toString());

        JLabel offerEndDateLabel = new JLabel("offerEndDate [yyyy-mm-dd]");
        final JTextField offerEndDateTextField = new JTextField();
        offerEndDateTextField.setText(loadedOffer.getOfferDescription().getBeginDate().toString());

        JLabel offerPriceLabel = new JLabel("offerPrice");
        final JTextField offerPriceTextField = new JTextField();
        offerPriceTextField.setText( loadedOffer.getOfferDescription().getPrice().toString());

        JLabel offerQuantityLabel = new JLabel("offerQuantity");
        final JTextField offerQuantityTextField = new JTextField();
        offerQuantityTextField.setText(loadedOffer.getOfferDescription().getQuantity().toString());

        JLabel offerTransportLabel = new JLabel("offerTransport");
        final JTextField offerTransportTextField = new JTextField();
        offerTransportTextField.setText(loadedOffer.getOfferDescription().getTransport());

        JLabel offerTypeLabel = new JLabel("offerType");
        final JTextField offerTypeTextField = new JTextField();
        offerTypeTextField.setText(loadedOffer.getOfferDescription().getType());

        JLabel offerDescLabel = new JLabel("offerDesc");
        final JTextField offerDescTextField = new JTextField();
        offerDescTextField.setText(loadedOffer.getOfferDescription().getDescription());


        //--------------------TravelAgency Data---------------------------------------------
        JLabel travelAgencyNameLabel = new JLabel("travelAgencyName");
        final JTextField travelAgencyNameTextField = new JTextField();
        travelAgencyNameTextField.setText(loadedOffer.getTravelAgency().getTravelAgencyName());

        JLabel travelAgencyStreetLabel = new JLabel("travelAgencyStreet");
        final JTextField travelAgencyStreetTextField = new JTextField();
        travelAgencyStreetTextField.setText(loadedOffer.getTravelAgency().getTravelAgencyStreet());

        JLabel travelAgencyNumberLabel = new JLabel("travelAgency houseHumber");
        final JTextField travelAgencyNumberTextField = new JTextField();
        travelAgencyNumberTextField.setText(loadedOffer.getTravelAgency().getTravelAgencyNumber());

        JLabel travelAgencyCityLabel = new JLabel("travelAgency City:");
        final JTextField travelAgencyCityTextField = new JTextField();
        travelAgencyCityTextField.setText(loadedOffer.getTravelAgency().getTravelAgencyCity());

        JLabel travelAgencyPhoneLabel = new JLabel("travelAgencyPhone");
        final JTextField travelAgencyPhoneTextField = new JTextField();
        travelAgencyPhoneTextField.setText(loadedOffer.getTravelAgency().getTravelAgencyPhone());

        JLabel travelAgencyEmailLabel = new JLabel("travelAgencyEmail");
        final JTextField travelAgencyEmailTextField = new JTextField();
        travelAgencyEmailTextField.setText(loadedOffer.getTravelAgency().getTravelAgencyEmail());

        //------------------------------------------------------------------------------------------------------
        OfferEditPanel.add( offerTitleLabel);
        OfferEditPanel.add( offerTitleTextField);
        OfferEditPanel.add(OfferIDLabel);
        OfferEditPanel.add(OfferIDTextField);

        OfferEditPanel.add(offerAddressStreetLabel);
        OfferEditPanel.add(offerAddressStreetTextField);
        OfferEditPanel.add(offerAddressHouseNumberLabel);
        OfferEditPanel.add(offerAddressHouseNumberTextField);
        OfferEditPanel.add(offerAddressCityLabel);
        OfferEditPanel.add(offerAddressCityTextField);
        OfferEditPanel.add(offerAddressCountryLabel);
        OfferEditPanel.add(offerAddressCountryTextField);
        OfferEditPanel.add(offerAddressHotelNameLabel);
        OfferEditPanel.add(offerAddressHotelNameTextField);
        OfferEditPanel.add(offerAddressHotelContactLabel);
        OfferEditPanel.add(offerAddressHotelContactTextField);

        OfferEditPanel.add(offerDescNameLabel);
        OfferEditPanel.add(offerDescNameTextField);
        OfferEditPanel.add(offerBeginDateLabel);
        OfferEditPanel.add( offerBeginDateTextField);
        OfferEditPanel.add(offerEndDateLabel);
        OfferEditPanel.add( offerEndDateTextField);
        OfferEditPanel.add(offerPriceLabel);
        OfferEditPanel.add(offerPriceTextField);
        OfferEditPanel.add(offerQuantityLabel);
        OfferEditPanel.add(offerQuantityTextField);
        OfferEditPanel.add(offerTransportLabel);
        OfferEditPanel.add(offerTransportTextField);
        OfferEditPanel.add(offerTypeLabel);
        OfferEditPanel.add(offerTypeTextField);
        OfferEditPanel.add(offerDescLabel);
        OfferEditPanel.add(offerDescTextField);

        OfferEditPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        OfferEditPanel.add(travelAgencyNameLabel);
        OfferEditPanel.add(travelAgencyNameTextField);
        OfferEditPanel.add(travelAgencyNumberLabel);
        OfferEditPanel.add(travelAgencyNumberTextField);
        OfferEditPanel.add(travelAgencyStreetLabel);
        OfferEditPanel.add(travelAgencyStreetTextField);
        OfferEditPanel.add(travelAgencyCityLabel);
        OfferEditPanel.add(travelAgencyCityTextField);
        OfferEditPanel.add(travelAgencyPhoneLabel);
        OfferEditPanel.add(travelAgencyPhoneTextField);
        OfferEditPanel.add(travelAgencyEmailLabel);
        OfferEditPanel.add(travelAgencyEmailTextField);


        JButton updatePreviewButon = new JButton("Update offer info");
        updatePreviewButon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("In Update");

                loadedOffer.setOfferId(Long.parseLong(OfferIDTextField.getText()));

                loadedOffer.getTravelAgency().setTravelAgencyName(travelAgencyNameTextField.getText());
                loadedOffer.getTravelAgency().setTravelAgencyStreet(travelAgencyStreetTextField.getText());
                loadedOffer.getTravelAgency().setTravelAgencyNumber(travelAgencyNumberTextField.getText());
                loadedOffer.getTravelAgency().setTravelAgencyCity(travelAgencyCityTextField.getText());
                loadedOffer.getTravelAgency().setTravelAgencyPhone(travelAgencyPhoneTextField.getText());
                loadedOffer.getTravelAgency().setTravelAgencyEmail(travelAgencyEmailTextField.getText());

                loadedOffer.getOfferDescription().getOfferAddress().setStreet(offerAddressStreetTextField.getText());
                loadedOffer.getOfferDescription().getOfferAddress().setHouseNumber(offerAddressHouseNumberTextField.getText());
                loadedOffer.getOfferDescription().getOfferAddress().setCity(offerAddressCityTextField.getText());
                loadedOffer.getOfferDescription().getOfferAddress().setCountry(offerAddressCountryTextField.getText());
                loadedOffer.getOfferDescription().getOfferAddress().setHotelName(offerAddressHotelNameTextField.getText());
                loadedOffer.getOfferDescription().getOfferAddress().setHotelContact(offerAddressHotelContactTextField.getText());

                loadedOffer.getOfferDescription().setOfferName(offerDescNameTextField.getText());
                loadedOffer.getOfferDescription().setDescription(offerDescTextField.getText());
                loadedOffer.getOfferDescription().setType(offerTypeTextField.getText());
                loadedOffer.getOfferDescription().setQuantity(new BigInteger(offerQuantityTextField.getText()));
                loadedOffer.getOfferDescription().setPrice(new BigDecimal(offerPriceTextField.getText()));
                loadedOffer.getOfferDescription().setTransport(travelAgencyNameTextField.getText());

                try {
                    loadedOffer.getOfferDescription().setBeginDate(DatatypeFactory.newInstance()
                            .newXMLGregorianCalendar(offerBeginDateTextField.getText()));
                } catch (DatatypeConfigurationException e1) {
                    e1.printStackTrace();
                }
                try {
                    loadedOffer.getOfferDescription().setEndDate(DatatypeFactory.newInstance()
                            .newXMLGregorianCalendar(offerEndDateTextField.getText()));
                } catch (DatatypeConfigurationException e1) {
                    e1.printStackTrace();
                }


                try {
                    editorPane.setText(itransformer.transformOfferToHtml(loadedOffer));
                } catch (HTMLTransformException e1) {
                    JOptionPane.showMessageDialog(mainPanel, "Could not update preview", "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

        //OfferEditPanel.add(updatePreviewButon);
        OfferEditPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        //OfferEditPanel.add(saveOfferButton);
        ///////////////////////////////////////////////////////////////////////////////
        controlButtonsPanel.add(stylesheetChooserButton);
        controlButtonsPanel.add(loadOfferButton);
        controlButtonsPanel.add(saveOfferButton);
        controlButtonsPanel.add(updatePreviewButon);
        controlPanel.add(controlButtonsPanel);
        controlPanel.add(OfferEditPanel);
        ///////////////////////////////////////////////////////////////////////////////

        mainPanel.add(editorPane);
        mainPanel.add(controlPanel);
        mainFrame.setContentPane(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private static void displayEditOrderDialog() {

        JPanel editOrderPanel = new JPanel();
        editOrderPanel.setLayout(new BoxLayout(editOrderPanel, BoxLayout.PAGE_AXIS));

    }
}