package com.gqz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gqz.pojo.Img;
import com.gqz.pojo.Shape;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-29 14:43
 **/
public class ImgJsonsUtil {
    private static List<String> nextJsonPath;
    private static final String BASE_PATH ="E:\\标注0924\\料型分类数据集0923\\mymid\\细分中型 - 副本";

    /**
     * 加载业务所需文件
     *
     * @return
     */
    public static List<Img> load() throws IOException {

        ArrayList<Img> globalImgs = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入起始文件名称:\n");
        String path = scanner.nextLine();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(BASE_PATH +"\\"+ path));

        String startJson = IOUtils.toString(bis);

        Img startImg = JSON.parseObject(startJson, Img.class);
        globalImgs.add(startImg);
        bis.close();
        //获取下面复制的几个json
        System.out.println("请输入要复制几个json");
        int i = scanner.nextInt();
        nextJsonPath = getJsonNames(i);

        BufferedInputStream bis2 = null;
        for (String s : nextJsonPath) {
            bis2 = new BufferedInputStream(new FileInputStream(s));
            String nextJson = IOUtils.toString(bis2);
            Img nextImg = JSON.parseObject(nextJson, Img.class);
            globalImgs.add(nextImg);
        }
        assert bis2 != null;
        bis2.close();

        return globalImgs;
    }


    /**
     * 获取最后几个json文件路径
     *
     * @param index
     * @return
     */
    public static List<String> getJsonNames(Integer index) {
        File[] files = new File(BASE_PATH).listFiles();

        assert files != null;

        ArrayList<String> list = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".json")) {
                    String absolutePath = file.getAbsolutePath();
                    list.add(file.getAbsolutePath());
                }
            }
        }

        ArrayList<String> temp = new ArrayList<>();
        while (index > 0) {
            temp.add(list.get(list.size() - index));
            index--;
        }

        return temp;
    }

    @Deprecated
    public static List<Img> json2Pojo(List<String> paths) throws IOException {

        ArrayList<Img> imgs = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入起始文件名称:\n");
        String pro = "E:\\标注0924\\料型分类数据集0923\\mymid\\细分中型\\";
        String path = scanner.nextLine();

        try {
            FileInputStream fileInputStream = new FileInputStream(pro + path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] bytes = new byte[512];

            StringBuilder sb = new StringBuilder();
            int length = 0;

            while ((length = fileInputStream.read(bytes)) != -1) {

                bos.write(bytes, 0, length);

            }

            String json = sb.append(bos).toString();


            Img img = JSON.parseObject(json, Img.class);

            imgs.add(img);

            fileInputStream.close();
            bos.close();


            for (String s : paths) {
                FileInputStream is = new FileInputStream(s);
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                byte[] bytess = new byte[512];

                StringBuilder sbb = new StringBuilder();
                int length1 = 0;

                while ((length1 = is.read(bytes)) != -1) {

                    os.write(bytess, 0, length1);

                }

                String jsonn = sbb.append(os).toString();


                Img imgg = JSON.parseObject(jsonn, Img.class);

                imgs.add(imgg);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgs;

    }

    public static Shape randomPoint(Shape shape) {
        List<Double[]> points = shape.getPoints();

        DecimalFormat formatDouble = new DecimalFormat("#.###");
//        Random random = new Random();
        for (Double[] doubles : points) {
            if (Math.random() > 0.5) {
                doubles[0] += Double.parseDouble(formatDouble.format(Math.random() * 10));
                doubles[1] += Double.parseDouble(formatDouble.format(Math.random() * 10));
            } else {
                doubles[0] -= Double.parseDouble(formatDouble.format(Math.random() * 10));
                doubles[1] -= Double.parseDouble(formatDouble.format(Math.random() * 10));
            }
        }
        return shape;

    }

    public static List<Img> copyShape(List<Img> imgs) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ArrayList<Img> temp = new ArrayList<>();
        Img startImg = imgs.get(0);
        List<Shape> startShapes = startImg.getShapes();

        ArrayList<Shape> shapeTemp = new ArrayList<>();

        for (Shape startShape : startShapes) {
            Shape shape = randomPoint(startShape);
            shapeTemp.add(shape);
        }

//        Img img0 = new Img();
        Img img0 = (Img) BeanUtils.cloneBean(startImg);
        img0.setShapes(shapeTemp);
        temp.add(img0);

        for (int i = 1; i < imgs.size(); i++) {
            Img img_i = imgs.get(i);
            List<Shape> baseShapes = imgs.get(0).getShapes();
            ArrayList<Shape> temp1 = new ArrayList<>();
            for (Shape baseShape : baseShapes) {
                Shape shape = randomPoint(baseShape);
                temp1.add(shape);
            }
            img_i.setShapes(temp1);

        }
        for (int i = 1; i < imgs.size(); i++) {
            temp.add(imgs.get(i));
        }

        return temp;

    }

    /**
     * 生成随机json
     *
     * @throws IOException
     */
    public static void generate(List<Img> imgs) throws IOException {
        BufferedOutputStream bos = null;

        int i = 1;
        for (String s : nextJsonPath) {
            bos = new BufferedOutputStream(new FileOutputStream(s));
            String jsonString = JSON.toJSONString(imgs.get(i), SerializerFeature.PrettyFormat);

            i++;
            IOUtils.write(jsonString, bos);
            bos.close();
        }



    }
}
