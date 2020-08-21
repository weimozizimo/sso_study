package boot.domain;

import lombok.Data;

import java.util.List;

@Data
public class Credentials {
    private int id;
    private boolean enabled;
    private String name;
    private String password;
    private int version;

    private List<Authority> authorities;
}
