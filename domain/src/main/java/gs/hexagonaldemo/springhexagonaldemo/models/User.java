package gs.hexagonaldemo.springhexagonaldemo.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class User {

    private int userId;

    @NonNull
    private String userName;

    private String contactEmail;
}
