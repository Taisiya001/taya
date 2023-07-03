import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery {
    private FileOperation fileOperation;
    private FileOperation prizeFileOperation;

    public Lottery(FileOperation fileOperation, FileOperation prizeFileOperation) {
        this.fileOperation = fileOperation;
        this.prizeFileOperation = prizeFileOperation;

    }

    public Toy drawToys() {
        List<Toy> toyList = fileOperation.readAllLines();
        if (toyList.size() != 0) {
            Random random = new Random();
            ArrayList<Integer> chance = new ArrayList<>();
            Toy prizeToy = null;
            int countToysChance = 0;
            for (Toy toy : toyList) {
                countToysChance += toy.getChance();
                chance.add(toy.getChance());
            }

            int index = random.nextInt(countToysChance);
            for (int i = 0; i < toyList.size(); i++) {
                index -= chance.get(i);
                if (index < 0) {
                    prizeToy = toyList.get(i);
                    if (prizeToy.getCount() > 1)
                        prizeToy.setCount(prizeToy.getCount() - 1);
                    else if (prizeToy.getCount() <= 1) {
                        toyList.remove(prizeToy);
                    }
                    fileOperation.saveAllLines(toyList);
                    System.out.printf("YOUR PRIZE %s, PRIZE ADD TO PRIZELIST", prizeToy.getName());
                    return prizeToy;
                }
            }
        }
        System.out.println("have not toys");
        return null;
    }

    public List<Toy> addToPrizeList() {
        List<Toy> prizeList = prizeFileOperation.readAllLines();
        prizeList.add(drawToys());
        prizeFileOperation.saveAllLines(prizeList);
        return prizeList;
    }

    public void remotePrize(List<Toy> toyList) {
        toyList.remove(0);
    }
}
