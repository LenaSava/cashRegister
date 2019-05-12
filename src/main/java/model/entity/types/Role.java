package model.entity.types;

public enum Role {
    CAHIER(1),
    CAHIER_CHIEF(2),
    MANAGER(3),
    VIZITOR(4);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
