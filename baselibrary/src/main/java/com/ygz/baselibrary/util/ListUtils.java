package com.ygz.baselibrary.util;

import java.util.List;

/**
 * @Date: 2019/10/10 11:21
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class ListUtils {

    /**
     * list是否为空
     *
     * @param list List
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * list不为空
     *
     * @param list List
     * @return
     */
    public static boolean notEmpty(List list) {
        return list != null && !list.isEmpty();
    }

    /**
     * list的size等于1
     *
     * @param list List
     * @return
     */
    public static boolean isSize1(List list) {
        return ListUtils.notEmpty(list) && list.size() == 1;
    }

    /**
     * list的size大于1
     *
     * @param list List
     * @return
     */
    public static boolean isSizeMoreThan1(List list) {
        return ListUtils.notEmpty(list) && list.size() > 1;
    }

}
