package model.strategy;

import model.Game;
import model.Player;

/**
 * Auteur : Yattoura
 */

public interface GameStrategy {
    // Méthode pour jouer un tour en appliquant la stratégie spécifique
    void playTurn(Player player, Game game);
}