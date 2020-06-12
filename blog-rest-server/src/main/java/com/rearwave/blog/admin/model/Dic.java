package com.rearwave.blog.admin.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@TableName("t_rearwave_dic")
public class Dic extends Model<Dic> {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String dicName;
    /**
     * key 数据库存储的代码
     */
    private String key;
    /**
     * value 实际显示的内容
     */
    private String value;
    /**
     * 0 无效 1 有效
     */
    private Integer status;


    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Dic{" +
        ", dicName=" + dicName +
        ", key=" + key +
        ", value=" + value +
        ", status=" + status +
        "}";
    }
}
