package com.example.demo.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @author zzs
 * @date 2019/9/24 15:49
 */
public class ReadTest {

    public static void main(String[] args) {
        String fileName = "D:\\temp\\新建XLS 工作表.xls";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
