public class Main {
    public static void main(String[] args) {
        play();
    }
    public static void play(){
        Warrior war;
        System.out.println("Player 1");
        Warrior p1 = Warrior.selectWarriors();
        System.out.println("Player 2");
        Warrior p2 = Warrior.selectWarriors();

        while(true){
            if(p1.arr[0][1] < 0 && p1.arr[1][1] < 0){
                System.out.println("===PLAYER 2 WINS===");
                break;
            }
            else if(p2.arr[0][1] < 0 && p2.arr[1][1] < 0){
                System.out.println("===PLAYER 1 WINS===");
                break;
            }
            Warrior.display(p1);
            Warrior.display(p2);
            Warrior.fightWarrior(p1, p2);
            Warrior.display(p1);
            Warrior.display(p2);
            Warrior.fightWarrior(p2, p1);
        }
    }
}