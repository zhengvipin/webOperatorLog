package com.springboot.domain;

import java.util.Date;
import java.util.List;

/**
 * @classDesc: 操作对象日志模型
 * @author: Vipin Zheng
 * @createDate: 2018-05-13 12:39:41
 * @version: v1.0
 */
public class ChangeLog {
    private String operator;                    // 操作人
    private Date operateDate;                   // 操作时间
    private List<Change> operateList;   // 操作数据

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public List<Change> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<Change> operateList) {
        this.operateList = operateList;
    }

    @Override
    public String toString() {
        return "ChangeLog{" +
                "operator='" + operator + '\'' +
                ", operateDate=" + operateDate +
                ", operateList=" + operateList +
                '}';
    }
}
