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
 * @create 2021-09-28 21:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img implements Serializable {
    @JSONField(ordinal = 0)
    private String Version;
    @JSONField(ordinal = 1,serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object flags;
    @JSONField(ordinal = 2)
    private List<Shape> shapes;
    @JSONField(ordinal = 3)
    private String imagePath;
    @JSONField(ordinal = 4)
    private String imageData;
    @JSONField(ordinal = 5)
    private Integer imageHeight;
    @JSONField(ordinal = 6)
    private Integer imageWidth;
}
