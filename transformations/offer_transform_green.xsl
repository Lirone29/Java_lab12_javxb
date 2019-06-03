<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html"/>

<xsl:template match="/">
    <html><body style="background: green; color: white;">
       <xsl:apply-templates/>
    </body></html>
 </xsl:template>
  
<xsl:template match="/offer/invoice_number">
   <h1 align="center"> <xsl:apply-templates/> </h1>
</xsl:template>

<xsl:template match="/offer/offer_name">
   <h1 align="center"> <xsl:apply-templates/> </h1>
</xsl:template>

    <xsl:template match="/offer/offer_description">
        <h2 align="left">OfferDescritpion</h2>
        <xsl:value-of select="offer_name"/>&#160;<br/>
        <xsl:value-of select="offer_address/street"/>&#160; <xsl:value-of select="offer_address/house_number"/><br/>
        <xsl:value-of select="offer_address/city"/>&#160; <xsl:value-of select="offer_address/country"/><br/>
        <xsl:value-of select="offer_address/hotel_name"/>&#160; <xsl:value-of select="offer_address/hotel_contact"/><br/>
        <xsl:value-of select="type"/>&#160;<xsl:value-of select="transport"/><br/>
        <xsl:value-of select="begin_date"/>&#160;<xsl:value-of select="end_date"/><br/>
        <xsl:value-of select="price"/>&#160; <xsl:value-of select="quantity"/><br/>
        <xsl:value-of select="description"/>&#160;<br/>
        <br/><br/>
    </xsl:template>

    <xsl:template match="/offer/travelAgency">
        <h2 align="left">TravelAgency</h2>
        <xsl:value-of select="travelAgency_name"/>&#160;<br/>
        <xsl:value-of select="travelAgency_street"/>&#160;<xsl:value-of select="travelAgency_number"/><br/>
        <xsl:value-of select="travelAgency_city"/>&#160;<br/>
        <xsl:value-of select="travelAgency_phone"/>&#160;<xsl:value-of select="travelAgency_email"/><br/>
        <br/><br/>
    </xsl:template>

  	
</xsl:stylesheet>