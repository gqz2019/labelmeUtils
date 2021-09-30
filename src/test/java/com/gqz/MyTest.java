package com.gqz;

import com.gqz.pojo.Img;
import com.gqz.pojo.Shape;
import com.gqz.utils.ImgJsonsUtil;
import lombok.extern.java.Log;
import org.junit.Test;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-29 14:51
 **/

public class MyTest {
    @Test
    public void test() {


        try {
//
            List<Img> load = ImgJsonsUtil.load();
            List<Img> imgs = ImgJsonsUtil.copyShape(load);
            ImgJsonsUtil.generate(imgs);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void doubleClick() {

        Double x = 526.5555462536;

        DecimalFormat formatDouble = new DecimalFormat("#.###"); //表示格式化为保留小数后六位
        System.out.println(formatDouble.format(x));//输出结果为1.234568

        List<Img> load = null;
        try {
            load = ImgJsonsUtil.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(load.get(0).getShapes().toString());
        System.out.println(load.get(1).getFlags());

    }
}
