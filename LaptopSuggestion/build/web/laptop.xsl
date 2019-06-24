<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : product.xsl
    Created on : March 11, 2019, 2:02 PM
    Author     : Admin
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:param name="priceValue"/>
    <xsl:template match="laptops">
        <table border = "2" width="100%">
            <tr>
                <th>No.</th>
                <th>Image</th>
                <th>Name</th>
                <th>CPU</th>
                <th>RAM</th>
                <th>Disk</th>
                <th>Graphic</th>
                <th>Price</th>
                <th>Point</th>
            </tr>
            <xsl:for-each select="laptop">
                <tr>
                    <td>
                        <xsl:number level="single" count="laptop"/>
                    </td>
                    <td>
                        <img src="{details/detail/img}" class="zoom"/>
                    </td>
                    <td>
                        <a href="{details/detail/href}" target="blank"><xsl:value-of select="name"/></a>
                    </td>
                    <td>
                        <xsl:value-of select="processor"/>
                    </td>
                    <td>
                        <xsl:value-of select="ramSize"/>
                    </td>
                    <td>
                        <xsl:value-of select="disk"/>
                    </td>
                    <td>
                        <xsl:value-of select="graphicProcessor"/>
                    </td>
                    <td>
                         <xsl:value-of select='format-number(details/detail/price,"###,###")'/>
                    </td>
                    <td>
                        <xsl:value-of select='format-number(point, "###.##")'/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
