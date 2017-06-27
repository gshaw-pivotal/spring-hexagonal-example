package gs.hexagonaldemo.springhexagonaldemo.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class User {

    private int id;

    @NonNull
    private String name;

    private String contactEmail;
}
