package com.zhang.my.test.animator.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

/**
 * Animator的propertyName枚举
 *
 * @author ZhangXiaoMing 2020-12-17 17:49 星期四
 */
@StringDef({
        PropertyName.BACKGROUND_COLOR,
        PropertyName.SCALE_X,
        PropertyName.SCALE_Y,
        PropertyName.TRANSLATION_X,
        PropertyName.TRANSLATION_Y,
        PropertyName.ALPHA,
        PropertyName.ROTATION,
        PropertyName.ROTATION_X,
        PropertyName.ROTATION_Y,
})
@Retention(RetentionPolicy.SOURCE)
public @interface PropertyName {
    String BACKGROUND_COLOR = "backgroundColor";
    String SCALE_X = "scaleX";
    String SCALE_Y = "scaleY";
    String TRANSLATION_X = "translationX";
    String TRANSLATION_Y = "translationY";
    String ALPHA = "alpha";
    String ROTATION = "rotation";
    String ROTATION_X = "rotationX";
    String ROTATION_Y = "rotationY";
}
