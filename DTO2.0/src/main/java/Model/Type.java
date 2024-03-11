package Model;

import java.util.Arrays;

public enum Type {
    F(0),
    M(1),
    U(2);

    private final int value;

    Type(int value) {
        this.value = value;
    }

    public static Type getTypeByValue(int value){
        return Arrays.stream(Type.values()).filter(e->e.value==value).findFirst().orElseThrow();
    }
}