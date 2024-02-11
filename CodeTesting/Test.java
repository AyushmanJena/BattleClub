import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Warrior plr = new Warrior();
        Warrior opp = new Warrior();
        int[][] plr2 = {
                {1, 30, 30, 0},
                {3, 30, 30, 0}
        };
        int[][] opp2 = {
                {3, 30, 30, 0},
                {1, 30, 30, 0}
        };
        plr.arr = plr2;
        opp.arr = opp2;

        while(true){
            chooseWarrior(opp);
            chooseWarrior(plr);
        }
    }

    public static void chooseWarrior(Warrior plr){
        Scanner sc = new Scanner(System.in);

        int[] tempArr = new int[2];

        int count = 1;
        for(int i = 0; i<2; i++){
            if(plr.arr[i][0] == 1){
                System.out.println(count++ +" Jett");
                tempArr[i] = 1;
            }
            else if(plr.arr[i][0] == 2){
                System.out.println(count++ +" Omen");
                tempArr[i] = 2;
            }
            else if(plr.arr[i][0] == 3){
                System.out.println(count++ +" Sage");
                tempArr[i] = 3;
            }
        }

        System.out.print(">>> ");
        int ch = sc.nextInt();

        for(int i = 1; i<=2; i++){
            if(ch == i && tempArr[i-1] == 1){
                System.out.println("jett attack");
            }
            if(ch == i && tempArr[i-1] == 2){
                System.out.println("omen attack");
            }
            if(ch == i && tempArr[i-1] == 3){
                System.out.println("sage attack");
            }
        }
    }

    public static int chooseOpponent(Warrior opp){

        // input by user : 1 means jett, 2 means sage
        // output : returns the index of the agent if sage is on 0 returns 0


        Scanner sc = new Scanner(System.in);
        int[] war = new int[2];
        String[] warName = new String[2];
        int k = 0;
        for(int i = 0;i<2; i++){
            if(opp.arr[k][0] == 1){
                war[k] = 1;
                warName[k] = "Jett";
                k++;
            }
            else if(opp.arr[k][0] == 2){
                war[k] = 2;
                warName[k] = "Omen";
                k++;
            }
            else if(opp.arr[k][0] == 3){
                war[k] = 3;
                warName[k] = "Sage";
                k++;
            }
        }
        System.out.println("Choose Opponent : ");
        Warrior.display(opp);
        System.out.print("1 : "+warName[0]+"\n2 : "+warName[1]+"\n >>> ");
        int ch = sc.nextInt();

        return ch -1;
    }
}
