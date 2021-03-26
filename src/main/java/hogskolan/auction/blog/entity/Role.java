package hogskolan.auction.blog.entity;

public class Role {
    public String role;
    public String selected;

    public Role(String role, Boolean selected) {
        this.role = role;
        this.selected = selected ? "selected" : "";
    }

    @Override
    public String toString() {
        return role;
    }
}