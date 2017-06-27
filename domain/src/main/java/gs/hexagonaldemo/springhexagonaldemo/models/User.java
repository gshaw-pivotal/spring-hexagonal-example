package gs.hexagonaldemo.springhexagonaldemo.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private int userId;

    private String userName;
}
