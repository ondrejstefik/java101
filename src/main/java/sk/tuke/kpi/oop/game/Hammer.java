package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private int usages;

    public Hammer(){
        this.usages = 1;
        Animation animation = new Animation("sprites/hammer.png");
        setAnimation(animation);
    }

    public void use(){
        this.usages--;

        if(this.usages == 0){
            getScene().removeActor(this);
        }
    }

    public int getUsages(){
        return this.usages;
    }


}
