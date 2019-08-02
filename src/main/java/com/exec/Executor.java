package com.exec;

import com.bean.AreaCode;
import com.bean.Category;
import com.cofig.PathCofig;
import com.utils.Util;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬取数据
 */
public class Executor {
    //private static final Area ROOT_AREA = new Area("50", "重庆市", "重庆市", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false);

    private static final List<Area> AREA_LIST = new ArrayList<>();
    private static final List<Category> CATEGORY_LIST = new ArrayList<>();

    static {
//        AREA_LIST.add(new Area("11", "北京市", "北京市", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("12", "天津市", "天津市", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("13", "河北省", "河北省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("14", "山西省", "山西省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("15", "内蒙古自治区", "内蒙古自治区", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("21", "辽宁省", "辽宁省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("22", "吉林省", "吉林省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("23", "黑龙江省", "黑龙江省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("31", "上海市", "上海市", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("32", "江苏省", "江苏省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("33", "浙江省", "浙江省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("34", "安徽省", "安徽省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("35", "福建省", "福建省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("36", "江西省", "江西省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("37", "山东省", "山东省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("41", "河南省", "河南省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("42", "湖北省", "湖北省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("43", "湖南省", "湖南省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("44", "广东省", "广东省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("45", "广西壮族自治区", "广西壮族自治区", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("46", "海南省", "海南省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("50", "重庆市", "重庆市", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000", false));
//        AREA_LIST.add(new Area("51", "四川省", "四川省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
        AREA_LIST.add(new Area("52", "贵州省", "数据字典/行政区域划分/贵州省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000", false));
//        AREA_LIST.add(new Area("53", "云南省", "云南省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("54", "西藏自治区", "西藏自治区", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("61", "陕西省", "陕西省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("62", "甘肃省", "甘肃省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("63", "青海省", "青海省", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("64", "宁夏回族自治区", "宁夏回族自治区", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));
//        AREA_LIST.add(new Area("65", "新疆维吾尔自治区", "新疆维吾尔自治区", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false));

//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("11"). setCategoryName("北京市").setpNames("北京市").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("12").setCategoryName("天津市").setpNames("天津市").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("13").setCategoryName("河北省").setpNames("河北省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("14").setCategoryName("山西省").setpNames("山西省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("15").setCategoryName( "内蒙古自治区").setpNames("内蒙古自治区").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("21"). setCategoryName("辽宁省").setpNames("辽宁省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("22").setCategoryName("吉林省").setpNames("吉林省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("23"). setCategoryName("黑龙江省").setpNames( "黑龙江省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("31").setCategoryName("上海市").setpNames("上海市").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("32").setCategoryName("江苏省").setpNames("江苏省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("33").setCategoryName("浙江省").setpNames("浙江省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("34").setCategoryName("安徽省").setpNames("安徽省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("35").setCategoryName("福建省").setpNames("福建省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("36").setCategoryName("江西省").setpNames("江西省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("37").setCategoryName("山东省").setpNames("山东省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("41").setCategoryName("河南省").setpNames("河南省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("42").setCategoryName("湖北省").setpNames("湖北省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("43").setCategoryName("湖南省").setpNames("湖南省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("44").setCategoryName("广东省").setpNames("广东省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("45").setCategoryName("广西壮族自治区").setpNames("广西壮族自治区").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("46").setCategoryName("海南省").setpNames("海南省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("50").setCategoryName("重庆市").setpNames("重庆市").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("51").setCategoryName("四川省").setpNames("四川省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("52").setCategoryName("贵州省").setpNames("数据字典/行政区域划分/贵州省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("53").setCategoryName("云南省").setpNames("云南省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("54").setCategoryName("西藏自治区").setpNames("西藏自治区").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("61").setCategoryName("陕西省").setpNames("陕西省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("62").setCategoryName("甘肃省").setpNames("甘肃省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("63").setCategoryName("青海省").setpNames("青海省").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("64").setCategoryName("宁夏回族自治区") .setpNames("宁夏回族自治区").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
//        CATEGORY_LIST.add(new Category.CategoryBuilder().setCategoryCode("65"). setCategoryName("新疆维吾尔自治区").setpNames("新疆维吾尔自治区").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build());
    }


    private static Category ROOT_CATEGORY;

    private static final String TARGET_FILE = PathCofig.TARGET.getName();

    private static OkHttpClient HTTP_CLIENT = new OkHttpClient();

    //private  static List<AreaCode> areaCodes = new ArrayList<>();
    private static List<Category> categorys = new ArrayList<>();

