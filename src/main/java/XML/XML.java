package XML;
import java.util.ArrayList;
public class XML {
    private ArrayList<XMLVillage> villages;

    public XML(){
        villages = new ArrayList<XMLVillage>();
    }

    public void addVillage(String name){
        villages.add(new XMLVillage(name));
    }

    public ArrayList<XMLVillage> getVillages(){
        return villages;
    }

    public int returnLength(){
        return villages.size();
    }
}
