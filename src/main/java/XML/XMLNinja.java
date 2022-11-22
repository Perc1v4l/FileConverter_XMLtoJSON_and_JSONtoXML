package XML;
public class XMLNinja {
    private String name;
    private String nature;
    private int CountOfMis;
    private String SpecialAbility;

    public XMLNinja(String name, String nature, int CountOfMis, String SpecialAbility)
    {
        this.name = name;
        this.nature = nature;
        this.CountOfMis = CountOfMis;
        this.SpecialAbility=SpecialAbility;
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

}
