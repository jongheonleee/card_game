package main.framework;

public abstract class PlayerFactory {

    public final Player create(String name) {
        Player player = createPlayer(name);
        return player;
    }

    protected abstract Player createPlayer(String name);

}
