package XMLtoJSON;

import Json.*;
import XML.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class XMLtoJSON extends DefaultHandler
{
    private static XML shinobies = new XML();

    public static XML ParserXML(String path_to_file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        File f = new File(path_to_file);
        parser.parse(f, handler);

        return shinobies;
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Village"))
            {
                String name = attributes.getValue("name");
                shinobies.addVillage(name);
            } else if (qName.equals("Clan"))
            {
                String name = attributes.getValue("name");
                shinobies.getVillages().get(shinobies.returnLength() - 1).addClan(name);
            } else if (qName.equals("Rank"))
            {
                String rank = attributes.getValue("rank");
                shinobies.getVillages().get(shinobies.returnLength() - 1).getClans()
                        .get(shinobies.getVillages().get(shinobies.returnLength() - 1).returnLength() - 1).addRank(rank);
            } else if (qName.equals("Ninja"))
            {
                String name = attributes.getValue("name");
                String nature = attributes.getValue("nature");
                Integer CountOfMis = Integer.parseInt(attributes.getValue("CountOfMis"));
                String SpecialAbility = attributes.getValue("SpecialAbility");
                shinobies.getVillages().get(shinobies.returnLength() - 1).getClans()
                        .get(shinobies.getVillages().get(shinobies.returnLength() - 1).returnLength() - 1).getRanks()
                        .get(shinobies.getVillages().get(shinobies.returnLength() - 1).getClans()
                                .get(shinobies.getVillages().get(shinobies.returnLength() - 1).returnLength() - 1).returnLength() - 1)
                        .addNinja(name, nature, CountOfMis, SpecialAbility);
            }
        }
    }

    private static JsonNinja getCurrentNinja(String name, ArrayList<JsonNinja> arrayList) {
        JsonNinja ninja = null;
        for (JsonNinja ninja1 : arrayList) {
            if (ninja1.getName().equals(name)) ninja = ninja1;
        }
        return ninja;
    }

    public static Json ConvetToJson()
    {
        Json jNinjas = new Json();

        for (int i = 0; i < shinobies.returnLength(); i++)
        {
            XMLVillage village = shinobies.getVillages().get(i);
            for (int j = 0; j < village.returnLength(); j++)
            {
                XMLClan clan = village.getClans().get(j);
                for (int k = 0; k < clan.returnLength(); k++)
                {
                    XMLRank rank = clan.getRanks().get(k);
                    for (int m = 0; m < rank.returnLength(); m++)
                    {
                        XMLNinja ninja = rank.getNinjas().get(m);
                        JsonNinja checker = getCurrentNinja(ninja.getName(), jNinjas.getNinjas());
                        if (checker == null)
                        {
                            jNinjas.addNinja(ninja.getName(), ninja.getNature(), ninja.getCountOfMis(), ninja.getSpecialAbility(), village.getName());
                            JsonNinja jsonNinja = jNinjas.getNinjas().get(jNinjas.returnLength() - 1);

                            jsonNinja.addClan(clan.getName());
                            jsonNinja.addRank(rank.getName());
                        } else {
                            checker.addClan(clan.getName());
                            checker.addRank(rank.getName());
                        }
                    }
                }
            }

        }
        return jNinjas;
    }


    public static void createFile(Json json, String path) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), json);
    }

    public static XML getShinobies()
    {
        return shinobies;
    }
}