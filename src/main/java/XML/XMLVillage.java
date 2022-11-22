package XML;
import java.util.ArrayList;
public class XMLVillage {
    private String name;
    private ArrayList<XMLClan> clans;

    public XMLVillage(String name) {
        this.name = name;
        this.clans = new ArrayList<XMLClan>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addClan(String name){
        clans.add(new XMLClan(name));
    }

    public ArrayList<XMLClan> getClans(){
        return clans;
    }

    public int returnLength(){
        return clans.size();
    }
}
