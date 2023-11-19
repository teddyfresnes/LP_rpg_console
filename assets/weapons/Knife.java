package weapons;

import rpg_game.Weapon;

public class Knife extends Weapon
{
    public Knife(String name, int damage, int price)
    {
        super(name, damage, price);
        super.define_ascii_art(" ___________________________________ ______________________\r\n"
        		+ " \\                                  | (_)     (_)    (_)   \\\r\n"
        		+ "  `.                                |  __________________   }\r\n"
        		+ "    `-..........................____|_(                  )_/\r\n"
        		+ "");
    }
}
