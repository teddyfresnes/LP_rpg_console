package rpg_game;

import java.util.ArrayList;
import java.util.Scanner;

import weapons.Axe;
import weapons.Hammer;
import weapons.Knife;

public class WeaponStore
{
	ArrayList<Weapon> stock;
	private String shop_name;
	
	public int shopX;
	public int shopY;
	
	
	public WeaponStore(String shop_name, int X, int Y)
	{
		this.shop_name = shop_name;
		this.shopX = X;
		this.shopY = Y;
		
		this.stock = new ArrayList<Weapon>();
		
		// Armes ajoutés par défaut à la création de chaque shop
		this.stock.add(new Knife("Cutter", 10, 3));
		this.stock.add(new Hammer("Marteau", 20, 5));
		this.stock.add(new Axe("Hachette", 35, 15));
		this.stock.add(new Knife("Couteau de chasse", 100, 35));
		
		// on peut changer l'ascii par défaut, par exemple pour le cutter
		this.stock.get(0).define_ascii_art("    _____________________________ _______________________\r\n  .'                             |   |-|-|-|-|-|        |\r\n.'_______________________________|______________________|");
	}
	
	
	public void displayTo(Player player)
	{
		// AFFICHAGE
		System.out.println("Bienvenue dans "+this.shop_name+", que voulez vous achetez?\n");
	    for (int i = 0; i < stock.size(); i++)
	    {
	        Weapon item_stock = stock.get(i);
	        System.out.print("["+(i+1)+"] -	"+item_stock.name()+" ("+item_stock.price()+"💲 | "+item_stock.damage()+"⚔)\n");
	        System.out.println(item_stock.ascii()+"\n");
	    }
	    
	    // On laisse l'user choisir son arme
	    int choice = -1;
	    
	    System.out.print("\n> ");
        try 
        {
		    Scanner input = new Scanner(System.in);
            choice = input.nextInt()-1;
        }
        catch (java.util.InputMismatchException e) {} // pass
	    
        if (choice >= 0 && choice < stock.size())
        {
        	player.buyWeapon(stock.get(choice));
        }
        else
        {
        	System.out.println("Erreur, sortie du magasin");
        }
	}
	
	
	// Efface tout le stock
	public void reset()
	{
		stock.clear();
	}
	
	
	// Ajoute l'item s'il n'existe pas
	public void add(Weapon item)
	{
	    for (int i = 0; i < stock.size(); i++)
	    {
	        Weapon item_stock = stock.get(i);
	        if (item.name().equals(item_stock.name()))
	        {
	            return;
	        }
	    }
	    stock.add(item);
	}
	
	
	// Efface l'item en cherchant son nom
	public void remove(Weapon item)
	{
	    for (int i = 0; i < stock.size(); i++)
	    {
	        Weapon item_stock = stock.get(i);
	        if (item.name().equals(item_stock.name()))
	        {
	            stock.remove(i);
	        }
	    }
	}
}
