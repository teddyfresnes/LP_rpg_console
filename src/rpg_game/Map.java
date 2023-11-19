package rpg_game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//on reutilise le lecteur de fichiers pour ouvrir les maps
import lecteur_de_fichiers.FilereaderTextCsv;


public class Map
{
	String map_path;
	String[][] mapData;
	List<WeaponStore> shops;
	List<Monster> monsters;
	
	int playerX;
	int playerY;
	
	public Map(String map_default)
	{
		this.map_path = map_default;
		
		FilereaderTextCsv csvReader = new FilereaderTextCsv(map_path);
		mapData = csvReader.f_import_csv();
		
		this.shops = new ArrayList<>();
		this.monsters = new ArrayList<>();
	}
	

    public WeaponStore getShopOnPlayer()
    {
        for (WeaponStore shop : shops)
        {
            //System.out.println("shop.shopX: " + shop.shopX);
            //System.out.println("shop.shopY: " + shop.shopY);
            //System.out.println("this.playerX: " + this.playerX);
            //System.out.println("this.playerY: " + this.playerY);
            if (shop.shopX == this.playerX && shop.shopY == this.playerY)
            {
                return shop;
            }
        }
        return null; // Aucun magasin trouvé sur la case spécifiée
    }
    
    
    public Monster getEntityOnPlayer()
    {
        for (Monster entity : monsters)
        {
            //System.out.println("monster.x : " + entity.x);
            //System.out.println("monster.y: " + entity.y);
            //System.out.println("this.playerX: " + this.playerX);
            //System.out.println("this.playerY: " + this.playerY);
            if (entity.x == this.playerX && entity.y  == this.playerY)
            {
            	//System.out.println("found it");
                return entity;
            }
        }
        return null;
    }
	
	
	public void add_shop(WeaponStore shop) // magasin plage de 100 à 200
	{
		mapData[shop.shopX][shop.shopY] = "3";
		shops.add(shop);
	}
	
	
    public int[] search_spawn()
    {
        for (int i = 0; i < this.mapData.length; i++)
        {
            for (int j = 0; j < this.mapData[i].length; j++)
            {
                if (Integer.parseInt(this.mapData[i][j]) == 2)
                {
                    return new int[]{i, j};
                }
            }
        }
        // non trouvé on renvoit des coordonnées par défaut
        return new int[]{1, 1};
    }
	
	
	public void display_map()
	{
	    for (int i = 0; i < this.mapData.length; i++)
	    {
	        for (int j = 0; j < this.mapData[i].length; j++)
	        {
	            int cellValue = Integer.parseInt(this.mapData[i][j]);
	            char letter = getLetterFromValue(cellValue);
	            if (i == playerY && j == playerX)
	            {
	                System.out.print('웃');
	            }
	            else
	            {
	            	System.out.print(letter);
	            }
	        }
	        System.out.println(); // saut de ligne changement de tableaux de rows
	    }
	}


    private char getLetterFromValue(int value)
    {
        switch (value)
        {
            case 0: // empty case
                return '⬜';
            case 1: // wall
                return '⬛';
            case 2: // spawn
                return '□';
            case 3: // shopp
                return '☆';
            case 4: // sortie
            	return '➚';
            case 5: // monstre
            	return '▣';
            default:
                return '?'; // valeur inconnu
        }
	}
    
    
    public void display_legend()
    {
    	System.out.println("웃 : emplacement joueur");
    	System.out.println("☆ : magasin");
    	System.out.println("▣ : monstre");
    	System.out.println("➚ : suite du niveau");
    	System.out.println("⬜ : vide");
    	System.out.println("⬛ : mur incassable");
    }
    
    
    public String move_player(String direction)
    {
    	String case_you_go = "normal";
    	switch (direction) 
    	{
    	    case "UP":
    	        if (playerY > 0) 
    	        {
    	            int cell_value = Integer.parseInt(mapData[playerY - 1][playerX]); 
    	            if (cell_value == 3) // si c'est un magasin
    	            {
    	            	case_you_go = "shop";
    	            } 
    	            if (cell_value == 5) // si c'est un monstre
    	            {
    	            	case_you_go = "monster";
    	            } 
    	            if (cell_value != 1) // si c'est un mur
    	            {
    	                playerY--;
    	            }
    	        }
    	        break;
    	    case "DOWN":
    	        if (playerY < mapData.length - 1) 
    	        {
    	            int cell_value = Integer.parseInt(mapData[playerY + 1][playerX]);
    	            if (cell_value == 3) 
    	            {
    	            	case_you_go = "shop";
    	            } 
    	            if (cell_value == 5)
    	            {
    	            	case_you_go = "monster";
    	            } 
    	            if (cell_value != 1) 
    	            {
    	                playerY++;
    	            }
    	        }
    	        break;
    	    case "LEFT":
    	        if (playerX > 0) 
    	        {
    	            int cell_value = Integer.parseInt(mapData[playerY][playerX - 1]);
    	            if (cell_value == 3) 
    	            {
    	            	case_you_go = "shop"; 
    	            } 
    	            if (cell_value == 5)
    	            {
    	            	case_you_go = "monster";
    	            } 
    	            if (cell_value != 1) 
    	            {
    	                playerX--;
    	            }
    	        }
    	        break;
    	    case "RIGHT":
    	        if (playerX < mapData[playerY].length - 1) 
    	        {
    	            int cell_value = Integer.parseInt(mapData[playerY][playerX + 1]);
    	            if (cell_value == 3)
    	            {
    	            	case_you_go = "shop"; 
    	            } 
    	            if (cell_value == 5)
    	            {
    	            	case_you_go = "monster";
    	            } 
    	            if (cell_value != 1) 
    	            {
    	                playerX++;
    	            }
    	        }
    	        break;
    	}

        //System.out.println("this.playerX: " + this.playerX);
        //System.out.println("this.playerY: " + this.playerY);
    	return case_you_go;
    }
    

    
    public void gen_monster(int lvl, String monster_type)
    {
    	Random random = new Random();
        int x, y;

        do
        {
            x = random.nextInt(this.mapData[0].length);    
            y = random.nextInt(this.mapData.length);  
            //System.out.println("x: " + x + ", y: " + y);
        } while (Integer.parseInt(this.mapData[y][x]) != 0);

        Monster monster = new Monster(x, y, monster_type, lvl);
        this.monsters.add(monster);
        this.mapData[y][x] = "5"; // ajout monstre
    }
}
