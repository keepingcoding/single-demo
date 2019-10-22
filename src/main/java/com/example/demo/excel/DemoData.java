package com.example.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

/**
 * @author zzs
 * @date 2019/9/24 15:57
 */
@Data
public class DemoData {
    Integer id;

    Integer lineId;

    String nationality;

    String name;

    @ExcelProperty(index = 4)
    String nameEn;

    Integer roomNo;

    Long mobile;

    String cardType;

    String cardNo;

    String gender;

    String teamNo;

    @ExcelProperty(value = "create_by")
    Integer createBy;

    @ExcelProperty(value = "create_time")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    String createTime;

    Integer isPrint;

    @ExcelProperty(value = "print_by")
    Integer printBy;

    @ExcelProperty(value = "print_time")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    String printTime;

    @ExcelProperty(value = "modified_by")
    Integer modifiedBy;

    @ExcelProperty(value = "modified_time")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    String modifiedTime;

    Integer isDelete;

    @DateTimeFormat("yyyy-MM-dd")
    String birthday;

    Integer isAdd;

    String remark;

    @ExcelProperty(value = "baggage_no")
    String baggageNo;

    @ExcelProperty(value = "id_num")
    Long idNum;

    Integer isLeader;

    String remark2;

    String uploadtoken;

    @ExcelProperty(value = "ticket_code")
    Long ticketCode;

    @ExcelProperty(value = "booking_no")
    Long bookingNo;

    Integer isEntry;
}
