package XML;
import java.util.ArrayList;

public class XMLRank {
    private String name;
    private ArrayList<XMLNinja> ninjas;

    public XMLRank(String name) {
        this.name = name;
        this.ninjas = new ArrayList<>();
    }
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArrayList<XMLNinja> getNinjas() {
        return ninjas;
    }

    public void addNinja(String name, String nature, int CountOfMis, String SpecialAbility){
        ninjas.add(new XMLNinja(name, nature, CountOfMis, SpecialAbility));
    }

    public int returnLength(){
        return ninjas.size();
    }
}
