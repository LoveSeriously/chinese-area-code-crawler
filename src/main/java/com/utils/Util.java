package com.utils;

import com.bean.Category;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

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
     * @param parentCode
     * @return
     */
    public static String getParentCode(String parentCode) {
        int len = parentCode.length();
        if(len == 1) {
            return parentCode + "00000000000";
        } else if (len == 2){
            return parentCode + "0000000000";
        } else if (len == 3){
            return parentCode + "000000000";
        } else if (len == 4){
            return parentCode + "00000000";
        } else if (len == 5){
            return parentCode + "0000000";
        } else if (len == 6){
            return parentCode + "000000";
        } else if (len == 7){
            return parentCode + "00000";
        } else if (len == 8){
            return parentCode + "0000";
        } else if (len == 9){
            return parentCode + "000";
        } else if (len == 10){
            return parentCode + "00";
        } else if (len == 11){
            return parentCode + "0";
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
     * 去除重复Id
     * @param list
     * @return
     */
    public static List removeDuplicateId(List<Category> list)  {
        for  (int i = 0; i < list.size() - 1; i ++)  {
            for  (int j = list.size() - 1; j > i; j --)  {
                if(list.get(j).getId().equals(list.get(i).getId()))  {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 根据父parent得到pid
     * @param parent
     * @return
     */
    public static String getPidByParent(List<Category> categorys, String parent) {
        for (Category category : categorys) {
            String parentCode = getParentCode(parent);
            if (category.getCategoryCode().equals(parentCode)) {
                return category.getId();
            }
        }
        return null;
    }

    /**
     * 根据父parent得到pids
     * @param categorys
     * @param parent
     * @return
     */
    public static String getPidsByParent(List<Category> categorys, String parent) {
        for (Category category : categorys) {
            String parentCode = getParentCode(parent);
            if (category.getCategoryCode().equals(parentCode)) {
                return category.getpIds() + "/" + category.getId();
            }
        }
        return null;
    }
}
