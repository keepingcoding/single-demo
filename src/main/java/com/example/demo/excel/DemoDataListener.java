package com.example.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zzs
 * @date 2019/9/24 16:07
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        LOGGER.error("解析失败，但是继续解析下一行", exception);
    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        //if (list.size() >= BATCH_COUNT) {
        //    saveData();
        //    list.clear();
        //}
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(new File("D:\\temp\\excel\\1.sql")));

        String sql =
                "	insert into csc_inactive_storehouse (LINE_ID, `NAME`, ENGLISH_NAME, SEX, NATION, BIRTH, ROOM_NUM, PHONE_NUM, CRED_TYPE, CRED_NUM, ID_NUM, GROUP_NUM, BAGGAGE_NUM, IS_LEADER, IS_IN_DESTINE, CREATE_PERSON_ID, CREATE_PERSON_NAME, CREATE_TIME, REMARK, REMARK2) values                                                                   ";

        StringBuilder sb = new StringBuilder(sql);
        for (DemoData data : list) {
            sb.append("(");
            sb.append(data.getLineId());
            sb.append(",");
            sb.append(data.getName());
            sb.append(",");
            sb.append(data.getNameEn());
            sb.append(",");
            sb.append(data.getGender());
            sb.append(",");
            sb.append(data.getNationality());
            sb.append(",");
            sb.append(data.getBirthday());
            sb.append(",");
            sb.append(data.getRoomNo());
            sb.append(",");
            sb.append(data.getMobile());
            sb.append(",");
            sb.append(data.getCardType());
            sb.append(",");
            sb.append(data.getCardNo());
            sb.append(",");
            sb.append(data.getIdNum());
            sb.append(",");
            sb.append(data.getTeamNo());
            sb.append(",");
            sb.append(data.getBaggageNo());
            sb.append(",");
            sb.append(data.getIsLeader());
            sb.append(",");
            sb.append(0);
            sb.append(",");
            sb.append(1);
            sb.append(",");
            sb.append("admin");
            sb.append(",");
            sb.append("now()");
            sb.append(",");
            sb.append(data.getRemark());
            sb.append(",");
            sb.append(data.getRemark2());

            sb.append(");");

            String s = sb.toString();
            br.write(s);
            br.newLine();
        }

        br.flush();
        br.close();
    }
}