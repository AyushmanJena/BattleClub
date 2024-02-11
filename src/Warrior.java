import java.util.Scanner;

public class Warrior {
    String plrName;
    int[][] arr = new int[2][4];// | agent no. | hp | ability points | bonus points |

    public static Warrior selectWarriors(){
        Warrior player = new Warrior();
        Scanner sc = new Scanner(System.in);
        System.out.print("Player Name : ");
        player.plrName = sc.nextLine();
        System.out.println("Select Warriors : ");
        System.out.println("1 : Jett <Attacker>\n" +
                "2 : Omen <Defender>\n" +
                "3 : Sage <Medic>");
        System.out.print("You select : ");
        player.arr[0][0] = sc.nextInt();
        player.arr[1][0] = sc.nextInt();
        for(int i = 0; i< 2; i++){
            player.arr[i][1] = 30;
            player.arr[i][2] = 0;
        }
        return player;
    }

    public static void fightWarrior(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);
        int[] tempArr = new int[2];
        System.out.println( "\n<"+ plr.plrName +">");

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
        System.out.println("Choose your Warrior");
        System.out.print(">>> ");
        int ch = sc.nextInt();

        for(int i = 1; i<=2; i++){
            if(ch == i && tempArr[i-1] == 1){
                Jett.attack(plr, opp);
            }
            if(ch == i && tempArr[i-1] == 2){
                Omen.attack(plr, opp);
            }
            if(ch == i && tempArr[i-1] == 3){
                Sage.attack(plr, opp);
            }
        }
    }

    public static int chooseOpponent(Warrior opp){
        Scanner sc = new Scanner(System.in);
        Warrior.display(opp);
        System.out.println("Choose Opponent : ");
        System.out.print(">>> ");
        int ch = sc.nextInt();
        return ch-1;
    }
    public static int chooseWarriorOppOrAlly(Warrior obj, boolean choice){ // true : plr, false : opp
        Scanner sc = new Scanner(System.in);

        if(choice){
            Warrior.display(obj);
            System.out.println("Choose Warrior : ");
        }
        else {
            Warrior.display(obj);
            System.out.println("Choose Opponent : ");
        }
        System.out.print(">>> ");
        int ch = sc.nextInt();
        return ch-1;
    }

    public static void display(Warrior obj){
        int hp, ap;
        System.out.println(obj.plrName + " STATS : ");
        for(int i = 0; i<2; i++){
            hp = obj.arr[i][1];
            ap = obj.arr[i][2];
            int pos = i +1;
            switch(obj.arr[i][0]){
                case 1:
                    System.out.println(pos+ " JETT : ");
                    break;
                case 2 :
                    System.out.println(pos+" OMEN : ");
                    break;
                case 3 :
                    System.out.println(pos+ " SAGE : ");
                    break;
                default:
                    System.out.println("Warrior doesn't exist");
            }
            for(int j = 0; j<hp; j++){
                System.out.print("|");
            }
            System.out.println("   HP : "+ hp);
            for(int j = 0; j<ap; j++){
                System.out.print("|");
            }
            System.out.println("   AP : "+ ap);
        }
    }
}

class Util{
    // isDead()
    // lost() (move from Warrior)

}

class Jett{ // Attacker
    public static void attack(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);


