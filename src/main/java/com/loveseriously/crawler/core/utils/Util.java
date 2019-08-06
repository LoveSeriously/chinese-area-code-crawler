package com.loveseriously.crawler.core.utils;

import com.loveseriously.crawler.exec.bean.Category;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class Util {
    public static void bufferedWriterWrite(BufferedWriter bw, String str) {
        try {
            bw.write(str);
        } catch (IOException e) {
            System.err.println("Failed to write the str " + str);
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

        /*if (parentCode.length() == 4 && mark.getValue().getCategoryCode().length() == 4) { // 市 下面的市辖区 ,县
             category1.setpId(mark.getValue().getId());
             category1.setpIds(mark.getValue().getpIds() + "/" + mark.getValue().getId());
             category1.setpNames(mark.getValue().getpNames());
        }*/
        Category category = pidMark.get(code);
        if (null != category) {
            category1.setpId(category.getId());
            category1.setpIds(category.getpIds() + "/" + category.getId());
            category1.setpNames(category.getpNames());
        }

        return category1;
    }
}
