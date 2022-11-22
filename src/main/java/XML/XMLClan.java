package XML;
import java.util.ArrayList;
public class XMLClan {
    private String name;
    private ArrayList<XMLRank> ranks;

    public XMLClan(String name) {
        this.name = name;
        this.ranks = new ArrayList<>();
    }
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArrayList<XMLRank> getRanks() {
        return ranks;
    }

    public void addRank(String name)
    {
        ranks.add(new XMLRank(name));
    }

    public int returnLength(){
        return ranks.size();
    }
}
