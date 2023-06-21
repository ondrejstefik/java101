package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    private int temperature;
    private boolean state;
    private int damage;
    private Animation normalAnimation;

    public Reactor() {
        this.temperature = 0;
        this.state = false;
        this.damage = 0;
        this.normalAnimation = new Animation("sprites/reactor_on.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG);
        this.setAnimation(normalAnimation);

    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int increment) {
        this.temperature = this.temperature + increment;

        // if reactor is broken already, do nothing
        if(this.damage == 100){
            return;
        }

        // update animation
        // set broken reactor, when temp is over 6000C
        if(this.temperature >= 6000){
            setAnimation(
                new Animation("sprites/reactor_broken.png",
                    80, 80, 0.1f,
                    Animation.PlayMode.LOOP_PINGPONG
                )
            );
            // set hot reactor, when temp is over 4000C
        }else if(this.temperature >= 4000){
            setAnimation(
                new Animation("sprites/reactor_hot.png",
                    80, 80, 0.05f,
                    Animation.PlayMode.LOOP_PINGPONG
                )
            );
            // otherwise use normal animation
        }else{
            setAnimation(normalAnimation);
        }

        // when temperature is above 2000 and below 6000, then damage increases
        if (this.temperature >= 2000) {
            // update damage
            if (this.temperature >= 6000) {
                this.damage = 100;
            } else {

                int damage = this.temperature / 40 - 50;

                // update when current damage is bigger than previous
                if (this.damage < damage) {
                    this.damage = damage;
                }
            }

        }
    }

    public boolean getState() {
        return this.state;
    }

}
