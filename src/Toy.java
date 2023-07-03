
public class Toy {
    private int id;
    private String name;
    private int count;
    private int chance;

    public Toy(String name, int chance) {
        this.name = name;
        this.chance = chance;
    }

    public Toy(int id, String name, int count, int chance) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.chance = chance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        if (chance > 100 || chance < 1)
            throw new IllegalStateException("chance must be in interval 1 and 100");
        this.chance = chance;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%d,%d", id, name, count, chance);
    }

}
