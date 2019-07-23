package com.polycis.websocket.util;


/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/5
 * description : 描述
 */
public class Test {
    public static void main(String[] args) {
        int[] num = {1, 3, 4, 5, 6, 7, 9, 8, 2, 0};
        int[] index = {0, 5, 4, 9, 2, 0, 1, 0, 0, 9, 1};
        StringBuilder str = new StringBuilder();
        for (int i : index) {
            str.append(num[i]);
        }
        //面试官电话:
        String tel = str.toString();
        System.out.println(tel);
    }
}