        while(true){
            boolean confirm = false;
            System.out.println("Choose JETT Move : ");
            System.out.println("1 : Attack\n2 : Ability\n3 : Defend");
            System.out.print(">>> ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    confirm = physical(plr,opp);
                    break;
                case 2:
                    confirm = ability(plr, opp);
                    break;
                case 3:
                    confirm = defend(plr);
                    break;
                default:
                    System.out.println("No such Move!");
                    //return false;
            }
            if(confirm){
                break;
            }
        }

    }
    public static boolean physical(Warrior plr, Warrior opp){ // opt : which warrior of opponent
        Scanner sc = new Scanner(System.in);
        System.out.println("Physical Attacks : ");
        System.out.println("1 : Kunai (4)(+4ap)\n 2 : Katana  (6)(+2ap)");
        System.out.print(">>> ");
        int atk = sc.nextInt();
        int opt = Warrior.chooseWarriorOppOrAlly(opp, false);
        int dmg = 0;
        int status = opp.arr[opt][3];
        int temp = 0;
        if(plr.arr[0][0] != 1){ // find location of jett
            temp = 1;
        }
        switch (atk){
            case 1:
                System.out.println("Kunai");
                dmg = 4;
                plr.arr[temp][2] += 4;
                if(plr.arr[temp][2] > 30){ // make sure its not over limit
                    plr.arr[temp][2] = 30;
                }
                break;
            case 2 :
                System.out.println("Katana Slash");
                dmg = 6;
                plr.arr[temp][2] += 2;
                if(plr.arr[temp][2] > 30){ // make sure its not over limit
                    plr.arr[temp][2] = 30;
                }
                break;
            default:
                System.out.println("Cannot Attack!");
                return false; //to be tested to get to previous selection
        }
        if(status != 0){
            dmg = dmg - (dmg*status/100);
            opp.arr[opt][3] = 0;
        }
        opp.arr[opt][1] = opp.arr[opt][1] - dmg;
        return true;
    }
    public static boolean ability(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);
        System.out.println("Special Abilities : ");
        System.out.println("1 : Dual Wield ");
        System.out.println("2 : Ultimate <Ultimate>");
        System.out.print(">>> ");
        int atk = sc.nextInt();
        int plrA = 0; //// plrA : Index of the Player which has jett
        if(plr.arr[0][0] != 2){
            plrA = 1;
        }
        switch ((atk)){
            case 1:
                int opt = Warrior.chooseWarriorOppOrAlly(opp, false);
                System.out.println("Dual Wield");
                opp.arr[opt][1] = opp.arr[opt][1] - 10;
                plr.arr[plrA][2] = plr.arr[plrA][2] - 8;
                break;
            case 2:
                System.out.println("Ultimate");
                opp.arr[0][1] = opp.arr[0][1] - 14;
                opp.arr[1][1] = opp.arr[1][1] - 14;
                plr.arr[plrA][2] = plr.arr[plrA][2] - 20;
                break;
            default:
                System.out.println("Cannot Use Ability !");
                return false;
        }
        return true;
    }
    public static boolean defend(Warrior plr){
        Scanner sc = new Scanner(System.in);
        System.out.println("Defend : ");
        System.out.println("1 : 20% less damage next round");
        System.out.print("choice : ");
        int atk = sc.nextInt();
        int temp = 0;
        if(plr.arr[0][0] != 1){ // find location of jett
            temp = 1;
        }
        if(atk == 1){
            System.out.println("Defend");
            plr.arr[temp][3] = 20;
        }
        else{
            System.out.println("No such Defence");
            return false;
        }
        return true;
    }
}

class Omen{ // Defender
    public static void attack(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose OMEN Move : ");
        System.out.println("1 : Attack\n2 : Ability\n3 : Defend");
        System.out.print("Choice : ");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                physical(plr,opp);
                break;
            case 2:
                ability(plr, opp);
                break;
            case 3:
                defend(plr);
                break;
            default:
                System.out.println("No such Move!");
        }
    }
    public static void physical(Warrior plr, Warrior opp){ // opt : which warrior of opponent
        Scanner sc = new Scanner(System.in);
        System.out.println("Physical Attacks : ");
        System.out.println("1 : Hammer Smash");
        System.out.print(">>> ");
        int atk = sc.nextInt();
        int opt = Warrior.chooseWarriorOppOrAlly(opp, false);
        int dmg = 0;
        int status = opp.arr[opt][3];
        int temp = 0;
        if(plr.arr[0][0] != 2){ // find location of omen
            temp = 1;
        }
        switch (atk){
            case 1:
                System.out.println("Hammer Smash");
                dmg = 5;
                plr.arr[temp][2] += 3;
                if(plr.arr[temp][2] > 30){
                    plr.arr[temp][2] = 30;
                }
                break;
            default:
                System.out.println("Cannot Attack!");
        }
        if(status != 0){
            dmg = dmg - (dmg*status/100);
            opp.arr[opt][3] = 0;
        }
        opp.arr[opt][1] = opp.arr[opt][1] - dmg;
    }
    public static void ability(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);
        System.out.println("Special Abilities : ");
        System.out.println("1 : OverHeal ");
        System.out.println("2 : Shatter Fate <Ultimate>");
        System.out.print(">>> ");
        int atk = sc.nextInt();
        int plrA = 0; //// plrA : Index of the Player which has Omen
        if(plr.arr[0][0] != 2){
            plrA = 1;
        }
        // display(); display agents with hp
        switch ((atk)){
            case 1:
                System.out.println("OverHeal");
                plr.arr[plrA][1] += 8;
                plr.arr[plrA][2] -= 6;
                break;
            case 2:
                System.out.println("Ultimate");
                opp.arr[0][1] -= 10;
                opp.arr[1][1] -= 10;
                plr.arr[plrA][2] -= 18;
                break;
            default:
                System.out.println("Cannot Use Ability !");
        }
    }
    public static void defend(Warrior plr){
        Scanner sc = new Scanner(System.in);
        System.out.println("Defend : ");
        System.out.println("1 : Ability Points Boost (+5)");
        System.out.println("2 : 35% less Damage Next Round");
        System.out.print("choice : ");
        int atk = sc.nextInt();
        int plrA = 0;
        if(plr.arr[0][0] != 2){ // find location of Omen
            plrA = 1;
        }
        switch(atk){
            case 1 :
                System.out.println("Ability points boosted");
                plr.arr[plrA][2] += 5;
                if(plr.arr[plrA][2] >=  30){
                    plr.arr[plrA][2] = 30;
                }
                break;
            case 2 :
                System.out.println("35% less damage next round");
                plr.arr[plrA][3] = 35;
                break;
            default:
                System.out.println("So Such Defence ! ");
        }
    }
}

