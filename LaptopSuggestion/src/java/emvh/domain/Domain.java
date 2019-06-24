/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.domain;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class Domain {

    private String homeURL;
    private String beginCategory;
    private String endCategory;
    private String beginPage;
    private String endPage;
    private String beginProduct;
    private String endProduct;
    private String beginDetail;
    private String endDetail;

    public Domain() {
    }

    public Domain(String homeURL, String beginCategory, String endCategory, String beginPage, String endPage, String beginProduct, String endProduct, String beginDetail, String endDetail) {
        this.homeURL = homeURL;
        this.beginCategory = beginCategory;
        this.endCategory = endCategory;
        this.beginPage = beginPage;
        this.endPage = endPage;
        this.beginProduct = beginProduct;
        this.endProduct = endProduct;
        this.beginDetail = beginDetail;
        this.endDetail = endDetail;
    }

    public String getHomeURL() {
        return homeURL;
    }

    public void setHomeURL(String homeURL) {
        this.homeURL = homeURL;
    }

    public String getBeginCategory() {
        return beginCategory;
    }

    public void setBeginCategory(String beginCategory) {
        this.beginCategory = beginCategory;
    }

    public String getEndCategory() {
        return endCategory;
    }

    public void setEndCategory(String endCategory) {
        this.endCategory = endCategory;
    }

    public String getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(String beginPage) {
        this.beginPage = beginPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }

    public String getBeginProduct() {
        return beginProduct;
    }

    public void setBeginProduct(String beginProduct) {
        this.beginProduct = beginProduct;
    }

    public String getEndProduct() {
        return endProduct;
    }

    public void setEndProduct(String endProduct) {
        this.endProduct = endProduct;
    }

    public String getBeginDetail() {
        return beginDetail;
    }

    public void setBeginDetail(String beginDetail) {
        this.beginDetail = beginDetail;
    }

    public String getEndDetail() {
        return endDetail;
    }

    public void setEndDetail(String endDetail) {
        this.endDetail = endDetail;
    }

    @Override
    public String toString() {
        return "Domain{" + "homeURL=" + homeURL + ", beginCategory=" + beginCategory + ", endCategory=" + endCategory + ", beginPage=" + beginPage + ", endPage=" + endPage + ", beginProduct=" + beginProduct + ", endProduct=" + endProduct + ", beginDetail=" + beginDetail + ", endDetail=" + endDetail + '}';
    }
}
