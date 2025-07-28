package models.lombok;

import lombok.Data;

@Data
public class LoginResponseModel {
    Data data;
    Support support;

    public String token,
        error,
        name,
        createdAt,
        job;

    public int
        id,
        total,
        page,
        per_page,
        total_pages;

    @lombok.Data
    public static class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }
}
