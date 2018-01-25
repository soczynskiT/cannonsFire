import java.util.*;

class Cannon {
    private boolean loaded;

    public Cannon(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void fire() {
        boolean readyForFireProcess = true;
        while (readyForFireProcess) {
            if (loaded) {
                System.out.println("Fire !!!");
                this.setLoaded(false);
                readyForFireProcess = false;
            } else {
                System.out.println("Cannon is empty, reloading...");
                this.setLoaded(true);
            }
        }
    }

    @Override
    public String toString() {
        String toString;
        if (this.loaded) {
            toString = "Cannon loaded. Ready to fire";
        } else {
            toString = "Cannon is empty...";
        }
        return toString;
    }
}

class CannonListProcessor {
    final private Random random = new Random();

    public LinkedList<Cannon> generateRandomCannonList(int noOfCannons) {
        LinkedList<Cannon> cannonList = new LinkedList<>();
        for (int i = 0; i < noOfCannons; i++) {
            Cannon aCannon = new Cannon(random.nextBoolean());
            cannonList.add(aCannon);
        }
        return cannonList;
    }

    public void reloadAllCannonsIn(LinkedList<Cannon> cannonList) {
        for (Cannon aCannon : cannonList) {
            aCannon.setLoaded(true);
        }
    }

    public int checkNoOfReadyCannons(LinkedList<Cannon> aCannonList) {
        int noOfReadyCannons = 0;
        for (Cannon checkedCannon : aCannonList) {
            if (checkedCannon.isLoaded()) {
                noOfReadyCannons++;
            }
        }
        return noOfReadyCannons;
    }

    public void aSalvoShot(LinkedList<Cannon> aCannonLIst) {
        int i = 0;
        while (i < random.nextInt(aCannonLIst.size())) {
            int noOfCannonToShot = random.nextInt(aCannonLIst.size());
            if (aCannonLIst.get(noOfCannonToShot).isLoaded()) {
                aCannonLIst.get(noOfCannonToShot).fire();
                i++;
            }
        }
    }
}

class CannonsApp {

    public static void main(String[] args) {
        final CannonListProcessor cannonListProcessor = new CannonListProcessor();

        final LinkedList<Cannon> aCannonList = cannonListProcessor.generateRandomCannonList(30);
        for (Cannon aCannon : aCannonList) {
            System.out.println(aCannon);
        }
        System.out.println("\nFire first cannon...!");
        aCannonList.getFirst().fire();

        System.out.println("\nNo of ready cannons is " + cannonListProcessor.checkNoOfReadyCannons(aCannonList) + "/" + (aCannonList.size()));

        System.out.println("\npreparing to salvo shot...\nReloading all cannons in progress...");
        cannonListProcessor.reloadAllCannonsIn(aCannonList);
        System.out.println("\nNo of ready cannons is " + cannonListProcessor.checkNoOfReadyCannons(aCannonList) + "/" + (aCannonList.size()));

        System.out.println("\nA salvo shot...");
        cannonListProcessor.aSalvoShot(aCannonList);
        System.out.println("\n");

        for (Cannon aCannon : aCannonList) {
            System.out.println(aCannon);
        }
    }
}
