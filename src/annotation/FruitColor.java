package annotation;

import java.lang.annotation.*;

/**
 * 水果颜色注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    public enum Color {BULE, RED, GREEN};

    Color fruitColor() default Color.GREEN;

}
