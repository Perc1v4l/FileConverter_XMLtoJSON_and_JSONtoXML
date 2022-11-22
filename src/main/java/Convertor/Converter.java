package Convertor;

import JSONtoXML.JSONtoXML;
import XML.XML;
import XML.XMLVillage;
import XMLtoJSON.XMLtoJSON;

public class Converter
{
    public void convertToJson(String pathToXML, String pathToNewFile)  {
        try {
            XMLtoJSON.ParserXML(pathToXML);
            XMLtoJSON.createFile(XMLtoJSON.ConvetToJson(), pathToNewFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void convertToXML(String pathToJSON, String pathToNewFile)  {
        try {
            JSONtoXML.parseJSON(pathToJSON);
            JSONtoXML.createXML(JSONtoXML.ConvertToXML(), pathToNewFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doSum(){
        XML w = XMLtoJSON.getShinobies();
        for (XMLVillage publisher : w.getVillages())
        {
            int y = 2;
        }
    }
}
