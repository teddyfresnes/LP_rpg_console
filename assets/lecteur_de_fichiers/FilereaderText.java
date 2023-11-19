package lecteur_de_fichiers;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public abstract class FilereaderText
{
	// protected pour le laisser priver mais accessible ou sous classes
    protected String chemin;

    
    public FilereaderText(String chemin)
    {
        this.chemin = chemin;
    }

    public void f_open()
    {
        System.out.println("Ouverture du fichier : "+this.chemin);
    }
    

    public void f_close()
    {
        System.out.println("Fermeture du fichier : "+this.chemin);
    }
    

    public String f_read()
    {
    	// meilleur façon de lire un fichier selon la version de java
    	// https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
    	
        Path filePath = Path.of(this.chemin);

        try
        {
            String content = Files.readString(filePath);
            //System.out.println("Lecture du fichier réussie : "+this.chemin);
            return content;
        }
        catch (IOException error)
        {
        	System.out.println("Lecture du fichier échouée : "+this.chemin);
            error.printStackTrace();
            return "N/A";
        }
    }
    
    
    // methode qui differe selon l'affichage que l'on souhaite dans les classes hérités
    public abstract void f_display();
    
    
    
    public void diff(String chemin2)
    {
    	Path filePath1 = Path.of(this.chemin);
    	Path filePath2 = Path.of(chemin2);
    	
        System.out.println("Lecture des deux fichiers avant comparaison : "+this.chemin+" et "+chemin2);
        
        try
        {
            String content1 = Files.readString(filePath1);
            String content2 = Files.readString(filePath2);
            
            // comparaison des deux contenus
            if (content1.equals(content2))
            {
            	System.out.println("Les fichiers "+this.chemin+" et "+chemin2+" ont un contenu identique");
            }
            else
            {
            	System.out.println("Les fichiers "+this.chemin+" et "+chemin2+" ont un contenu différent");
            }
        }
        catch (IOException error)
        {
        	System.out.println("Erreur de lecture des fichiers");
            error.printStackTrace();
        }
    }
}
