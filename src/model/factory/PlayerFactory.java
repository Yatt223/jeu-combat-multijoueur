package model.factory;

import model.Player;
import model.Position;
import model.strategy.AdvancedStrategy;
import model.strategy.RandomStrategy;
import model.strategy.GameStrategy;
import model.weapons.Weapon;

import java.util.List;

/**
 * Auteur : Yattoura
 */
public class PlayerFactory {

    // Méthode pour créer un joueur de type "humain" avec une stratégie avancée et
    // des armes de base
    public static Player createHumanPlayer(String name, Position startPosition, int initialEnergy) {
        GameStrategy strategy = new AdvancedStrategy();
        Player player = new Player(name, initialEnergy, startPosition, strategy);

        // Ajouter des armes de base pour un joueur humain
        player.addWeapon(new model.weapons.HorizontalShot(3, 25, 5, player)); // Exemple d'arme
        player.addWeapon(new model.weapons.VerticalShot(3, 25, 5, player));

        return player;
    }

    // Méthode pour créer un joueur de type "IA" avec une stratégie aléatoire
    public static Player createAIPlayer(String name, Position startPosition, int initialEnergy) {
        GameStrategy strategy = new RandomStrategy();
        Player player = new Player(name, initialEnergy, startPosition, strategy);

        // Ajouter des armes pour l'IA
        player.addWeapon(new model.weapons.HorizontalShot(2, 20, 4, player)); // Moins de munitions et de dégâts pour
                                                                              // l'IA
        player.addWeapon(new model.weapons.Bomb(initialEnergy, initialEnergy, player));
        // Bomb(1, 50, 2, player)

        return player;
    }

    // Méthode pour créer un joueur personnalisé avec une stratégie et une liste
    // d'armes spécifiques
    public static Player createCustomPlayer(String name, Position startPosition, int initialEnergy,
            GameStrategy strategy, List<Weapon> weapons) {
        Player player = new Player(name, initialEnergy, startPosition, strategy);

        // Ajouter les armes fournies à la création du joueur
        for (Weapon weapon : weapons) {
            player.addWeapon(weapon);
        }

        return player;
    }
}