    public static void main(String[] args) {
        int len = CATEGORY_LIST.size();
        for (int i = 0; i < len; i++) {
            int index = i;
            Thread thread = new Thread(() -> {
                ROOT_CATEGORY = CATEGORY_LIST.get(index);
                try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(TARGET_FILE + CATEGORY_LIST.get(index).getCategoryName() + ".sql"), StandardCharsets.UTF_8))) {
                    Util.bufferedWriterWrite(bufferedWriter, "insert into um_category(ID, CATEGORY_CODE, CATEGORY_NAME, P_NAMES, P_ID, CREATE_TIME, CATEGORY_TYPE, IS_LEAF) values\n");
                    resolveAll(AREA_LIST.get(index), bufferedWriter);
                    System.err.println("开始写入SQL 。。。。 ");
                    wrieSql(bufferedWriter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    private static void wrieSql(BufferedWriter bufferedWriter) {
        categorys = Util.removeDuplicateId(categorys);
        Integer count = 0;
        for (int i = 0; i < categorys.size(); i++) {
            if (categorys.get(i).getCategoryName().contains(",")) {
                throw new RuntimeException("不支持的区域名称（有英文逗号） " + categorys.get(i).getCategoryName());
            }

            if (count == i) {
                Util.bufferedWriterWrite(bufferedWriter, "insert into um_category(ID, CATEGORY_CODE, CATEGORY_NAME, P_NAMES, P_ID, CREATE_TIME, CATEGORY_TYPE, IS_LEAF) values\n");
            }
            //util.bufferedWriterWrite(bw,area.getCategoryCode() + ',' + area.getCategoryName() + ',' + area.getpNames() + ',' + area.getpId() + '\n');
            String line1 = "";
            if (categorys.size() - 1 == i) {
                line1 = String.format("    ('%S','%s','%s','%s', '%s', '%s','%s', '%s')\n",
                        categorys.get(i).getId(),
                        categorys.get(i).getCategoryCode(),
                        categorys.get(i).getCategoryName(),
                        "数据字典/行政区域划分/" + categorys.get(i).getpNames(),
                        categorys.get(i).getpId(),
                        categorys.get(i).getCreateTime(),
                        "3",
                        categorys.get(i).getIsLeaf());
            } else {
                line1 = String.format("    ('%S','%s','%s','%s', '%s', '%s','%s', '%s'),\n",
                        categorys.get(i).getId(),
                        categorys.get(i).getCategoryCode(),
                        categorys.get(i).getCategoryName(),
                        "数据字典/行政区域划分/" + categorys.get(i).getpNames(),
                        categorys.get(i).getpId(),
                        categorys.get(i).getCreateTime(),
                        "3",
                        categorys.get(i).getIsLeaf());
            }
            Util.bufferedWriterWrite(bufferedWriter, line1);

            System.out.println("已处理 " + i + "条");


            count += 50;
        }
    }

    private static int resolvedCount = 0;

    private static void resolveAll(Area parentArea, BufferedWriter bw) {
        List<Area> areas = resolve(parentArea);
        areas.forEach(area -> {
            if (!area.leaf) {
                resolveAll(area, bw);
            }
        });
    }

    private static List<Area> resolve(Area parentArea) {
        while (true) {
            try {
                return resolveInternal(parentArea);
            } catch (SocketTimeoutException ex) {
                HTTP_CLIENT = new OkHttpClient();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    private static List<Area> resolveInternal(Area parentArea) throws IOException {
        String parentCode = parentArea.code;
        Request.Builder requestBuilder = getBuilder(parentCode);
        return getAreas(parentArea, parentCode, requestBuilder);
    }

    private static Request.Builder getBuilder(String parentCode) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018");
        int codeLen = parentCode.length();
        if (codeLen > 2) urlBuilder.append('/').append(parentCode, 0, 2);
        if (codeLen > 4) urlBuilder.append('/').append(parentCode, 2, 4);
        if (codeLen > 6) urlBuilder.append('/').append(parentCode, 4, 6);
        urlBuilder.append('/').append(parentCode).append(".html");

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(urlBuilder.toString());
        requestBuilder.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
        requestBuilder.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        requestBuilder.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        requestBuilder.header("Referer", "http://www.stats.gov.cn/waf_verify.htm");
        requestBuilder.header("Cookie", "_trs_uv=jyr1gamz_6_ciri; AD_RS_COOKIE=20082855; __utma=207252561.1066798203.1564680696.1564680696.1564680696.1; __utmz=207252561.1564680696.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wzws_cid=d50ee460f6aa7ab6fd15e01ce74b3413c2a0f9bb730ef751ba9fdc7bfa1a2fcb24e52cfef5f02d6ee099f0dedaf7d699bd00d44c042e8d2abeb167fd4e94ad10");
        requestBuilder.cacheControl(CacheControl.FORCE_NETWORK);
        System.out.println("url => " + urlBuilder.toString());
        return requestBuilder;
    }


    private static List<Area> getAreas(Area parentArea, String parentCode, Request.Builder requestBuilder) throws IOException {
        try (Response response = HTTP_CLIENT.newCall(requestBuilder.build()).execute()) {
            if (response == null || response.code() != 200 || response.body() == null) {
                throw new RuntimeException("Error response " + response);
            }

            byte[] bs = response.body().bytes();
            Document document = Jsoup.parse(new String(bs, "gbk"));
            Elements elements = document.getElementsByClass("citytable");
            if (elements.size() != 1) {
                elements = document.getElementsByClass("countytable");
                if (elements.size() != 1) {
                    elements = document.getElementsByClass("towntable");
                    if (elements.size() != 1) {
                        elements = document.getElementsByClass("villagetable");
                        if (elements.size() != 1) {
                            throw new RuntimeException();
                        }
                    }
                }
            }

            elements = elements.get(0).child(0).children();
            List<Area> areas = new ArrayList<>(elements.size());

            Integer isLeaf = 0; // 不是
            for (Element element : elements) {
                String className = element.className();

                if ("tr".equalsIgnoreCase(element.nodeName()) &&
                        ("citytr".equalsIgnoreCase(className)
                                || "countytr".equalsIgnoreCase(className)
                                || "towntr".equalsIgnoreCase(className)
                                || "villagetr".equalsIgnoreCase(className))) {
                    Elements tdElements = element.children();

                    int tdElementsSize = tdElements.size();
                    if (tdElementsSize != 2 && tdElementsSize != 3) {
                        throw new RuntimeException();
                    }
                    String areaId, areaName;
                    boolean leaf = true;
                    Element areaIdElement = tdElements.get(0);
                    Element leafElement = null;
                    if ("villagetr".equalsIgnoreCase(className)) {
                        leafElement = tdElements.get(1);
                    }
                    Element areaNameElement = tdElements.get(tdElementsSize - 1);
                    if (areaIdElement.children().size() == 1 && "a".equalsIgnoreCase(areaIdElement.child(0).nodeName())) {
                        areaId = areaIdElement.child(0).text();
                        areaName = areaNameElement.child(0).text();
                        leaf = false;
                    } else if (areaIdElement.children().size() == 0) {
                        areaId = areaIdElement.text();
                        areaName = areaNameElement.text();
                    } else {
                        throw new RuntimeException();
                    }

                    if (null != leafElement) {
                        isLeaf = 1;   // 是
                    }

                    if (!areaName.equalsIgnoreCase("市辖区") && !areaName.equalsIgnoreCase("县")) {
                        Category category = new Category.CategoryBuilder()
                                .setCategoryCode(Util.getShortAreaId(areaId))
                                .setCategoryName(areaName)
                                .setIsLeaf(isLeaf)
                                .build();

                        if (categorys.size() != 0) {
                            Category categoryByParent = Util.getCategoryByParent(categorys, parentCode);
                            category.setpId(categoryByParent.getpId());
                            category.setpIds(categoryByParent.getpIds());
                            category.setpNames(categoryByParent.getpNames() + "/" + areaName);
                            category.setCategoryName(areaName);
                        } else {
                            category.setCategoryCode(ROOT_CATEGORY.getCategoryCode()  + "00");
                            category.setpId(ROOT_CATEGORY.getId());
                            category.setpIds(ROOT_CATEGORY.getpIds() + "/" + ROOT_CATEGORY.getId());
                            category.setCategoryName(ROOT_CATEGORY.getCategoryName());
                            category.setpNames(ROOT_CATEGORY.getpNames());
                            categorys.add(ROOT_CATEGORY);

                            Category categoryByParent = Util.getCategoryByParent(categorys, category.getCategoryCode());
                            category.setpId(categoryByParent.getpId());
                            category.setCategoryCode(Util.getShortAreaId(areaId));
                            category.setpIds(categoryByParent.getpIds());
                            category.setpNames(categoryByParent.getpNames() + "/" + areaName);
                            category.setCategoryName(areaName);
                            category.setIsLeaf(isLeaf);
                        }

                        areas.add(new Area(Util.getShortAreaId(areaId), areaName, parentArea.fullName + '/' + areaName, category.getId(), "", leaf));
                        categorys.add(category);
                    }
                    areas.add(new Area(Util.getShortAreaId(areaId), areaName, parentArea.fullName, ROOT_CATEGORY.getId(), "", leaf));
                }
            }
            return areas;
        }
    }

    private static class Area {
        private String code;
        private String name;
        private String fullName;
        private String parent;
        private String parents;
        private boolean leaf;

        public Area(String code, String name, String fullName, String parent, String parents, boolean leaf) {
            this.code = code;
            this.name = name;
            this.fullName = fullName;
            this.parent = parent;
            this.parents = parents;
            this.leaf = leaf;
        }
    }
}