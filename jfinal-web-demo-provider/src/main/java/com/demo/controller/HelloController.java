package com.demo.controller;

import com.demo.interceptor.AAA;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

import java.io.File;

public class HelloController extends Controller {

    @Before(AAA.class)
    @Clear
    public void index() {
        renderText("hello world");
    }

    public void toLogin() {
        // 渲染名为test.html的视图，且视图类型为 JFinal Template
        renderTemplate("login.html");
        /*// 渲染名为test.html的视图，且视图类型为FreeMarker
        renderFreeMarker("login.html");
        // 渲染名为test.html的视图，且视图类型为Velocity
        renderVelocity("login.html");*/
    }

    /*public void login() {
        User user = new User();
        user.setUserName(getPara("uname"));
        user.setAge(getParaToInt("age"));
        if (user.getUserName().equals("milo")&&user.getAge().equals(18)) {
            setSessionAttr("currentUser", user);
            renderTemplate("index.html");
        } else {
            renderError(500 , "error.html");
        }
    }*/

    public void upload() {
        renderTemplate("upload.html");
        UploadFile file = getFile("file");
        System.out.println(file);
    }

    public void getQrCode() {
        //纠错级别从高到低可以指定为：'H'、'Q'、'M'、'L'，其纠错率分别为：30%、25%、15%、7%。 不指定该参数值默认为 'L'。
        renderQrCode("https://www.baidu.com",300,300, 'M');
    }

    /*public void getJson() {
        User user = new User(1L, "milo", "张二狗", "17718152603");
        // 将user对象转换成 json 并渲染
        renderJson(user);
    }

    public void getJsons() {
        User user1 = new User(1L, "milo", "张二狗", "17718152603");
        User user2 = new User(2L, "egon", "李大狗", "17718152605");
        User user3 = new User(3L, "hero", "王二牛", "17718152607");
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        // 以 "users" 为根，仅将 userList 中的数据转换成 json 并渲染
        renderJson("users", userList);
    }*/

    public void testJson() {
        setAttr("user","milo");
        setAttr("blog","CSDN");
        /*// 直接渲染 json 字符串
        renderJson("{\"age\":18}" );*/
        /*// 仅将setAttr(“user”, user)与setAttr(“blog”, blog)设置的属性转换成json并渲染
        renderJson(new String[]{"user", "blog"});*/
        // 将所有setAttr(..)设置的变量转换成 json 并渲染
        renderJson();
    }

    public void testHtml() {
        /*// 渲染 Html 内容 "Hello Html"
        renderHtml("Hello Html");*/
        /*// 渲染名为 test.html 的文件，且状态为 404
        renderError(404 , "login.html");*/
        // 渲染名为 test.html 的文件，且状态为 500
        renderError(500 , "login.html");
    }

    public void testFile() {
        //根据BaseDownloadPath路径下载
        renderFile("001.jpg");
        //绝对路径下载
        String file = "D:/downloads/001.txt";
        renderFile(new File(file),"dean.txt");
    }




    public void testParam() {
        renderText("Hello, My name is "+getPara(0)+", My age is "+getPara(1));
    }

    @ActionKey("/key")
    public void testKey() {
        render("login.html");
    }

}
