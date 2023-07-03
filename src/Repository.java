import java.util.List;

public class Repository {
    private FileOperation fileOperation;

    public Repository(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public void addToy(Toy toy) {
        List<Toy> toyList = fileOperation.readAllLines();
        if (searchEqualsOrReturnList(toy, toyList) == null) {
            int maxId = 0;
            for (Toy toyInList : toyList) {
                int id = toyInList.getId();
                if (maxId < id) {
                    maxId = id;
                }
            }
            int newId = maxId + 1;
            toy.setId(newId);
            toy.setCount(1);
            toyList.add(toy);
            fileOperation.saveAllLines(toyList);
        }
    }

    public void upgradeToyChance(String toyName, int newChance) {
        List<Toy> toyList = fileOperation.readAllLines();
        for (Toy toy : toyList) {
            if (toy.getName().equals(toyName)) {
                toy.setChance(newChance);
            }
        }
        fileOperation.saveAllLines(toyList);
    }

    public List<Toy> searchEqualsOrReturnList(Toy toy, List<Toy> toyList) {
        // List<Toy> toyList = fileOperation.readToyList();
        for (Toy toyInList : toyList) {
            if (toyInList.getName().equals(toy.getName())) {
                toyInList.setCount(toyInList.getCount() + 1);
                fileOperation.saveAllLines(toyList);
                return toyList;
            }
        }
        return null;
    }

    public List<Toy> getAllToys() {
        return fileOperation.readAllLines();
    }

}
