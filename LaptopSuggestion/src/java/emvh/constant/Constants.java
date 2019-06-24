/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.constant;

import emvh.domain.BenchmarkDomain;
import emvh.domain.Domain;
import emvh.utils.DomainUtilities;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class Constants {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABET_VN = "ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";

    //Domain for crawl data
    public static Domain LAPTOP_GIAHUY;
    public static Domain LAPTOP_LESON;
    public static BenchmarkDomain BENCHMARK_CPU;
    public static BenchmarkDomain BENCHMARK_GPU;

    //XML File for config
    public static final String XML_CONFIG_TITLE = "config/title.xml";
    public static final String XML_CONFIG_SCHEDULE = "config/schedule.xml";
    public static final String XML_CONFIG_RAM = "config/ram.xml";
    public static final String XML_CONFIG_DOMAIN = "config/domain.xml";
    public static final String XML_CONFIG_BENCHMARK = "config/benchmark.xml";
    public static String realPath = "";

    public static Map<String, Integer> mapSchedule = new HashMap<>();

}
