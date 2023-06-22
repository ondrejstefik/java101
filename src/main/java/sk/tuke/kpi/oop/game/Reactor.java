package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Reactor extends AbstractActor {
    private int temperature;
    private boolean state;
    private int damage;
    private Animation normalAnimation;
    private Animation brokenAnimation;
    private Animation hotAnimation;

    public Reactor() {
        // init attributes
        this.temperature = 0;
        this.state = false;
        this.damage = 0;
        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        this.hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );

        // set init reactor animation
        setAnimation(this.normalAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int increment) {
        if (increment < 0) {
            return;
        }

        this.temperature = this.temperature + increment;

        if (this.damage == 100) {
            return;
        }

        updateAnimation();

        // update damage
        if (this.temperature >= 2000) {
            if (this.temperature >= 6000) {
                this.damage = 100;
            } else {
                int damage = (this.temperature / 40) - 50;
                if (this.damage < damage) {
                    this.damage = damage;
                }
            }
        }
    }

    public void decreaseTemperature(int decrement) {
        if (decrement < 0) {
            return;
        }

        this.temperature = this.temperature - decrement;

        if (this.damage == 100) {
            return;
        }

        updateAnimation();
    }

    private void updateAnimation() {
        // if temperature is >= 6000, then broken show reactor
        if (this.temperature >= 6000) {
            setAnimation(this.brokenAnimation);

            // if (4000 <= temperature < 6000), then show hot reactor
        } else if (this.temperature >= 4000) {
            setAnimation(this.hotAnimation);

            // otherwise show normal reactor
        } else {
            setAnimation(this.normalAnimation);
        }
    }

    public void repairWith(Hammer hammer) {
        // if no hammer was provided, then quit
        if (hammer == null) {
            return;
        }

        // quit if damage is 0 or reactor is broken
        if (this.damage == 0 || this.damage == 100) {
            return;
        }

        // use hammer
        hammer.use();

        // decrease damage by 50 and temperature to 0
        this.damage = this.damage - 50;
        this.temperature = 0;
        if(this.damage < 0){
            this.damage = 0;
        }
    }
}
