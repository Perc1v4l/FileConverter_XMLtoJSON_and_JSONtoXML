package JSONtoXML;

import Json.*;
import XML.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JSONtoXML
{
    private static Json ninjas = new Json();

    public static Json parseJSON(String path) throws IOException
    {
        File f = new File(path);
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(f);

        parser.nextToken();
        parser.nextToken();

        if (parser.nextToken() == JsonToken.START_ARRAY)
        {
            //loop until token equal to "]"
            while (parser.nextToken() != JsonToken.END_ARRAY)
            {
                if (parser.getCurrentName() == null)
                    continue;
                String cur = parser.getCurrentName();

                if(cur.equals("name"))
                {
                    ninjas.addNinja(" "," ",0," "," ");
                    parser.nextToken();
                    ninjas.returnLastNinja().setName(parser.getText());
                }
                else if(cur.equals("nature"))
                {
                    parser.nextToken();
                    ninjas.returnLastNinja().setNature(parser.getText());
                }
                else if(cur.equals("countOfMis"))
                {
                    parser.nextToken();
                    ninjas.returnLastNinja().setCountOfMis(Integer.parseInt(parser.getText()));
                }
                else if(cur.equals("specialAbility"))
                {
                    parser.nextToken();
                    ninjas.returnLastNinja().setSpecialAbility(parser.getText());
                }
                else if(cur.equals("village"))
                {
                    parser.nextToken();
                    ninjas.returnLastNinja().setVillage(parser.getText());
                }
                else if (cur.equals("clans")) {
                    parser.nextToken();
                    parser.nextToken();

                    //loop until end of devStudios
                    while (parser.nextToken() != JsonToken.END_ARRAY) {

                        //checker if we're looking at "{" / "}"
                        if (parser.getCurrentName() == null)
                            continue;
                        if (parser.getCurrentName().equals("name"))
                        {
                            parser.nextToken();
                            ninjas.returnLastNinja().addClan(parser.getText());
                        }
                    }
                }
                else if (cur.equals("ranks")) {
                    parser.nextToken();
                    parser.nextToken();

                    //loop until end of devStudios
                    while (parser.nextToken() != JsonToken.END_ARRAY) {

                        //checker if we're looking at "{" / "}"
                        if (parser.getCurrentName() == null)
                            continue;
                        if (parser.getCurrentName().equals("name")) {
                            parser.nextToken();
                            ninjas.returnLastNinja().addRank(parser.getText());
                        }
                    }
                }
            }
        }
        else return null;

        return ninjas;
    }

    private static boolean checkVil(String name, ArrayList<XMLVillage> vil)
    {
        for(XMLVillage v:vil)
        {
            if(v.getName().equals(name)) return true;
        }
        return false;
    }

    private static XMLVillage findVil(String name, ArrayList<XMLVillage> vil)
    {
        for (int i=vil.size()-1;i>=0;i--){
            if (vil.get(i).getName().equals(name))
                return vil.get(i);
        }
        return null;
    }

    private static boolean checkClan(String name, ArrayList<XMLClan> clan){
        for (XMLClan c : clan) {
            if(c.getName().equals(name))
                return true;
        }
        return false;
    }
    private static XMLClan findClan(String name, ArrayList<XMLClan> clan)
    {
        for (int i=clan.size()-1;i>=0;i--){
            if (clan.get(i).getName().equals(name))
                return clan.get(i);
        }
        return null;
    }

    private static boolean checkRank(String name, ArrayList<XMLRank> rank){
        for (XMLRank r : rank) {
            if(r.getName().equals(name))
                return true;
        }
        return false;
    }
    private static XMLRank findRank(String name, ArrayList<XMLRank> rank)
    {
        for (int i=rank.size()-1;i>=0;i--){
            if (rank.get(i).getName().equals(name))
                return rank.get(i);
        }
        return null;
    }

    public static XML ConvertToXML()
    {
        XML shinobies = new XML();

        shinobies.addVillage(ninjas.getNinjas().get(0).getVillage());

        for(int i=0;i<ninjas.returnLength();i++)
        {
            JsonNinja jsonNinja =ninjas.getNinjas().get(i);

            if (!checkVil(jsonNinja.getVillage(),shinobies.getVillages()))
                shinobies.addVillage(jsonNinja.getVillage());

            XMLVillage XMLvillage = findVil(jsonNinja.getVillage(),shinobies.getVillages());



            for (int j=0;j<jsonNinja.getClans().size();j++)
            {
                JsonClan jsonClan = jsonNinja.getClans().get(j);

                XMLClan XMLclan;
                //check if we need to create new
                if (!checkClan(jsonClan.getName(), XMLvillage.getClans()))
                {
                    //XMLvillage.setName(jsonNinja.getName());

                    //create new dev
                    XMLvillage.addClan(jsonClan.getName());
                }
                XMLclan = findClan(jsonClan.getName(), XMLvillage.getClans());

                //add ranks
                for (int k=0;k<jsonNinja.getRanks().size();k++)
                {
                    JsonRank jsonRank=jsonNinja.getRanks().get(k);

                    XMLRank XMLrank;
                    if(!checkRank(jsonRank.getName(),XMLclan.getRanks()))
                    {
                        //XMLclan.setName(jsonNinja.getName());

                        XMLclan.addRank(jsonRank.getName());
                    }
                    XMLrank=findRank(jsonRank.getName(),XMLclan.getRanks());

                    XMLrank.addNinja(jsonNinja.getName(), jsonNinja.getNature(),
                            jsonNinja.getCountOfMis(), jsonNinja.getSpecialAbility());
                }
            }
        }

        return shinobies;
    }

    private static void writeXml(OutputStream out, XML xmlClass) throws XMLStreamException
    {
        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");

        //header
        writer.writeStartElement("Shinobies");


        writer.writeStartElement("Villages");


        // insides

        for (int i = 0; i < xmlClass.returnLength(); i++) {
            writer.writeStartElement("Village");
            writer.writeAttribute("name", xmlClass.getVillages().get(i).getName());

            writer.writeStartElement("Clans");


            for (int j = 0; j < xmlClass.getVillages().get(i).getClans().size(); j++) {
                XMLClan clan = xmlClass.getVillages().get(i).getClans().get(j);

                writer.writeStartElement("Clan");
                writer.writeAttribute("name", clan.getName());

                writer.writeStartElement("Ranks");

                for (int k = 0; k < clan.getRanks().size(); k++) {
                    XMLRank rank = clan.getRanks().get(k);

                    writer.writeStartElement("Rank");
                    writer.writeAttribute("rank", rank.getName());

                    writer.writeStartElement("Ninjas");

                    for (int l = 0; l < rank.getNinjas().size(); l++) {
                        XMLNinja ninja = rank.getNinjas().get(l);

                        writer.writeStartElement("Ninja");
                        writer.writeAttribute("name", ninja.getName());
                        writer.writeAttribute("nature", ninja.getNature());
                        writer.writeAttribute("CountOfMis", ((Integer)ninja.getCountOfMis()).toString());
                        writer.writeAttribute("SpecialAbility", ninja.getSpecialAbility());
                        writer.writeEndElement(); //end ninja

                    }
                    writer.writeEndElement();//end ninjas
                    writer.writeEndElement();//end rank

                }
                writer.writeEndElement();//end ranks
                writer.writeEndElement();//end clan
            }
            writer.writeEndElement();//end clans
            writer.writeEndElement();//end village

        }

        writer.writeEndElement();//end villages
        writer.writeEndElement();//end shinobies



        writer.flush();

        writer.close();
    }

    public static void createXML(XML xmlClass, String path)
    {
        try(FileOutputStream out = new FileOutputStream(path))
        {
            writeXml(out, xmlClass);
        } catch (IOException | XMLStreamException e)
        {
            e.printStackTrace();
        }
    }
}
