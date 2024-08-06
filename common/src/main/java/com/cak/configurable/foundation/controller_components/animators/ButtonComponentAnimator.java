package com.cak.configurable.foundation.controller_components.animators;

import com.cak.configurable.foundation.controller_components.ComponentAnimator;

public class ButtonComponentAnimator extends ComponentAnimator {
    
    static final float pressedAnimationSpeed = 1 / 5f;
    
    boolean pressed;
    
    float pressedAnimation = 0;
    
}
