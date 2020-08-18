package boot.domain;

import lombok.Data;

@Data
public class Credentials {
    private String id;
    private int enabled;
    private String name;
    private String password;
    private int version;
}
