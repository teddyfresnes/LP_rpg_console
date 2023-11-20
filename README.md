# LP_rpg_console
### Description
Projet de création d'un jeu RPG en java en console Eclipse avec :  
- Sélection de personnage
- Déplacement dans map
- Achat armes dans plusieurs magasins et équipement des objets
- Combat avec des monstres
- Gain d'XP, montée de niveau, difficulté progressive
- Plusieurs niveaux avec un boss et une fin de jeu
### Structure
- Un fichier main pour choisir son personnage, initier les maps et proposer les actions à l'utilisateur
- Un fichier magasin qui permet de créer des magasins avec des objets par défaut, les supprimer, gérer le stock, ajouter un objet
- Un fichier player qui s'occupe de gérer le personnage, son sac, ses caractéristiques, sa montée en niveau aléatoire et ses variations selon les sous classes de personnages
- Un dossier characters avec les sous classes Player qui fournit des avantages propres à chacun (Warrior, Tank, Assassin, Goblin, Mage)
- Un dossier armes qui contient les types d'armes, celles ci pourront être utilisés pour avoir des caractéristiques uniques selon leurs type
- Un fichier map qui permet d'importer une map à l'aide du projet lecteur de fichiers, ajout d'une sous classe pour gérer l'importation CSV et la retranscription dans un tableau 2D. La map est ensuite structuré avec des caractères unicode de même taille. Elle permet l'ajout des magasins et la génération aléatoires des monstres de plus en plus fort.
- Un dossier map qui contient les maps en CSV suivant un format précis 36x15 avec des cellules prédefinis
- Un fichier monstre qui contient la liste des types de monstres qui seront générés aléatoirement avec des avantages et des faiblesses
- Un fichier fight qui gère la rencontre avec les monstres, le combat et les options, les récompenses variables selon la puissance de l'ennemi, la défaite du joueur
### Screenshot
- Choix de la classe de joueur :
![image](https://github.com/teddyfresnes/LP_rpg_console/assets/80900011/4d5285de-1fd8-4228-b98f-8bb7b25628db)
- Interface de base :
![image](https://github.com/teddyfresnes/LP_rpg_console/assets/80900011/1b046dc2-1e8a-42c0-8577-f80dd2546321)  
- Un exemple de magasin :
![image](https://github.com/teddyfresnes/LP_rpg_console/assets/80900011/e08cdd34-de98-4ccb-8883-fc3671a2f58f)
- Exemples de combats :
![image](https://github.com/teddyfresnes/LP_rpg_console/assets/80900011/24ba5705-f3dc-4cf3-a515-19b2319d3389)
![image](https://github.com/teddyfresnes/LP_rpg_console/assets/80900011/aa34a30b-c8e2-447a-8b50-977c91de62d5)
- Changement de niveau (niveau suivant) :
![image](https://github.com/teddyfresnes/LP_rpg_console/assets/80900011/3fd74703-01fd-4340-ae29-11a33510b406)

