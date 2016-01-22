package com.hello.testxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.text);


        try {


            //过去xml文件路径
//          getAssets().open("languages.xml");

            //文档生成器工程对象
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            //实例化一个新的文档生成器
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //利用文档生成器解析xml
            Document document = builder.parse(getAssets().open("languages.xml"));

            //获取根元素（Languages）
            Element element = document.getDocumentElement();

            //获取节点列表，获取子tag
            NodeList list = element.getElementsByTagName("lan");
            //遍历lan内的子元素
            for (int i = 0; i < list.getLength(); i++) {
                //把对象强制转换为元素（第一组对象转为元素）
                Element lan = (Element) list.item(i);
                //获取lan的属性 id=1
                text.append(lan.getAttribute("id")+"\n");
                //获取name、ide元素内的值
                text.append(lan.getElementsByTagName("name").item(0).getTextContent());
                text.append(lan.getElementsByTagName("ide").item(0).getTextContent());
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
