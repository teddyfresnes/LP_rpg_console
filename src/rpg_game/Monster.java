package rpg_game;


import java.util.Random;



public class Monster extends Entity
{
    public String type;
    public int level;
    public int hp;
    public int damage;
    public String art;
    

    public Monster(int x, int y, String m_type, int level)
    {
        super(x, y, 0);
        this.type = m_type;
        this.level = level;
        this.art = "test";
        
        initializeStats();
    }

    
    private void initializeStats()
    {
    	Random random = new Random();
        switch (type.toLowerCase())
        {
            case "slime":
                this.hp = random.nextInt(14)+4 + level*random.nextInt(3)+2;
                this.damage = random.nextInt(1) + level*random.nextInt(2);
                this.art = "                ██████████\n        ████████░░░░░░░░░░████████\n      ██░░░░░░░░░░░░░░░░░░░░░░░░░░██\n    ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██\n  ██░░░░░░░░░░░░░░░░░░            ░░██\n  ██░░░░░░░░░░░░░░                  ░░██\n██░░░░░░░░░░                        ░░░░██\n██░░░░░░░░░░                        ░░░░██\n██░░░░░░░░░░        ██        ██      ░░██\n██░░░░░░░░          ██        ██      ░░██\n██░░░░░░░░          ██        ██      ░░██\n██░░░░░░░░                            ░░██\n██░░░░░░░░░░                          ░░██\n██░░░░░░░░░░░░                        ░░██\n██░░░░░░░░░░░░                      ░░██\n██░░░░░░░░░░░░░░░░░░                ░░░░██\n████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████\n    ██████████████████████████████████";
                break;
            case "squelette":
                this.hp = random.nextInt(8)+1 + level*random.nextInt(2)+1;
                this.damage = random.nextInt(2)+1 + level*random.nextInt(3);
                this.art = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠄⠒⠒⠢⠤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢞⠏⠀⠀⠀⠀⠀⠀⠈⢢⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⡎⡎⢀⠄⠀⣀⠀⠀⣀⠀⠄⡇⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⢃⢂⣴⣿⣿⡧⢸⣿⣷⢨⠃⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⠂⠙⢿⣿⣷⡾⡛⠋⢪⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢺⣧⣀⣀⣠⣿⠒⠋⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠉⠻⠿⠋⠉⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⢶⣤⣄⣶⠀⠀⠀⠀⠀⢀⣤⣾⣶⣶⡶⢾⣷⢶⡟⡆⠀⠀⢰⣀⣤⣤⠄\n⠐⢛⣿⣿⡿⣤⣄⡀⠀⡀⢊⢼⢽⡿⢿⢹⠷⠶⠝⡷⠜⠄⣀⢸⣿⣿⡻⠃\n⠀⠻⠋⠀⠁⠒⠠⠭⢇⡐⠁⠘⠩⣬⠛⣶⣇⠐⣿⠁⠘⢮⣕⠜⠁⠀⠁⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠉⢁⡿⠛⢉⡄⡹⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⠦⠀⠀⡀⠀⢺⠳⠤⠄⢤⡀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢎⠜⠒⠉⠈⠁⠉⢉⣩⢏⣾⠇⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣋⣎⠀⠀⠀⠀⡤⣤⢟⠟⠁⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢴⠺⣄⠀⠀⠀⢸⡑⡁⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠡⣙⠢⡄⠀⠀⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⢗⢻⠝⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠮⠕⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
                break;
            case "boss":
            	this.hp = random.nextInt(100)+50 + level*random.nextInt(10)+10;
            	this.damage = random.nextInt(20)+10 + level*random.nextInt(10);
            	this.art = "      -. -. `.  / .-' _.'  _\n     .--`. `. `| / __.-- _' `\n    '.-.  \\  \\ |  /   _.' `_\n    .-. \\  `  || |  .' _.-' `.\n  .' _ \\ '  -    -'  - ` _.-.\n   .' `. %%%%%   | %%%%% _.-.`-\n .' .-. ><(@)> ) ( <(@)>< .-.`.\n   ((\"`(   -   | |   -   )'\"))\n/ \\\\#)\\    (.(_).)    /(\\#//\\\n' / ) ((  /   | |   \\  )) (`.`.\n .'  (.) \\ .md88o88bm. / (.) \\)\n   / /| / \\ `Y88888Y' / \\ | \\ \\\n .' / O  / `.   -   .' \\  O \\ \\\\\n  / /(O)/ /| `.___.' | \\\\(O) \\\n   / / / / |  |   |  |  \\  \\ \\\n   / / // /|  |   |  |  \\  \\ \\  VK\n _.--/--/'( ) ) ( ) ) )`\\-\\-\\-._\n( ( ( ) ( ) ) ( ) ) ( ) ) ) ( ) )";
            	break;
        }
    }
}