class Sage{ // Support
    public static void attack(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose SAGE Move : ");
        System.out.println("1 : Attack\n2 : Ability\n3 : Defend");
        System.out.print(">>> ");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                physical(plr,opp);
                break;
            case 2:
                ability(plr, opp);
                break;
            case 3:
                defend(plr);
                break;
            default:
                System.out.println("No such Move!");
        }
    }
    public static void physical(Warrior plr, Warrior opp){ // opt : which warrior of opponent
        Scanner sc = new Scanner(System.in);
        System.out.println("Physical Attacks : ");
        System.out.println("1 : Arrow");
        System.out.print(">>> ");
        int atk = sc.nextInt();
        int opt = Warrior.chooseWarriorOppOrAlly(opp, false);
        int dmg = 0;
        int status = opp.arr[opt][3];
        int temp = 0;
        if(plr.arr[0][0] != 3){ // find location of SAGE
            temp = 1;
        }
        switch (atk){
            case 1:
                System.out.println("Arrow");
                dmg = 4;
                plr.arr[temp][2] += 3;
                break;
            default:
                System.out.println("Cannot Attack!");
        }
        if(status != 0){
            dmg = dmg - (dmg*status/100);
            opp.arr[opt][3] = 0;
        }
        opp.arr[opt][1] = opp.arr[opt][1] - dmg;
    }
    public static void ability(Warrior plr, Warrior opp){
        Scanner sc = new Scanner(System.in);
        System.out.println("Special Abilities : ");
        System.out.println("1 : Quick Heal (+6hp) (-3ap)");
        System.out.println("2 : Heal (+12hp)(-6ap)");
        System.out.println("3 : Rebibe / Max hp<Ultimate>");
        System.out.print(">>> ");
        int atk = sc.nextInt();
        int plrA = 0; /// plrA : Index of the Player which has Sage
        if(plr.arr[0][0] != 3){
            plrA = 1;
        }
        Warrior.display(plr);
        switch ((atk)){
            case 1:
                System.out.println("Choose Teammate to Heal : ");
                int plrB = Warrior.chooseWarriorOppOrAlly(plr, true);
                plr.arr[plrB][1] += 6;
                // making sure he's not overhealed
                if(plr.arr[plrB][1] > 30){
                    plr.arr[plrB][1] = 30;
                }
                plr.arr[plrA][2] -= 3;
                break;
            case 2 :
                System.out.println("Choose Teammate to Heal : ");
                int temp = Warrior.chooseWarriorOppOrAlly(plr, true);
                plr.arr[temp][1] += 12;
                // making sure he's not overhealed
                if(plr.arr[temp][1] > 30){
                    plr.arr[temp][1] = 30;
                }
                plr.arr[plrA][2] -= 6;
                break;
            case 3:
                System.out.println("Ultimate");
                int temp2 = 0;
                if(plrA == 0){
                    temp2 = 1;
                }
                plr.arr[temp2][1] = 30;
                plr.arr[plrA][2] = plr.arr[plrA][2] - 22;
                break;
            default:
                System.out.println("Cannot Use Ability !");
        }
    }
    public static void defend(Warrior plr){
        Scanner sc = new Scanner(System.in);
        System.out.println("Defend : ");
        System.out.println("1 : Shield (50% less dmg)");
        System.out.print("choice : ");
        int atk = sc.nextInt();
        int plrA = 0;
        if(plr.arr[0][0] != 3){ // find location of Sage
            plrA = 1;
        }
        switch(atk){
            case 1 :
                System.out.println("Shield Activated");
                plr.arr[plrA][3] = 50;
                break;
            default:
                System.out.println("So Such Defence ! ");
        }
    }
}
