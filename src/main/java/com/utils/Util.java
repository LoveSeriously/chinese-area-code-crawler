package com.utils;

import com.bean.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);
    public static void bufferedWriterWrite(BufferedWriter bw, String str) {
        try {
            bw.write(str);
        } catch (Exception e) {
            System.err.println("Failed to write the str " + str);
            log.error("执行出错: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加AreaId后面的0
     *
     * @param parentCode
     * @return
     */
    public static String getParentCode(String parentCode) {
        int len = parentCode.length();
        if (len == 2) {
            return parentCode + "0000"; // 6 位
        } else if (len == 4) {
            return parentCode + "00";   // 6 位
        } else {
            return parentCode;
        }
    }

    /**
     * 去除AreaId后面的0
     *
     * @param areaId
     * @return
     */
    public static String getShortAreaId(String areaId) {
        if (areaId.length() != 12) {
            log.error("执行出错: ", new RuntimeException(areaId));
            throw new RuntimeException(areaId);
        }
        if (areaId.endsWith("0000000000")) {
            return areaId.substring(0, 2);
        } else if (areaId.endsWith("00000000")) {
            return areaId.substring(0, 4);
        } else if (areaId.endsWith("000000")) {
            return areaId.substring(0, 6);
        } else if (areaId.endsWith("000")) {
            return areaId.substring(0, 9);
        } else {
            return areaId;
        }
    }

    /**
     * 根据父parent得到Category
     *
     * @param pidMark
     * @return
     */
    public static Category getCategoryByParent(Map<String, Category> pidMark, String parentCode) {
        Category category1 = new Category.CategoryBuilder().build();

        String code = Util.getParentCode(parentCode);

        Category category = pidMark.get(code);
        if (null != category) {
            category1.setpId(category.getId());
            category1.setpIds(category.getpIds() + "/" + category.getId());
            category1.setpNames(category.getpNames());
            int result = getLevel(category1);
            category1.setLevel(result);
        } else {
            // 市 下面的市辖区 ,县
            for(Map.Entry<String,Category> mapEntry : pidMark.entrySet()){
                boolean contains = mapEntry.getKey().contains("/" + code);
                if (contains) {
                    category = pidMark.get(mapEntry.getKey());
                    category1.setpId(category.getId());
                    category1.setpIds(category.getpIds() + "/" + category.getId());
                    category1.setpNames(category.getpNames());
                    int result = getLevel(category1);
                    category1.setLevel(result);
                }
            }
        }

        return category1;
    }

    private static int getLevel(Category category1) {
        String s = category1.getpIds();
        String[] split = s.split("/");
        int length = split.length;
        return length;
    }
}
