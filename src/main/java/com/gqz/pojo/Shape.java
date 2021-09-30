package com.gqz.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-28 21:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shape implements Serializable {
    @JSONField(ordinal = 0)
    private String label;
    @JSONField(ordinal = 1)
    private List<Double[]> points;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue,ordinal = 2)
    private String group_id;
    @JSONField(ordinal = 3)
    private String shape_type;
    @JSONField(ordinal = 4,serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object flags;
}
