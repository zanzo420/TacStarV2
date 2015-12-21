package com.tacstargame.ui.combat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.combat.core.Combat;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.element.UiElementImpl;
import com.tacstargame.ui.element.UiImage;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class AbilityQueueBar extends UiElementImpl {
    
    final Combat combat;
    final UiImage abilityQueued;
    final UiImage abilityNotQueued;

    public AbilityQueueBar(Combat combat, Vector2 position, Measure measure) {
        super(position, measure);
        this.combat = combat;
        abilityQueued = new UiImage(TextureResource.get(TextureResource.COMBAT_ABILITYQUEUE_QUEUED), position.cpy(), measure.divide(combat.getPlayerGroup().size(), 1));
        abilityNotQueued = new UiImage(TextureResource.get(TextureResource.COMBAT_ABILITYQUEUE_NOTQUEUED), position.cpy(), measure.divide(combat.getPlayerGroup().size(), 1));
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        for (int i = 0; i < combat.getPlayerGroup().size(); i++) {
            if (combat.hasAbilityQueued(combat.getPlayerGroup().get(i))) {
                abilityQueued.setPosition(abilityQueued.getPosition().set(position.x + i * abilityQueued.getMeasure().width, abilityQueued.getPosition().y));
                abilityQueued.render(batch, origin);
            } else {
                abilityNotQueued.setPosition(abilityNotQueued.getPosition().set(position.x + i * abilityNotQueued.getMeasure().width, abilityNotQueued.getPosition().y));
                abilityNotQueued.render(batch, origin);
            }
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void update() {
        abilityNotQueued.update();
        abilityQueued.update();
    }

    @Override
    public void scale(float scale) {
        super.scale(scale); 
        abilityNotQueued.scale(scale);
        abilityQueued.scale(scale);
    }
    
    
    
}
