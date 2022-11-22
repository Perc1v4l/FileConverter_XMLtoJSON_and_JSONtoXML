package Json;
import java.util.ArrayList;
public class JsonNinja {
    private String name;
    private String nature;
    private int CountOfMis;
    private String SpecialAbility;
    private String Village;

    private ArrayList<JsonClan> clans;
    private ArrayList<JsonRank> ranks;

    public JsonNinja(String name, String nature, int CountOfMis, String SpecialAbility, String Village)
    {
        this.name = name;
        this.nature = nature;
        this.CountOfMis = CountOfMis;
        this.SpecialAbility=SpecialAbility;
        this.Village=Village;
        this.clans=new ArrayList<JsonClan>();
        this.ranks=new ArrayList<JsonRank>();
    }
    public String getName()
    {
        return name;
    }
    public String getNature()
    {
        return nature;
    }
    public int getCountOfMis()
    {
        return CountOfMis;
    }
    public String getSpecialAbility()
    {
        return SpecialAbility;
    }
    public String getVillage()
    {
        return Village;
    }

    public ArrayList<JsonRank> getRanks() {
        return ranks;
    }

    public ArrayList<JsonClan> getClans() { return clans; }


    public void setName(String name) {
        this.name = name;
    }
    public void setNature(String nature) {
        this.nature = nature;
    }
    public void setCountOfMis(int CountOfMis) {
        this.CountOfMis = CountOfMis;
    }
    public void setSpecialAbility(String SpecialAbility) {
        this.SpecialAbility = SpecialAbility;
    }
    public void setVillage(String Village) {
        this.Village = Village;
    }
    public void addClan(String name){
        clans.add(new JsonClan(name));
    }
    public void addRank(String name){
        ranks.add(new JsonRank(name));
    }
}
