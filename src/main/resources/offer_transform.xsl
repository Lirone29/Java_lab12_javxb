<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html"/>

<xsl:template match="/">
    <html><body>
       <xsl:apply-templates/>
    </body></html>
 </xsl:template>
  
<xsl:template match="/offer/offer_name">
   <h1 align="center"> <xsl:apply-templates/> </h1>
</xsl:template>

    <xsl:template match="/offer/offer_description">
        <h2 align="center" bgcolor="#ccddff">Offer:</h2>
        <p align="center"><xsl:value-of select="offer_name"/>&#160;</p><br/>
        <p align="center"><strong>Information: </strong></p>
        <p align="center"><xsl:value-of select="offer_address/street"/>&#160; <xsl:value-of select="offer_address/house_number"/></p><br/>
        <p align="center"><xsl:value-of select="offer_address/city"/>&#160; <xsl:value-of select="offer_address/country"/></p><br/>
        <p align="center"><xsl:value-of select="offer_address/hotel_name"/>&#160; <xsl:value-of select="offer_address/hotel_contact"/></p><br/>
        <p align="center"><xsl:value-of select="type"/>&#160;<xsl:value-of select="transport"/></p><br/>
        <p align="center"><strong>Begin date: </strong><xsl:value-of select="begin_date"/>&#160; <strong>End date: </strong><xsl:value-of select="end_date"/></p><br/>
        <p align="center"><strong>Price: </strong><xsl:value-of select="price"/>&#160;<strong>Quantity: </strong> <xsl:value-of select="quantity"/></p><br/>
        <p align="center"><strong>Description: </strong><xsl:value-of select="description"/>&#160;</p><br/>
        <br/><br/>
    </xsl:template>

    <xsl:template match="/offer/travelAgency">
        <h2 align="center">TravelAgency:</h2>
        <p align="center" bgcolor="#ccddff"><xsl:value-of select="travelAgency_name"/>&#160;<br/>
        <xsl:value-of select="travelAgency_street"/>&#160;<xsl:value-of select="travelAgency_number"/><br/>
        <xsl:value-of select="travelAgency_city"/>&#160;<br/>
        <xsl:value-of select="travelAgency_phone"/>&#160;<xsl:value-of select="travelAgency_email"/></p>
        <br/><br/>
    </xsl:template>

</xsl:stylesheet>