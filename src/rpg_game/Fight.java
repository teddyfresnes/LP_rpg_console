package rpg_game;

import java.util.Scanner;

public class Fight
{
    public Player joueur;
    public Monster monster;
    
    // variables hp
    public int player_hp;
    public int monster_hp;

    
    public Fight(Player joueur, Monster monster)
    {
        this.joueur = joueur;
        this.monster = monster;
        
        this.player_hp = joueur.hp;
        this.monster_hp = monster.hp;
    }

    
    public boolean start()
    {
    	boolean done = false;
    	
        System.out.println("Vous avez rencontré un "+monster.type);
        
        while (!done)
        {
        	System.out.println("");
        	System.out.println("");
		    System.out.println("\t"+monster.type+" lvl "+monster.level+" ("+monster.damage+"⚔ "+monster_hp+"/"+monster.hp+"❤)");
		    System.out.println("----------------------------------------");
		    System.out.println(monster.art);
		    System.out.println("----------------- ＶＳ -------------------");
			System.out.println("\t"+joueur.category+" "+joueur.username+" ("+(joueur.damage+joueur.selectedWeapon.damage())+"⚔ "+player_hp+"/"+joueur.hp+"❤)");
			System.out.println("----------------------------------------");
			System.out.println("[1] Attaquer");
			System.out.println("[2] Fuir (perte d'HP définif et d'argent)");
			System.out.print("\n\nQue voulez vous faire?\n> ");
		    Scanner input = new Scanner(System.in);
		    String choice = input.nextLine();
		    clear();
		    switch(choice)
		    {
		    	case "1":
		    		player_hp = player_hp - monster.damage;
		    		monster_hp = monster_hp - (joueur.damage + joueur.selectedWeapon.damage());
		    		System.out.println("Vous avez attaqué "+monster.type);
		    		System.out.println(monster.type+" vous a attaqué");
		    		if (monster_hp <= 0) // victoire
		    		{
		    			// on gagne de l'argent et de l'exp en fonction de la puissance de l'ennemi
		    			int money_reward = (int) (((monster.damage*7 + monster.hp)/5)*joueur.money_multiplier);
		    			int exp_reward = (int) (((monster.damage*7 + monster.hp)/3)*joueur.exp_multiplier);
		    			joueur.money = joueur.money+money_reward;
		    			joueur.add_exp(exp_reward);
		    			System.out.println("Vous avez gagné le combat (+"+money_reward+"💲 | "+exp_reward+"xp)");
		    			return true;
		    		}
		    		else if (player_hp <= 0) // defaite
		    		{
		    			System.out.println("Vous avez perdu le combat.");
		    			joueur.hp = 0;
		    			return false;
		    		}
		    		break;
		    		
		    		
		    	case "2": // FUITE
		    		if (joueur.hp < 6)
		    		{
		    			System.out.println("Trop faible pour vous enfuir, l'ennemi vous a terrasé.");
		    			return false;
		    		}
		    		else
		    		{
			    		System.out.println("Vous vous êtes enfui, laissant derrière vous argent et cicatrices...");
		    			joueur.money = 0; // perte de l'argent
		    			joueur.hp = (int) (joueur.hp - (joueur.hp*0.25)); // perte d'un quart des hp definitevement
		    			return true;
		    		}

		    		
		    	default:
		    		//pass
		    }
        }
        return true;
    }
    
	public static void clear()
	{
	    //System.out.print("\033[H\033[2J");  
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		//System.out.flush();
		
	}
}