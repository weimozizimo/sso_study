package boot.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
public class Authority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1;

    private int id;
    private String authority;
}
