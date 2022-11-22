package org.example;

import Convertor.Converter;

public class Main {
    public static void main(String[] args) throws Exception
    {
        Converter f = new Converter();

        if(args.length!=0)
        {
            if(args[0].contains(".json"))
                f.convertToXML(args[0],args[1]);
            else if(args[0].contains(".xml"))
                f.convertToJson(args[0], args[1]);
            else throw new Exception("Wrong input!");
        }
        else {
            f.convertToXML("E:\\3курс\\pr2_maven\\src\\test\\BBBBBB.json", "E:\\3курс\\pr2_maven\\src\\test\\AAAAAA.xml");
            f.convertToJson("E:\\3курс\\pr2_maven\\src\\test\\Nikita.xml", "E:\\3курс\\pr2_maven\\src\\test\\Matthew.json");
        }
    }
}