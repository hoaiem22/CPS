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
public class BenchmarkDomain {
    
    private String url;
    private String begin;
    private String end;

    public BenchmarkDomain() {
    }

    public BenchmarkDomain(String url, String begin, String end) {
        this.url = url;
        this.begin = begin;
        this.end = end;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "BenchmarkDomain{" + "url=" + url + ", begin=" + begin + ", end=" + end + '}';
    }
    
}
