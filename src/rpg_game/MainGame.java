package rpg_game;


import java.util.*;


import characters.*;
import weapons.Hammer;

import java.io.IOException;
import java.util.Random;


public class MainGame
{
	public static void main(String[] args)
	{
        
        Scanner input;
        String choice;
        
        /* CREATION JOUEUR */
		// pseudo
	    input = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("\nBienvenue dans Sorbonne RPG\n");
	    System.out.print("\nEntrez votrez nom :\n> ");
	    String username = input.nextLine();
	    
	    
	    // choix de la catégorie du perso
	    input = new Scanner(System.in);  // Create a Scanner object
	    System.out.print("\n\n\n\n");
	    System.out.print("\n[1] Guerrier\t(Avantage de dégat)");
	    System.out.print("\n[2] Tank    \t(Avantage de vie)");
	    System.out.print("\n[3] Assassin\t(Avantage de dégat, malus de vie)");
	    System.out.print("\n[4] Gobelin \t(Avantage à l'argent)");
	    System.out.print("\n[5] Mage    \t(Avantage d'expérience)");
	    System.out.print("\nQuel personnage vous correspond le mieux :\n> ");
	    choice = input.nextLine();
	    Player joueur;
    	joueur = (choice.equals("1")) ? new Warrior(username) :
            (choice.equals("2")) ? new Tank(username) :
            (choice.equals("3")) ? new Assassin(username) :
            (choice.equals("4")) ? new Goblin(username) :
            (choice.equals("5")) ? new Mage(username) :
            new Player(username); // sans sous-classe si rien rentré
		
		
		// chargement de la carte
		Map carte = new Map("assets/maps/a.csv");
	    int[] spawnCoordinates = carte.search_spawn();
	    carte.playerX = spawnCoordinates[1];
	    carte.playerY = spawnCoordinates[0];
	    
		WeaponStore shop_spawn = new WeaponStore("Magasin du début",2,12);
		shop_spawn.remove(shop_spawn.stock.get(3)); shop_spawn.remove(shop_spawn.stock.get(2)); shop_spawn.remove(shop_spawn.stock.get(1)); // supprime armes par defaut
		shop_spawn.add(new Hammer("Massue", 75, 20));
		carte.add_shop(shop_spawn);
	    
		WeaponStore shop_default = new WeaponStore("Magasin 308",4,4);
		carte.add_shop(shop_default);
		
		carte.gen_monster(1, "Slime");
		carte.gen_monster(1, "Slime");
		carte.gen_monster(1, "Slime");
		carte.gen_monster(2, "Slime");
		carte.gen_monster(1, "Squelette");
		carte.gen_monster(1, "Squelette");
		carte.gen_monster(1, "Squelette");
		carte.gen_monster(2, "Squelette");
	    System.out.println("\n\n\n\n");
	    
	    
	    // affichage du menu
	    boolean done = false;
		while (!done)
		{
			clear();
			
			carte.display_legend();
			System.out.println("");
			carte.display_map();
			System.out.println("");
			System.out.println(joueur.category+" "+joueur.username+" ("+(joueur.damage+joueur.selectedWeapon.damage())+"⚔ "+joueur.hp+"❤)");
			System.out.println("----------------------------------------");
			System.out.println(" Or\t: "+joueur.money+"💲");
			System.out.println(" Lvl\t: "+joueur.level);
			System.out.println(" Exp\t: "+joueur.exp+"/"+joueur.exp_need);
			System.out.println(" Arme\t: "+joueur.selectedWeapon.name());
			System.out.println("----------------------------------------");
			System.out.println("[Z] Haut");
			System.out.println("[S] Bas");
			System.out.println("[Q] Gauche");
			System.out.println("[D] Droite");
			System.out.println("[1] Afficher mon sac");
			
		    input = new Scanner(System.in);
		    System.out.print("\n\nQue voulez vous faire?\n> ");
		    choice = input.nextLine();
		    
		    clear();
		    
		    String next_case_type = "none";
		    switch(choice.toUpperCase())
		    {
		    	case "Z":
		    		next_case_type = carte.move_player("UP");
		    		break;
		    	case "S":
		    		next_case_type = carte.move_player("DOWN");
		    		break;
		    	case "Q":
		    		next_case_type = carte.move_player("LEFT");
		    		break;
		    	case "D":
		    		next_case_type = carte.move_player("RIGHT");
		    		break;
		    	case "1":
		    		joueur.display_bag(); // pour equipe une arme
		    		wait_now();
		    		break;
		    	//default:
		    		//System.out.println("Cette option n'existe pas");
		    }
		    //wait_now();
		    if (next_case_type == "shop") // si la prochaine case est un magasin
		    {
		    	WeaponStore magasin = carte.getShopOnPlayer();
		    	magasin.displayTo(joueur);
		    	wait_now();
		    }
		    if (next_case_type == "monster") // si la prochaine case est un magasin
		    {
		    	Random r = new Random();
		    	Monster monster_case = carte.getEntityOnPlayer();
		    	Fight my_fight = new Fight(joueur, monster_case);
		    	clear();
		    	boolean alive = my_fight.start();
		    	carte.gen_monster(r.nextInt(1)+monster_case.level, monster_case.type); // regeneration du monstre sur la carte avec une chance de montée de niveau
		    	carte.remove_monster(monster_case);
		    	wait_now();
		    	if (alive == false)
		    	{
		    		done = true; // fin du jeu
		    		System.out.println("\nVous êtes mort\n");
		    	}
		    }
		    if (next_case_type == "next_map") // si la prochaine case est un magasin
		    {
		    	if (carte.map_path.equals("assets/maps/a.csv")) // début map b, fin du niveau a
		    	{
		    		carte = new Map("assets/maps/b.csv");
		    	    spawnCoordinates = carte.search_spawn();
		    	    carte.playerX = spawnCoordinates[1];
		    	    carte.playerY = spawnCoordinates[0];
		    	    
		    		shop_default = new WeaponStore("Magasin au milieu de nul part",17,7);
		    		carte.add_shop(shop_default);
		    		
		    		carte.gen_monster(2, "Slime");
		    		carte.gen_monster(2, "Slime");
		    		carte.gen_monster(3, "Slime");
		    		carte.gen_monster(3, "Slime");
		    		carte.gen_monster(3, "Slime");
		    		carte.gen_monster(1, "Squelette");
		    		carte.gen_monster(2, "Squelette");
		    		carte.gen_monster(3, "Squelette");
		    		carte.gen_monster(3, "Squelette");
		    	}
		    	else if (carte.map_path.equals("assets/maps/b.csv")) // début map c, fin du niveau b
		    	{
		    		carte = new Map("assets/maps/c.csv");
		    	    spawnCoordinates = carte.search_spawn();
		    	    carte.playerX = spawnCoordinates[1];
		    	    carte.playerY = spawnCoordinates[0];
		    		
		    		carte.gen_monster(1, "Boss");
		    	}
		    	else if (carte.map_path.equals("assets/maps/c.csv")) // fin du jeu
		    	{
		    		System.out.println("Félicitations, "+joueur.username+", vous avez terminé Sorbonne RPG avec "+joueur.mana+"xp accumulé.");
		    	}
		    }
		}
	}
	
	
	public static void clear()
	{
	    //System.out.print("\033[H\033[2J");  
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		//System.out.flush();
		
	}
	
	
	public static void wait_now()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("\nEntrez une touche pour continuer...");
	    input.nextLine();
	}
}
