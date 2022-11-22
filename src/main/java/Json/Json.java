package Json;
import java.util.ArrayList;
public class Json {
    private ArrayList<JsonNinja> ninjas;

    public Json() {
        this.ninjas = new ArrayList<>();
    }

    public ArrayList<JsonNinja> getNinjas() {
        return ninjas;
    }

    public void addNinja(String name, String nature, int CountOfMis, String SpecialAbility, String Village) {
        ninjas.add(new JsonNinja(name, nature, CountOfMis, SpecialAbility, Village));
    }

    public int returnLength() {
        return ninjas.size();
    }

    public JsonNinja returnLastNinja() {
        if (ninjas.size() > 0)
            return ninjas.get(ninjas.size() - 1);
        else
            return null;
    }
}
