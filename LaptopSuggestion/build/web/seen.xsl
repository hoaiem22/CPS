<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : search.xsl
    Created on : March 27, 2019, 7:17 PM
    Author     : Admin
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="laptops">
        <div class="panel_header">
            <div class="panel_title">
                Tìm kiếm gần đây
            </div>
        </div>
        <div class="panel_body">
            <xsl:for-each select="laptop">
                <a href="detail.jsp?id={id}">
                    <div class="item_box">
                        <xsl:if test="details/detail/status != '' and details/detail/status != 'Bình thường'">
                            <span class="discount">
                                <xsl:value-of select="details/detail/status"/>
                            </span>
                        </xsl:if>
                        <img src="{details/detail/img}"/>
                        <span>
                            <xsl:value-of select='format-number(details/detail/price,"###,###")'/> VNĐ
                        </span>
                        <h4 class="item_title">
                            <xsl:value-of select="name"/>
                        </h4>   
                        <div class="mask">
                            <form action="/" method="post" autocomplete="off">
                                <table>
                                    <xsl:if test="price != ''">
                                        <tr>
                                            <td>Điểm</td>
                                            <td>
                                                <xsl:value-of select="price"></xsl:value-of>
                                            </td>
                                        </tr>
                                    </xsl:if>
                                    <xsl:if test="processor != ''">
                                        <tr>
                                            <td>CPU</td>
                                            <td>
                                                <xsl:value-of select="processor"></xsl:value-of>
                                            </td>
                                        </tr>
                                    </xsl:if>
                                    <xsl:if test="ramSize != ''">
                                        <tr>
                                            <td>Ram</td>
                                            <td>
                                                <xsl:value-of select="ramSize"></xsl:value-of>
                                            </td>
                                        </tr>
                                    </xsl:if>
                                    <xsl:if test="disk != ''">
                                        <tr>
                                            <td>Ổ cứng</td>
                                            <td>
                                                <xsl:value-of select="disk"></xsl:value-of>
                                            </td>
                                        </tr>
                                    </xsl:if>
                                    <xsl:if test="graphicProcessor != ''">
                                        <tr>
                                            <td>VGA</td>
                                            <td>
                                                <xsl:value-of select="graphicProcessor"></xsl:value-of>
                                            </td>
                                        </tr>
                                    </xsl:if>
                                </table>
                            </form>
                        </div>
                    </div>
                </a>
            </xsl:for-each>
        </div>
    </xsl:template>
</xsl:stylesheet>
