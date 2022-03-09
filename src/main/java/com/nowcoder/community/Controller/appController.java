package com.nowcoder.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class appController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Soring Boot...";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        // 获取请求数据
        //请求行
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //请求的消息头
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(request.getParameter("code"));

        //返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter writer = response.getWriter();){
            writer.write("<h1>牛客网</h1>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //get请求
    // /student?current=1&limit=10
    @RequestMapping(path = "/student",method = RequestMethod.GET)//只能以get的请求访问
    @ResponseBody
    public String getStudent(
            @RequestParam(name = "current",required = false,defaultValue = "1")int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10")int limit
    ){
        return "goajglj";
    }

    //   /stuent/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //相应html数据   没成功
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeach(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",52);
        mav.setViewName("/demo/view");
        return mav;
    }
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",20);
        return "/demo/view";
    }

    //相应JSON数据（异步请求）
    //java 对象  -> JSON 字符串   ->   JS对象
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",25);
        emp.put("ads",522);
        return emp;
    }
}
