package com.gqz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.gqz.pojo.Img;
import com.gqz.pojo.Shape;
import com.gqz.utils.ImgJsonsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static com.gqz.utils.ImgJsonsUtil.generate;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-28 21:01
 **/
public class RandomImg {
    public static void main(String[] args) {


        try {
//
            List<Img> load = ImgJsonsUtil.load();
            List<Img> imgs = ImgJsonsUtil.copyShape(load);
            ImgJsonsUtil.generate(imgs);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        generate();
    }


}
