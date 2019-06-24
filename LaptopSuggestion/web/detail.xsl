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
    <xsl:template match="laptop">
        <div class="panel_header">
            <div class="panel_title">
                Chi tiết sản phẩm
            </div>
        </div>
        <div class="panel_body">
            <div class="panel_body_img">
                <img src="{details/detail/img}"/>
            </div>
            <div class="panel_body_form">
                <form action="/" method="post" autocomplete="off">
                    <table border="1">
                        <thead>     
                            <tr>
                                <th>
                                    <xsl:value-of select="processor"/>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <xsl:if test="point != ''">
                                <tr>
                                    <td>Điểm</td>
                                    <td>
                                        <xsl:value-of select='format-number(point,"###.00")'/>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/price != ''">
                                <tr>
                                    <td>Giá</td>
                                    <td data="{details/detail/price}" id="tdPrice">
                                        <xsl:value-of select='format-number(details/detail/price,"###,###")'/> VNĐ
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="processor != ''">
                                <tr>
                                    <td>Bộ vi xử lý</td>
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
                                    <td>Card đồ họa</td>
                                    <td>
                                        <xsl:value-of select="graphicProcessor"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/href != ''">
                                <tr>
                                    <td>Nguồn</td>
                                    <td>
                                        <a href="{details/detail/href}" style="color:blue;" target="_blank">Tại đây</a>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/model != ''">
                                <tr>
                                    <td>Mẫu</td>
                                    <td>
                                        <xsl:value-of select="details/detail/model"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/dvd != ''">
                                <tr>
                                    <td>Ổ đĩa</td>
                                    <td>
                                        <xsl:value-of select="details/detail/dvd"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/screenSize != ''">
                                <tr>
                                    <td>Màn hình</td>
                                    <td>
                                        <xsl:value-of select="details/detail/screenSize"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/lan != ''">
                                <tr>
                                    <td>Lan</td>
                                    <td>
                                        <xsl:value-of select="details/detail/lan"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/wl != ''">
                                <tr>
                                    <td>WL</td>
                                    <td>
                                        <xsl:value-of select="details/detail/wl"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/bluetooth != ''">
                                <tr>
                                    <td>Bluetooth</td>
                                    <td>
                                        <xsl:value-of select="details/detail/bluetooth"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/port != ''">
                                <tr>
                                    <td>Cổng giao tiếp</td>
                                    <td>
                                        <xsl:value-of select="details/detail/port"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/pin != ''">
                                <tr>
                                    <td>Pin</td>
                                    <td>
                                        <xsl:value-of select="details/detail/pin"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/weight != ''">
                                <tr>
                                    <td>Cân nặng</td>
                                    <td>
                                        <xsl:value-of select="details/detail/weight"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/os != ''">
                                <tr>
                                    <td>Hệ điều hành</td>
                                    <td>
                                        <xsl:value-of select="details/detail/os"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/guarantee != ''">
                                <tr>
                                    <td>Bảo hành</td>
                                    <td>
                                        <xsl:value-of select="details/detail/guarantee"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="details/detail/ship != ''">
                                <tr>
                                    <td>Ship</td>
                                    <td>
                                        <xsl:value-of select="details/detail/ship"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:if>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
