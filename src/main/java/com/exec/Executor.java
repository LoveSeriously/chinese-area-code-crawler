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
    private static final Area ROOT_AREA = new Area("50", "重庆市", "重庆市", "40283f81513ec42d01513ec524150000", "0/40283f81513ec42d01513ec524150000",false);
    //private static AreaCode ROOT_AREARESULT = new AreaCode.AreaCodeBuilder().setAreaCode("50").setAreaName("重庆市").setpNames("中国,重庆市").setIsLeaf(0).build();
    private static Category ROOT_CATEGORY = new Category.CategoryBuilder().setCategoryCode("50").setCategoryName("重庆市").setpNames("重庆市").setpId("40283f81513ec42d01513ec524150000").setpIds("0/40283f81513ec42d01513ec524150000").setIsLeaf(0).build();

    private static final String TARGET_FILE = PathCofig.TARGET.getName();

    private static OkHttpClient HTTP_CLIENT = new OkHttpClient();

    //private  static List<AreaCode> areaCodes = new ArrayList<>();
    private  static List<Category> categorys = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(TARGET_FILE), StandardCharsets.UTF_8))) {
            Util.bufferedWriterWrite(bufferedWriter, "insert into um_category(ID, CATEGORY_CODE, CATEGORY_NAME, P_NAMES, P_ID, CREATE_TIME, CATEGORY_TYPE, IS_LEAF) values\n");
            /*String line = String.format("    ('%S','%s','%s','%s', '%s', '%s','%s', '%s'),\n",
                    ROOT_CATEGORY.getId(),
                    ROOT_CATEGORY.getCategoryCode()+"0000000000",
                    ROOT_CATEGORY.getCategoryName(),
                    "数据字典/行政区域划分/"+ROOT_CATEGORY.getpNames(),
                    "40283f81513ec42d01513ec524150000",
                    ROOT_CATEGORY.getCreateTime(),
                    "3",
                    ROOT_CATEGORY.getIsLeaf());
            Util.bufferedWriterWrite(bufferedWriter, line);*/
            resolveAll(ROOT_AREA, bufferedWriter);
            System.err.println("开始写入SQL 。。。。 ");
            wrieSql(bufferedWriter);
        }
    }

    private static void wrieSql(BufferedWriter bufferedWriter) {
        categorys = Util.removeDuplicateId(categorys);
        //util.bufferedWriterWrite(bufferedWriter, "insert into um_category(ID, CATEGORY_CODE, CATEGORY_NAME, P_NAMES, P_ID, CREATE_TIME, CATEGORY_TYPE) values");
        for (int i = 0; i < categorys.size(); i ++){
            if (categorys.get(i).getCategoryName().contains(",")) {
                throw new RuntimeException("不支持的区域名称（有英文逗号） " + categorys.get(i).getCategoryName());
            }
            //util.bufferedWriterWrite(bw,area.getCategoryCode() + ',' + area.getCategoryName() + ',' + area.getpNames() + ',' + area.getpId() + '\n');
            String line1 = "";
                    if(categorys.size() - 1 == i) {
                        line1 = String.format("    ('%S','%s','%s','%s', '%s', '%s','%s', '%s')\n",
                                categorys.get(i).getId(),
                                categorys.get(i).getCategoryCode(),
                                categorys.get(i).getCategoryName(),
                                "数据字典/行政区域划分/"+categorys.get(i).getpNames(),
                                categorys.get(i).getpId(),
                                categorys.get(i).getCreateTime(),
                                "3",
                                categorys.get(i).getIsLeaf());
                    } else {
                        line1 = String.format("    ('%S','%s','%s','%s', '%s', '%s','%s', '%s'),\n",
                                categorys.get(i).getId(),
                                categorys.get(i).getCategoryCode(),
                                categorys.get(i).getCategoryName(),
                                "数据字典/行政区域划分/"+categorys.get(i).getpNames(),
                                categorys.get(i).getpId(),
                                categorys.get(i).getCreateTime(),
                                "3",
                                categorys.get(i).getIsLeaf());
                    }

            Util.bufferedWriterWrite(bufferedWriter, line1);
            if ((resolvedCount++ & 0x7F) == 0) {
                System.out.println("已处理 " + resolvedCount);
            }
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
        requestBuilder.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        requestBuilder.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        requestBuilder.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
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
                            elements = document.getElementsByClass("villagehead");
                            if (elements.size() != 1) {
                                throw new RuntimeException();
                            }
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

                    Category category = new Category.CategoryBuilder()
                            .setCategoryCode(areaId)
                            .setCategoryName(areaName)
                            .setpNames(parentArea.fullName + "/" + areaName)
                            .setIsLeaf(isLeaf)
                            .build();

                    if(categorys.size() != 0) {
                        category.setpId(Util.getPidByParent(categorys, parentCode));
                        category.setpIds(Util.getPidsByParent(categorys, parentCode));
                    } else {
                        ROOT_CATEGORY.setCategoryCode(Util.getParentCode(ROOT_CATEGORY.getCategoryCode()));
                        categorys.add(ROOT_CATEGORY);
                        category.setpId(ROOT_CATEGORY.getId());
                        category.setpIds(ROOT_CATEGORY.getpIds() + "/" +ROOT_CATEGORY.getId());
                    }

                    areas.add(new Area(Util.getShortAreaId(areaId), areaName, parentArea.fullName + '/' + areaName, category.getId(),"", leaf));
                    categorys.add(category);
                    //areaCodes.add(new AreaCode(areaId, areaName, parentArea.fullName + '.' + areaName, getParentCode(parentCode), leaf));
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

        public Area(String code, String name, String fullName, String parent,String parents, boolean leaf) {
            this.code = code;
            this.name = name;
            this.fullName = fullName;
            this.parent = parent;
            this.parents = parents;
            this.leaf = leaf;
        }
    }
}